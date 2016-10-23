/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inm5001.rapidoservices.service;

/**
 *
 * @author omer
 */
public class TypeServices extends AbstraiteServices{
        
    private float tauxHorraire;
    private float prixFixe;

    public TypeServices(float tauxHorraire, float prixFixe, String nomSservice, byte disponible, String ville, byte cote, String noTelephone, String courriel, String description ) {
        super( nomSservice, disponible, ville, cote, noTelephone, courriel, description );
        this.tauxHorraire = tauxHorraire;
        this.prixFixe = prixFixe;
    }

    public float getTauxHorraire() {
        return tauxHorraire;
    }

    public void setTauxHorraire( float tauxHorraire ) {
        this.tauxHorraire = tauxHorraire;
    }

    public float getPrixFixe() {
        return prixFixe;
    }

    public void setPrixFixe( float prixFixe ) {
        this.prixFixe = prixFixe;
    }
}
