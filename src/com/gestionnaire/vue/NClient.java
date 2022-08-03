/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.vue;

import com.gestionnaire.controller.Formater;
import com.gestionnaire.model.ClientDAO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author User
 */
public class NClient extends JDialog{
    
    private final JPanel contentPanel = new JPanel();
	private JList list;
	private String n=new String();
	private JButton btnNewButton;
	private JTextField textField;
	private JTextField textField_1;
	

	public String getN() {
		return n;
	}



	/**
	 * Create the dialog.
	 */
	public NClient() {
		setTitle("Client");
		setIconImage(new ImageIcon(getClass().getResource("/com/gestionnaire/vue/logo.png")).getImage());
		setBounds(100, 100, 501, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(140, 80));
			contentPanel.add(scrollPane, BorderLayout.WEST);
			{
				list = new JList();
				list.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						textField.setText(list.getSelectedValue().toString());
					}
				});
				list.setFont(new Font("Trebuchet MS", Font.ITALIC, 12));
                            try {
                                list.setModel(ClientDAO.retrieveListeClient(""));
                            } catch (SQLException ex) {
                                Logger.getLogger(NClient.class.getName()).log(Level.SEVERE, null, ex);
                            }

				scrollPane.setViewportView(list);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblPrenom = new JLabel("Prenom");
			lblPrenom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lblPrenom.setBounds(74, 97, 64, 17);
			panel.add(lblPrenom);
			
			textField = new JTextField();
			textField.addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent arg0) {
					try {
						list.setModel(ClientDAO.retrieveListeClient(textField.getText()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int i=textField.getText().trim().length();
					if(i>2){
						textField_1.setEditable(true);
					}else{
						textField_1.setEditable(false);
					}
					
					if(i>0 && i<3){
						btnNewButton.setEnabled(false);
					}else{
						btnNewButton.setEnabled(true);
					}
				}
			});
			textField.setToolTipText("20 caract\u00E8res maximum pour nom et prenom");
			textField.setBounds(148, 96, 117, 20);
			panel.add(textField);
			textField.setColumns(10);
			
			JLabel lblNom = new JLabel("Nom");
			lblNom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lblNom.setBounds(74, 126, 64, 17);
			panel.add(lblNom);
			
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setColumns(10);
			textField_1.setBounds(148, 125, 117, 20);
			panel.add(textField_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnNewButton = new JButton("Valider");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							n=Formater.Nom((textField.getText()+" "+textField_1.getText()).trim());
						} catch (Exception e) {
							e.printStackTrace();
						}
						dispose();
					}
				});
				btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				buttonPane.add(btnNewButton);
			}
		}
		setLocationRelativeTo(null);
		setModal(true);
	}
}
