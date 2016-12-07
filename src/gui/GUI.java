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

	/**A DeFAULT PANEL SIZE FOR JPANELS. */
	private static final Dimension DEFAULT_F_MIN = new Dimension(1000, 600);
	/**Size of Table */
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
	public Object[][] mData;

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
		setLayout(new BorderLayout());
	    contentPanel = new JPanel();
	    contentPanel.setVisible(true);
	    contentPanel.setLayout(new BorderLayout());
		cmbInts = new JComboBox();
		cmbEmps = new JComboBox();
		cmbInts.setEditable(false);
		cmbEmps.setEditable(false);

		//cmbEmps.setSelectedIndex(-1);
		//cmbInts.setSelectedIndex(-1);
		add(contentPanel);
		mList = getData(null);
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

				for (int x = 0; x< listEmps.size(); x++) {
					cmbEmps.addItem(listEmps.get(x).toString());

				}
				for (int y = 0; y < listInts.size(); y++){
					cmbInts.addItem(listInts.get(y).toString());
				}



				mData[i][0] = mList.get(i).getName();
				mData[i][1] = mList.get(i).getGraduateID();
				mData[i][2] = mList.get(i).getGraduationYear();
				mData[i][3] = mList.get(i).getGPA();
				mData[i][4] = mList.get(i).getEmail();
				mData[i][5] = mList.get(i).isTransferStatus();
				mData[i][6] = mList.get(i).isResponseFlag();
                //mData[i][7] = listEmps;
				//mData[i][8] = listInts;

			}
		}
		return mList;
	}

	/**
	 * Creates the ButtonPanel
	 */
	public void addBtnPanel(){
		//create button panel
		tblPanel = new JPanel();
		btnPanel = new JPanel ();
		btnPanel.setVisible(true);
		btnAddGrad = new JButton("Add/Remove Grad");
		btnUpdateGradInfo = new JButton("Update Grad Info");
		btnRunReport = new JButton("Run Report on Grad");
		btnListGrads = new JButton("List Grads");
		btnRunReport.addActionListener(this);
		btnListGrads.addActionListener(this);
		btnAddGrad.addActionListener(this);
		btnPanel.add(btnListGrads);
		btnPanel.add(btnAddGrad);
		btnPanel.add( btnRunReport);
		btnPanel.add(btnUpdateGradInfo);

		add(btnPanel, BorderLayout.NORTH);


	}

    /**
     * Creates the TablePanel to host the table.
     */
	public void addTablePanel(){
        // Add Table Panel
        table = new JTable(new GradTableModel());
		table.getModel().addTableModelListener(this);
	///	TableColumn trans = newe
       	 setUpJobColumn(table, table.getColumnModel().getColumn(7),
				table.getColumnModel().getColumn(8));
		table.getColumnClass(7);
        //table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(DEFAULT_T_MIN);
        scrollPane = new JScrollPane(table);
        tblPanel.add(scrollPane);
        contentPanel.add(tblPanel, BorderLayout.CENTER);

    }

	public void setUpJobColumn(JTable table,
								 TableColumn Emps, TableColumn Ints ) {
		//Set up the editor for the sport cells.

		Emps.setCellEditor(new DefaultCellEditor(cmbEmps));
		Ints.setCellEditor(new DefaultCellEditor(cmbInts));

		//Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer =
				new DefaultTableCellRenderer();
		DefaultTableCellRenderer renderer1 =
				new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for employers option");
		Emps.setCellRenderer(renderer);
		renderer1.setToolTipText("Click for internships");
		Ints.setCellRenderer(renderer1);
	}

//	/*
//    * This method picks good column sizes.
//    * If all column heads are wider than the column's cells'
//    * contents, then you can just use column.sizeWidthToFit().
//    */
//	private void initColumnSizes(JTable table) {
//		Table model = (MyTableModel)table.getModel();
//		TableColumn column = null;
//		Component comp = null;
//		int headerWidth = 0;
//		int cellWidth = 0;
//		Object[] longValues = model.longValues;
//		TableCellRenderer headerRenderer =
//				table.getTableHeader().getDefaultRenderer();
//
//		for (int i = 0; i < 5; i++) {
//			column = table.getColumnModel().getColumn(i);
//
//			comp = headerRenderer.getTableCellRendererComponent(
//					null, column.getHeaderValue(),
//					false, false, 0, 0);
//			headerWidth = comp.getPreferredSize().width;
//
//			comp = table.getDefaultRenderer(model.getColumnClass(i)).
//					getTableCellRendererComponent(
//							table, longValues[i],
//							false, false, 0, i);
//			cellWidth = comp.getPreferredSize().width;
//
//			if (true) {
//				System.out.println("Initializing width of column "
//						+ i + ". "
//						+ "headerWidth = " + headerWidth
//						+ "; cellWidth = " + cellWidth);
//			}
//
//			column.setPreferredWidth(Math.max(headerWidth, cellWidth));
//		}
//	}


	/**
	 * ActionListeners for all the buttons.
	 * @param e Action event from the user
	 */
    public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == btnAddGrad){
            addGradPanel = new AddGraduateGUI();
            contentPanel.removeAll();

            contentPanel.add(addGradPanel, BorderLayout.CENTER);
            repaint();
            revalidate();
        } else if (e.getSource() == btnListGrads){
            contentPanel.removeAll();
            contentPanel.add(tblPanel, BorderLayout.CENTER);
            repaint();
            revalidate();
        } else if (e.getSource() == btnRunReport){
			dataReportPanel = new DataReportGUI();
			contentPanel.removeAll();

        	contentPanel.add(dataReportPanel, BorderLayout.CENTER);
			repaint();
			revalidate();
        } else if (e.getSource() == btnUpdateGradInfo){

        } else if (e.getSource() == btnBack){

        }
    }
    
	/**
	 * Listen to the cell changes on the table.
	 *
	 * @param e event when table is changed by the user
	 */
	@Override
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

	class GradTableModel extends AbstractTableModel {
		/**
		 * Name of Columns for table.
		 */
		private String[] GradColumnNames = {"graduateName", "graduateId", "graduationYear",
				"gpa", "email", "transferStatus", "responsive", "employers", "internships"};

		private Object[][] data = mData;

		public int getColumnCount() {
			return GradColumnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return GradColumnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		/*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
		public Class getColumnClass(int c) {
			Class clazz = String.class;
			switch (c) {
				case 1:
					clazz = Integer.class;
					break;
				case 2:
					clazz = Integer.class;
					break;
				case 3:
					clazz = Double.class;
					break;
				case 5:
					clazz = Boolean.class;
					break;
				case 6:
					clazz = Boolean.class;
					break;
				case 7:
					clazz = List.class;
					break;
				case 8:
					clazz = List.class;
					break;


			}
			return clazz;
		}

		/*
         * Don't need to implement this method unless your table's
         * editable.
         */
		public boolean isCellEditable(int row, int col) {
			//Note that the data/cell address is constant,
			//no matter where the cell appears onscreen.
			if (col == 6 && col == 7) {
				return true;
			} else {
				return false;
			}
		}

	}
	}
