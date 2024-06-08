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

INSERT INTO point_interet (libelle) VALUES
    ('Métro moderne'),
    ('Ragoût de mouton'),
    ('Montagne majestueuse'),
    ('Festival de musique'),
    ('Musée Art Moderne'),
    ('Réseau de bus efficace'),
    ('Fruits de mer frais'),
    ('Vieille ville'),
    ('Parc national'),
    ('Marché local')
;

INSERT INTO province (nom_province, description_province) VALUES
    ('Antananarivo', 'Capitale de Madagascar, connue pour ses collines et ses marchés animés.'),
    ('Fianarantsoa', 'Région viticole avec des paysages vallonnés et des plantations de thé.'),
    ('Toamasina', 'Important port maritime avec des plages et une vie nocturne animée.'),
    ('Mahajanga', 'Ville côtière réputée pour ses plages et son ambiance détendue.'),
    ('Antsiranana', 'Située a l extrême nord, connue pour ses baies pittoresques et ses parcs nationaux.'),
    ('Toliara', 'Région du sud-ouest célèbre pour ses récifs coralliens et ses forêts de baobabs.')
;

-- Antananarivo
INSERT INTO point_interet_province (id_province, id_point_interet) VALUES
    (1, 1),  
    (1, 2),  
    (1, 3), 
    (2, 4),  
    (2, 5),  
    (2, 6), 
    (3, 7),  
    (3, 8),  
    (3, 9),  
    (4, 10), 
    (4, 1),  
    (4, 2),  
    (5, 3), 
    (5, 4),  
    (5, 5),
    (6, 6),
    (6, 7),  
    (6, 8)
;
