CREATE VIEW v_ville_province AS 
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

CREATE VIEW v_point_interet_ville AS 
    SELECT
        piv.id_ville,
        piv.id_point_interet,
        pi.libelle 
    FROM point_interet_ville AS piv 
    JOIN point_interet AS pi 
    ON piv.id_point_interet = pi.id_point_interet
;



