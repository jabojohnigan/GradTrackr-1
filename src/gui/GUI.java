package gui;

import graduate.Graduate;
import graduate.GraduateCollection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JButton;
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
	private static final Dimension DEFAULT_F_MIN = new Dimension(175, 352);

	private JFrame frameOfGradTrackr = this;

	private JPanel btnPanel;

	private JButton btnAddGrad, btnRunReport, btnListGrad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI guiRUNOBJ = new GUI();
					guiRUNOBJ.frameOfGradTrackr.setVisible(true);
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
		frameOfGradTrackr = new JFrame();
		frameOfGradTrackr.setTitle("Grad Trackr");
		frameOfGradTrackr.setBounds(100, 100, 800, 700);
		frameOfGradTrackr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameOfGradTrackr.getContentPane().setLayout(null);

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//TODO: make bigger plz

		//TODO: link the buttons the controller
		JButton btnRequestInfo = new JButton("Request Info");
		btnRequestInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRequestInfo.setBounds(48, 109, 117, 29);
		frameOfGradTrackr.getContentPane().add(btnRequestInfo);
		
		btnRunReport = new JButton("Run Report");
		btnRunReport.setBounds(48, 68, 117, 29);
		frameOfGradTrackr.getContentPane().add(btnRunReport);
		
		JButton btnUpdateGradInfo = new JButton("Update Grad Info");
		btnUpdateGradInfo.setBounds(48, 150, 150, 29);
		frameOfGradTrackr.getContentPane().add(btnUpdateGradInfo);
		
		btnAddGrad = new JButton("Add/Remove Grad");
		btnAddGrad.setBounds(48, 191, 150, 29);
		frameOfGradTrackr.getContentPane().add(btnAddGrad);
		
		JTextPane txtpnIdkWhatShould = new JTextPane();
		txtpnIdkWhatShould.setText("IDK WHAT SHOULD GO HERE");
		txtpnIdkWhatShould.setBounds(226, 68, 265, 202);
		frameOfGradTrackr.getContentPane().add(txtpnIdkWhatShould);
	}


}
