
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

SELECT 
    DATE_PART('YEAR', date_debut_reservation) AS annee,
    DATE_PART('MONTH', date_debut_reservation) AS mois,
    id_categorie_reservation,
    (
        (SELECT CAST(pourcentage AS NUMERIC) FROM benefice WHERE id_benefice = 'BENEF1') / 100.0
    ) * SUM(prix) AS total_prix
FROM 
    Reservation
WHERE
    DATE_PART('YEAR', date_debut_reservation) = DATE_PART('YEAR', CURRENT_DATE) -- Filtrer pour l'année en cours
GROUP BY 
    id_categorie_reservation, mois, annee;



CREATE OR REPLACE VIEW v_nombre_total_partenaire AS
SELECT
    COUNT(email_partenaire) as total_partenaire
FROM 
    partenaire;


CREATE OR REPLACE VIEW v_nombre_partenaire_mois AS
SELECT
    DATE_PART('YEAR', date_insertion) AS annee,
    DATE_PART('MONTH', date_insertion) AS mois,
    COUNT(email_partenaire) as total_partenaire
FROM 
    partenaire
GROUP BY 
    annee,mois;


CREATE OR REPLACE VIEW v_nombre_partenaire_jour AS
SELECT
    COUNT(email_partenaire) as total_partenaire
FROM 
    partenaire
WHERE DATE_PART('DAY', date_insertion) = DATE_PART('DAY', CURRENT_DATE);


CREATE OR REPLACE VIEW v_nombre_total_client AS
SELECT
    COUNT(email) as total_client
FROM 
    Utilisateur;


CREATE OR REPLACE VIEW v_nombre_client_mois AS
SELECT
    DATE_PART('YEAR', date_insertion) AS annee,
    DATE_PART('MONTH', date_insertion) AS mois,
    COUNT(email) as total_client
FROM 
    Utilisateur
GROUP BY 
    annee,mois;


CREATE OR REPLACE VIEW v_nombre_client_jour AS
SELECT
    COUNT(email) as total_client
FROM 
    Utilisateur
WHERE DATE_PART('DAY', date_insertion) = DATE_PART('DAY', CURRENT_DATE);

