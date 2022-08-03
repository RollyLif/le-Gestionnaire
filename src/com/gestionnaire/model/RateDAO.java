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

/**
 *
 * @author User
 */
public class RateDAO {
    
    public static void CreateRate(int taux) throws SQLException{
		PreparedStatement p= new Connexion().connect().prepareStatement("INSERT INTO TAUX(Rate) VALUES (?)");
		p.setInt(1, taux);
		p.executeUpdate();
	}
	
	public static void UpdateRate(int taux){
		try {
			PreparedStatement p = new Connexion().connect().prepareStatement("UPDATE Taux SET Rate="+taux+";");
			p.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}	
	}

	public static int retrieveTaux(){
		int ta=1;

		try {
			Statement st = new Connexion().connect().createStatement();
			ResultSet rs=st.executeQuery("SELECT Rate FROM Taux;");
			while(rs.next()){
				ta=rs.getInt("Rate");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return ta;
	}
}
