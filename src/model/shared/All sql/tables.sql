CREATE TABLE Genre(
    id_genre TEXT DEFAULT generer_id_genre(),
    libelle VARCHAR(20) NOT NULL,
    PRIMARY KEY(id_genre)
);

CREATE TABLE Categorie_utilisateur(
    id_categorie_utilisateur TEXT DEFAULT generer_id_categorie_utilisateur(),
    libelle VARCHAR(50) NOT NULL,
    PRIMARY KEY(id_categorie_utilisateur)
);

CREATE TABLE Categorie_attraction(
    id_categorie_attraction TEXT DEFAULT generer_id_categorie_attraction(),
    libelle VARCHAR(100) NOT NULL,
    PRIMARY KEY(id_categorie_attraction)
);

CREATE TABLE Province(
    id_province TEXT DEFAULT generer_id_province(),
    nom_province VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY(id_province)
);

CREATE TABLE Point_interet(
    id_point_interet TEXT DEFAULT generer_id_point_interet(),
    libelle VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_point_interet)
);

CREATE TABLE categorie_evenement(
    id_categorie_evenement TEXT DEFAULT generer_id_categorie_evenement(),
    libelle VARCHAR(50) NOT NULL,
    PRIMARY KEY(id_categorie_evenement)
);

CREATE TABLE categorie_hotel(
    id_categorie_hotel TEXT DEFAULT generer_id_categorie_hotel(),
    libelle VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_categorie_hotel)
);

CREATE TABLE partenaire(
    id_partenaire TEXT DEFAULT generer_id_partenaire(),
    nom_partenaire VARCHAR(150) NOT NULL,
    email_partenaire VARCHAR(150) NOT NULL,
    mot_de_passe VARCHAR(256) NOT NULL,
    date_insertion TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY(id_partenaire),
    UNIQUE(email_partenaire)
);

CREATE TABLE commodite(
    id_commodite TEXT DEFAULT generer_id_commodite(),
    libelle VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_commodite)
);

CREATE TABLE categorie_chambre(
    id_categorie_chambre TEXT DEFAULT generer_id_categorie_chambre(),
    libelle VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_categorie_chambre)
);

CREATE TABLE categorie_transport(
    id_categorie_tranport TEXT DEFAULT generer_id_categorie_transport(),
    libelle VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_categorie_tranport)
);

CREATE TABLE region(
    id_region TEXT DEFAULT generer_id_region(),
    nom_region VARCHAR(150) NOT NULL,
    id_province TEXT NOT NULL,
    PRIMARY KEY(id_region),
    FOREIGN KEY(id_province) REFERENCES Province(id_province)
);

CREATE TABLE Guide(
    id_guide TEXT DEFAULT generer_id_guide(),
    nom_guide VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    disponibilite SMALLINT DEFAULT 0,
    date_insertion TIMESTAMP DEFAULT NOW(),
    id_partenaire TEXT NOT NULL,
    PRIMARY KEY(id_guide),
    FOREIGN KEY(id_partenaire) REFERENCES partenaire(id_partenaire)
);

CREATE TABLE categorie_pack(
    id_categorie_pack TEXT DEFAULT generer_id_categorie_pack(),
    libelle VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_categorie_pack)
);

CREATE TABLE language_(
    id_language TEXT DEFAULT generer_id_language(),
    libelle VARCHAR(50) NOT NULL,
    PRIMARY KEY(id_language)
);

CREATE TABLE categorie_avis(
    id_categorie_avis TEXT DEFAULT generer_id_categorie_avis(),
    libelle VARCHAR(50) NOT NULL,
    PRIMARY KEY(id_categorie_avis)
);

CREATE TABLE categorie_evaluation(
    id_categorie_evaluation TEXT DEFAULT generer_id_categorie_evaluation(),
    libelle VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_categorie_evaluation)
);

CREATE TABLE categorie_reservation(
    id_categorie_reservation TEXT DEFAULT generer_id_categorie_reservation(),
    libelle VARCHAR(50) NOT NULL,
    PRIMARY KEY(id_categorie_reservation)
);

