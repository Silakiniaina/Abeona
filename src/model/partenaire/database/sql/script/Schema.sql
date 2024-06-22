CREATE TABLE region (
    id_region TEXT PRIMARY KEY DEFAULT generate_id_region(),
    nom_region VARCHAR(255) NOT NULL
);

CREATE TABLE ville (
    id_ville TEXT PRIMARY KEY DEFAULT generate_id_ville(),
    id_region TEXT NOT NULL REFERENCES region(id_region),
    nom_ville VARCHAR(255) NOT NULL
);

CREATE TABLE language (
    id_language TEXT PRIMARY KEY DEFAULT generate_id_language(),
    nom_language VARCHAR(50) NOT NULL
);

CREATE TABLE categorie_hotel (
    id_categorie_hotel TEXT PRIMARY KEY DEFAULT generate_id_categorie_hotel(),
    libelle VARCHAR(255) NOT NULL
);

CREATE TABLE categorie_chambre (
    id_categorie_chambre TEXT PRIMARY KEY DEFAULT generate_id_categorie_chambre(),
    libelle VARCHAR(255) NOT NULL
);

CREATE TABLE categorie_evenement (
    id_categorie_evenement TEXT PRIMARY KEY DEFAULT generate_id_categorie_evenement(),
    libelle VARCHAR(255) NOT NULL
);

CREATE TABLE categorie_transport (
    id_categorie_transport TEXT PRIMARY KEY DEFAULT generate_id_categorie_transport(),
    libelle VARCHAR(255) NOT NULL
);

CREATE TABLE categorie_attraction (
    id_categorie_attraction TEXT PRIMARY KEY DEFAULT generate_id_categorie_attraction(),
    libelle VARCHAR(255) NOT NULL
);

CREATE TABLE partenaires (
    id_partenaire TEXT PRIMARY KEY  DEFAULT generate_id_partenaire(),
    email_contact varemail NOT NULL,
    nom_partenaire VARCHAR(255) NOT NULL,
    mot_de_passe VARCHAR(256) NOT NULL,
    date_insertion DATE DEFAULT now()
);

CREATE TABLE telephone_partenaire (
    id_telephone TEXT PRIMARY KEY DEFAULT generate_id_telephone(),
    id_partenaire TEXT NOT NULL REFERENCES partenaires(id_partenaire),
    numero_telephone VARCHAR(11) NOT NULL
);

CREATE TABLE hotels(
    id_hotel TEXT PRIMARY KEY DEFAULT generate_id_hotel(),
    id_partenaire TEXT NOT NULL REFERENCES partenaires(id_partenaire),
    nom_hotel VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    id_categorie_hotel TEXT NOT NULL REFERENCES categorie_hotel(id_categorie_hotel),
    evaluation NUMERIC(2,2) CHECK (evaluation >= 0 AND evaluation <= 5),
    id_ville TEXT NOT NULL REFERENCES ville(id_ville),
    adresse VARCHAR(255) NOT NULL,
    date_insertion DATE DEFAULT now()
);

CREATE TABLE chambres (
    id_chambre TEXT PRIMARY KEY DEFAULT generate_id_chambre(),
    id_hotel TEXT NOT NULL REFERENCES hotels(id_hotel),
    id_categorie_chambre TEXT NOT NULL REFERENCES categorie_chambre(id_categorie_chambre),
    capacite INT DEFAULT 2 CHECK (capacite >= 1),
    prix DOUBLE PRECISION NOT NULL CHECK(prix >= 0),
    status varstatus DEFAULT 'libre',
    date_insertion DATE DEFAULT now()
);

ALTER TABLE chambres 
ADD COLUMN checking_time TIME DEFAULT '10:00:00';

CREATE TABLE evenement (
    id_evenement TEXT PRIMARY KEY DEFAULT generate_id_evenement(),
    nom_evenement VARCHAR(255) NOT NULL,
    date_evenement DATE NOT NULL,
    date_insertion DATE DEFAULT now(),
    description TEXT NOT NULL,
    id_categorie_evenement TEXT NOT NULL REFERENCES categorie_evenement(id_categorie_evenement),
    id_ville TEXT NOT NULL REFERENCES ville(id_ville),
    id_hotel TEXT REFERENCES hotels(id_hotel)
);

CREATE TABLE transport (
    id_transport TEXT PRIMARY KEY DEFAULT generate_id_transport(),
    id_partenaire TEXT NOT NULL REFERENCES partenaires(id_partenaire),
    nom_transport VARCHAR(255) NOT NULL,
    id_categorie_transport TEXT NOT NULL REFERENCES categorie_transport(id_categorie_transport),
    tarif_par_jour DOUBLE PRECISION CHECK(tarif_par_jour > 0),
    date_insertion DATE DEFAULT now()
);

CREATE TABLE attractions (
    id_attraction TEXT PRIMARY KEY DEFAULT generate_id_attraction(),
    nom_attraction VARCHAR(255) NOT NULL,
    description_attraction TEXT NOT NULL,
    id_ville TEXT NOT NULL REFERENCES ville(id_ville),
    id_categorie_attraction TEXT NOT NULL REFERENCES categorie_attraction(id_categorie_attraction),
    date_insertion DATE DEFAULT now()
);

CREATE TABLE guide (
    id_guide TEXT PRIMARY KEY DEFAULT generate_id_guide(),
    nom_guide VARCHAR(255) NOT NULL,
    biographie TEXT NOT NULL,
    disponiblite varstatus DEFAULT 'libre',
    date_insertion DATE DEFAULT now(),
    id_partenaire TEXT REFERENCES partenaires(id_partenaire)
);

CREATE TABLE language_guide (
    id_language TEXT NOT NULL REFERENCES language(id_language),
    id_guide TEXT NOT NULL REFERENCES guide(id_guide) 
);

CREATE TABLE reset_date (
    sequence_name VARCHAR(255),
    last_reset_date DATE DEFAULT now()
);

CREATE TABLE reservation_chambres (
    id_reservation_chambre TEXT DEFAULT generate_id_reservation_chambre('reservation_chambres_sequence'),
    id_utilisateur TEXT NOT NULL REFERENCES utilisateurs(id_utilisateur),
    id_chambre TEXT NOT NULL REFERENCES chambres(id_chambre),
    date_insertion TIMESTAMP DEFAULT now(),
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL
);

CREATE TABLE reservation_transport (
    id_reservation_transport TEXT DEFAULT generate_id_reservation_transport('reservation_transport_sequence'),
    id_utilisateur TEXT NOT NULL REFERENCES utilisateur(id_utilisateur),
    id_transport TEXT NOT NULL REFERENCES transport(id_transport),
    id_ville_depart TEXT NOT NULL REFERENCES ville(id_ville),
    id_ville_arrive TEXT NOT NULL REFERENCES ville(id_ville),
    date_insertion TIMESTAMP DEFAULT now(),
    date_reservation TIMESTAMP
);

CREATE TABLE reservation_attraction (
    id_reservation_attraction TEXT DEFAULT generate_id_reservation_attraction('reservation_attraction_sequence'),
    id_utilisateur TEXT NOT NULL REFERENCES utilisateurs(id_utilisateur),
    id_attraction TEXT NOT NULL REFERENCES attractions(id_attraction),
    nombre_personne INT CHECK (nombre_personne > 0),
    tarif DOUBLE PRECISION CHECK (tarif > 0),
    date_insertion TIMESTAMP DEFAULT now(),
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL
);

CREATE TABLE update_log (
    actual TIMESTAMP
);