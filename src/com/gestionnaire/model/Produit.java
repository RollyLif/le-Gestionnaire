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
public class Produit {
    
    private int id, volume, Qte, PUnit, Qtp, Qtc;
	private String nom="";
	
	public Produit(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getQte() {
		return Qte;
	}

	public void setQte(int qte) {
		Qte = qte;
	}

	public int getPUnit() {
		return PUnit;
	}

	public void setPUnit(int pUnit) {
		PUnit = pUnit;
	}

	public int getQtp() {
		return Qtp;
	}

	public void setQtp(int qtp) {
		Qtp = qtp;
	}

	public int getQtc() {
		return Qtc;
	}

	public void setQtc(int qtc) {
		Qtc = qtc;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String toString(){
		return nom+" "+volume+"ml";
	}
	
    
}
