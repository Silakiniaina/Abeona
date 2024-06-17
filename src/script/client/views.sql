-- VILLE --
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

-- POIN INTERET --
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


-- EVALUTATION --
CREATE OR REPLACE VIEW v_evaluation_hotel AS   
    SELECT 
        h.id_hotel,AVG(evaluation) AS evaluation 
    FROM evaluation AS e
    JOIN hotel AS h
    ON e.id_partenaire=h.id_hotel
    GROUP BY h.id_hotel
;

CREATE OR REPLACE VIEW v_liste_evaluation_attraction AS 
    SELECT 
        * 
        FROM evaluation 
        WHERE id_categorie_evaluation = 'CEV4'
;

CREATE OR REPLACE VIEW v_evaluation_attraction AS   
    SELECT 
        a.id_attraction,AVG(evaluation) AS evaluation 
    FROM v_liste_evaluation_attraction AS e
    JOIN attraction AS a
    ON e.id_partenaire=a.id_attraction
    GROUP BY a.id_attraction
;

CREATE OR REPLACE VIEW v_evaluation_transport AS   
    SELECT 
        t.id_transport,AVG(evaluation) AS evaluation 
    FROM evaluation AS e
    JOIN transport AS t
    ON e.id_partenaire=t.id_transport
    GROUP BY t.id_transport
;

CREATE OR REPLACE VIEW v_evaluation_guide AS   
    SELECT 
        g.id_guide,AVG(evaluation) AS evaluation 
    FROM evaluation AS e
    JOIN guide AS g
    ON e.id_partenaire=g.id_guide
    GROUP BY g.id_guide
;

-- RANKING --
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

-- RANKING PAR PROVINCE --
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

-- EVENEMENT --
CREATE VIEW v_evenement_calendrier AS 
    SELECT 
        * 
    FROM evenement 
    WHERE id_hotel IS NULL
;

CREATE VIEW v_evenement_calendrier_passe AS
    SELECT 
        * 
    FROM v_evenement_calendrier 
    WHERE date_fin_evenement < NOW()
;

CREATE VIEW v_evenement_calendrier_futur AS
    SELECT 
        * 
    FROM v_evenement_calendrier 
    WHERE date_debut_evenement > NOW()
;

CREATE VIEW v_evenement_calendrier_en_cours AS 
    SELECT 
        * 
    FROM v_evenement_calendrier 
    WHERE date_debut_evenement <= NOW() 
    AND date_fin_evenement >= NOW()
;

CREATE VIEW v_commodite_hotel AS 
    SELECT 
        ch.id_hotel,c.id_commodite,c.libelle
    FROM commodite_hotel AS ch
    JOIN commodite  AS c
    ON ch.id_commodite = c.id_commodite
;


SELECT 
    h.*,
    COALESCE(eh.evaluation,0) as evaluation
FROM hotel AS h 
LEFT JOIN v_evaluation_hotel AS eh 
ON h.id_hotel = eh.id_hotel
WHERE
h.id_hotel IN (
        SELECT 
            id_hotel
        FROM v_info_commodite_hotel AS ich 
        WHERE id_commodite = 'COM1'
        OR id_commodite = 'COM2'
        GROUP BY id_hotel
    )
AND evaluation BETWEEN 0 AND 4
AND id_ville = 'VIL1'
OR id_ville = 'VIL2'
;