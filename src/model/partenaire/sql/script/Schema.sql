CREATE TABLE partenaires (
    id_partenaire TEXT DEFAULT generate_id_partenaire(),
    email_contact varemail NOT NULL,
    nom_partenaire VARCHAR(255) NOT NULL,
    mot_de_passe VARCHAR(256) NOT NULL,
    date_insertion DATE DEFAULT now()
);