CREATE TABLE Utilisateur(
    id_utilisateur TEXT DEFAULT generer_id_utilisateur(),
    nom_utilisateur VARCHAR(150) NOT NULL,
    prenom_utilisateur VARCHAR(150),
    date_de_naissance DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    mot_de_passe VARCHAR(256) NOT NULL,
    date_insertion TIMESTAMP DEFAULT NOW(),
    id_genre TEXT,
    id_categorie_utilisateur TEXT,
    PRIMARY KEY(id_utilisateur),
    FOREIGN KEY(id_genre) REFERENCES Genre(id_genre),
    FOREIGN KEY(id_categorie_utilisateur) REFERENCES Categorie_utilisateur(id_categorie_utilisateur)
);

CREATE TABLE Ville(
    id_ville TEXT DEFAULT generer_id_ville(),
    nom_ville VARCHAR(50) NOT NULL,
    id_region TEXT NOT NULL,
    PRIMARY KEY(id_ville),
    FOREIGN KEY(id_region) REFERENCES region(id_region)
);

CREATE TABLE Hotel(
    id_hotel TEXT DEFAULT generer_id_hotel(),
    nom_hotel VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    adresse_hotel VARCHAR(150) NOT NULL,
    date_insertion TIMESTAMP DEFAULT NOW(),
    id_partenaire TEXT NOT NULL,
    id_categorie_hotel TEXT,
    id_ville TEXT,
    PRIMARY KEY(id_hotel),
    FOREIGN KEY(id_partenaire) REFERENCES partenaire(id_partenaire),
    FOREIGN KEY(id_categorie_hotel) REFERENCES categorie_hotel(id_categorie_hotel),
    FOREIGN KEY(id_ville) REFERENCES Ville(id_ville)
);

CREATE TABLE chambre(
    id_chambre TEXT DEFAULT generer_id_chambre(),
    capacite INTEGER NOT NULL,
    tarif NUMERIC(18,2) NOT NULL,
    status SMALLINT DEFAULT 0,
    date_insertion TIMESTAMP DEFAULT NOW(),
    id_categorie_chambre TEXT,
    id_hotel TEXT,
    PRIMARY KEY(id_chambre),
    FOREIGN KEY(id_categorie_chambre) REFERENCES categorie_chambre(id_categorie_chambre),
    FOREIGN KEY(id_hotel) REFERENCES Hotel(id_hotel)
);

CREATE TABLE Transport(
    id_transport TEXT DEFAULT generer_id_transport(),
    nom_transport VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    tarif NUMERIC(18,2) NOT NULL,
    date_insertion TIMESTAMP DEFAULT NOW(),
    id_categorie_tranport TEXT,
    id_partenaire TEXT NOT NULL,
    PRIMARY KEY(id_transport),
    FOREIGN KEY(id_categorie_tranport) REFERENCES categorie_transport(id_categorie_tranport),
    FOREIGN KEY(id_partenaire) REFERENCES partenaire(id_partenaire)
);

CREATE TABLE Attraction(
    id_attraction TEXT DEFAULT generer_id_attraction(),
    nom_attraction VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    date_insertion TIMESTAMP DEFAULT NOW(),
    id_categorie_attraction TEXT,
    id_ville_id_ville TEXT NOT NULL,
    PRIMARY KEY(id_attraction),
    FOREIGN KEY(id_categorie_attraction) REFERENCES Categorie_attraction(id_categorie_attraction),
    FOREIGN KEY(id_ville_id_ville) REFERENCES Ville(id_ville)
);

CREATE TABLE pack(
    id_pack TEXT DEFAULT generer_id_pack(),
    nom_pack VARCHAR(150) NOT NULL,
    date_insertion TIMESTAMP DEFAULT NOW(),
    id_categorie_pack TEXT,
    id_transport_id_transport TEXT,
    id_attraction TEXT,
    id_hotel TEXT,
    PRIMARY KEY(id_pack),
    FOREIGN KEY(id_categorie_pack) REFERENCES categorie_pack(id_categorie_pack),
    FOREIGN KEY(id_transport_id_transport) REFERENCES Transport(id_transport),
    FOREIGN KEY(id_attraction) REFERENCES Attraction(id_attraction),
    FOREIGN KEY(id_hotel) REFERENCES Hotel(id_hotel)
);

CREATE TABLE avis(
    id_avis TEXT DEFAULT generer_id_avis(),
    avis_utilisateur TEXT NOT NULL,
    id_partenaire TEXT,
    date_insertion TIMESTAMP DEFAULT NOW(),
    id_utilisateur TEXT,
    id_categorie_avis TEXT,
    PRIMARY KEY(id_avis),
    FOREIGN KEY(id_utilisateur) REFERENCES Utilisateur(id_utilisateur),
    FOREIGN KEY(id_categorie_avis) REFERENCES categorie_avis(id_categorie_avis)
);

