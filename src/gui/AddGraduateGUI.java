package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import graduate.Graduate;
import graduate.GraduateCollection;
/**
 * GUI Panel that handles adding graduates to the Database.
 *
 * @author Jabo Johnigan
 * @author humzam
 * @version 12/2/16
 */
public class AddGraduateGUI extends JPanel implements ActionListener {
    /**
	 * Serialization ID, mostly unused.
	 */
	private static final long serialVersionUID = -1262967559520618107L;

	/**
     * labels for the addGrad panel.
     */
    private JLabel[] lblAddGrad = new JLabel[9];

    /**
     * PANEL SIZE
     */
    private static final Dimension DEFAULT_P_MIN = new Dimension(300, 300);

	private JTextField[] txfField = new JTextField[9];
	
	private JTextField idField, nameField;

    private JPanel pnlAddGrad, pnlRemoveGrad, pnlAddJobs, pnlButtons, removeBtnPanel;
 
    private JButton addViewBtn, removeViewBtn;

    private JButton addEmps, addInts, addGrad, removeGrad;

	/**
	 * Creates the Add Graduate GUI
	 */
	public AddGraduateGUI(){
        setLayout(new BorderLayout());
        setVisible(true);
        setSize(DEFAULT_P_MIN);
        setUpButtons();
        setUpPanel();
    }

	/**
	 * Creates and places the button for the GUI.
	 */
	public void setUpButtons() {
    	pnlButtons = new JPanel();
    	
    	addViewBtn = new JButton("Add a Grad");
    	addViewBtn.addActionListener(this);
    	pnlButtons.add(addViewBtn);
    	
    	removeViewBtn = new JButton("Remove a Grad");
    	removeViewBtn.addActionListener(this);
    	pnlButtons.add(removeViewBtn);
    	
    	add(pnlButtons, BorderLayout.NORTH);
    }

	/**
	 * Set up method to handle the creation of the add graduate panel.
	 */
	public void setUpPanel(){
    	pnlAddGrad = new JPanel();
    	pnlAddGrad.setLayout(new GridLayout(10, 0));
    	add(pnlAddGrad, BorderLayout.CENTER);
    	
    	String[] lblNames = {"Enter Name:", "Enter Student ID:", "Enter Graduate Year:", "Enter GPA:",
                "Enter Email:", "Transfer Student:", "Responsive:", "Employers:", "Internships:"};
		for (int i = 0; i < lblNames.length; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());
			lblAddGrad[i] = new JLabel(lblNames[i]);
			txfField[i] = new JTextField(15);
			panel.add(lblAddGrad[i]);
			panel.add(txfField[i]);
			pnlAddGrad.add(panel, BorderLayout.CENTER);
		}

		
		JPanel panel = new JPanel();
		addGrad = new JButton("Add Grad");
		addGrad.addActionListener(this);
		panel.add(addGrad);

		pnlAddGrad.add(panel);
		
		
		pnlRemoveGrad = new JPanel(new GridLayout(2, 0));
		
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new GridLayout(2, 0));
		JLabel idLabel = new JLabel("Search ID:");
		idField = new JTextField(25);
		idPanel.add(idLabel);
		idPanel.add(idField);
		pnlRemoveGrad.add(idPanel);
		
		removeBtnPanel = new JPanel();
		removeGrad = new JButton("Remove Grad");
		removeGrad.addActionListener(this);
		removeBtnPanel.add(removeGrad);
		
		
    }

	/**
	 * Button handler for the add graduate GUI.
	 * @param e event that was preformed by the user
	 */
	public void actionPerformed(ActionEvent e){
    	if (e.getSource() == addGrad) {
    		if(!txfField[0].getText().equals("") && !txfField[1].getText().equals("") 
    			&& !txfField[4].getText().equals("")) {
				performAddGrad();
			}
    	} else if (e.getSource() == removeGrad) {
    		if (!idField.getText().equals("") || !nameField.getText().equals("")) {
    			performRemoveGrad();
    		}
    	}	else if (e.getSource() == addViewBtn) {
    		remove(pnlRemoveGrad);
    		add(pnlAddGrad, BorderLayout.CENTER);
    		repaint();
    		revalidate();
    	} else if (e.getSource() == removeViewBtn) {
    		remove(pnlAddGrad);	
    		add(pnlRemoveGrad, BorderLayout.CENTER);
    		add(removeBtnPanel, BorderLayout.SOUTH);
    		repaint();
    		revalidate();
    	}
    }
    
	/**
	 * Removes the Grad in the system given a student ID or name. 
	 */
    private void performRemoveGrad() {
    	String id = idField.getText();
 	
    	String message = "Graduate remove failed";
    	if (id.length() > 0) {
    		if (GraduateCollection.remove(Integer.parseInt(id))) {
    			message = "Graduate removed";
    		}
    	}
		JOptionPane.showMessageDialog(null, message);
	}

	/**
     * Adds a new Grad to the system. Name, Year, GPA, and Email are required.
     */
	private void performAddGrad() {
		String name = txfField[0].getText();
		if (name.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a grad name");
			txfField[0].setFocusable(true);
			return;
		}
		
		String studentIdStr = txfField[1].getText();
		int studentId;
		if (studentIdStr.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a grad student ID");
			txfField[1].setFocusable(true);
			return;
		} else {
			studentId = Integer.parseInt(studentIdStr);
		}
		
		String yearStr = txfField[2].getText();
		if (yearStr.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a grad year");
			txfField[2].setFocusable(true);
			return;
		} else {
		}
		
		double gpa = Double.parseDouble(txfField[3].getText());	
		String email = txfField[4].getText(); 
		String transfer = txfField[5].getText().toLowerCase();
		String responsive = txfField[6].getText().toLowerCase();
		
		
		// make array of all employers entered (comma separated), and make into ArrayList
		String[] employers = txfField[7].getText().split(",");
		ArrayList<String> employersList = new ArrayList<String>();
		for (int i = 0; i < employers.length; i++) {
			employersList.add(employers[i].trim());
		}
		
		
		// make array of all internships entered (comma separated), and make into ArrayList
		String[] internships = txfField[8].getText().split(",");
		ArrayList<String> internshipsList = new ArrayList<String>();
		for (int i = 0; i < internships.length; i++) {
			internshipsList.add(internships[i].trim());
		}

		
		Graduate graduate = new Graduate(name, studentId, yearStr, gpa, email, transfer.startsWith("y"), 
										responsive.startsWith("y"), employersList, internshipsList);

		
		String message = "Graduate add failed";
		if (GraduateCollection.add(graduate)) {
			message = "Graduate added";
		}
		JOptionPane.showMessageDialog(null, message);

		// Clear all text fields.
		for (int i = 0; i < txfField.length; i++) {
			if (txfField[i].getText().length() != 0) {
				txfField[i].setText("");
			}
		}		
	}

}
