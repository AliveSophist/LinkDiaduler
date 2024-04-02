package com.linkdiaduler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Scheduler{
	public JFrame frameScheduler;
	
	private JTable table;
	private JTextField memo;
	private JPanel pnlControler;
	private DefaultTableModel model;
	private JButton rowAdd, rowDel;
	private Choice choiceAlarm, choiceHour, choiceMinute;
	
	
	public Scheduler(){
		initialize();
	}
	private void initialize(){
	  
		frameScheduler = new JFrame("Table Test");
		frameScheduler.setPreferredSize(new Dimension(700,200));
		frameScheduler.setLocation(500,400);

	  
		String colName[] = {"TIME","MEMO","ALARM"};
		model = new DefaultTableModel(colName,0){
			//Override delete시에 행 선택시 더블클릭할경우 생기는  Edit이벤트의 삭제.
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false; 
				}
		};
		
		table = new JTable(model);
		table.setEnabled(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(600);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getTableHeader().setReorderingAllowed(false);  
		DefaultTableCellRenderer  dtcr = new DefaultTableCellRenderer ();
	    dtcr.setHorizontalAlignment(SwingConstants .CENTER);
	    TableColumnModel  tcm = table.getColumnModel();
	    tcm.getColumn(0).setCellRenderer(dtcr);  
	    tcm.getColumn(2).setCellRenderer(dtcr);

		pnlControler = new JPanel();
		memo = new JTextField(3);
		rowAdd = new JButton("add row");
		rowAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String data[] = new String[3];
				data[0] = choiceHour.getSelectedItem()+" : "+choiceMinute.getSelectedItem();
				data[1] = memo.getText();
				data[2] = choiceAlarm.getSelectedItem();
				if(model.getRowCount()>0)
				{
					for(int i=0; i<=(model.getRowCount()-1); i++)
					{	
						String getValue=model.getValueAt(i,0).toString();
						if(getValue.length()>=data[0].length() && getValue.compareTo(data[0])>=0)
							{
								model.insertRow(i, data);
								break;
							}
						if(i==(model.getRowCount()-1))
						{
							model.addRow(data);

						}
					}
				}
				else
				{
					model.addRow(data);
				}
				
				for(int i=0; i<=(model.getRowCount()-1); i++)   //removing overwrite line
				{	
					String getValue=model.getValueAt(i,0).toString();
					for(int j=0; j<=(model.getRowCount()-1); j++)
						if(getValue==model.getValueAt(j,0) && i!=j)	
						{	model.removeRow(j);
							break;
						}
				}
			}
		});
		rowDel = new JButton("delete row");
		choiceHour = new Choice();
		for(int hour=1; hour<=24; hour++)
			choiceHour.add(hour+"");
		choiceMinute = new Choice();
		for(int minute=1; minute<=60; minute++)
			choiceMinute.add(minute+"");
		choiceAlarm = new Choice();
		choiceAlarm.add("X");
		choiceAlarm.add("O");
		

		rowDel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					if(rowDel.getText()=="delete row"){
						table.setEnabled(true);
						rowDel.setText("choose row and click me");
					}
					else{
						int row = table.getSelectedRow();
						while(table.getSelectedRowCount()!=0)
							model.removeRow(row);
						table.setEnabled(false);
						rowDel.setText("delete row");
					}
			 	}
			});
		
		
		pnlControler.add(new JLabel("TIME"));
		pnlControler.add(choiceHour);
		pnlControler.add(choiceMinute);
		pnlControler.add(new JLabel("MEMO"));
		pnlControler.add(memo);
		pnlControler.add(new JLabel("ALARM"));
		pnlControler.add(choiceAlarm);
		pnlControler.add(rowAdd);
		pnlControler.add(rowDel);
		
		frameScheduler.getContentPane().add(new JScrollPane(table),BorderLayout.CENTER);
		frameScheduler.getContentPane().add(pnlControler,BorderLayout.SOUTH);
		frameScheduler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameScheduler.pack();
		frameScheduler.setVisible(true);
	}
	public static void main(String[] args) {
		//TODO Auto generated method stub
		
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					Scheduler home = new Scheduler();
					home.frameScheduler.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		JFrame.setDefaultLookAndFeelDecorated(true);
	}	
}

