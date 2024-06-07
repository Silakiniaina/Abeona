INSERT INTO categorie_utilisateur(libelle) VALUES
    ('client'),
    ('admin')
;

INSERT INTO genre(libelle) VALUES 
    ('Homme'),
    ('Femme'),
    ('N/A')
;

INSERT INTO Utilisateur(nom_utilisateur,prenom_utilisateur,email,date_naissance,id_genre,id_categorie_utilisateur,mot_de_passe) VALUES
    ('Ravelonarivo','Silakiniaina Sanda','sandasilakiniaina4@gmail.com','2005-07-12',1,2,digest('admin','sha1'))
;

INSERT INTO categorie_attraction(libelle) VALUES    
    ('Plage'),
    ('Desert'),
    ('Foret')
;

INSERT INTO preference_utilisateur(id_utilisateur,id_categorie_attraction) VALUES
    (1,1),
    (1,2),
    (4,1)
;