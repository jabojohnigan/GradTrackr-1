package gui;

import graduate.Graduate;
import graduate.GraduateCollection;

import java.awt.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.*;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.event.TableModelListener;
import java.awt.Dimension;

/**
 * This class holds the GUI of the project and acts as a Main Menu for the system. All other
 * parts of the program should be called from this GUI in order to instantiate their page/panel.
 * @author Jabo Johnigan
 * @version Dec 4, 2016
 *
 */
public class GUI extends JFrame implements ActionListener, TableModelListener {

	/**
	 * Serialization ID, mostly unused.
	 */
	private static final long serialVersionUID = 499203492615966125L;

	/**
	 * A DeFAULT PANEL SIZE FOR JPANELS.
	 */
	private static final Dimension DEFAULT_F_MIN = new Dimension(1000, 600);
	/**
	 * Size of Table
	 */
	private static final Dimension DEFAULT_T_MIN = new Dimension(900, 500);


	/**
	 * Name of Columns for table.
	 */
	private String[] GradColumnNames = {"graduateName", "graduateId", "graduationYear",
			"gpa", "email", "transferStatus", "responsive", "employers", "internships"};

	/**
	 * Panels for main menu.
	 */
	private JPanel btnPanel, tblPanel, contentPanel;

	/**
	 * Add/Grad Panel To Replace the Table
	 */
	private AddGraduateGUI addGradPanel;

	/**
	 * Data Report Panel to replace Table and Add Grad Panel
	 */
	private DataReportGUI dataReportPanel;

	/**
	 * The JComboBoxs for the Employer list and Internship List
	 */
	private JComboBox cmbEmps, cmbInts;

	/**
	 * Table for displaying Grad Students
	 */
	private JTable table;

	/**
	 * A scroll pane obj so we can scroll on the table of grads.
	 */
	private JScrollPane scrollPane;


	/**
	 * Btns to control the different functions of the system.
	 */
	private JButton btnAddGrad, btnRunReport, btnListGrads, btnBack;

	/**
	 * List used to access the graduated information
	 */
	private List<Graduate> mList;

	/**
	 * List of Employer and Internship as Strings
	 */
	private List<String> listEmps, listInts;

	/**
	 * Data For 2D table.
	 */
	private Object[][] mData;


	/**
	 * The hex RGB value for UW Purple.
	 */
	private static final String UW_PURPLE = "#4b2e83";

	/**
	 * The hex RGB value for UW Gold.
	 */
	private static final String UW_GOLD = "#b7a57a";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI guiRUNOBJ = new GUI();
					guiRUNOBJ.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * The GUI constructor that instantiates a Frame for the application. The class is a
	 * JFrame.
	 */
	public GUI() {
		setLayout(new BorderLayout());
		contentPanel = new JPanel();

		contentPanel.setVisible(true);
		contentPanel.setLayout(new BorderLayout());

		cmbInts = new JComboBox();
		cmbEmps = new JComboBox();

		add(contentPanel);
		MainMenu();
		//pack();

	}


	/**
	 * Initialize the contents of the frame with the main menu window.
	 */
	private void MainMenu() {
		//SetUp MAIN GUI MENU
		setTitle("Grad Trackr");
		setMinimumSize(DEFAULT_F_MIN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);
		addBtnPanel();
		addTablePanel();


	}


	/**
	 * Returns the data (2d) to use in this table.
	 *
	 * @param searchKey
	 * @return
	 */
	private List<Graduate> getData(String searchKey) {
		if (searchKey != null) {
			mList = GraduateCollection.search(searchKey);
		} else {
			mList = GraduateCollection.getGraduates();
		}

		if (mList != null) {
			mData = new Object[mList.size()][GradColumnNames.length];
			for (int i = 0; i < mList.size(); i++) {
				listEmps = mList.get(i).getEmployers();
				listInts = mList.get(i).getInternships();

				for (int x = 0; x < listEmps.size(); x++) {
					cmbEmps.addItem(listEmps.get(x).toString());
				}
				for (int y = 0; y < listInts.size(); y++) {
					cmbInts.addItem(listInts.get(y).toString());
				}
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
	 * Creates the ButtonPanel
	 */
	public void addBtnPanel() {
		//create button panel
		btnPanel = new JPanel();

		btnPanel.setOpaque(true);
		btnPanel.setBackground(Color.decode(UW_PURPLE));
		btnPanel.setVisible(true);
		btnAddGrad = new JButton("Add/Remove Grad");
		btnRunReport = new JButton("Run Report on Grad");
		btnListGrads = new JButton("List Grads");
		btnRunReport.addActionListener(this);
		btnListGrads.addActionListener(this);
		btnAddGrad.addActionListener(this);
		btnPanel.add(btnListGrads);
		btnPanel.add(btnAddGrad);
		btnPanel.add(btnRunReport);
		add(btnPanel, BorderLayout.NORTH);


	}

	/**
	 * Creates the TablePanel to host the table.
	 */
	public void addTablePanel() {

		mList = getData(null);
		tblPanel = new JPanel();
		tblPanel.setOpaque(true);
		tblPanel.setBackground(Color.decode(UW_GOLD));
		table = new JTable(mData, GradColumnNames);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setUpJobColumn(table, table.getColumnModel().getColumn(7),
				table.getColumnModel().getColumn(8));
		table.getModel().addTableModelListener(this);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(28);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(2);
		table.getColumnModel().getColumn(5).setPreferredWidth(35);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);



		table.setPreferredScrollableViewportSize(DEFAULT_T_MIN);

		//Make Panel with ScrollPane
		scrollPane = new JScrollPane(table);
		tblPanel.add(scrollPane);
		contentPanel.add(tblPanel, BorderLayout.CENTER);

	}

	/**
	 * A method to help sets up the Column for the both the Employer and Internship Columns.
	 */
	public void setUpJobColumn(JTable table,
							   TableColumn Emps, TableColumn Ints) {
		//Set up editor for the Job cells.
		cmbEmps.setEditable(true);
		cmbInts.setEditable(true);
		Emps.setCellEditor(new DefaultCellEditor(cmbEmps));
		Ints.setCellEditor(new DefaultCellEditor(cmbInts));

	}


	/**
	 * ActionListeners for all the buttons.
	 *
	 * @param e Action event from the user
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddGrad) {
			addGradPanel = new AddGraduateGUI();
			contentPanel.removeAll();

			contentPanel.add(addGradPanel, BorderLayout.CENTER);
			repaint();
			revalidate();
		} else if (e.getSource() == btnListGrads) {
			contentPanel.removeAll();
			tblPanel.removeAll();
			addTablePanel();

			repaint();
			revalidate();
		} else if (e.getSource() == btnRunReport) {
			dataReportPanel = new DataReportGUI();
			contentPanel.removeAll();

			contentPanel.add(dataReportPanel, BorderLayout.CENTER);
			repaint();
			revalidate();
		} else if (e.getSource() == btnBack) {

		}
	}

	/**
	 * Listen to the cell changes on the table.
	 *
	 * @param e event when table is changed by the user
	 */
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel model = (TableModel) e.getSource();
		String columnName = model.getColumnName(column);
		Object data = model.getValueAt(row, column);
		if (data != null && ((String) data).length() != 0) {
			Graduate grad = mList.get(row);
			if (!GraduateCollection.update(grad, columnName, data)) {
				JOptionPane.showMessageDialog(null, "Update failed");

			} else {
				JOptionPane.showMessageDialog(null, "Graduate updated");
			}
		}
	}
}



