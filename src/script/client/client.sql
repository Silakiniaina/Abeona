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
    mot_de_passe VARCHAR(256) NOT NULL UNIQUE, 
    adresse VARCHAR(100), 
    date_insertion DATE DEFAULT NOW(),
    FOREIGN KEY(id_genre) REFERENCES genre(id_genre),
    FOREIGN KEY(id_categorie_utilisateur) REFERENCES categorie_utilisateur(id_categorie_utilisateur) 
);