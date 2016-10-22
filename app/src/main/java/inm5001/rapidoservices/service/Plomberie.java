package inm5001.rapidoservices.service;

/**
 * Created by Francis Bernier on 2016-10-10.
 */

public class Plomberie extends AbstraiteServices{

    private final String nomSservice;
    private float tauxHorraire;
    private float prixFixe;

    public Plomberie(float tauxHorraire, float prixFixe, boolean disponible, String ville, byte cote, String noTelephone,
                     String courriel, String description ) {
        super( disponible, ville, cote, noTelephone, courriel, description );
        this.nomSservice = "Plomberie";
        this.tauxHorraire = tauxHorraire;
        this.prixFixe = prixFixe;
    }

    public String getNomSservice() {
        return nomSservice;
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