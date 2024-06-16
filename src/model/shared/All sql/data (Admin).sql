
-- LIGNE 1

INSERT INTO partenaire (id_partenaire, nom_partenaire, email_partenaire, mot_de_passe, date_insertion)
VALUES 
('PART031', 'Jean Dupont', 'jean.dupont@example.com', 'password123', CURRENT_DATE),
('PART032', 'Marie Curie', 'marie.curie@example.com', 'secret456', CURRENT_DATE),
('PART033', 'Albert Einstein', 'albert.einstein@example.com', 'physics101', CURRENT_DATE),
('PART034', 'Isaac Newton', 'isaac.newton@example.com', 'gravity777', CURRENT_DATE),
('PART035', 'Nikola Tesla', 'nikola.tesla@example.com', 'electricity888', CURRENT_DATE),
('PART036', 'Charles Darwin', 'charles.darwin@example.com', 'evolution999', CURRENT_DATE),
('PART037', 'Galileo Galilei', 'galileo.galilei@example.com', 'astronomy111', CURRENT_DATE),
('PART038', 'Leonardo da Vinci', 'leonardo.da.vinci@example.com', 'art222', CURRENT_DATE),
('PART039', 'Thomas Edison', 'thomas.edison@example.com', 'invention333', CURRENT_DATE),
('PART040', 'Ada Lovelace', 'ada.lovelace@example.com', 'computer444', CURRENT_DATE);
INSERT INTO partenaire (nom_partenaire, email_partenaire, mot_de_passe, date_insertion) 
VALUES 
('Partenaire One', 'one@example.com', 'password1', '2023-01-15 10:00:00'),
('Partenaire Two', 'two@example.com', 'password2', '2023-05-20 15:30:00'),
('Partenaire Three', 'three@example.com', 'password3', '2023-08-10 09:00:00'),
('Partenaire Four', 'four@example.com', 'password4',CURRENT_DATE),
('Partenaire Five', 'five@example.com', 'password5', CURRENT_DATE);



