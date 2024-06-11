INSERT INTO categorie_utilisateur(libelle) VALUES
    ('client'),
    ('admin')
;

INSERT INTO genre(libelle) VALUES 
    ('Homme'),
    ('Femme'),
    ('N/A')
;

INSERT INTO utilisateur(nom_utilisateur,prenom_utilisateur,email,date_naissance,id_genre,id_categorie_utilisateur,mot_de_passe) VALUES
    ('Ravelonarivo','Silakiniaina Sanda','sandasilakiniaina4@gmail.com','2005-07-12','GEN1','CTU2',digest('admin','sha1'))
;

INSERT INTO categorie_attraction(libelle) VALUES    
    ('Plage'),
    ('Desert'),
    ('Foret')
;

INSERT INTO preference_utilisateur(id_utilisateur,id_categorie_attraction) VALUES
    ('CLT1','CTA1'),
    ('CLT1','CTA2')
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
    ('PRO1', 'POI1'),  
    ('PRO1', 'POI2'),  
    ('PRO1', 'POI3'), 
    ('PRO2', 'POI4'),  
    ('PRO2', 'POI5'),  
    ('PRO2', 'POI6'), 
    ('PRO3', 'POI7'),  
    ('PRO3', 'POI8'),  
    ('PRO3', 'POI9'),  
    ('PRO4', 'POI10'), 
    ('PRO4', 'POI1'),  
    ('PRO4', 'POI2'),  
    ('PRO5', 'POI3'), 
    ('PRO5', 'POI4'),  
    ('PRO5', 'POI5'),
    ('PRO6', 'POI6'),
    ('PRO6', 'POI7'),  
    ('PRO6', 'POI8')
;

INSERT INTO ville (nom_ville, id_province) VALUES
    ('Antananarivo', 'PRO1'),  
    ('Antsirabe', 'PRO1'),     
    ('Ambatolampy', 'PRO1'),   
    ('Fianarantsoa', 'PRO2'),   
    ('Ambalavao', 'PRO2'),      
    ('Manakara', 'PRO2'),       
    ('Toamasina', 'PRO3'),      
    ('Brickaville', 'PRO3'),    
    ('Foulpointe', 'PRO3'),     
    ('Mahajanga', 'PRO4'),      
    ('Marovoay', 'PRO4'),       
    ('Ambato-Boeny', 'PRO4'),   
    ('Antsiranana', 'PRO5'),    
    ('Ambanja', 'PRO5'),        
    ('Nosy Be', 'PRO5'),        
    ('Toliara', 'PRO6'),        
    ('Morondava', 'PRO6'),      
    ('Tulear', 'PRO6')
;  

INSERT INTO hotel (nom_hotel) VALUES
    ('Hotel Colbert'),
    ('Carlton Hotel'),
    ('Hotel Tamboho'),
    ('Hotel Thermes'),
    ('Hotel Vanilla')
;

INSERT INTO evenement (nom_evenement, date_evenement, description_evenement, id_ville, id_hotel) VALUES
    ('Festival de Jazz', '2024-07-10', 'Un festival de jazz annuel avec des musiciens internationaux.', 'VIL1', 'HOT1'),
    ('Conférence sur le climat', '2024-08-15', 'Conférence internationale sur les changements climatiques.', 'VIL2', 'HOT2'),
    ('Salon de l automobile', '2024-09-20', 'Présentation des derniers modèles de voitures.', 'VIL3', NULL),
    ('Marathon de Tana', '2024-10-05', 'Un marathon annuel dans la capitale.', 'VIL1', 'HOT3'),
    ('Festival des arts', '2024-11-12', 'Exposition d arts locaux et internationaux.', 'VIL4', NULL),
    ('Fête nationale', '2024-06-26', 'Célébration de la fête nationale avec des parades et des feux d artifice.', 'VIL5', 'HOT3'),
    ('Conférence médicale', '2024-07-22', 'Conférence sur les nouvelles avancées médicales.', 'VIL3', NULL),
    ('Festival de la mer', '2024-08-30', 'Célébration des ressources maritimes avec des activités de plage.', 'VIL7', 'HOT5'),
    ('Salon du livre', '2024-09-15', 'Rencontre avec des auteurs et présentation de nouveaux livres.', 'VIL8', NULL),
    ('Festival du film', '2024-10-25', 'HOTjection de films internationaux et nationaux.', 'VIL9', 'HOT1'),
    ('Exposition de photographie', '2024-11-18', 'Exposition de photographies de divers artistes.', 'VIL10', 'HOT2'),
    ('Conférence technologique', '2024-12-05', 'Présentation des dernières innovations technologiques.', 'VIL11', NULL),
    ('Tournoi de tennis', '2024-06-10', 'Tournoi de tennis avec des joueurs locaux et internationaux.', 'VIL12', 'HOT3'),
    ('Festival de musique', '2024-07-18', 'Festival de musique avec des artistes de divers genres.', 'VIL13', NULL),
    ('Exposition de voitures anciennes', '2024-08-22', 'Exposition de voitures classiques et anciennes.', 'VIL14', 'HOT4')
;

INSERT INTO attraction (nom_attraction) VALUES
    ('Tour Eiffel'),
    ('Musée du Louvre'),
    ('Mont Saint-Michel'),
    ('Cathédrale Notre-Dame de Paris'),
    ('Château de Versailles'),
    ('Pont du Gard'),
    ('Côte  Azur'),
    ('Château de Chambord'),
    ('Dune du Pilat'),
    ('Mont Blanc')
;


