package gui;

import graduate.Graduate;
import graduate.GraduateCollection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import gui.AddGraduateGUI;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextPane;
import javax.swing.table.TableColumn;

/**
 * This class holds the GUI of the project and acts as a Main Menu for the system. All other
 * parts of the program should be called from this GUI in order to instantiate their page/panel.
 * @author Jabo Johnigan
 * @version Dec 4, 2016
 *
 */
public class GUI extends JFrame implements ActionListener{

	/**Size of Table */
	private static final Dimension DEFAULT_T_MIN = new Dimension(900, 500);

	/**Buttn Pnl Dimension. */
	private static final Dimension DEFAULT_F_MIN = new Dimension(1000, 700);

    /**
     * Name of Columns for table.
     */
	private String[] GradColumnNames = {"id", "graduateName", "graduateId", "graduationYear",
			"gpa", "email", "transferStatus", "responsive", "employers", "internships"};

    /**
     * Panels for main menu.
     */
	private JPanel btnPanel, tblPanel;

    /**
     * Add/Grad Panel To Replace the Table
     */
    private AddGraduateGUI addGradPanel;

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
     * Btns to control the different functions of the system.
     */
	private JButton btnAddGrad, btnRunReport, btnListGrads , btnUpdateGradInfo, btnBack;

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
	    //SetUp MAIN GUI MENU

		setTitle("Grad Trackr");
		setMinimumSize(DEFAULT_F_MIN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		mList = getData(null);
		MainMenu();
		addTablePanel();

	}


	/*
	 * Returns the data (2d) to use in this list as well as in other panels.
	 *
	 * @param searchKey
	 *
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
				cmbInts = new JComboBox();

				cmbEmps = new JComboBox();
				cmbInts.setEditable(false);
				cmbEmps.setEditable(false);
				for (int x = 0; x< listEmps.size(); x++) {
					System.out.println(1);
					cmbEmps.addItem(listEmps.get(x).toString());
					System.out.println(listEmps.get(x).toString());
				}
				for (int y = 0; y < listInts.size(); y++){
					cmbInts.addItem(listInts.get(y).toString());
                    System.out.println(listInts.get(y));
				}
                cmbEmps.setSelectedIndex(-1);
                cmbInts.setSelectedIndex(-1);

				mData[i][0] = mList.get(i).getID();
				mData[i][1] = mList.get(i).getName();
				mData[i][2] = mList.get(i).getGraduateID();
				mData[i][3] = mList.get(i).getGraduationYear();
				mData[i][4] = mList.get(i).getGPA();
				mData[i][5] = mList.get(i).getEmail();
				mData[i][6] = mList.get(i).isTransferStatus();
				mData[i][7] = mList.get(i).isResponseFlag();
                mData[i][8] = listEmps;
				mData[i][9] = listInts;

			}
		}
		return mList;
	}


	/**
	 * Initialize the contents of the frame with the main menu window.
	 */
	private void MainMenu() {

		//create button panel
        tblPanel = new JPanel();
		btnPanel = new JPanel ();
		btnPanel.setVisible(true);
		btnAddGrad = new JButton("Add/Remove Grad");
		btnUpdateGradInfo = new JButton("Update Grad Info");
		btnRunReport = new JButton("Run Report");
		btnListGrads = new JButton("List Grads");
		btnListGrads.addActionListener(this);
		btnPanel.add(btnListGrads);
		btnPanel.add(btnAddGrad);
		btnPanel.add( btnRunReport);
		btnPanel.add(btnUpdateGradInfo);
		btnAddGrad.addActionListener(this);
		add(btnPanel, BorderLayout.NORTH);







	}

    /**
     * Creates the TablePanel to host the table.
     */
	public void addTablePanel(){
        // Add Table Panel

        table = new JTable( mData, GradColumnNames);
        TableColumn empsColumn = table.getColumnModel().getColumn(8);
        TableColumn intsColumn = table.getColumnModel().getColumn(9);
        empsColumn.setCellEditor(new DefaultCellEditor(cmbEmps));
        intsColumn.setCellEditor(new DefaultCellEditor(cmbInts));
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(DEFAULT_T_MIN);
        scrollPane = new JScrollPane(table);
        tblPanel.add(scrollPane);
        add(tblPanel, BorderLayout.CENTER);


    }


    public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == btnAddGrad){
            addGradPanel = new AddGraduateGUI();
            remove(tblPanel);
            add(addGradPanel, BorderLayout.CENTER);
            repaint();
            revalidate();
        } else if (e.getSource() == btnListGrads){
            remove(addGradPanel);
            //btnListGrads.setEnabled(false);
            add(tblPanel, BorderLayout.CENTER);
            repaint();
            revalidate();
        } else if (e.getSource() == btnRunReport){

        } else if (e.getSource() == btnUpdateGradInfo){

        } else if (e.getSource() == btnBack){

        }
    }

}
