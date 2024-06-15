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

CREATE OR REPLACE VIEW v_ranking_hotel AS 
    SELECT 
        h.*,
        COALESCE(eh.evaluation,0) AS evaluation
    FROM v_evaluation_hotel AS eh 
    RIGHT JOIN hotel AS h 
    ON eh.id_hotel = h.id_hotel
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



