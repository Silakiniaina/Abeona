package model.shared;

import java.sql.Date;

public class PartenaireUtils {
    
    /* Fonction pour avoir la requete de recherche multicriter a en fonction du nom de table */
    public static String get_recherche_query(String categ_partenaire,String nom, String id_ville, String evaluation, Date dateInsertionDebut,Date dateInsertionFin){
        String result = "SELECT * FROM v_"+categ_partenaire+"_with_evaluation WHERE 1=1";
        if(nom != null) result += " AND LOWER(nom_"+categ_partenaire+") LIKE ?";
        if(id_ville != null) result += " AND id_ville = ?";
        if(dateInsertionDebut != null) result +=" AND DATE(date_insertion) >= ?";
        if(dateInsertionFin!=null) result +=" AND DATE(date_insertion) <= ? ";
        if(evaluation != null) result += " AND evaluation BETWEEN ? AND ?";
        System.out.println(result);
        return result;
    }
}
