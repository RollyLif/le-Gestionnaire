/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.controller;

import com.gestionnaire.model.Produit;
import com.gestionnaire.model.ProduitDAO;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class Conversion {
    
    public static String Aujourdhui(){
		DateFormat m=DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String a=m.format(new Date());
		return a.substring(0, a.length()-13).trim();
	}
	
	public static int text2Chiffre(String chiffre){
		int c=Integer.valueOf(chiffre).intValue();
		return c;
	}
	
	public static Produit selectedProduit(String mot) throws SQLException{
		Produit cl=new Produit();
		String [] m=mot.substring(0, mot.length()-2).split(" ");
		String n=" ";
		for(int i=0;i<m.length-1;i++){
			n+=m[i]+ " ";
		}
		cl.setNom(n.trim());
		cl.setVolume(text2Chiffre(m[m.length-1]));
		cl=ProduitDAO.retrieveProduit(cl);
		
		return cl;
		
	}
	
	public static int box2bouteille(int quantite, Produit pro){
		return quantite*pro.getQtp();
	}
    
}