INSERT INTO partenaire (id_partenaire, nom_partenaire, email_partenaire, mot_de_passe, date_insertion)
VALUES 
('PART001', 'Jean Dupont', 'jean.dupont@example.com', 'password123', TO_DATE('2021-01-01', 'YYYY-MM-DD')),
('PART002', 'Marie Curie', 'marie.curie@example.com', 'secret456', TO_DATE('2022-02-02', 'YYYY-MM-DD')),
('PART003', 'Albert Einstein', 'albert.einstein@example.com', 'physics101', TO_DATE('2023-03-03', 'YYYY-MM-DD')),
('PART004', 'Isaac Newton', 'isaac.newton@example.com', 'gravity777', TO_DATE('2024-04-04', 'YYYY-MM-DD')),
('PART005', 'Nikola Tesla', 'nikola.tesla@example.com', 'electricity888', TO_DATE('2021-05-05', 'YYYY-MM-DD')),
('PART006', 'Charles Darwin', 'charles.darwin@example.com', 'evolution999', TO_DATE('2022-06-06', 'YYYY-MM-DD')),
('PART007', 'Galileo Galilei', 'galileo.galilei@example.com', 'astronomy111', TO_DATE('2023-07-07', 'YYYY-MM-DD')),
('PART008', 'Leonardo da Vinci', 'leonardo.da.vinci@example.com', 'art222', TO_DATE('2024-08-08', 'YYYY-MM-DD')),
('PART009', 'Thomas Edison', 'thomas.edison@example.com', 'invention333', TO_DATE('2021-09-09', 'YYYY-MM-DD')),
('PART010', 'Ada Lovelace', 'ada.lovelace@example.com', 'computer444', TO_DATE('2022-10-10', 'YYYY-MM-DD')),
('PART011', 'Newtonian Physics', 'newton.physics@example.com', 'gravity555', TO_DATE('2023-11-11', 'YYYY-MM-DD')),
('PART012', 'Einstein Theory', 'einsteins.theory@example.com', 'relativity666', TO_DATE('2024-12-12', 'YYYY-MM-DD')),
('PART013', 'Tesla Innovation', 'teslas.innovation@example.com', 'electricity777', TO_DATE('2021-01-13', 'YYYY-MM-DD')),
('PART014', 'Darwin Evolution', 'darwins.evolution@example.com', 'biology888', TO_DATE('2022-02-14', 'YYYY-MM-DD')),
('PART015', 'Galilean Astronomy', 'galileans.astronomy@example.com', 'stars999', TO_DATE('2023-03-15', 'YYYY-MM-DD')),
('PART016', 'Vincian Art', 'vincians.art@example.com', 'painting111', TO_DATE('2024-04-16', 'YYYY-MM-DD')),
('PART017', 'Edison Inventions', 'edisons.inventions@example.com', 'lightbulb222', TO_DATE('2021-05-17', 'YYYY-MM-DD')),
('PART018', 'Lovelace Computing', 'lovelaces.computing@example.com', 'programming333', TO_DATE('2022-06-18', 'YYYY-MM-DD')),
('PART019', 'Quantum Mechanics', 'quantum.mechanics@example.com', 'particles444', TO_DATE('2023-07-19', 'YYYY-MM-DD')),
('PART020', 'Chemistry Elements', 'chemistry.elements@example.com', 'elements555', TO_DATE('2024-08-20', 'YYYY-MM-DD')),
('PART021', 'Biological Cells', 'biological.cells@example.com', 'cells666', TO_DATE('2021-09-21', 'YYYY-MM-DD')),
('PART022', 'Planetary Systems', 'planetary.systems@example.com', 'planets777', TO_DATE('2022-10-22', 'YYYY-MM-DD')),
('PART023', 'Historical Figures', 'historical.figures@example.com', 'history888', TO_DATE('2023-11-23', 'YYYY-MM-DD')),
('PART024', 'Mathematical Theorems', 'mathematical.theorems@example.com', 'theorems999', TO_DATE('2024-12-24', 'YYYY-MM-DD')),
('PART025', 'Philosophical Concepts', 'philosophical.concepts@example.com', 'concepts111', TO_DATE('2021-01-25', 'YYYY-MM-DD')),
('PART026', 'Scientific Discoveries', 'scientific.discoveries@example.com', 'discoveries222', TO_DATE('2022-02-26', 'YYYY-MM-DD')),
('PART027', 'Innovative Technologies', 'innovative.technologies@example.com', 'tech333', TO_DATE('2023-03-27', 'YYYY-MM-DD')),
('PART028', 'Evolutionary Biology', 'evolutionary.biology@example.com', 'species444', TO_DATE('2024-04-28', 'YYYY-MM-DD')),
('PART029', 'Astronomical Phenomena', 'astronomical.phenomena@example.com', 'cosmos555', TO_DATE('2021-05-29', 'YYYY-MM-DD')),
('PART030', 'Artistic Movements', 'artistic.movements@example.com', 'movements666', TO_DATE('2022-06-30', 'YYYY-MM-DD'));


-- LIGNE 2

-- LIGNE 3

INSERT INTO categorie_reservation (libelle) VALUES
('Hotel'),
('Transport'),
('Attraction');

INSERT INTO Genre (libelle) VALUES
('Homme'),
('Femme');

INSERT INTO Categorie_utilisateur (libelle) VALUES
('Admin'),
('User');

INSERT INTO utilisateur (nom_utilisateur, prenom_utilisateur, date_de_naissance, email, mot_de_passe, id_genre, id_categorie_utilisateur)
VALUES 
('Doe', 'John', '1980-01-01', 'john.doe@example.com', 'password123', 'GEN1', 'CTU1'),
('Smith', 'Jane', '1985-02-02', 'jane.smith@example.com', 'password456', 'GEN2', 'CTU2'),
('Brown', 'Michael', '1990-03-03', 'michael.brown@example.com', 'password789', 'GEN1', 'CTU2'),
('Johnson', 'Emily', '1995-04-04', 'emily.johnson@example.com', 'password000', 'GEN2', 'CTU2'),
('Williams', 'Robert', '2000-05-05', 'robert.williams@example.com', 'password111', 'GEN1', 'CTU1'),
('Jones', 'Jessica', '2005-06-06', 'jessica.jones@example.com', 'password222', 'GEN2', 'CTU2'),
('Garcia', 'James', '2010-07-07', 'james.garcia@example.com', 'password333', 'GEN1', 'CTU1'),
('Miller', 'Patricia', '2015-08-08', 'patricia.miller@example.com', 'password444', 'GEN2', 'CTU2'),
('Davis', 'David', '2020-09-09', 'david.davis@example.com', 'password555', 'GEN1', 'CTU2'),
('Rodriguez', 'Linda', '2025-10-10', 'linda.rodriguez@example.com', 'password666', 'GEN2', 'CTU1');


