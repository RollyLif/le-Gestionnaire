/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.vue;

import com.gestionnaire.controller.Conversion;
import com.gestionnaire.controller.Transaction;
import com.gestionnaire.model.Client;
import com.gestionnaire.model.ClientDAO;
import com.gestionnaire.model.Facture;
import com.gestionnaire.model.FactureDAO;
import com.gestionnaire.model.Produit;
import com.gestionnaire.model.ProduitDAO;
import com.gestionnaire.model.RateDAO;
import com.gestionnaire.model.Service;
import com.gestionnaire.model.ServiceDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author User
 */
public class NFacture extends JPanel {

    private JTable table;
    private JTable table_1;
    private int taux = 0;
    private int montant = 0;
    private JLabel lblTauxDuJour;
    private JLabel lblFc;
    private JTextField textField;
    private JCheckBox chckbxFc;
    private JCheckBox checkBox;
    private JList list;
    private Facture f;
    private JSpinner spinner;
    private JLabel lblFc_1;
    private JButton btnValider;
    private int bag = 0;

    /**
     * Create the panel.
     *
     * @throws SQLException
     */
    public NFacture(int id) throws SQLException {
        setFont(new Font("Times New Roman", Font.BOLD, 11));
        setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(60, 60));
        panel.setMinimumSize(new Dimension(40, 40));
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));

        f = FactureDAO.retrieveFacture(id);

        JLabel lblFactureN = new JLabel("Facture 00" + id);
        lblFactureN.setHorizontalAlignment(SwingConstants.CENTER);
        lblFactureN.setHorizontalTextPosition(SwingConstants.CENTER);
        lblFactureN.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel.add(lblFactureN, BorderLayout.NORTH);

        Client c = new Client();
        c.setId(f.getIdclient());
        c = ClientDAO.retrieveClient(c);
        JLabel lblNomDuClient = new JLabel(c.getNom());
        lblNomDuClient.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        panel.add(lblNomDuClient, BorderLayout.WEST);

        JLabel lblLe = new JLabel("le " + f.getDate());
        lblLe.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        panel.add(lblLe, BorderLayout.EAST);

        taux = RateDAO.retrieveTaux();
        lblTauxDuJour = new JLabel("taux du jour :" + taux + "fc");
        lblTauxDuJour.setForeground(Color.BLUE);
        lblTauxDuJour.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblTauxDuJour.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTauxDuJour, BorderLayout.SOUTH);

        textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
        textField.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent arg0) {
                if (textField.getText().length() != 0) {
                    montant = Conversion.text2Chiffre(textField.getText());
                } else {
                    montant = 0;
                }
                if (checkBox.isSelected() == true) {
                    lblFc_1.setText((montant * taux) + " fc");
                } else {
                    lblFc_1.setText((montant) + " fc");
                }
            }
        });
        textField.setForeground(Color.BLUE);
        textField.setFont(new Font("Times New Roman", Font.BOLD, 15));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(textField, BorderLayout.CENTER);
        textField.setColumns(10);

        btnValider = new JButton("valider");
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                retire();
            }
        });
        add(btnValider, BorderLayout.SOUTH);

        JPanel panel_1 = new JPanel();
        panel_1.setMinimumSize(new Dimension(90, 70));
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setPreferredSize(new Dimension(90, 70));
        add(panel_1, BorderLayout.EAST);
        panel_1.setLayout(new BorderLayout(0, 0));

        lblFc = new JLabel("000000 fc");
        lblFc.setHorizontalAlignment(SwingConstants.CENTER);
        lblFc.setForeground(Color.RED);
        lblFc.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblFc.setText(ServiceDAO.Addition(f.getId()) + " fc");
        panel_1.add(lblFc, BorderLayout.SOUTH);

        JPanel panel_4 = new JPanel();
        panel_4.setBackground(new Color(255, 127, 80));
        panel_4.setPreferredSize(new Dimension(30, 30));
        panel_1.add(panel_4, BorderLayout.NORTH);

        ButtonGroup rg = new ButtonGroup();

        chckbxFc = new JCheckBox("fc");
        chckbxFc.setSelected(true);
        chckbxFc.setOpaque(false);
        rg.add(chckbxFc);
        panel_4.add(chckbxFc);

        checkBox = new JCheckBox("$");
        checkBox.setOpaque(false);
        rg.add(checkBox);
        panel_4.add(checkBox);

        JPanel panel_5 = new JPanel();
        panel_1.add(panel_5, BorderLayout.CENTER);

        lblFc_1 = new JLabel("000000 fc");
        lblFc_1.setForeground(new Color(0, 0, 255));
        lblFc_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblFc_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblFc_1.setPreferredSize(new Dimension(70, 14));
        lblFc_1.setFont(new Font("Times New Roman", Font.ITALIC, 13));
        panel_5.add(lblFc_1);

        JPanel panel_2 = new JPanel();
        panel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_2.setPreferredSize(new Dimension(130, 80));
        add(panel_2, BorderLayout.WEST);
        panel_2.setLayout(new BorderLayout(0, 0));

        JPanel panel_3 = new JPanel();
        panel_3.setPreferredSize(new Dimension(30, 35));
        panel_2.add(panel_3, BorderLayout.SOUTH);

        JButton button = new JButton("+");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                try {
                    String o = list.getSelectedValue().toString();
                    int qte = Conversion.text2Chiffre(spinner.getValue().toString());
                    Produit p = Conversion.selectedProduit(o);
                    System.out.print(p.getNom() + " " + p.getPUnit());
                    Transaction.servir(qte, p, f);
                    table_1.setModel(ServiceDAO.Facture(f.getId()));
                    lblFc.setText(ServiceDAO.Addition(f.getId()) + " fc");
                    spinner.setValue(1);
                    list.setModel(ProduitDAO.retrieveListeProduit());
                    actButton();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        panel_3.add(button);

        JButton button_1 = new JButton("-");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (table_1.getRowCount() == 0) {
                    retire();
                    try {
                        FactureDAO.deleteFacture(id);
                    } catch (SQLException ex) {
                        Logger.getLogger(NFacture.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (table_1.getSelectedRow() >= 0) {
                        Service s = new Service();
                        s.setId(Conversion.text2Chiffre(table_1.getValueAt(table_1.getSelectedRow(), 5).toString()));
                        s.setDesignation(table_1.getValueAt(table_1.getSelectedRow(), 1).toString());
                        s.setVol(Conversion.text2Chiffre(table_1.getValueAt(table_1.getSelectedRow(), 2).toString()));
                        s.setQte(Conversion.text2Chiffre(table_1.getValueAt(table_1.getSelectedRow(), 0).toString()));
                        try {
                            Transaction.debarasser(s);
                            table_1.setModel(ServiceDAO.Facture(f.getId()));
                            lblFc.setText(ServiceDAO.Addition(f.getId()) + " fc");
                            list.setModel(ProduitDAO.retrieveListeProduit());
                            actButton();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        panel_3.add(button_1);

        spinner = new JSpinner();
        spinner.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        spinner.setModel(new SpinnerNumberModel(1, 1, 25, 1));
        panel_2.add(spinner, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        panel_2.add(scrollPane, BorderLayout.CENTER);

        list = new JList();
        list.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        list.setModel(ProduitDAO.retrieveListeProduit());
        list.setSelectedIndex(0);
        scrollPane.setViewportView(list);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
        scrollPane_1.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent arg0) {
                try {
                    list.setModel(ProduitDAO.retrieveListeProduit());
                    taux = RateDAO.retrieveTaux();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        add(scrollPane_1, BorderLayout.CENTER);

        table_1 = new JTable();
        table_1.setShowVerticalLines(false);
        table_1.setShowHorizontalLines(false);
        table_1.setShowGrid(false);
        table_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        table_1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent arg0) {
                try {
                    list.setModel(ProduitDAO.retrieveListeProduit());
                    taux = RateDAO.retrieveTaux();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                lblTauxDuJour.setText("taux du jour :" + taux + "fc");
            }
        });
        table_1.setModel(ServiceDAO.Facture(f.getId()));
        table_1.setDefaultEditor(Object.class, null);
        scrollPane_1.setViewportView(table_1);
        actButton();
    }

    private void actButton() {
        if (table_1.getRowCount() < 1) {
            btnValider.setEnabled(false);
            textField.setEnabled(false);
        } else {
            btnValider.setEnabled(true);
            textField.setEnabled(true);
        }
    }

    private void retire() {
        bag=1;
        try {
            FactureDAO.UpdateProduit(f);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public int Etat(){
        return bag;
    }

}
