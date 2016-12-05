package gui;

import javax.swing.JPanel;
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

    /**
     * labels Name
     */
    private String[] lblNames = {"Enter Name", "Enter Graduate Year", "Enter GPA",
                                "Enter Email", "Transfer Student", "Responsive"};

    private JPanel pnlAddGrad, pnlAddJobs;

    private JButton addEmps, addInts, addGrad;
    public AddGraduateGUI(){
        setLayout(new BorderLayout());
        setVisible(true);
        setSize(DEFAULT_P_MIN);
        BuildPanel();
    }

    public void BuildPanel(){

    }
    public void actionPerformed(ActionEvent someAction){

    }

}
