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
import javax.swing.DefaultListModel;

/**
 *
 * @author User
 */
public class ClientDAO {
    
    public static void Createclient(Client client) throws SQLException{
		PreparedStatement p= new Connexion().connect().prepareStatement("INSERT INTO CLIENT(Nom) VALUES (?)");
		p.setString(1, client.getNom());
		p.executeUpdate();
	}
	
	public static void Updateclient(Client client1, Client client2) throws SQLException{
		PreparedStatement p= new Connexion().connect().prepareStatement("UPDATE CLIENT SET Nom=? WHERE Nom=?");
		p.setString(1, client2.getNom());
		p.setString(2, client1.getNom());
		p.executeUpdate();
		
	}
	
	public static void deleteClient(Client client) throws SQLException{
		PreparedStatement p= new Connexion().connect().prepareStatement("DELETE FROM CLIENT WHERE Nom=?");
		p.setString(1, client.getNom());
		p.executeUpdate();
		
	}
	
	public static Client retrieveClient(Client client) throws SQLException{
		Client c=new Client();
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT id, nom FROM CLIENT WHERE Nom like '"+client.getNom()+"' or id="+client.getId()+";");
		while(rs.next()){
			c.setId(rs.getInt("Id"));
			c.setNom(rs.getString("Nom"));
		}
		
		return c;
	}
	
	public static DefaultListModel<Client> retrieveListeClient(String nom) throws SQLException{
		DefaultListModel<Client> l= new DefaultListModel<Client>();
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM CLIENT WHERE NOM LIKE '"+nom+"%' ;");
		while(rs.next()){
			Client pro=new Client();
			pro.setId(rs.getInt("Id"));
			pro.setNom(rs.getString("Nom"));
			l.addElement(pro);
		}
		
		return l;
	}
	
    
}
