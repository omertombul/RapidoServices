package inm5001.rapidoservices;

import inm5001.rapidoservices.service.TypeServices;

/**
 * Created by Francis Bernier on 2016-11-10.
 */

public class Recherche {
    private String nomUtilisateur;
    private TypeServices service;

    public Recherche(String nomUtilisateur, TypeServices service){
        this.nomUtilisateur = nomUtilisateur;
        this.service = service;
    }

    public String getNomUtilisateur(){ return nomUtilisateur; }
    public TypeServices getService(){ return service; }
    public void setNomUtilisateur(String nomUtilisateur){ this.nomUtilisateur = nomUtilisateur; }
    public void setService(TypeServices service){ this.service = service; }
}
