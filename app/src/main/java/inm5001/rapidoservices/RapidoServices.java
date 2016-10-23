package inm5001.rapidoservices;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author omer
 */
import java.util.ArrayList;

import inm5001.rapidoservices.utilisateur.*;
import inm5001.rapidoservices.service.*;
import inm5001.rapidoservices.BaseDonnees.*;

public class RapidoServices {
    public static Profile profile;
    public static Identifiant id;
    public static Utilisateur user;
    private static ArrayList<AbstraiteServices> listeServices;
    private static ArrayList<String> listeCompetences;
    public static AbstraiteServices plomberie;
    public static BdApi bdConnect;
    public int a;
    
    
    
     public static void main(String[] args){
         float tauxHorraire = 15.0f;
         float prixFixe = 12.0f;
         byte cote = 4;
         try{
             profile = new Profile("Empror", "Mathieu", "5149999999", "e@e.com" );
             
             id = new Identifiant("usager12", "Passwo_r" );
             //plomberie = new TypeServices(tauxHorraire, prixFixe, null, true, null, cote, null, null, null );
             user = new Utilisateur(id, profile, listeServices, listeCompetences);
             System.out.println("\nConnection BD from test class");
             System.out.println("Test user: " + user.identifiant.nomUtilisateur +  " " + user.profile.nom + "\n");
             
             bdConnect = new BdApi();
             bdConnect.addUser(user);
         }catch(MyException e){
             System.out.println(e);
         }
     }
}
