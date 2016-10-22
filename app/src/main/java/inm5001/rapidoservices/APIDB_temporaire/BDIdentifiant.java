package inm5001.rapidoservices.APIDB_temporaire;

/**
 * Created by Admin on 2016-10-14.
 */

public class BDIdentifiant {
    public static String GetUtilisateur(String nomUtilisateur) {
        if (nomUtilisateur == "ilExiste") {
            return "nomUtilisateurExiste";
        } else {
            return null;
        }
    }
    /*
    public static Identifiant GetIdentifiant(String nomUtilisateur) throws MyException {
        Identifiant identifiant = new Identifiant(nomUtilisateur, "Allo123!");
        return identifiant;
    }

    public static int PostReplacerIdentifiant(String nomUtilisateurActuel, String nomUtilisateurNouveau) throws MyException {
        Identifiant identifiant = new Identifiant(nomUtilisateurNouveau, "Allo123!");
        return 0;
    }
    */
}