CREATE TABLE evaluation(
    id_evaluation TEXT DEFAULT generer_id_evaluation(),
    evaluation NUMERIC(2,1) NOT NULL,
    id_partenaire VARCHAR(50),
    date_insertion TIMESTAMP DEFAULT NOW(),
    id_categorie_evaluation TEXT,
    PRIMARY KEY(id_evaluation),
    FOREIGN KEY(id_categorie_evaluation) REFERENCES categorie_evaluation(id_categorie_evaluation)
);

CREATE TABLE achat_pack(
    id_achat_pack TEXT DEFAULT generer_id_achat_pack(),
    prix NUMERIC(18,2) NOT NULL,
    date_achat TIMESTAMP DEFAULT NOW(),
    id_utilisateur TEXT,
    id_pack TEXT,
    PRIMARY KEY(id_achat_pack),
    FOREIGN KEY(id_utilisateur) REFERENCES Utilisateur(id_utilisateur),
    FOREIGN KEY(id_pack) REFERENCES pack(id_pack)
);

CREATE TABLE Reservation(
    id_reservation TEXT DEFAULT generer_id_reservation(),
    date_debut_reservation DATE NOT NULL,
    date_fin_reservation DATE NOT NULL,
    nombre_personne SMALLINT NOT NULL,
    prix NUMERIC(18,2) NOT NULL,
    status SMALLINT DEFAULT 0,
    date_insertion TIMESTAMP DEFAULT NOW(),
    date_validation DATE,
    id_utilisateur TEXT,
    id_categorie_reservation TEXT,
    PRIMARY KEY(id_reservation),
    FOREIGN KEY(id_utilisateur) REFERENCES Utilisateur(id_utilisateur),
    FOREIGN KEY(id_categorie_reservation) REFERENCES categorie_reservation(id_categorie_reservation)
);

CREATE TABLE Evenement(
    id_evenement TEXT DEFAULT generer_id_evenement(),
    description TEXT NOT NULL,
    lieu_evenement VARCHAR(150) NOT NULL,
    date_insertion TIMESTAMP DEFAULT NOW(),
    titre_evenement VARCHAR(150) NOT NULL,
    id_hotel TEXT,
    id_ville TEXT,
    id_categorie_evenement TEXT,
    PRIMARY KEY(id_evenement),
    FOREIGN KEY(id_hotel) REFERENCES Hotel(id_hotel),
    FOREIGN KEY(id_ville) REFERENCES Ville(id_ville),
    FOREIGN KEY(id_categorie_evenement) REFERENCES categorie_evenement(id_categorie_evenement)
);

CREATE TABLE preference_utilisateur(
    id_utilisateur TEXT,
    id_categorie_attraction TEXT,
    PRIMARY KEY(id_utilisateur, id_categorie_attraction),
    FOREIGN KEY(id_utilisateur) REFERENCES Utilisateur(id_utilisateur),
    FOREIGN KEY(id_categorie_attraction) REFERENCES Categorie_attraction(id_categorie_attraction)
);

CREATE TABLE commodite_hotel(
    id_hotel TEXT,
    id_commodite TEXT,
    PRIMARY KEY(id_hotel, id_commodite),
    FOREIGN KEY(id_hotel) REFERENCES Hotel(id_hotel),
    FOREIGN KEY(id_commodite) REFERENCES commodite(id_commodite)
);

CREATE TABLE point_interet_ville(
    id_ville TEXT,
    id_point_interet TEXT,
    PRIMARY KEY(id_ville, id_point_interet),
    FOREIGN KEY(id_ville) REFERENCES Ville(id_ville),
    FOREIGN KEY(id_point_interet) REFERENCES Point_interet(id_point_interet)
);

CREATE TABLE language_guide(
    id_guide TEXT,
    id_language TEXT,
    PRIMARY KEY(id_guide, id_language),
    FOREIGN KEY(id_guide) REFERENCES Guide(id_guide),
    FOREIGN KEY(id_language) REFERENCES language_(id_language)
);

CREATE TABLE benefice(
    id_benefice TEXT DEFAULT generer_id_benefice(),
    pourcentage INTEGER,
    PRIMARY KEY(id_benefice)
);