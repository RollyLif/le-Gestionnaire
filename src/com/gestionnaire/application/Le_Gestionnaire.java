/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.application;


import com.gestionnaire.model.Connexion;
import com.gestionnaire.vue.Introduction;
import com.gestionnaire.vue.Principale;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class Le_Gestionnaire {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Connexion Con=new Connexion();
		Introduction in=new Introduction();
		in.setVisible(true);
		Con.connect();
		in.setCharge("base des donnees");
		Thread.sleep(1000);
		in.setValue(20);
		Con.client();
		in.setCharge("des clients");
		Thread.sleep(1000);
		in.setValue(40);
		Con.facture();
		in.setCharge("des factures");
		Thread.sleep(1000);
		in.setValue(60);
		Con.paiement();
		in.setCharge("des paiements");
		Thread.sleep(1000);
		in.setValue(80);
		Con.produit();
		in.setCharge("des produits");
		Thread.sleep(1000);
		Con.ravit();
		in.setCharge("de l'historique d'approvisionnement");
		Thread.sleep(1000);
		in.setValue(100);
		Con.service();
		in.setCharge("des services");
		Thread.sleep(1000);
		Con.taux();
		in.setCharge("du taux d'echange");
		Thread.sleep(1000);
		in.setVisible(false);
		try {
			Principale p=new Principale();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
