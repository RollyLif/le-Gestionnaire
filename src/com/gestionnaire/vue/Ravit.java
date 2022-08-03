/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.vue;

import com.gestionnaire.controller.Conversion;
import com.gestionnaire.controller.Transaction;
import com.gestionnaire.model.Produit;
import com.gestionnaire.model.ProduitDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author User
 */
public class Ravit extends JDialog{

        private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private JCheckBox chckbxBouteilles;
	private JCheckBox chckbxBox;
	private JLabel lbl;
	private Produit p=new Produit();
	private int qu=0;
	private ButtonGroup bg=new ButtonGroup();

	/**
	 * Create the dialog.
	 */
	public Ravit() {
		setIconImage(new ImageIcon(getClass().getResource("/com/gestionnaire/vue/logo.png")).getImage());
		setTitle("Approvisionnement");
		setBounds(100, 100, 557, 378);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBackground(new Color(244, 164, 96));
			panel.setPreferredSize(new Dimension(65, 50));
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1, BorderLayout.NORTH);
				{
					chckbxBox = new JCheckBox("Box");
					bg.add(chckbxBox);
					panel_1.add(chckbxBox);
				}
				{
					textField = new JTextField();
					textField.addCaretListener(new CaretListener() {
						public void caretUpdate(CaretEvent arg0) {
							int row=table.getSelectedRow();
							if(row>=0 && textField.getText().length()>0){
								try {
									if(chckbxBox.isSelected()==true){
										p.setNom(table.getValueAt(row, 1).toString());
										p.setVolume(Conversion.text2Chiffre(table.getValueAt(row, 2).toString()));
										p=ProduitDAO.retrieveProduit(p);
										qu=Conversion.text2Chiffre(textField.getText())*p.getQtp();
										lbl.setText((qu)+" bouteilles");
									}else{
										qu=Conversion.text2Chiffre(textField.getText());
										lbl.setText(qu +" bouteilles");
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					});
					panel_1.add(textField);
					textField.setPreferredSize(new Dimension(14, 20));
					textField.setHorizontalAlignment(SwingConstants.CENTER);
					textField.setBorder(new LineBorder(new Color(0, 0, 0), 2));
					textField.setColumns(10);
				}
				{
					chckbxBouteilles = new JCheckBox("Bouteilles");
					chckbxBouteilles.setSelected(true);
					bg.add(chckbxBouteilles);
					panel_1.add(chckbxBouteilles);
				}
			}
			{
				lbl = new JLabel("0 bouteilles");
				lbl.setFont(new Font("Times New Roman", Font.ITALIC, 13));
				lbl.setHorizontalAlignment(SwingConstants.CENTER);
				lbl.setHorizontalTextPosition(SwingConstants.CENTER);
				lbl.setOpaque(true);
				panel.add(lbl, BorderLayout.SOUTH);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.setFont(new Font("Times New Roman", Font.PLAIN, 11));
				try {
					table.setModel(ProduitDAO.Appro());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				table.setRowSelectionInterval(0, 0);
				table.setDefaultEditor(Object.class, null);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Valider");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							Transaction.approvisionner(p, qu);
							table.setModel(ProduitDAO.Appro());
							textField.setText("");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				});
				cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
		setModal(true);
	}
    
}
