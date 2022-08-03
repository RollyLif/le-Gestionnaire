/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author User
 */
public class Descript extends JDialog{
    
    private final JPanel contentPanel = new JPanel();
	
	public Descript() {
		setIconImage(new ImageIcon(getClass().getResource("/com/gestionnaire/vue/logo.png")).getImage());
		setBounds(100, 100, 696, 334);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(getClass().getResource("/com/gestionnaire/vue/logo (2).jpg")));
			contentPanel.add(label, BorderLayout.WEST);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblLeGestionnaire = new JLabel("Le Gestionnaire");
				lblLeGestionnaire.setBackground(Color.WHITE);
				lblLeGestionnaire.setForeground(Color.BLUE);
				lblLeGestionnaire.setBorder(new LineBorder(Color.BLUE, 3, true));
				lblLeGestionnaire.setFont(new Font("Lucida Handwriting", Font.BOLD, 36));
				lblLeGestionnaire.setOpaque(true);
				lblLeGestionnaire.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblLeGestionnaire, BorderLayout.NORTH);
			}
			{
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setOpaque(true);
				lblNewLabel.setBackground(Color.WHITE);
				panel.add(lblNewLabel, BorderLayout.WEST);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					JTextArea txtrLeGestionnaireEst = new JTextArea();
					txtrLeGestionnaireEst.setFont(new Font("Lucida Handwriting", Font.PLAIN, 12));
					txtrLeGestionnaireEst.setEditable(false);
					txtrLeGestionnaireEst.setText("Le Gestionnaire version demo\r\n\r\nLe Gestionnaire est un logiciel de gestion  destin\u00E9e pour\r\nla gestion des bars ,terrasses, boites de nuit, etc.\r\n\r\nPour la promotion la monnaie nationale congolaise, le franc \r\ncongolais(CDF) comme unit\u00E9 mon\u00E9taire.");
					scrollPane.setViewportView(txtrLeGestionnaireEst);
				}
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setPreferredSize(new Dimension(40, 40));
				panel.add(scrollPane, BorderLayout.SOUTH);
				{
					JTextArea txtrLeGestionnaireVersion = new JTextArea();
					txtrLeGestionnaireVersion.setForeground(Color.RED);
					txtrLeGestionnaireVersion.setFont(new Font("Lucida Handwriting", Font.PLAIN, 10));
					txtrLeGestionnaireVersion.setText("Rolly Lifungula\r\nrelifungula@yahoo.fr");
					txtrLeGestionnaireVersion.setEditable(false);
					scrollPane.setViewportView(txtrLeGestionnaireVersion);
				}
			}
		}
		setLocationRelativeTo(null);
		setModal(true);
	}
    
}
