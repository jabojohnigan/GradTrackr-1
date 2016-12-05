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

public class GUI extends JFrame {

	/**A DeFAULT PANEL SIZE FOR JPANELS. */
	private static final Dimension DEFAULT_F_MIN = new Dimension(900, 500);

	/**Buttn Pnl Dimension. */
	private static final Dimension DEFAULT_P_MIN = new Dimension(300, 600);


	private String[] GradColumnNames = {"id", "graduateName", "graduateId", "graduationYear",
			"gpa", "email", "transferStatus", "responsive", "employers", "internships"};

	private JPanel btnPanel, tblPanel;

	private AddGraduateGUI addGradPanel;


	private JTable table;

	private JScrollPane scrollPane;

	private List<Graduate> mList;

	private Object[][] mData;

	private JButton btnAddGrad, btnRunReport, btnRequestInfo , btnUpdateGradInfo;

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
	 * Create the application.
	 */
	public GUI() {
	    //SetUp MAIN GUI MENU

		setTitle("Grad Trackr");
		setMinimumSize(DEFAULT_F_MIN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		mList = getData(null);
		initialize();

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
		mData = new Object[mList.size()][GradColumnNames.length];

		if (mList != null) {
			mData = new Object[mList.size()][GradColumnNames.length];
			for (int i = 0; i < mList.size(); i++) {
				mData[i][0] = mList.get(i).getID();
				mData[i][1] = mList.get(i).getName();
				mData[i][2] = mList.get(i).getGraduateID();
				mData[i][3] = mList.get(i).getGraduationYear();
				mData[i][4] = mList.get(i).getGPA();
				mData[i][5] = mList.get(i).getEmail();
				mData[i][6] = mList.get(i).isTransferStatus();
				mData[i][7] = mList.get(i).isResponseFlag();
				mData[i][8] = mList.get(i).getEmployers();
				mData[i][9] = mList.get(i).getInternships();

			}
		}
		return mList;
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//create button panel
		btnPanel = new JPanel ();
		btnPanel.setVisible(true);
		btnAddGrad = new JButton("Add/Remove Grad");
		btnUpdateGradInfo = new JButton("Update Grad Info");
		btnRunReport = new JButton("Run Report");
		btnRequestInfo = new JButton("Request Info");
		btnRequestInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		//btnRequestInfo.setBounds(109, 48, 117, 29);
		btnPanel.add(btnRequestInfo);
		//btnRunReport.setBounds(48, 68, 117, 29);
		btnPanel.add( btnRunReport);
		//btnUpdateGradInfo.setBounds(48, 150, 150, 29);
		btnPanel.add(btnUpdateGradInfo);
		//btnAddGrad.setBounds(48, 191, 150, 29);
		btnPanel.add( btnAddGrad);
		btnAddGrad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			addGradPanel = new AddGraduateGUI();
			remove(tblPanel);
			add(addGradPanel, BorderLayout.CENTER);
			repaint();
			revalidate();


			}
		});
		add(btnPanel, BorderLayout.NORTH);

		//TODO: link the buttons the controller


		// Add Table Panel
		tblPanel = new JPanel();
		table = new JTable( mData, GradColumnNames);
		scrollPane = new JScrollPane(table);
		tblPanel.add(scrollPane);
		add(tblPanel, BorderLayout.CENTER);

	}

	/**
	 * An InnerClass to hold the implementation of the btnBack
	 * @author Jabo Johnigan
	 */
	class btnBackListener implements ActionListener {

		public void actionPerformed(ActionEvent someAction){

		}




	}

}
