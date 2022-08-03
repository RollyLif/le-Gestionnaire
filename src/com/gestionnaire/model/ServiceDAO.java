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
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author User
 */
public class ServiceDAO {
    
    public static void createService(Service service) throws SQLException{
		PreparedStatement p= new Connexion().connect().prepareStatement("INSERT INTO Service(Produit, Volume, PU, Qte, idFacture) VALUES (?,?,?,?,?)");
		p.setString(1, service.getDesignation());
		p.setInt(2, service.getVol());
		p.setInt(3, service.getPU());
		p.setInt(4, service.getQte());
		p.setInt(5, service.getIdFacture());
		p.executeUpdate();
	}
	
	public static void deleteService(int  reference) throws SQLException{
		PreparedStatement p= new Connexion().connect().prepareStatement("DELETE FROM SERVICE WHERE Id=?");
		p.setInt(1, reference);
		p.executeUpdate();
		
	}
	
	public static int Addition(int IdFacture) throws SQLException{
		int a=0;
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT Sum(PU*Qte) as 'Prix' FROM SERVICE WHERE idFacture="+IdFacture+";");
		while(rs.next()){
			a=rs.getInt("Prix");
		}
		return a;
		
		
	}
	
	public static TableModel Facture (int IdFacture) throws SQLException{
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT  Qte as 'Qte',Produit as Designation, Volume , PU as 'Prix unitaire', (PU*Qte) as 'Prix total', Id as Reference FROM SERVICE WHERE idFacture="+IdFacture+";");
		return DbUtils.resultSetToTableModel(rs);
		
		
	}
    
}
