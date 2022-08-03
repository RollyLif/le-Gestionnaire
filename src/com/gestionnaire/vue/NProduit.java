/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.vue;

import com.gestionnaire.controller.Conversion;
import com.gestionnaire.controller.Formater;
import com.gestionnaire.model.Produit;
import com.gestionnaire.model.ProduitDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author User
 */
public class NProduit extends JDialog{
    
    private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Produit p=new Produit();

	/**
	 * Create the dialog.
	 */
	public NProduit() {
		setIconImage(new ImageIcon(getClass().getResource("/com/gestionnaire/vue/logo.png")).getImage());
		setTitle("Nouveau produit");
		setBounds(100, 100, 449, 292);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblDesignation = new JLabel("Designation");
			lblDesignation.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDesignation.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			lblDesignation.setBounds(82, 25, 114, 20);
			contentPanel.add(lblDesignation);
		}
		{
			textField = new JTextField();
			textField.setToolTipText("15 caract\u00E8res maximum");
			textField.setBounds(206, 25, 114, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblVolume = new JLabel("Volume");
			lblVolume.setHorizontalAlignment(SwingConstants.RIGHT);
			lblVolume.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			lblVolume.setBounds(82, 56, 114, 20);
			contentPanel.add(lblVolume);
		}
		{
			textField_1 = new JTextField();
			textField_1.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c=e.getKeyChar();
					if(!(Character.isDigit(c) || c== KeyEvent.VK_DELETE)){
						e.consume();
					}
				}
			});
			textField_1.setColumns(10);
			textField_1.setBounds(206, 56, 114, 20);
			contentPanel.add(textField_1);
		}
		{
			JLabel lblPrixUnitaire = new JLabel("Prix unitaire");
			lblPrixUnitaire.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPrixUnitaire.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			lblPrixUnitaire.setBounds(82, 87, 114, 20);
			contentPanel.add(lblPrixUnitaire);
		}
		{
			textField_2 = new JTextField();
			textField_2.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c=e.getKeyChar();
					if(!(Character.isDigit(c) || c== KeyEvent.VK_DELETE)){
						e.consume();
					}
				}			});
			textField_2.setColumns(10);
			textField_2.setBounds(206, 87, 114, 20);
			contentPanel.add(textField_2);
		}
		{
			JLabel lblQuantiteInitiale = new JLabel("Quantite initiale");
			lblQuantiteInitiale.setHorizontalAlignment(SwingConstants.RIGHT);
			lblQuantiteInitiale.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			lblQuantiteInitiale.setBounds(82, 118, 114, 20);
			contentPanel.add(lblQuantiteInitiale);
		}
		{
			textField_3 = new JTextField();
			textField_3.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c=e.getKeyChar();
					if(!(Character.isDigit(c) || c== KeyEvent.VK_DELETE)){
						e.consume();
					}
				}
			});
			textField_3.setColumns(10);
			textField_3.setBounds(206, 118, 114, 20);
			contentPanel.add(textField_3);
		}
		{
			JLabel lblQuantiteCritique = new JLabel("Quantite critique");
			lblQuantiteCritique.setHorizontalAlignment(SwingConstants.RIGHT);
			lblQuantiteCritique.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			lblQuantiteCritique.setBounds(82, 149, 114, 20);
			contentPanel.add(lblQuantiteCritique);
		}
		{
			textField_4 = new JTextField();
			textField_4.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c=e.getKeyChar();
					if(!(Character.isDigit(c) || c== KeyEvent.VK_DELETE)){
						e.consume();
					}
				}
			});
			textField_4.setColumns(10);
			textField_4.setBounds(206, 149, 114, 20);
			contentPanel.add(textField_4);
		}
		{
			JLabel lblQuantiteParBox = new JLabel("Quantite par box");
			lblQuantiteParBox.setHorizontalAlignment(SwingConstants.RIGHT);
			lblQuantiteParBox.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			lblQuantiteParBox.setBounds(82, 178, 114, 20);
			contentPanel.add(lblQuantiteParBox);
		}
		{
			textField_5 = new JTextField();
			textField_5.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c=e.getKeyChar();
					if(!(Character.isDigit(c) || c== KeyEvent.VK_DELETE)){
						e.consume();
					}
				}
			});
			textField_5.setColumns(10);
			textField_5.setBounds(206, 178, 114, 20);
			contentPanel.add(textField_5);
		}
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Valider");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Produit p=new Produit();
						
						p.setNom(Formater.Produit(textField.getText()));
						p.setVolume(Conversion.text2Chiffre(textField_1.getText()));
						p.setPUnit(Conversion.text2Chiffre(textField_2.getText()));
						p.setQte(Conversion.text2Chiffre(textField_3.getText()));
						p.setQtc(Conversion.text2Chiffre(textField_4.getText()));
						p.setQtp(Conversion.text2Chiffre(textField_5.getText()));
						try {
							ProduitDAO.CreateProduit(p);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						dispose();
					}
				});
				cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
		setModal(true);
	}
    
}
