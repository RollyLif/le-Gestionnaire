/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.vue;

import com.gestionnaire.model.ProduitDAO;
import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author User
 */
public class Stock extends JDialog{
    
    private final JPanel contentPanel = new JPanel();
	private JTable table;



	/**
	 * Create the dialog.
	 */
	public Stock() {
		setTitle("Le Stock");
		setIconImage(new ImageIcon(getClass().getResource("/com/gestionnaire/vue/logo.png")).getImage());
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.setShowVerticalLines(false);
				table.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
				table.setDefaultEditor(Object.class, null);
				try {
					table.setModel(ProduitDAO.Stock());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scrollPane.setViewportView(table);
			}
		}
		setModal(true);
		setLocationRelativeTo(null);
	}
    
}
