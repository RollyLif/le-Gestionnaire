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
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author User
 */
public class ProduitDAO {
    public static void CreateProduit(Produit produit) throws SQLException{
		PreparedStatement p= new Connexion().connect().prepareStatement("INSERT INTO PRODUIT(Nom,Volume,Qte,PUnit,Qtp,Qtc) VALUES (?,?,?,?,?,?)");
		p.setString(1, produit.getNom());
		p.setInt(2, produit.getVolume());
		p.setInt(3, produit.getQte());
		p.setInt(4, produit.getPUnit());
		p.setInt(5, produit.getQtp());
		p.setInt(6, produit.getQtc());
		p.executeUpdate();
	}
	
	public static void UpdateProduit(Produit produit1, Produit produit2) throws SQLException{
		PreparedStatement p= new Connexion().connect().prepareStatement("UPDATE PRODUIT SET Nom=? , Volume=? , Qte=? , PUnit=? , Qtp=? , Qtc=?  WHERE Nom=? AND Volume=?");
		p.setString(1, produit2.getNom());
		p.setInt(2, produit2.getVolume());
		p.setInt(3, produit2.getQte());
		p.setInt(4, produit2.getPUnit());
		p.setInt(5, produit2.getQtp());
		p.setInt(6, produit2.getQtc());
		p.setString(7, produit1.getNom());
		p.setInt(8, produit1.getVolume());
		p.executeUpdate();
		
	}
	
	public static void deleteProduit(Produit produit) throws SQLException{
		PreparedStatement p= new Connexion().connect().prepareStatement("DELETE FROM PRODUIT WHERE Nom=? AND Volume=?");
		p.setString(1, produit.getNom());
		p.setInt(2, produit.getVolume());
		p.executeUpdate();
		
	}
	
	public static Produit retrieveProduit(Produit produit) throws SQLException{
		Produit pro=new Produit();
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM PRODUIT WHERE Nom = '"+produit.getNom()+"' AND Volume = "+produit.getVolume()+";");
		while(rs.next()){
			pro.setId(rs.getInt("Id"));
			pro.setNom(rs.getString("Nom"));
			pro.setPUnit(rs.getInt("PUnit"));
			pro.setQte(rs.getInt("Qte"));
			pro.setVolume(rs.getInt("Volume"));
			pro.setQtc(rs.getInt("Qtc"));
			pro.setQtp(rs.getInt("Qtp"));
		}
		
		return pro;
	}
	
	public static DefaultListModel<Produit> retrieveListeProduit() throws SQLException{
		DefaultListModel<Produit> l= new DefaultListModel<Produit>();
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM PRODUIT WHERE QTE>0 ORDER BY NOM ;");
		while(rs.next()){
			Produit pro=new Produit();
			pro.setId(rs.getInt("Id"));
			pro.setNom(rs.getString("Nom"));
			pro.setPUnit(rs.getInt("PUnit"));
			pro.setQte(rs.getInt("Qte"));
			pro.setVolume(rs.getInt("Volume"));
			pro.setQtc(rs.getInt("Qtc"));
			pro.setQtp(rs.getInt("Qtp"));
			l.addElement(pro);
		}
		
		return l;
	}
	
	public static String retListeProduit() throws SQLException{
		String l= "";
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM PRODUIT  WHERE (Qte<=Qtc);");
		while(rs.next()){
			String r= rs.getInt("Qte") +" "+rs.getString("Nom")+" "+rs.getInt("Volume")+"ml";
			l+=r;
		}
		
		return l;
	}
	
	
	public static String retTariProduit() throws SQLException{
		String l= "";
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM PRODUIT  WHERE Qte>0 ORDER BY NOM;");
		while(rs.next()){
			String r= rs.getString("Nom")+" "+rs.getInt("Volume")+"ml :"+rs.getInt("PUnit")+"fc \n";
			l += r;
		}
		return l;
	}
	
	public static TableModel Stock() throws SQLException {
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT  Qte/Qtp as Casiers, Qte%Qtp as Bouteilles ,NOM as Designation, Volume as 'Volume(ml)', PUnit as 'Prix Unitaire' FROM PRODUIT ORDER BY NOM");
		return DbUtils.resultSetToTableModel(rs);	
	}
	
	
	public static TableModel Appro() throws SQLException{
		Statement st=new Connexion().connect().createStatement();
		ResultSet rs=st.executeQuery("SELECT  Qte as Quantite ,NOM as Designation, Volume as 'Volume(ml)', Qtp as 'Bouteilles par box' FROM PRODUIT ORDER BY NOM");
		return DbUtils.resultSetToTableModel(rs);	
	}

}
