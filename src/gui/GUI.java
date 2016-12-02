package gui;

import graduate.Graduate;
import graduate.GraduateCollection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextPane;

public class GUI {

	private JFrame frmGradTrackr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmGradTrackr.setVisible(true);
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGradTrackr = new JFrame();
		frmGradTrackr.setTitle("Grad Trackr");
		//TODO: make bigger plz
		frmGradTrackr.setBounds(100, 100, 550, 354);
		frmGradTrackr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGradTrackr.getContentPane().setLayout(null);

		//TODO: link the buttons the controller
		JButton btnRequestInfo = new JButton("Request Info");
		btnRequestInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRequestInfo.setBounds(48, 109, 117, 29);
		frmGradTrackr.getContentPane().add(btnRequestInfo);
		
		JButton btnRunReport = new JButton("Run Report");
		btnRunReport.setBounds(48, 68, 117, 29);
		frmGradTrackr.getContentPane().add(btnRunReport);
		
		JButton btnUpdateGradInfo = new JButton("Update Grad Info");
		btnUpdateGradInfo.setBounds(48, 150, 150, 29);
		frmGradTrackr.getContentPane().add(btnUpdateGradInfo);
		
		JButton btnAddremoveGrad = new JButton("Add/Remove Grad");
		btnAddremoveGrad.setBounds(48, 191, 150, 29);
		frmGradTrackr.getContentPane().add(btnAddremoveGrad);
		
		JTextPane txtpnIdkWhatShould = new JTextPane();
		txtpnIdkWhatShould.setText("IDK WHAT SHOULD GO HERE");
		txtpnIdkWhatShould.setBounds(226, 68, 265, 202);
		frmGradTrackr.getContentPane().add(txtpnIdkWhatShould);
	}
}
