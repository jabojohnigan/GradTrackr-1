package gui;

import graduate.Graduate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by noxor on 12/2/16.
 * @author noxor
 * @author Vlad
 */
public class DataReportGUI extends JPanel implements ActionListener {

    /**Size of Panel */
    private static final Dimension DEFAULT_T_MIN = new Dimension(900, 500);

    private JButton btnDataReport;
    private JPanel pnlButton, pnlContent;
    private List<Graduate> mList;
    private String[] mGraduateColumnNames = {"name", "ID", "graduation year", "gpa", "email",
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

    public DataReportGUI() {
        setLayout(new BorderLayout());

        //mList = getData(null);
        createComponents();
        setVisible(true);
        //setSize(DEFAULT_T_MIN);
    }

//    /**
//	 * Returns the data (2d) to use in the Run Report list.
//	 *
//	 * @param searchKey
//	 *
//	 * @return mList the
//	 */

//    private List<Graduate> getData(String searchKey) {
//
//        if (searchKey != null)
//            mList = .search(searchKey);
//        else
//            mList = ItemCollection.getItems();
//
//        if (mList != null) {
//            mData = new Object[mList.size()][mGraduateColumnNames.length];
//            for (int i = 0; i < mList.size(); i++) {
//                mData[i][0] = mList.get(i).getName();
//                mData[i][1] = mList.get(i).getDescription();
//                mData[i][2] = mList.get(i).getPrice();
//                mData[i][3] = mList.get(i).getCondition();
//                mData[i][4] = mList.get(i).getItemCategory().getCategory();
//
//            }
//        }
//
//        return mList;
//    }

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
        Object[] reportCriteria = {"Name", "ID", "Graduation Year", "GPA",
                "Transferred", "Did Not Transfer","Is Responsive", "Is not Responsive",
                "Employer", "Internship"};

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

//        } else if (e.getSource() == btnTitleRunReport) {
//            String title = txfTitle.getText();
//            String searchCriteria = (String) cmbCriteria.getSelectedItem();
//            if (title.length() > 0) {
//                mList = getData(title);
//                pnlContent.removeAll();
//                table = new JTable(mData, mGraduateColumnNames);
//                scrollPane = new JScrollPane(table);
//                pnlContent.add(scrollPane);
//                pnlContent.revalidate();
//                this.repaint();
//                txfTitle.setText("");
//            }
//        } else if (e.getSource() == btnAddItem) {
//            performAddItem();
        }
    }
}
