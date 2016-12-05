package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import graduate.Graduate;
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
    private JLabel[] lblAddGrad = new JLabel[8];

    /**
     * PANEL SIZE
     */
    private static final Dimension DEFAULT_P_MIN = new Dimension(300, 600);

  
    
	private JTextField[] txfField = new JTextField[8];

    private JPanel pnlAddGrad, pnlAddJobs;
    

    private JButton addEmps, addInts, addGrad;
    
    public AddGraduateGUI(){
        setLayout(new BorderLayout());
        setVisible(true);
        setSize(DEFAULT_P_MIN);
        setUpPanel();
    }

    public void setUpPanel(){
    	pnlAddGrad = new JPanel();
    	pnlAddGrad.setLayout(new GridLayout(9, 0));
    	add(pnlAddGrad, BorderLayout.CENTER);
    	
    	String[] lblNames = {"Enter Name:", "Enter Graduate Year:", "Enter GPA:",
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
		pnlAddGrad.add(panel);
    }
    
    public void actionPerformed(ActionEvent e){
    	if (e.getSource() == addGrad) {
    		if(!txfField[0].getText().equals("") && !txfField[1].getText().equals("") 
    			&& !txfField[2].getText().equals("") && !txfField[3].getText().equals("")) {
				performAddGrad();
			}
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
		
		String yearStr = txfField[1].getText();
		if (yearStr.length() == 0) {
			JOptionPane.showMessageDialog(null, "Enter a grad year");
			txfField[1].setFocusable(true);
			return;
		} else {
			int yearInt = Integer.parseInt(yearStr);
		}
		
		double gpa = Double.parseDouble(txfField[2].getText());	
		String email = txfField[3].getText(); 
		String transfer = txfField[4].getText();
		String responsive = txfField[5].getText();
		
		
		Graduate graduate;

		
		String message = "Client add failed";
//		if (GraduateCollection.add(graduate)) {
//			message = "Client added";
//		}
		JOptionPane.showMessageDialog(null, message);

		// Clear all text fields.
		for (int i = 0; i < txfField.length; i++) {
			if (txfField[i].getText().length() != 0) {
				txfField[i].setText("");
			}
		}		
	}

}
