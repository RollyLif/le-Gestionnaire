/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.vue;

import com.gestionnaire.controller.Conversion;
import com.gestionnaire.model.Client;
import com.gestionnaire.model.ClientDAO;
import com.gestionnaire.model.Facture;
import com.gestionnaire.model.FactureDAO;
import com.gestionnaire.model.ProduitDAO;
import com.gestionnaire.model.RateDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author User
 */
public class Principale extends JFrame {

    private JPanel contentPane;
    private JTabbedPane tabbedPane;
    private JTextArea Alerte, textArea;
    private Thread t, th;

    /**
     * Create the frame.
     *
     * @throws SQLException
     */
    public Principale() throws SQLException {
        setTitle("Le Gestionnaire");
        setIconImage(new ImageIcon(getClass().getResource("/com/gestionnaire/vue/logo.png")).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 860, 600);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFichier = new JMenu("Fichier");
        mnFichier.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        menuBar.add(mnFichier);

        JMenuItem mntmNouveauProduit = new JMenuItem("nouveau produit");
        mntmNouveauProduit.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        mntmNouveauProduit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                NProduit n = new NProduit();
                n.setVisible(true);
            }
        });

        JMenuItem mntmNouvelleFacture = new JMenuItem("nouvelle facture");
        mntmNouvelleFacture.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        mntmNouvelleFacture.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ajouterFacture();
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
            }
        });
        mntmNouvelleFacture.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        mnFichier.add(mntmNouvelleFacture);
        mntmNouveauProduit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        mnFichier.add(mntmNouveauProduit);

        JMenuItem mntmApprovisionnement = new JMenuItem("approvisionnement");
        mntmApprovisionnement.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        mntmApprovisionnement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Ravit ra = new Ravit();
                ra.setVisible(true);
            }
        });
        mntmApprovisionnement.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        mnFichier.add(mntmApprovisionnement);

        JMenuItem mntmFermer = new JMenuItem("fermer");
        mntmFermer.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        mntmFermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        mntmFermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        mnFichier.add(mntmFermer);

        JMenu mnEdition = new JMenu("Edition");
        mnEdition.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        menuBar.add(mnEdition);

        JMenuItem mntmChangerTaux = new JMenuItem("taux");
        mntmChangerTaux.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        mntmChangerTaux.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Taux t = new Taux();
                t.setVisible(true);
            }
        });
        mntmChangerTaux.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_MASK));
        mnEdition.add(mntmChangerTaux);

        JMenuItem mntmProduit = new JMenuItem("produit");
        mntmProduit.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        mntmProduit.setEnabled(false);
        mnEdition.add(mntmProduit);

        JMenuItem mntmClient = new JMenuItem("client");
        mntmClient.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        mntmClient.setEnabled(false);
        mnEdition.add(mntmClient);

        JMenu mnComptabilite = new JMenu("Comptabilite");
        mnComptabilite.setEnabled(false);
        mnComptabilite.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        menuBar.add(mnComptabilite);

        JMenu mnAPropos = new JMenu("A propos");
        mnAPropos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                Descript d = new Descript();
                d.setVisible(true);
            }
        });
        mnAPropos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        menuBar.add(mnAPropos);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 12));
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel.setPreferredSize(new Dimension(180, 100));
        contentPane.add(panel, BorderLayout.EAST);

        JPanel panel_1 = new JPanel();
        panel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                Stock s = new Stock();
                s.setVisible(true);
            }
        });
        panel_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        panel_1.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)), "Tarif", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setPreferredSize(new Dimension(160, 210));
        panel.add(panel_1);
        panel_1.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setMinimumSize(new Dimension(158, 23));
        scrollPane.setPreferredSize(new Dimension(158, 28));
        scrollPane.setBounds(0, 11, 160, 188);
        panel_1.add(scrollPane);

        textArea = new JTextArea();
        textArea.setMinimumSize(new Dimension(158, 22));
        textArea.setPreferredSize(new Dimension(158, 22));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setText("kingRobert 300ml : 1500fc");
        textArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
        scrollPane.setViewportView(textArea);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "alerte", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(255, 0, 0)));
        panel_2.setPreferredSize(new Dimension(150, 160));
        panel.add(panel_2);
        panel_2.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setPreferredSize(new Dimension(24, 28));
        scrollPane_1.setBounds(10, 11, 130, 138);
        panel_2.add(scrollPane_1);

        Alerte = new JTextArea();
        Alerte.setEditable(false);
        scrollPane_1.setViewportView(Alerte);
        Alerte.setFont(new Font("Lucida Handwriting", Font.PLAIN, 10));
        Alerte.setText("0 kingRobert 300ml");

        JButton btnFacture = new JButton("facture");
        btnFacture.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ajouterFacture();
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
            }
        });
        contentPane.add(btnFacture, BorderLayout.SOUTH);

        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon("logo (2).jpg"));
        lblLogo.setBackground(Color.WHITE);
        lblLogo.setOpaque(true);
        lblLogo.setHorizontalTextPosition(SwingConstants.CENTER);
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblLogo, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setOpaque(true);
        tabbedPane.setBackground(new Color(255, 99, 71));
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        List<Facture> l = FactureDAO.ListFacture();
        for (int i = 0; i < l.size(); i++) {
            Facture fa = l.get(i);
            Client cl = new Client();
            cl.setId(fa.getIdclient());
            cl = ClientDAO.retrieveClient(cl);
            tabbedPane.addTab(cl.getNom(), new NFacture(fa.getId()));
        }
        
        t = new Thread(new Retirer());
        System.out.println("Retirer started");
        t.start();
        th = new Thread(new Produit());
        th.start();
        
        setLocationRelativeTo(null);
        setVisible(true);

        if (ProduitDAO.retrieveListeProduit().size() == 0 && FactureDAO.toutFacture().size() == 0) {
            RateDAO.CreateRate(1999);
            NProduit p = new NProduit();
            p.setVisible(true);

            if (ProduitDAO.retrieveListeProduit().size() == 0) {
                System.exit(0);
            }

        }
    }

    private void ajouterFacture() {
        NClient n = new NClient();
        n.setVisible(true);
        String s = n.getN();
        if (s.length() > 0) {
            try {
                Client c = new Client();
                c.setNom(s);
                c = ClientDAO.retrieveClient(c);
                Facture f = new Facture();
                f.setDate(Conversion.Aujourdhui());
                f.setIdclient(c.getId());
                FactureDAO.CreateFacture(f);
                NFacture fa = new NFacture(FactureDAO.lastProduit());
                tabbedPane.addTab(s, fa);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void retirer() {
        while (true) {
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                NFacture c = (NFacture) tabbedPane.getComponentAt(i);
                int a=c.Etat();
                if(a==1){
                    System.out.println("Remove tab at:"+i);
                }


            }
        }
    }

    public void Clignoter() {
        while (true) {

            try {
                Alerte.setForeground(Color.red);
                Alerte.setText(ProduitDAO.retListeProduit());
                textArea.setText(ProduitDAO.retTariProduit());
                Thread.sleep(500);
                Alerte.setForeground(Color.green);
                Alerte.setText(ProduitDAO.retListeProduit());
                textArea.setText(ProduitDAO.retTariProduit());
                Thread.sleep(500);
                Alerte.setForeground(Color.blue);
                Alerte.setText(ProduitDAO.retListeProduit());
                textArea.setText(ProduitDAO.retTariProduit());
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class Retirer implements Runnable {

        @Override
        public void run() {
            retirer();
        }

    }

    class Produit implements Runnable {

        @Override
        public void run() {
            try {
                Clignoter();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
