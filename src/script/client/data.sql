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

INSERT INTO ville (nom_ville, id_province) VALUES
    ('Antananarivo', 1),  
    ('Antsirabe', 1),     
    ('Ambatolampy', 1),   
    ('Fianarantsoa', 2),   
    ('Ambalavao', 2),      
    ('Manakara', 2),       
    ('Toamasina', 3),      
    ('Brickaville', 3),    
    ('Foulpointe', 3),     
    ('Mahajanga', 4),      
    ('Marovoay', 4),       
    ('Ambato-Boeny', 4),   
    ('Antsiranana', 5),    
    ('Ambanja', 5),        
    ('Nosy Be', 5),        
    ('Toliara', 6),        
    ('Morondava', 6),      
    ('Tulear', 6)
;  

INSERT INTO hotel (nom_hotel) VALUES
    ('Hotel Colbert'),
    ('Carlton Hotel'),
    ('Hotel Tamboho'),
    ('Hotel Thermes'),
    ('Hotel Vanilla')
;

INSERT INTO evenement (nom_evenement, date_evenement, description_evenement, id_ville, id_hotel) VALUES
    ('Festival de Jazz', '2024-07-10', 'Un festival de jazz annuel avec des musiciens internationaux.', 1, 1),
    ('Conférence sur le climat', '2024-08-15', 'Conférence internationale sur les changements climatiques.', 2, 2),
    ('Salon de l automobile', '2024-09-20', 'Présentation des derniers modèles de voitures.', 3, NULL),
    ('Marathon de Tana', '2024-10-05', 'Un marathon annuel dans la capitale.', 1, 3),
    ('Festival des arts', '2024-11-12', 'Exposition d arts locaux et internationaux.', 4, NULL),
    ('Fête nationale', '2024-06-26', 'Célébration de la fête nationale avec des parades et des feux d artifice.', 5, 4),
    ('Conférence médicale', '2024-07-22', 'Conférence sur les nouvelles avancées médicales.', 6, NULL),
    ('Festival de la mer', '2024-08-30', 'Célébration des ressources maritimes avec des activités de plage.', 7, 5),
    ('Salon du livre', '2024-09-15', 'Rencontre avec des auteurs et présentation de nouveaux livres.', 8, NULL),
    ('Festival du film', '2024-10-25', 'Projection de films internationaux et nationaux.', 9, 1),
    ('Exposition de photographie', '2024-11-18', 'Exposition de photographies de divers artistes.', 10, 2),
    ('Conférence technologique', '2024-12-05', 'Présentation des dernières innovations technologiques.', 11, NULL),
    ('Tournoi de tennis', '2024-06-10', 'Tournoi de tennis avec des joueurs locaux et internationaux.', 12, 3),
    ('Festival de musique', '2024-07-18', 'Festival de musique avec des artistes de divers genres.', 13, NULL),
    ('Exposition de voitures anciennes', '2024-08-22', 'Exposition de voitures classiques et anciennes.', 14, 4)
;



