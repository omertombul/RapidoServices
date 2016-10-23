package inm5001.rapidoservices.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public abstract class AbstraiteServices {
	private String nomSservice;
 	private byte disponible;
 	private String ville;
 	private byte cote;
 	private String noTelephone;
 	private String courriel;
 	private String description;

    public AbstraiteServices( String nomSservice, byte disponible, String ville, byte cote, String noTelephone, String courriel, String description ) {
        this.nomSservice = nomSservice;
        this.disponible = disponible;
        this.ville = ville;
        this.cote = cote;
        this.noTelephone = noTelephone;
        this.courriel = courriel;
        this.description = description;
    }

    public String getNomSservice() {
        return nomSservice;
    }

    public void setNomSservice( String nomSservice ) {
        this.nomSservice = nomSservice;
    }

    public byte isDisponible() {
        return disponible;
    }

    public void setDisponible( byte disponible ) {
        this.disponible = disponible;
    }

    public String getVille() {
        return ville;
    }

    public void setVille( String ville ) {
        this.ville = ville;
    }

    public byte getCote() {
        return cote;
    }

    public void setCote( byte cote ) {
        this.cote = cote;
    }

    public String getNoTelephone() {
        return noTelephone;
    }

    public void setNoTelephone( String noTelephone ) {
        this.noTelephone = noTelephone;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel( String courriel ) {
        this.courriel = courriel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }
}