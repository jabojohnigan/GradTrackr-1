package gui;

import data.DataConnection;
import graduate.Graduate;
import report.DataReport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The GUI responsible for handling Data report. The core function of the program.
 * Will return the requested information about a graduate.
 *
 * @author concox
 * @author Vlad
 */
public class DataReportGUI extends JPanel implements ActionListener {

    /**Size of Panel */
    private static final Dimension DEFAULT_T_MIN = new Dimension(900, 500);
    private Connection mConnection;

    private JButton btnDataReport;
    private JPanel pnlButton, pnlContent;
    private List<Graduate> mList;
    private String[] mGraduateColumnNames = {"name", "graduate ID", "graduation year", "gpa", "email",
            "transfer status", "responsive", "employers", "internships"};

    private Object[][] mData;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel pnlDataReport;
    private JLabel lblTitle;
    private JTextField txfTitle;
    private JButton btnTitleRunReport;

    private JLabel[] txfLabel = new JLabel[2];
    private JTextField[] txfField = new JTextField[2];
    private JComboBox cmbCriteria;

    /**
     * Constructor for the data report GUI.
     */
    public DataReportGUI() {
        setLayout(new BorderLayout());

        //mList = getData(null);
        createComponents();
        setVisible(true);
        //setSize(DEFAULT_T_MIN);
    }

    /**
	 * Returns the data (2d) to use in the Run Report list.
	 *
	 * @param searchKey
	 *
	 * @return mList the
	 */

    private List<Graduate> getData(String searchKey) {
        String searchCriteria = (String) cmbCriteria.getSelectedItem();
        String stringQuery;
        DataReport reportQuery = new DataReport();
//        "Name", " Graduate ID", "Graduation Year", "GPA",
//                "Transferred", "Did Not Transfer",
//                "Employer", "Internship"
        if (searchKey != null) {
            if (searchCriteria.equals("Name")) {
                reportQuery.searchByName(searchKey);
            } else if (searchCriteria.equals("Graduation Year")) {
                reportQuery.searchByGraduationYear(searchKey);
            } else if (searchCriteria.equals("Graduate ID")) {
                reportQuery.searchByGraduateID(searchKey);
            } else if (searchCriteria.equals("GPA")) {
                reportQuery.searchByGPA(searchKey);
            } else if (searchCriteria.equals("Employer")) {
                reportQuery.searchByEmployer(searchKey);
            } else if (searchCriteria.equals("Internship")) {
                reportQuery.searchByInternship(searchKey);
            }
        }

        if (searchCriteria.equals("Transferred")) {
            reportQuery.searchByTransferStatus(1);
        } else if (searchCriteria.equals("Did Not Transfer")) {
            reportQuery.searchByTransferStatus(0);
        }


        stringQuery = reportQuery.getReportQuery();

        try {
            mList = sqlRetrieveGraduates(stringQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (mList != null) {
            mData = new Object[mList.size()][mGraduateColumnNames.length];
            for (int i = 0; i < mList.size(); i++) {
                mData[i][0] = mList.get(i).getName();
                mData[i][1] = mList.get(i).getGraduateID();
                mData[i][2] = mList.get(i).getGraduationYear();
                mData[i][3] = mList.get(i).getGPA();
                mData[i][4] = mList.get(i).getEmail();
                mData[i][5] = mList.get(i).isTransferStatus();
                mData[i][6] = mList.get(i).isResponseFlag();
                mData[i][7] = mList.get(i).getEmployersAsString();
                mData[i][8] = mList.get(i).getInternshipsAsString();
            }
        }

        return mList;
    }

    /**
     * Retrieves the Graduates from the SQL Graduate Table based on the search criteria
     *
     * @param sqlQuery the SQL Query String
     * @return graduates a list of graduates
     * @throws SQLException
     */
    private List<Graduate> sqlRetrieveGraduates(String sqlQuery) throws SQLException {
        List<Graduate> graduates = new ArrayList<>();

        if (mConnection == null) {
            try {
                mConnection = DataConnection.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Statement stmt = null;

        try {
            stmt = mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("graduateName");
                int graduateID = rs.getInt("graduateId");
                String graduationYear = rs.getString("graduationYear");


                //  String program = rs.getString("program");
                Double gpa = rs.getDouble("gpa");
                String email = rs.getString("email");
                boolean transferStatus = rs.getBoolean("transferStatus");
                boolean responsive = rs.getBoolean("responsive");
                String employers = rs.getString("employers");
                String internships = rs.getString("internships");
                Graduate graduate = new Graduate(name, graduateID, graduationYear, gpa, email,
                        transferStatus, responsive, Graduate.stringToList(employers),
                        Graduate.stringToList(internships));
                graduate.setID(id);
                graduates.add(graduate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return graduates;
    }

    /**
     * Handles the creation of components on the GUI.
     */
    private void createComponents() {

        // A button panel at the top for Data Report
        pnlButton = new JPanel();

        btnDataReport = new JButton("Data Report");
        btnDataReport.addActionListener(this);
        pnlButton.add(btnDataReport);
        add(pnlButton, BorderLayout.NORTH);

        pnlContent = new JPanel();

        // Run Report Panel
        pnlDataReport = new JPanel();
        pnlDataReport.setLayout(new GridLayout(3, 0));

        //display Report Criteria
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new GridLayout(1, 1));
        Object[] reportCriteria = {"Name", "Graduate ID", "Graduation Year", "GPA",
                "Transferred", "Did Not Transfer","Employer", "Internship"};

        cmbCriteria = new JComboBox(reportCriteria);
        cmbCriteria.setSelectedIndex(0);
        comboPanel.add(new JLabel("Select Report Criteria: "));
        comboPanel.add(cmbCriteria);
        pnlDataReport.add(comboPanel);

        JPanel criteriaValuePanel = new JPanel();
        lblTitle = new JLabel("Enter Criteria Value: ");
        txfTitle = new JTextField(25);
        criteriaValuePanel.setLayout(new GridLayout(1, 0));
        criteriaValuePanel.add(lblTitle);
        criteriaValuePanel.add(txfTitle);
        pnlDataReport.add(criteriaValuePanel);

        btnTitleRunReport = new JButton("Run Report");
        btnTitleRunReport.addActionListener(this);
        pnlDataReport.add(btnTitleRunReport);
        pnlContent.add(pnlDataReport);
        add(pnlContent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDataReport) {
            pnlContent.removeAll();
            pnlContent.add(pnlDataReport);
            pnlContent.revalidate();
            this.repaint();

        } else if (e.getSource() == btnTitleRunReport) {
            String title = txfTitle.getText();
            String searchCriteria = (String) cmbCriteria.getSelectedItem();
            if (searchCriteria.equals("Transferred") ||
                    searchCriteria.equals("Did Not Transfer") ||
                    title.length() > 0) {
                mList = getData(title);
                pnlContent.removeAll();
                table = new JTable(mData, mGraduateColumnNames);
                table.setPreferredScrollableViewportSize( new Dimension (900, 500));
                table.setFillsViewportHeight(true);
                scrollPane = new JScrollPane(table);

                pnlContent.add(scrollPane);
                pnlContent.revalidate();
                this.repaint();
                txfTitle.setText("");
            }
        }
    }
}
