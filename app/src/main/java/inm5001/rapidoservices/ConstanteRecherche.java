package inm5001.rapidoservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Francis Bernier on 2016-11-20.
 */

public interface ConstanteRecherche {
    String MESSAGE_MODE_TRI_INTROUVABLE = "Le mode de tri sélectionné est introuvable.";

    String[] trie =new String[]{"tauxHorraire","prixFixe","nomService","ville","coteUtilisateur","coteServiceMoyenne","coteService"};

    List<String> typeDeTrie = (Arrays.asList(trie));



}
