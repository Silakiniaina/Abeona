CREATE OR REPLACE VIEW v_point_interet_province AS 
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