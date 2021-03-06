package com.dakshpokar.storemanager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.BorderLayout;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ShowBills {

	public JFrame frmBills;
	private JTextField textField;
	JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ShowBills() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBills = new JFrame();
		frmBills.getContentPane().setLayout(null);
		frmBills.setBounds(100, 100, 550, 400);
		String sql;
		sql = "select * from bills";
		table = new JTable(ClientDashboard.clientConnection.builderFromSender((new Query(sql, 0, 1)))){
			public boolean isCellEditable(int row, int column){
			    return false;
			  }
			public boolean getScrollableTracksViewportWidth() {
				   return getPreferredSize().width < getParent().getWidth();
				 }
		};
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(33, 139, 480, 189);
		frmBills.getContentPane().add(scrollPane);
		
		JLabel label = new JLabel("List of items found: ");
		label.setBounds(37, 110, 188, 14);
		frmBills.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 18));
		textField.setBounds(37, 46, 476, 40);
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String s;
				if(!textField.getText().toString().equalsIgnoreCase("")){
					Integer bill_id = Integer.parseInt(textField.getText().toString());
					s= "select * from bills where bill_id = "+bill_id;
					table.setModel(ClientDashboard.clientConnection.builderFromSender((new Query(s, 0, 1))));
				}
				else{
					table.setModel(ClientDashboard.clientConnection.builderFromSender((new Query("select * from bills", 0, 1))));

				}
			}
			
		});
		frmBills.getContentPane().add(textField);
		
		JLabel label_1 = new JLabel("Enter the item to be searched:");
		label_1.setBounds(37, 20, 188, 14);
		frmBills.getContentPane().add(label_1);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
