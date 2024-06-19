-------------------------------------------- Ville d'un province---------------------------------------------------------------
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

-------------------------------------------- Point interet dans une ville ----------------------------------------------------------
CREATE OR REPLACE VIEW v_point_interet_ville AS 
    SELECT
        piv.id_ville,
        piv.id_point_interet,
        pi.libelle 
    FROM point_interet_ville AS piv 
    JOIN point_interet AS pi 
    ON piv.id_point_interet = pi.id_point_interet
;

------------------------------------------- Point interet par province -------------------------------------------------------------
CREATE OR REPLACE VIEW v_point_interet_province AS 
    SELECT 
        piv.id_point_interet,
        piv.libelle,
        vp.id_province
    FROM v_point_interet_ville AS piv 
    JOIN v_ville_province AS vp 
    ON piv.id_ville = vp.id_ville
;

------------------------------------------ Evaluation des hotel --------------------------------------------------------------------
CREATE OR REPLACE VIEW v_evaluation_hotel AS   
    SELECT 
        h.*,
        COALESCE(AVG(evaluation),0) AS evaluation 
    FROM evaluation AS e
    RIGHT JOIN hotel AS h
    ON e.id_partenaire=h.id_hotel
    GROUP BY h.id_hotel
    ORDER BY evaluation DESC
;

----------------------------------------- Liste des attractions attractions avec evaluation ------------------------------------
CREATE OR REPLACE VIEW v_evaluation_attraction AS   
    SELECT 
        a.*,
        COALESCE(AVG(evaluation),0)  AS evaluation
    FROM evaluation AS e
    RIGHT JOIN attraction AS a
    ON e.id_partenaire=a.id_attraction
    GROUP BY a.id_attraction
    ORDER BY evaluation DESC
;

----------------------------------------- Liste des transport avec evaluation --------------------------------------------------- 
CREATE OR REPLACE VIEW v_evaluation_transport AS   
    SELECT 
        t.*,
        COALESCE(AVG(evaluation),0)  AS evaluation
    FROM evaluation AS e
    RIGHT JOIN transport AS t
    ON e.id_partenaire=t.id_transport
    GROUP BY t.id_transport
    ORDER BY evaluation DESC
;

------------------------------------------- Liste des guides avec evaluation -------------------------------------------------------
CREATE OR REPLACE VIEW v_evaluation_guide AS   
    SELECT 
        g.*,
        COALESCE(AVG(evaluation),0)  AS evaluation
    FROM evaluation AS e
    RIGHT JOIN guide AS g
    ON e.id_partenaire=g.id_guide
    GROUP BY g.id_guide
    ORDER BY evaluation DESC
;

----------------------------------------- Ranking des hotel par province ------------------------------------------------------------
CREATE OR REPLACE VIEW v_ranking_hotel_province AS 
    SELECT 
        rh.*,
        vp.id_province
    FROM v_evaluation_hotel AS rh 
    JOIN v_ville_province AS vp 
    ON rh.id_ville = vp.id_ville
    ORDER BY 
        id_province ASC,
        evaluation DESC
;

----------------------------------------- Ranking des attraction par province ------------------------------------------------------------
CREATE OR REPLACE VIEW v_ranking_attraction_province AS 
    SELECT 
        ra.*,
        vp.id_province
    FROM v_evaluation_attraction AS ra
    JOIN v_ville_province AS vp 
    ON ra.id_ville = vp.id_ville
    ORDER BY 
        id_province ASC,
        evaluation DESC
;

---------------------------------------- Liste des evenements du calendrier -------------------------------------------------------
CREATE VIEW v_evenement_calendrier AS 
    SELECT 
        id_evenement AS id,
        titre_evenement AS titre ,
        description,
        lieu_evenement as lieu,
        date_debut_evenement AS date_debut,
        date_fin_evenement AS date_fin, 
        id_hotel,
        id_ville as ville, 
        id_categorie_evenement as categorie ,
        date_insertion as insertion
    FROM evenement 
    WHERE id_hotel IS NULL
    ORDER BY date_debut_evenement DESC