INSERT INTO reservation (date_debut_reservation, date_fin_reservation, nombre_personne, prix, status, date_insertion, date_validation, id_utilisateur, id_categorie_reservation)
VALUES 
('2020-01-01', '2020-01-07', 2, 200.00, 1, NOW(), '2020-01-02', 'USR1', 'CRS1'),
('2020-02-15', '2020-02-21', 3, 300.00, 0, NOW(), NULL, 'USR5', 'CRS2'),
('2020-03-10', '2020-03-16', 4, 400.00, 1, NOW(), '2020-03-11', 'USR3', 'CRS1'),
('2022-04-15', '2022-04-21', 2, 250.00, 1, NOW(), '2022-04-16', 'USR2', 'CRS3'),
('2022-05-10', '2022-05-16', 3, 350.00, 0, NOW(), NULL, 'USR6', 'CRS1'),
('2022-06-15', '2022-06-21', 4, 400.00, 1, NOW(), '2022-06-16', 'USR4', 'CRS2'),
('2023-07-10', '2023-07-16', 2, 300.00, 1, NOW(), '2023-07-11', 'USR2', 'CRS2'),
('2023-08-15', '2023-08-21', 3, 350.00, 0, NOW(), NULL, 'USR4', 'CRS3'),
('2023-09-10', '2023-09-16', 4, 400.00, 1, NOW(), '2023-09-11', 'USR1', 'CRS2'),
('2023-10-15', '2023-10-21', 2, 250.00, 1, NOW(), '2023-10-16', 'USR2', 'CRS1'),
('2024-07-01', '2024-07-05', 2, 500.00, 1, NOW(), '2024-07-02', 'USR8', 'CRS2'),
('2024-08-15', '2024-08-20', 4, 800.00, 0, NOW(), NULL, 'USR10', 'CRS3'),
('2024-09-10', '2024-09-14', 3, 600.00, 1, NOW(), '2024-09-11', 'USR1', 'CRS1'),
('2024-10-01', '2024-10-07', 4, 700.00, 1, NOW(), '2024-10-02', 'USR2', 'CRS3'),
('2024-11-15', '2024-11-21', 2, 300.00, 0, NOW(), NULL, 'USR9', 'CRS3'),
('2024-12-10', '2024-12-16', 3, 450.00, 1, NOW(), '2024-12-11', 'USR7', 'CRS1'),
('2025-01-05', '2025-01-11', 5, 900.00, 0, NOW(), NULL, 'USR4', 'CRS2'),
('2025-02-20', '2025-02-26', 1, 250.00, 1, NOW(), '2025-02-21', 'USR5', 'CRS3');

-- LIGNE 4

INSERT INTO Province (nom_province, description)
VALUES 
('Antananarivo', 'La capitale du pays, connue pour son riche patrimoine historique.'),
('Toamasina', 'La plus grande ville portuaire du pays, situee sur la cote est.'),
('Fianarantsoa', 'Une ville importante dans le sud du pays, connue pour ses vignobles.'),
('Mahajanga', 'Une ville portuaire majeure sur la cote nord, avec une riche histoire maritime.'),
('Toliara', 'Situee sur la cote sud-ouest, connue pour ses plages et sa faune marine.'),
('Antsiranana', 'Une ville portuaire sur la cote nord, proche de la baie d''Antsiranana.'),
('Manakara', 'Une ville cotiere sur la cote est, offrant des vues spectaculaires sur l''ocean Indien.'),
('Morondava', 'Reconnue pour ses baobabs impressionnants et ses paysages naturels.'),
('Amboangibe', 'Une ville situee dans la region montagneuse centrale, connue pour ses activites agricoles.'),
('Maroantsetra', 'Une petite ville portuaire sur la cote nord, avec des plages de sable blanc.');

INSERT INTO region (nom_region, id_province)
VALUES 
('Antananarivo-Vakinankaratra', 'PRO1'),
('Atsinanana', 'PRO2'),
('Haute Matsiatra', 'PRO3'),
('Boeny', 'PRO4'),
('Atsimo-Andrefana', 'PRO5'),
('Analalava', 'PRO6'),
('Fitovinany', 'PRO7'),
('Menabe', 'PRO8'),
('Itasy', 'PRO9'),
('Sava', 'PRO10');


