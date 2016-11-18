package inm5001.rapidoservices;

import inm5001.rapidoservices.service.TypeServices;
import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by Francis Bernier on 2016-11-10.
 */

public class Recherche {
    private Utilisateur utilisateur;
    private TypeServices service;

    public Recherche(Utilisateur utilisateur, TypeServices service){
        this.utilisateur = utilisateur;
        this.service = service;
    }

    public Utilisateur getUtilisateur(){ return utilisateur; }
    public TypeServices getService(){ return service; }
    public void setUtilisateur(Utilisateur utilisateur){ this.utilisateur = utilisateur; }
    public void setService(TypeServices service){ this.service = service; }
}