;

---------------------------------------- Liste des evenements hotel ----------------------------------------------------------------
CREATE VIEW v_evenement_hotel AS 
    SELECT 
        * 
    FROM evenement 
    WHERE id_hotel IS NOT NULL
;

--------------------------------------- Liste de tous les evenements --------------------------------------------------------------
CREATE VIEW v_evenement_par_annee AS 
    SELECT
        *,
        DATE_PART('year',date_debut_evenement) AS annee
    FROM evenement
    GROUP BY 
        DATE_PART('year',date_debut_evenement),
        id_evenement
    ORDER BY 
        annee DESC
;

--------------------------------------- Nombre des evenements : total, cet annee , cet mois et aujourdhui---------------------------
CREATE OR REPLACE VIEW v_nombre_evenement AS
    SELECT 
        vnt.total AS total,
        vna.annee AS cet_annee,
        vnm.mois AS cet_mois,
        vnj.jour AS aujourdhui
    FROM 
    ( SELECT COUNT(*) as mois FROM evenement WHERE DATE_TRUNC('month', date_debut_evenement) = DATE_TRUNC('month', CURRENT_DATE) OR DATE_TRUNC('month', date_fin_evenement) = DATE_TRUNC('month', CURRENT_DATE)) AS vnm,
    ( SELECT COUNT(*) as annee FROM evenement WHERE DATE_PART('year', date_debut_evenement) = DATE_PART('year', CURRENT_DATE) OR DATE_PART('year', date_fin_evenement) = DATE_PART('year', CURRENT_DATE)) AS vna,
    ( SELECT COUNT(*) as total FROM evenement) AS vnt,
    ( SELECT COUNT(*) as jour FROM evenement WHERE date_debut_evenement = CURRENT_DATE OR date_fin_evenement = CURRENT_DATE) AS vnj
;

-------------------------------------- Liste des utilisateurs par annee ------------------------------------------------------------
CREATE VIEW v_utilisateur_par_annee AS 
    SELECT 
        DATE_PART('year',date_insertion) AS annee,
        DATE_PART('month',date_insertion) AS month,
        * 
    FROM utilisateur
    GROUP BY 
        DATE_PART('year', date_insertion),
        DATE_PART('month', date_insertion),
        id_utilisateur
    ORDER BY annee DESC
;

--------------------------------------- Nombre d'utilisateur : total, cet annee, cet mois ------------------------------------------
CREATE OR REPLACE VIEW v_nombre_utilisateur AS
    SELECT 
        total,
        cet_annee,
        cet_mois
    FROM 
        ( SELECT sum(nombre) as cet_annee FROM v_statistique_nombre_utilisateur WHERE annee = DATE_PART('year',CURRENT_DATE) ),
        ( SELECT sum(nombre) as cet_mois FROM v_statistique_nombre_utilisateur WHERE mois = DATE_PART('month',CURRENT_DATE)),
        ( SELECT sum(nombre) as total FROM v_statistique_nombre_utilisateur )
;

------------------------------------- Liste des commodites des hotels -----------------------------------------------------------
CREATE VIEW v_commodite_hotel AS 
    SELECT 
        ch.id_hotel,c.id_commodite,c.libelle
    FROM commodite_hotel AS ch
    JOIN commodite  AS c
    ON ch.id_commodite = c.id_commodite
;

------------------------------------- Liste des comptes partenaires ayant des compte valides ------------------------------------
CREATE VIEW v_partenaire_active AS 
    SELECT 
        * 
    FROM partenaire 
    WHERE status = 1 
;

-------------------------------------- Nombre des partenaires total -----------------------------------------------------------
CREATE OR REPLACE VIEW v_nombre_partenaire_total AS
    SELECT 
        sum(n) AS total
    FROM(
        SELECT 
            COUNT(*) as n 
        FROM hotel
        UNION ALL
        SELECT 
            COUNT(*) as h
        FROM transport
        UNION ALL 
        SELECT 
            COUNT(*) as g
        FROM guide
    )
