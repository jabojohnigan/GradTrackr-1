package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Created by noxor on 12/2/16.
 * @author Jabo Johnigan
 * @versioin 12/2/16
 */
public class AddGraduateGUI extends JPanel implements ActionListener {
    /**
     * labels for the addGrad panel.
     */
    private JLabel[] lblAddGrad = new JLabel[6];

    /**
     * PANEL SIZE
     */
    private static final Dimension DEFAULT_P_MIN = new Dimension(300, 600);

  
    
	private JLabel[] txfLabel = new JLabel[6];
	private JTextField[] txfField = new JTextField[6];

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
    	pnlAddGrad.setLayout(new GridLayout(6, 0));
    	add(pnlAddGrad, BorderLayout.CENTER);
    	
    	String[] lblNames = {"Enter Name:", "Enter Graduate Year:", "Enter GPA:",
                "Enter Email:", "Transfer Student:", "Responsive:"};
		for (int i = 0; i < lblNames.length; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(2, 0));
			txfLabel[i] = new JLabel(lblNames[i]);
			txfField[i] = new JTextField(25);
			panel.add(txfLabel[i]);
			panel.add(txfField[i]);
			pnlAddGrad.add(panel);
		}
		
		JPanel panel = new JPanel();
		addGrad = new JButton("Add Grad");
		addGrad.addActionListener(this);
		panel.add(addGrad);
		pnlAddGrad.add(panel);
    }
    public void actionPerformed(ActionEvent someAction){

    }

}
