/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class FactureDAO {
    
    public static void CreateFacture(Facture facture) throws SQLException{
		PreparedStatement p= new Connexion().connect().prepareStatement("INSERT INTO FACTURE(Date,IdClient) VALUES (?,?)");
		p.setString(1, facture.getDate());
		p.setInt(2, facture.getIdclient());
		p.executeUpdate();
		
	}
	
	public static void deleteFacture(int id) throws SQLException{
		PreparedStatement p= new Connexion().connect().prepareStatement("DELETE FROM FACTURE WHERE ID=?");
		p.setInt(1, id);
		p.executeUpdate();
		
	}
	
	public static void UpdateProduit(Facture f) throws SQLException{
		PreparedStatement p= new Connexion().connect().prepareStatement("UPDATE FACTURE SET Etat=2  WHERE id=?");
		p.setInt(1, f.getId());
		p.executeUpdate();
		
	}
	
	public static int lastProduit() throws SQLException{
		int a=0;
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT MAX(ID) as LAST FROM FACTURE");
		while(rs.next()){
			a=rs.getInt("LAST");
		}
		return a;
	}
	
	public static Facture retrieveFacture(int id) throws SQLException{
		Facture facture=new Facture();
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT id, Date, Etat, IdClient FROM FACTURE WHERE id="+id+";");
		while(rs.next()){
			facture.setId(rs.getInt("Id"));
			facture.setDate(rs.getString("Date"));
			facture.setEtat(rs.getInt("Etat"));
			facture.setIdclient(rs.getInt("IdClient"));
		}
		
		return facture;
	}
	
	public static List<Facture> ListFacture() throws SQLException{
		List<Facture> l=new ArrayList<Facture>();
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT id, Date, Etat, IdClient FROM FACTURE WHERE Etat=1;");
		while(rs.next()){
			Facture facture=new Facture();
			facture.setId(rs.getInt("Id"));
			facture.setDate(rs.getString("Date"));
			facture.setEtat(rs.getInt("Etat"));
			facture.setIdclient(rs.getInt("IdClient"));
			l.add(facture);
		}
		return l;
	}
	
	public static List<Facture> toutFacture() throws SQLException{
		List<Facture> l=new ArrayList<Facture>();
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT id, Date, Etat, IdClient FROM FACTURE;");
		while(rs.next()){
			Facture facture=new Facture();
			facture.setId(rs.getInt("Id"));
			facture.setDate(rs.getString("Date"));
			facture.setEtat(rs.getInt("Etat"));
			facture.setIdclient(rs.getInt("IdClient"));
			l.add(facture);
		}
		return l;
	}
    
}
