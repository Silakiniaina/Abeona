CREATE DATABASE "abeona" WITH OWNER sanda;

CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE categorie_utilisateur(
    id_categorie_utilisateur SERIAL PRIMARY KEY, 
    libelle VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE genre(
    id_genre SERIAL PRIMARY KEY, 
    libelle VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE Utilisateur(
    id_utilisateur SERIAL PRIMARY KEY, 
    nom_utilisateur VARCHAR(50) NOT NULL,
    prenom_utilisateur VARCHAR(100), 
    email VARCHAR(50) NOT NULL, 
    numero_telephone VARCHAR(15), 
    date_naissance DATE NOT NULL,
    id_genre INT NOT NULL, 
    id_categorie_utilisateur INT NOT NULL, 
    mot_de_passe VARCHAR(256) NOT NULL, 
    adresse VARCHAR(100), 
    date_insertion DATE DEFAULT NOW(),
    FOREIGN KEY(id_genre) REFERENCES genre(id_genre),
    FOREIGN KEY(id_categorie_utilisateur) REFERENCES categorie_utilisateur(id_categorie_utilisateur) 
);

CREATE TABLE categorie_attraction (
    id_categorie_attraction SERIAL PRIMARY KEY, 
    libelle VARCHAR(20) NOT NULL
);

CREATE TABLE preference_utilisateur(
    id_preference SERIAL PRIMARY KEY, 
    id_utilisateur INT NOT NULL, 
    id_categorie_attraction INT NOT NULL, 
    FOREIGN KEY(id_utilisateur) REFERENCES utilisateur(id_utilisateur),
    FOREIGN KEY(id_categorie_attraction) REFERENCES categorie_attraction(id_categorie_attraction),
    UNIQUE(id_utilisateur,id_categorie_attraction)
);

CREATE TABLE point_interet(
    id_point_interet SERIAL PRIMARY KEY, 
    libelle VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE province (
    id_province SERIAL PRIMARY KEY, 
    nom_province VARCHAR(100) NOT NULL UNIQUE,
    description_province VARCHAR(250)
);

CREATE TABLE point_interet_province(
    id_interet SERIAL PRIMARY KEY, 
    id_province INT NOT NULL, 
    id_point_interet INT NOT NULL, 
    FOREIGN KEY(id_province) REFERENCES province(id_province),
    FOREIGN KEY(id_point_interet) REFERENCES point_interet(id_point_interet)
);

CREATE TABLE ville (
    id_ville SERIAL PRIMARY KEY, 
    nom_ville VARCHAR(100) NOT NULL, 
    id_province INT NOT NULL, 
    FOREIGN KEY(id_province) REFERENCES province(id_province)
);

CREATE TABLE hotel (
    id_hotel SERIAL PRIMARY KEY, 
    nom_hotel VARCHAR(20) NOT NULL
);

CREATE TABLE evenement (
    id_evenement SERIAL PRIMARY KEY, 
    nom_evenement VARCHAR(100) NOT NULL, 
    date_evenement DATE NOT NULL, 
    description_evenement VARCHAR(256) NOT NULL, 
    id_ville INT NOT NULL, 
    id_hotel INT, 
    FOREIGN KEY(id_ville) REFERENCES ville(id_ville),
    FOREIGN KEY(id_hotel) REFERENCES hotel(id_hotel)
);