;

------------------------------------ Nombre des partenaires : Total, cet annee, cet mois et aujourdhui
CREATE OR REPLACE VIEW v_nombre_partenaire AS
    SELECT 
        vnt.total AS total,
        vna.annee AS cet_annee,
        vnm.mois AS cet_mois,
        vnj.jours AS jours
    FROM 
        ( SELECT sum(n) AS total FROM( SELECT COUNT(*) as n FROM hotel UNION ALL SELECT COUNT(*) as t FROM transport UNION ALL SELECT COUNT(*) as g FROM guide)) AS vnt,
        ( SELECT sum(n) AS annee FROM( SELECT COUNT(*) as n FROM hotel UNION ALL SELECT COUNT(*) as t FROM transport UNION ALL SELECT COUNT(*) as g FROM guide WHERE DATE_PART('year', date_insertion) = DATE_PART('year', CURRENT_DATE))) AS vna,
        ( SELECT sum(n) AS mois FROM( SELECT COUNT(*) as n FROM hotel UNION ALL SELECT COUNT(*) as t FROM transport UNION ALL SELECT COUNT(*) as g FROM guide WHERE DATE_TRUNC('month', date_insertion) = DATE_TRUNC('month', CURRENT_DATE))) AS vnm,
        ( SELECT sum(n) AS jours FROM( SELECT COUNT(*) as n FROM hotel UNION ALL SELECT COUNT(*) as t FROM transport UNION ALL SELECT COUNT(*) as g FROM guide  WHERE DATE(date_insertion) = CURRENT_DATE)) AS vnj
;

-- Benefice --
CREATE OR REPLACE VIEW v_benefice AS
    SELECT 
        DATE_PART('YEAR', date_debut_reservation) AS annee,
        DATE_PART('MONTH', date_debut_reservation) AS mois,
        id_categorie_reservation,
        ((SELECT CAST(pourcentage AS NUMERIC) FROM benefice WHERE id_benefice = 'BEN1') / 100) * SUM(prix_unitaire) AS total_prix
    FROM 
        Reservation as r
    GROUP BY 
        id_categorie_reservation, mois, annee
;

 ------------------------------------- MOIS --------------------------------------------------------------------------------------
CREATE VIEW Mois AS
WITH RECURSIVE MoisRec AS (
    SELECT 1 AS numero, 'Janvier' AS nom
    UNION ALL
    SELECT numero + 1, 
        CASE numero + 1
            WHEN 2 THEN 'Février'
            WHEN 3 THEN 'Mars'
            WHEN 4 THEN 'Avril'
            WHEN 5 THEN 'Mai'
            WHEN 6 THEN 'Juin'
            WHEN 7 THEN 'Juillet'
            WHEN 8 THEN 'Août'
            WHEN 9 THEN 'Septembre'
            WHEN 10 THEN 'Octobre'
            WHEN 11 THEN 'Novembre'
            WHEN 12 THEN 'Décembre'
        END
    FROM MoisRec
    WHERE numero < 12
)
SELECT numero AS mois_num, nom AS mois_nom
FROM MoisRec;


--------------------------------------------Annee et Mois----------------------------------------------------------------------
CREATE OR REPLACE VIEW V_annees_et_mois AS
WITH Years AS (
    SELECT DISTINCT EXTRACT(YEAR FROM date_insertion) AS annee FROM Utilisateur
    UNION
    SELECT DISTINCT EXTRACT(YEAR FROM date_achat) AS annee FROM achat_pack
),
Months AS (
    SELECT Mois.mois_num, Mois.mois_nom FROM Mois
)
SELECT
    y.annee,
    m.mois_num,
    m.mois_nom
FROM
    Years y
CROSS JOIN
    Months m;

--------------------------------------------Nombre de Client par an-------------------------------------------------------------

