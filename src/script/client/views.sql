CREATE OR REPLACE VIEW v_ville_province AS 
    SELECT 
        v.id_ville,
        v.nom_ville,
        reg.id_region,
        reg.nom_region,
        pro.id_province,
        pro.nom_province 
    FROM ville AS v 
    JOIN region AS reg 
    ON v.id_region = reg.id_region
    JOIN province AS pro 
    ON reg.id_province = pro.id_province
;

CREATE OR REPLACE VIEW v_point_interet_ville AS 
    SELECT
        piv.id_ville,
        piv.id_point_interet,
        pi.libelle 
    FROM point_interet_ville AS piv 
    JOIN point_interet AS pi 
    ON piv.id_point_interet = pi.id_point_interet
;

CREATE OR REPLACE VIEW v_point_interet_province AS 
    SELECT 
        piv.id_point_interet,
        piv.libelle,
        vp.id_province
    FROM v_point_interet_ville AS piv 
    JOIN v_ville_province AS vp 
    ON piv.id_ville = vp.id_ville
;

CREATE OR REPLACE VIEW v_evaluation_hotel AS   
    SELECT 
        h.id_hotel,AVG(evaluation) AS evaluation 
    FROM evaluation AS e
    JOIN hotel AS h
    ON e.id_partenaire=h.id_hotel
    GROUP BY h.id_hotel
;

CREATE OR REPLACE VIEW v_liste_evaluation_hotel AS 
    SELECT 
        * 
        FROM evaluation 
        WHERE id_categorie_evaluation = 'CEV1'
;

CREATE OR REPLACE VIEW v_liste_evaluation_pack AS 
    SELECT 
        * 
        FROM evaluation 
        WHERE id_categorie_evaluation = 'CEV2'
;

CREATE OR REPLACE VIEW v_liste_evaluation_transport AS 
    SELECT 
        * 
        FROM evaluation 
        WHERE id_categorie_evaluation = 'CEV3'
;

CREATE OR REPLACE VIEW v_liste_evaluation_attraction AS 
    SELECT 
        * 
        FROM evaluation 
        WHERE id_categorie_evaluation = 'CEV4'
;

CREATE OR REPLACE VIEW v_liste_evaluation_evenement AS 
    SELECT 
        * 
        FROM evaluation 
        WHERE id_categorie_evaluation = 'CEV5'
;

CREATE OR REPLACE VIEW v_evaluation_attraction AS   
    SELECT 
        a.id_attraction,AVG(evaluation) AS evaluation 
    FROM v_liste_evaluation_attraction AS e
    JOIN attraction AS a
    ON e.id_partenaire=a.id_attraction
    GROUP BY a.id_attraction
;

CREATE OR REPLACE VIEW v_ranking_hotel AS 
    SELECT 
        h.*,
        COALESCE(eh.evaluation,0) AS evaluation
    FROM v_evaluation_hotel AS eh 
    RIGHT JOIN hotel AS h 
    ON eh.id_hotel = h.id_hotel
    ORDER BY evaluation DESC
;

CREATE OR REPLACE VIEW v_ranking_attraction AS 
    SELECT 
        a.*,
        COALESCE(ea.evaluation,0) AS evaluation
    FROM v_evaluation_attraction AS ea
    RIGHT JOIN attraction AS a 
    ON ea.id_attraction = a.id_attraction
    ORDER BY evaluation DESC
;

CREATE OR REPLACE VIEW v_ranking_hotel_province AS 
    SELECT 
        rh.*,
        vp.id_province
    FROM v_ranking_hotel AS rh 
    JOIN v_ville_province AS vp 
    ON rh.id_ville = vp.id_ville
    ORDER BY 
        id_province ASC,
        evaluation DESC
;

CREATE OR REPLACE VIEW v_ranking_attraction_province AS 
    SELECT 
        ra.*,
        vp.id_province
    FROM v_ranking_attraction AS ra
    JOIN v_ville_province AS vp 
    ON ra.id_ville = vp.id_ville
    ORDER BY 
        id_province ASC,
        evaluation DESC
;



