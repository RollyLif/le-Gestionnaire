/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnaire.model;

/**
 *
 * @author User
 */
public class Service {
    
    private int id, Qte, Vol, idFacture,PU;
	private String designation;
	
	public Service(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQte() {
		return Qte;
	}

	public void setQte(int qte) {
		Qte = qte;
	}

	public int getVol() {
		return Vol;
	}

	public void setVol(int vol) {
		Vol = vol;
	}

	public int getIdFacture() {
		return idFacture;
	}

	public void setIdFacture(int idFacture) {
		this.idFacture = idFacture;
	}

	public int getPU() {
		return PU;
	}

	public void setPU(int pU) {
		PU = pU;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

    
}