CREATE OR REPLACE VIEW V_evolution_nombreclients AS
SELECT
    vm.mois_nom AS mois,
    COALESCE(COUNT(u.id_utilisateur), 0) AS nombre_clients,
    vm.annee,
    ARRAY_AGG(vm.mois_num ORDER BY vm.mois_num) AS mois_num_array
FROM
    V_annees_et_mois vm
LEFT JOIN
    Utilisateur u ON vm.mois_num = EXTRACT(MONTH FROM u.date_insertion)
    AND vm.annee = EXTRACT(YEAR FROM u.date_insertion)
GROUP BY
    vm.mois_nom, vm.annee
ORDER BY
    vm.annee, mois_num_array;



SELECT * FROM V_evolution_nombreclients WHERE annee = '2024';

--------------------------------------------Nombre de Client ayant achetes les pack par mois-------------------------------------------------------------

CREATE OR REPLACE VIEW V_evolution_nombre_client_ayant_acheter_pack AS
SELECT
    vm.mois_nom AS mois,
    COALESCE(COUNT(DISTINCT ap.id_utilisateur), 0) AS nombre_clients_ayant_achete_pack,
    vm.annee,
    ARRAY_AGG(vm.mois_num ORDER BY vm.mois_num) AS mois_num_array
FROM
    V_annees_et_mois vm
LEFT JOIN
    achat_pack ap ON vm.mois_num = EXTRACT(MONTH FROM ap.date_achat)
    AND vm.annee = EXTRACT(YEAR FROM ap.date_achat)
GROUP BY
    vm.mois_nom, vm.annee
ORDER BY
    vm.annee, mois_num_array;

SELECT * FROM V_evolution_nombre_client_ayant_acheter_pack WHERE annee = '2024';

--------------------------------------------Revenue mensuel-------------------------------------------------------------

CREATE OR REPLACE VIEW V_evolution_revenu_mensuel AS
SELECT
    vm.mois_nom AS mois,
    SUM(COALESCE(ec.nombre_clients, 0)) AS nombre_clients,
    SUM(COALESCE(eap.nombre_clients_ayant_achete_pack, 0)) AS nombre_clients_ayant_achete_pack,
    vm.annee,
    ARRAY_AGG(vm.mois_num ORDER BY vm.mois_num) AS mois_num_array,
    COALESCE(SUM(ap.prix), 0) AS revenu_mensuel
FROM
    V_annees_et_mois vm
LEFT JOIN
    V_evolution_nombreclients ec ON vm.mois_nom = ec.mois
    AND vm.annee = ec.annee
LEFT JOIN
    V_evolution_nombre_client_ayant_acheter_pack eap ON vm.mois_nom = eap.mois
    AND vm.annee = eap.annee
LEFT JOIN
    achat_pack ap ON vm.mois_num = EXTRACT(MONTH FROM ap.date_achat)
    AND vm.annee = EXTRACT(YEAR FROM ap.date_achat)
GROUP BY
    vm.mois_nom, vm.annee
ORDER BY
    vm.annee, mois_num_array;


---------------------------------------------Total Revenue et Moyenne By Year--------------------------------------------------------------
CREATE OR REPLACE VIEW V_total_moyenne_revenu_mensuel AS
SELECT
    vm.annee,
    COALESCE(SUM(revenu_mensuel), 0) AS total_revenu_mensuel,
    COALESCE(AVG(revenu_mensuel), 0) AS moyenne_revenu_mensuel
FROM
    V_evolution_revenu_mensuel vm
GROUP BY
    vm.annee
ORDER BY
    vm.annee;


---------------------------------------------Total Revenue et Moyenne By Year--------------------------------------------------------------
CREATE OR REPLACE VIEW V_total_moyenne_revenu AS
SELECT
    COALESCE(SUM(revenu_mensuel), 0) AS total_revenu_mensuel,
    COALESCE(AVG(revenu_mensuel), 0) AS moyenne_revenu_mensuel
FROM
    V_evolution_revenu_mensuel;



-----------------------------------------------Check chambre diso---------------------------------------------------------------------


