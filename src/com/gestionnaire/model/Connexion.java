/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Connexion {
    private String url="jdbc:sqlite:"+getClass().getProtectionDomain().getCodeSource().getLocation().getFile()+"database.db";
   
    public Connection connect(){
        
                
		Connection conn=null;
		try {
                       
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return conn;
	}
	
	public void client(){
		try {
			Statement st= connect().createStatement();
			st.execute("CREATE TABLE IF NOT EXISTS Client(Id INTEGER NOT NULL,Nom TEXT UNIQUE,PRIMARY KEY(Id AUTOINCREMENT));");
		} catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
	}
	
	public void facture(){
		try {
			Statement st= connect().createStatement();
			st.execute("CREATE TABLE IF NOT EXISTS Facture(id INTEGER NOT NULL, Date TEXT NOT NULL,Etat INTEGER DEFAULT 1,IdClient INTEGER,PRIMARY KEY(id));");
		} catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
	}
	
	public void paiement(){
		try {
			Statement st= connect().createStatement();
			st.execute("CREATE TABLE IF NOT EXISTS Paiement(id INTEGER NOT NULL, Date TEXT NOT NULL, Montant NUMERIC,idFacture INTEGER NOT NULL,PRIMARY KEY(id AUTOINCREMENT));");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void produit(){
		try {
			Statement st= connect().createStatement();
			st.execute("CREATE TABLE IF NOT EXISTS Produit(id INTEGER NOT NULL,nom TEXT NOT NULL, volume INTEGER NOT NULL, Qte INTEGER,PUnit INTEGER,Qtp INTEGER,Qtc INTEGER,Pic BLOB,UNIQUE(nom,volume),PRIMARY KEY(id AUTOINCREMENT));");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void ravit(){
		try {
			Statement st= connect().createStatement();
			st.execute("CREATE TABLE IF NOT EXISTS Ravit(id INTEGER NOT NULL, date TEXT NOT NULL, Produit TEXT NOT NULL,volume INTEGER,qte INTEGER,PRIMARY KEY(id AUTOINCREMENT));");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void service(){
		try {
			Statement st= connect().createStatement();
			st.execute("CREATE TABLE IF NOT EXISTS Service(id INTEGER NOT NULL,Produit TEXT, Volume INTEGER, PU INTEGER, Qte INTEGER, idFacture INTEGER, PRIMARY KEY(id AUTOINCREMENT))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void taux(){
		try {
			Statement st= connect().createStatement();
			st.execute("CREATE TABLE IF NOT EXISTS Taux(Rate INTEGER NOT NULL);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
    
}
