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
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author User
 */
public class Introduction extends JFrame{
    
    private JPanel contentPane;
	private JProgressBar progressBar;
	private JLabel charge;

	/**
	 * Create the frame.
	 */
	public Introduction() {
                
		setIconImage(new ImageIcon(getClass().getResource("/com/gestionnaire/vue/logo.png")).getImage());
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		charge = new JLabel("Chargement...");
		charge.setForeground(Color.RED);
		charge.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		contentPane.add(charge, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		progressBar = new JProgressBar();
		progressBar.setFocusable(false);
		progressBar.setFocusTraversalPolicyProvider(false);
		progressBar.setFocusTraversalKeysEnabled(false);
		progressBar.setFocusCycleRoot(false);
		progressBar.setEnabled(false);
		progressBar.setDoubleBuffered(false);
		progressBar.setForeground(Color.RED);
		progressBar.setAutoscrolls(false);
		progressBar.setBorderPainted(false);
		progressBar.setIgnoreRepaint(false);
		progressBar.setIndeterminate(false);
		progressBar.setInheritsPopupMenu(false);
		progressBar.setOpaque(false);
		progressBar.setRequestFocusEnabled(false);
		progressBar.setVerifyInputWhenFocusTarget(false);
		progressBar.setBackground(Color.WHITE);
		progressBar.setPreferredSize(new Dimension(0, 3));
		panel.add(progressBar, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
	}
	
	public void setValue(int valeur){
		progressBar.setValue(valeur);
	}
	
	public void setCharge(String label){
		charge.setText("chargement "+label);
	}
    
}