INSERT INTO Ville (nom_ville, id_region)
VALUES 
('Antananarivo', 'REG1'),
('Toamasina', 'REG2'),
('Fianarantsoa', 'REG3'),
('Mahajanga', 'REG4'),
('Toliara', 'REG5'),
('Antsiranana', 'REG6'),
('Manakara', 'REG7'),
('Morondava', 'REG8'),
('Amboangibe', 'REG9'),
('Maroantsetra', 'REG10');

INSERT INTO categorie_hotel (libelle)
VALUES 
('Luxe'),
('Moyen'),
('Simple');



INSERT INTO Hotel (nom_hotel, description, adresse_hotel, id_partenaire, id_categorie_hotel, id_ville)
VALUES 
('Hotel Luxe Antananarivo', 'Un hôtel luxueux situé au centre de la ville.', 'Rue de la Paix, Antananarivo', 'PART001', 'CHO1', 'VIL1'),
('Hotel Moyen Toamasina', 'Un hôtel moyen avec des chambres confortables.', 'Boulevard de la Mer, Toamasina', 'PART002', 'CHO2', 'VIL2'),
('Hotel Simple Fianarantsoa', 'Un hôtel simple mais accueillant.', 'Avenue de la Nature, Fianarantsoa', 'PART003', 'CHO3', 'VIL3'),
('Hotel Luxe Mahajanga', 'Un hôtel luxueux avec vue sur la mer.', 'Quai de la Plage, Mahajanga', 'PART004', 'CHO1', 'VIL4'),
('Hotel Moyen Toliara', 'Un hôtel moyen avec une belle plage à proximité.', 'Route de la Baie, Toliara', 'PART005', 'CHO2', 'VIL5'),
('Hotel Simple Antsiranana', 'Un hôtel simple avec une bonne connexion internet.', 'Rue de l''Internet, Antsiranana', 'PART006', 'CHO3', 'VIL6'),
('Hotel Luxe Manakara', 'Un hôtel luxueux avec une piscine extérieure.', 'Promenade de la Piscine, Manakara', 'PART007', 'CHO1', 'VIL7'),
('Hotel Moyen Morondava', 'Un hôtel moyen avec des chambres spacieuses.', 'Chemin des Baobabs, Morondava', 'PART008', 'CHO2', 'VIL8'),
('Hotel Simple Amboangibe', 'Un hôtel simple avec une terrasse panoramique.', 'Place de l''Observation, Amboangibe', 'PART009', 'CHO3', 'VIL9'),
('Hotel Moyen Maroantsetra', 'Un hôtel moyen avec une plage privée.', 'Route de la Plage, Maroantsetra', 'PART010', 'CHO2', 'VIL10');

INSERT INTO categorie_evenement (libelle) VALUES
('Evenement hotel'),
('Evenement region');

INSERT INTO Evenement (description, lieu_evenement, titre_evenement, id_hotel, id_ville, id_categorie_evenement)
VALUES 
('Conférence sur le développement durable', 'Auditorium principal', 'Conférence Green Earth', 'HOT1', 'VIL1', 'CEV1'),
('Séminaire de gastronomie locale', 'Restaurant Le Lotus', 'Saveurs de Madagascar', 'HOT2', 'VIL2', 'CEV1'),
('Exposition d''arts plastiques', 'Galerie d''Art La Renaissance', 'Créations Vivantes', 'HOT3', 'VIL3', 'CEV1'),
('Festival de musique traditionnelle', 'Salle de concert Le Phare', 'Rhythmes Ancestraux', 'HOT4', 'VIL4', 'CEV2'),
('Atelier de photographie', 'Studio Photo Lumière', 'Capturer le Moment', 'HOT5', 'VIL5', 'CEV1'),
('Workshop de yoga', 'Centre de Yoga Shanti', 'Harmonie Intérieure', 'HOT6', 'VIL6', 'CEV1'),
('Tournoi de football amical', 'Stade National Raveloherinahitra', 'Match de Solidarité', 'HOT7', 'VIL7', 'CEV1'),
('Semaine de cinéma indépendant', 'Cinéma Le Paradis', 'Révélations Cinématographiques', 'HOT8', 'VIL8', 'CEV2'),
('Masterclass de cuisine internationale', 'Chef''s Table', 'Cuisines du Monde', 'HOT9', 'VIL9', 'CEV2'),
('Journée de lecture poétique', 'Librairie Littéraire', 'Vers et Rimes', 'HOT10', 'VIL10', 'CEV1');

-- LIGNE 6

INSERT INTO Benefice (pourcentage)
VALUES 
(10);