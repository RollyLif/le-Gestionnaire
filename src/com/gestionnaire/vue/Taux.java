/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.vue;

import com.gestionnaire.model.RateDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
public class Taux extends JDialog{
    
    private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private int rate=0;

	Taux() {
		setTitle("Taux");
		setIconImage(new ImageIcon(getClass().getResource("/com/gestionnaire/vue/logo.png")).getImage());
		setBounds(100, 100, 448, 192);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("1$ vaut");
			lblNewLabel.setBounds(133, 57, 49, 16);
			lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			rate=RateDAO.retrieveTaux();
			textField.setText(""+rate);
			textField.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c=e.getKeyChar();
					if(!(Character.isDigit(c) || c== KeyEvent.VK_DELETE)){
						e.consume();
					}
				}
			});
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			textField.setBounds(187, 55, 86, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblFc = new JLabel("fc");
			lblFc.setBounds(278, 57, 23, 16);
			lblFc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			contentPanel.add(lblFc);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Valider");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int tau= Integer.valueOf(textField.getText()).intValue();
						RateDAO.UpdateRate(tau);
						dispose();
					}
				});
				okButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setLocationRelativeTo(null);
		setModal(true);
	}
    
}
