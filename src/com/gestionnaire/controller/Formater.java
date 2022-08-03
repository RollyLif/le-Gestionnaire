/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.controller;

import com.gestionnaire.model.Client;
import com.gestionnaire.model.ClientDAO;

/**
 *
 * @author User
 */
public class Formater {
    public static String Nom(String nom) throws Exception{
		String n="";
		String n1="";
		Client c=new Client();
		if(nom.isEmpty() || nom==null){
			c.setNom("a");
			ClientDAO.Createclient(c);
			Client c1=ClientDAO.retrieveClient(c);
			n="Client n#"+c1.getId();
			c1.setNom(n);
			ClientDAO.Updateclient(c, c1);
		}else{
			n=Mot(nom);
			n1=n;
			
			if (n.length()>21){
				n=n.substring(0, 19);
			}
			
			c.setNom(n);
			c=ClientDAO.retrieveClient(c);
			n=c.getNom();
			 if(n==null){
				 c.setNom(n1);
				 ClientDAO.Createclient(c);
				 c=ClientDAO.retrieveClient(c);
				 n=c.getNom();
			 }
		}
		
		return n;
	}
	
	public static String Produit(String produit){
		String n= Mot(produit);
		
		if(n.length()>16){
			n=n.substring(0, 15);
		}
		return n;
	}
	
	private static String Mot(String nom){
		String n="";
		String na=nom.toLowerCase().trim();
		String []nb=na.split("[-_ ]");
		for(int i=0;i<nb.length;i++){
			String nc=nb[i].trim();
			String nf="";
			try{
				char [] nd=new char[1];
				nd[0]=nc.charAt(0);
				String ne=new String(nd);
				ne=ne.toUpperCase();
				nf=ne + nc.substring(1)+" ";
			}catch(Exception e){
				e.getMessage();
			}
			n +=nf;
		}
		
		return n.trim();
	}
    
}
