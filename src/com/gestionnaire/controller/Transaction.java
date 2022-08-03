/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.controller;

import com.gestionnaire.model.Facture;
import com.gestionnaire.model.Produit;
import com.gestionnaire.model.ProduitDAO;
import com.gestionnaire.model.Service;
import com.gestionnaire.model.ServiceDAO;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class Transaction {
    
    public static void servir(int quantite,Produit pro, Facture f) throws SQLException{
		if(quantite<=pro.getQte()){
			
			Service s=new Service();
			s.setDesignation(pro.getNom());
			s.setVol(pro.getVolume());
			s.setQte(quantite);
			s.setPU(pro.getPUnit());
			s.setIdFacture(f.getId());
			ServiceDAO.createService(s);
			Produit p= pro;
			p.setQte(p.getQte()-quantite);
			ProduitDAO.UpdateProduit(pro, p);
		}
		
	}
	
	public static void debarasser(Service s) throws SQLException{
		Produit p=new Produit();
		p.setNom(s.getDesignation());
		p.setVolume(s.getVol());
		Produit po=ProduitDAO.retrieveProduit(p);
		p=po;
		po.setQte(po.getQte()+s.getQte());
		ProduitDAO.UpdateProduit(p, po);
		ServiceDAO.deleteService(s.getId());
	}
	
	public static void approvisionner(Produit p, int quantite) throws SQLException{
		Produit po=p;
		p.setQte(p.getQte()+quantite);
		ProduitDAO.UpdateProduit(po, p);
	}
    
}
