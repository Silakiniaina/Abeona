/*--   Partenaire   --*/

create or replace view v_nombre_partenaire_mois as 
    SELECT 
        COUNT(*) as mois 
    FROM 
        partenaire 
    WHERE 
        date_trunc('month', date_insertion) = date_trunc('month', CURRENT_DATE)

create or replace view v_nombre_partenaire_annee as 
    SELECT 
        COUNT(*) as annee
    FROM 
        partenaire 
    WHERE 
        DATE_PART('year', date_insertion) = DATE_PART('year', CURRENT_DATE)

create or replace view v_nombre_partenaire_jour as 
    SELECT 
        COUNT(*) as jour
    FROM 
        partenaire 
    WHERE 
        DATE(date_insertion) = CURRENT_DATE

CREATE OR REPLACE VIEW v_nombre_partenaire AS
SELECT 
    vnm.mois,
    vna.annee,
    vnj.jour,
    vnt.total
FROM 
    v_nombre_partenaire_mois vnm,
    v_nombre_partenaire_annee vna,
    v_nombre_partenaire_total vnt,
    v_nombre_partenaire_jour vnj;

CREATE OR REPLACE VIEW v_nombre_partenaire_total AS
SELECT
    count(*) as total
FROM
    partenaire;


/*--   Client   --*/
CREATE OR REPLACE VIEW v_nombre_client_mois AS 
SELECT 
    COUNT(*) as mois 
FROM 
    Utilisateur 
WHERE 
    date_trunc('month', date_insertion) = date_trunc('month', CURRENT_DATE);


CREATE OR REPLACE VIEW v_nombre_client_annee AS 
SELECT 
    COUNT(*) as annee
FROM 
    Utilisateur 
WHERE 
    DATE_PART('year', date_insertion) = DATE_PART('year', CURRENT_DATE);

CREATE OR REPLACE VIEW v_nombre_client_jour AS 
SELECT 
    COUNT(*) as jour
FROM 
    Utilisateur 
WHERE 
    DATE(date_insertion) = CURRENT_DATE;

CREATE OR REPLACE VIEW v_nombre_client_total AS
SELECT
    COUNT(*) as total
FROM
    Utilisateur;

CREATE OR REPLACE VIEW v_nombre_client AS
SELECT 
    vnm.mois,
    vna.annee,
    vnj.jour,
    vnt.total
FROM 
    v_nombre_client_mois vnm,
    v_nombre_client_annee vna,
    v_nombre_client_total vnt,
    v_nombre_client_jour vnj;

/*--  Evenement  --*/
CREATE OR REPLACE VIEW v_nombre_evenement_mois AS 
SELECT 
    COUNT(*) as mois 
FROM 
    Evenement 
WHERE 
    date_trunc('month', date_insertion) = date_trunc('month', CURRENT_DATE);

CREATE OR REPLACE VIEW v_nombre_evenement_annee AS 
SELECT 
    COUNT(*) as annee
FROM 
    Evenement 
WHERE 
    DATE_PART('year', date_insertion) = DATE_PART('year', CURRENT_DATE);

CREATE OR REPLACE VIEW v_nombre_evenement_jour AS 
SELECT 
    COUNT(*) as jour
FROM 
    Evenement 
WHERE 
    DATE(date_insertion) = CURRENT_DATE;

CREATE OR REPLACE VIEW v_nombre_evenement_total AS
SELECT
    COUNT(*) as total
FROM
    Evenement;

CREATE OR REPLACE VIEW v_nombre_evenement AS
SELECT 
    vnm.mois,
    vna.annee,
    vnj.jour,
    vnt.total
FROM 
    v_nombre_evenement_mois vnm,
    v_nombre_evenement_annee vna,
    v_nombre_evenement_total vnt,
    v_nombre_evenement_jour vnj;



/*--  Benefice  --*/

CREATE OR REPLACE VIEW v_benefice AS
    SELECT 
        DATE_PART('YEAR', date_debut_reservation) AS annee,
        DATE_PART('MONTH', date_debut_reservation) AS mois,
        id_categorie_reservation,
        ((SELECT CAST(pourcentage AS NUMERIC) FROM benefice WHERE id_benefice = 'BENEF1') / 100) * SUM(prix) AS total_prix
    FROM 
        Reservation as r
    WHERE 
        r.status = 1
    GROUP BY 
        id_categorie_reservation, mois, annee;




