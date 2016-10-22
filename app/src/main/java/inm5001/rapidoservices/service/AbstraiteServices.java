package inm5001.rapidoservices.service;

/**
 * Created by Francis Bernier on 2016-10-10.
 */

public abstract class AbstraiteServices {
    private boolean disponible;
    private String ville;
    private byte cote;
    private String noTelephone;
    private String courriel;
    private String description;

    public AbstraiteServices( boolean disponible, String ville, byte cote, String noTelephone,
                              String courriel, String description ) {
        this.disponible = disponible;
        this.ville = ville;
        this.cote = cote;
        this.noTelephone = noTelephone;
        this.courriel = courriel;
        this.description = description;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible( boolean disponible ) {
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