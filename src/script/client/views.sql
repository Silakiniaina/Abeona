CREATE VIEW v_point_interet_province AS 
    SELECT 
        pip.id_province,
        pip.id_point_interet ,
        pi.libelle AS libelle_point_interet
        FROM point_interet_province AS pip 
        JOIN province AS p 
        ON pip.id_province = p.id_province
        JOIN point_interet AS pi 
        ON pip.id_point_interet = pi.id_point_interet
    ;

CREATE  VIEW v_evenement_calendrier AS 
    SELECT 
        id_evenement, 
        nom_evenement, 
        date_evenement, 
        description_evenement, 
        v.nom_ville AS ville, 
        COALESCE(CAST(id_hotel AS VARCHAR), 'NULL') AS id_hotel 
    FROM evenement AS e
    JOIN ville AS v 
    ON e.id_ville = v.id_ville
    WHERE id_hotel IS NULL
;

CREATE VIEW v_evenement_calendrier_passe AS 
    SELECT 
        * 
    FROM v_evenement_calendrier 
    WHERE date_evenement < NOW()
;

CREATE VIEW v_evenement_calendrier_futur AS 
    SELECT 
        * 
    FROM v_evenement_calendrier 
    WHERE date_evenement > NOW()
;

CREATE VIEW v_evenement_calendrier_en_cours AS 
    SELECT 
        * 
    FROM v_evenement_calendrier 
    WHERE date_evenement = NOW()
;