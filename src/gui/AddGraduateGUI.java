package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import graduate.Graduate;
import graduate.GraduateCollection;
/**
 * Created by noxor on 12/2/16.
 * @author Jabo Johnigan
 * @versioin 12/2/16
 */
public class AddGraduateGUI extends JPanel implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1262967559520618107L;

	/**
     * labels for the addGrad panel.
     */
    private JLabel[] lblAddGrad = new JLabel[9];

    /**
     * PANEL SIZE
     */
    private static final Dimension DEFAULT_P_MIN = new Dimension(300, 600);

  
    
	private JTextField[] txfField = new JTextField[9];

    private JPanel pnlAddGrad, pnlRemoveGrad, pnlAddJobs, pnlButtons;
 
    private JButton addViewBtn, removeViewBtn;

    private JButton addEmps, addInts, addGrad;
    
    public AddGraduateGUI(){
        setLayout(new BorderLayout());
        setVisible(true);
        setSize(DEFAULT_P_MIN);
        setUpButtons();
        setUpPanel();
    }
    
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

    public void setUpPanel(){
    	pnlAddGrad = new JPanel();
    	pnlAddGrad.setLayout(new GridLayout(10, 0));
    	add(pnlAddGrad, BorderLayout.CENTER);
    	
    	String[] lblNames = {"Enter Name:", "Enter Student ID:", "Enter Graduate Year:", "Enter GPA:",
                "Enter Email:", "Transfer Student:", "Responsive:", "Employers:", "Internships:"};
		for (int i = 0; i < lblNames.length; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(2, 0));
			lblAddGrad[i] = new JLabel(lblNames[i]);
			txfField[i] = new JTextField(25);
			panel.add(lblAddGrad[i]);
			panel.add(txfField[i]);
			pnlAddGrad.add(panel);
		}
		
		JPanel panel = new JPanel();
		addGrad = new JButton("Add Grad");
		addGrad.addActionListener(this);
		panel.add(addGrad);
		pnlAddGrad.setLayout(new GridLayout(3, 0));
		pnlAddGrad.add(panel);
		
		
		pnlRemoveGrad = new JPanel();
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(2, 0));
		JLabel nameLabel = new JLabel("Search name:");
		JTextField nameField = new JTextField(25);
		pnlRemoveGrad.add(namePanel);
		
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new GridLayout(2, 0));
		JLabel idLabel = new JLabel("Search ID:");
		JTextField idField = new JTextField(25);
		pnlRemoveGrad.add(idPanel);
		
    }
    
    public void actionPerformed(ActionEvent e){
    	if (e.getSource() == addGrad) {
    		if(!txfField[0].getText().equals("") && !txfField[1].getText().equals("") 
    			&& !txfField[2].getText().equals("") && !txfField[3].getText().equals("")) {
				performAddGrad();
			}
    	} else if (e.getSource() == addViewBtn) {
    		remove(pnlRemoveGrad);
    		add(pnlAddGrad, BorderLayout.CENTER);
    		repaint();
    		revalidate();
    	} else if (e.getSource() == removeViewBtn) {
    		remove(pnlAddGrad);	
    		add(pnlRemoveGrad, BorderLayout.CENTER);
    		repaint();
    		revalidate();
    	}
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
