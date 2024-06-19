-- Insertion des données pour la table Genre
INSERT INTO Genre (libelle) VALUES
('Masculin'),
('Féminin'),
('Autre')
;

-- Insertion des données pour la table Categorie_utilisateur
INSERT INTO Categorie_utilisateur (libelle) VALUES
('Utilisateur standard'),
('Utilisateur premium'),
('Administrateur'),
('Modérateur'),
('Support technique');

-- Insertion des données pour la table Categorie_attraction
INSERT INTO Categorie_attraction (libelle) VALUES
('Parc National'),
('Musée'),
('Plage'),
('Monument historique'),
('Zoo');

-- Insertion des données pour la table Province
INSERT INTO Province (nom_province, description) VALUES
('Antananarivo', 'Capitale politique de Madagascar'),
('Toamasina', 'Port principal de Madagascar'),
('Antsiranana', 'Ville portuaire au nord de Madagascar'),
('Fianarantsoa', 'Centre administratif au centre de Madagascar'),
('Toliara', 'Ville côtière au sud-ouest de Madagascar');

-- Insertion des données pour la table Point_interet
INSERT INTO Point_interet (libelle) VALUES
('Avenue de Indépendance'),
('Parc Zoologique de Tsimbazaza'),
('Baie de Diego-Suarez'),
('Avenue Ranavalona III'),
('Parc National de Isalo');

-- Insertion des données pour la table categorie_evenement
INSERT INTO categorie_evenement (libelle) VALUES
('Conférence'),
('Festival'),
('Concert'),
('Exposition'),
('Compétition sportive');

-- Insertion des données pour la table categorie_hotel
INSERT INTO categorie_hotel (libelle) VALUES
('Hôtel de luxe'),
('Hôtel économique'),
('Auberge de jeunesse'),
('Villa'),
('Résidence hôtelière');

-- Insertion des données pour la table partenaire
INSERT INTO partenaire (nom_partenaire, email_partenaire, mot_de_passe) VALUES
('Hotel Madagascar', 'hotel.madagascar@example.com', '12345'),
('Transport Express', 'transport.express@example.com', '12345'),
('Guide Touristique', 'guide.touristique@example.com', '12345'),
('Attraction Tropicale', 'attraction.tropicale@example.com', '12345'),
('Agence de Voyage Aventure', 'agence.aventure@example.com', '12345');

-- Insertion des données pour la table commodite
INSERT INTO commodite (libelle) VALUES
('Wi-Fi gratuit'),
('Piscine'),
('Spa'),
('Restaurant'),
('Salle de sport');

-- Insertion des données pour la table categorie_chambre
INSERT INTO categorie_chambre (libelle) VALUES
('Chambre Standard'),
('Chambre Deluxe'),
('Suite Junior'),
('Suite Présidentielle'),
('Appartement');

-- Insertion des données pour la table categorie_transport
INSERT INTO categorie_transport (libelle) VALUES
('Voiture de Location'),
('Bus Touristique'),
('Train Express'),
('Vol Local'),
('Croisière Fluviale');

-- Insertion des données pour la table region
INSERT INTO region (nom_region, id_province) VALUES
('Analamanga', 'PRO1'),
('Atsinanana', 'PRO2'),
('Diana', 'PRO3'),
('Haute Matsiatra', 'PRO4'),
('Atsimo-Andrefana', 'PRO5');

-- Insertion des données pour la table Guide
INSERT INTO Guide (nom_guide, description, disponibilite, id_partenaire) VALUES
('Jean Guide', 'Guide expérimenté à Madagascar', 1, 'PAR3'),
('Emma Guide', 'Spécialiste des parcs nationaux', 1, 'PAR3'),
('Luc Guide', 'Guide touristique à Antananarivo', 0, 'PAR3'),
('Sophie Guide', 'Guide pour les circuits culturels', 1, 'PAR3'),
('Pierre Guide', 'Guide pour les excursions en montagne', 1, 'PAR3');

-- Insertion des données pour la table categorie_pack
INSERT INTO categorie_pack ( libelle) VALUES
('Aventure'),
('Détente'),
('Familial'),
('Romantique'),
('Économique');

-- Insertion des données pour la table language_
INSERT INTO language_ ( libelle) VALUES
('Français'),
('Anglais'),
('Malagasy'),
('Espagnol'),
('Allemand');

-- Insertion des données pour la table categorie_avis
INSERT INTO categorie_avis ( libelle) VALUES
('Hôtel'),
('Pack'),
('Transport'),
('Attraction'),
('Événement');

-- Insertion des données pour la table categorie_evaluation
INSERT INTO categorie_evaluation ( libelle) VALUES
('Hôtel'),
('Pack'),
('Transport'),
('Attraction'),
('Événement');

-- Insertion des données pour la table categorie_reservation
INSERT INTO categorie_reservation (libelle) VALUES
('Chambre'),
('Pack'),
('Transport'),
('Attraction'),
('Événement');

-- Insertion des données pour la table Utilisateur
INSERT INTO Utilisateur (nom_utilisateur, prenom_utilisateur, date_de_naissance, email, mot_de_passe, id_genre, id_categorie_utilisateur) VALUES
('Dupont', 'Jean', '1990-05-15', 'jean.dupont@example.com','12345', 'GEN1', 'CTU1'),
('Durand', 'Sophie', '1985-08-20', 'sophie.durand@example.com','12345', 'GEN2', 'CTU2'),
('Martin', 'Pierre', '1982-10-10', 'pierre.martin@example.com','12345', 'GEN1', 'CTU3'),
('Lefevre', 'Emma', '1995-03-25', 'emma.lefevre@example.com','12345', 'GEN2', 'CTU4'),
('Leroy', 'Luc', '1998-07-30', 'luc.leroy@example.com','12345', 'GEN1', 'CTU5');

INSERT INTO Utilisateur (nom_utilisateur, prenom_utilisateur, date_de_naissance, email, mot_de_passe, id_genre, id_categorie_utilisateur) VALUES
('Dupont', 'Jean', '1990-05-15', 'jean.dupont1@example.com',digest('12345','sha1'), 'GEN1', 'CTU1'),
('Durand', 'Sophie', '1985-08-20', 'sophie.durand1@example.com',digest('12345','sha1'), 'GEN2', 'CTU2'),
('Martin', 'Pierre', '1982-10-10', 'pierre.martin1@example.com',digest('12345','sha1'), 'GEN1', 'CTU3'),
('Lefevre', 'Emma', '1995-03-25', 'emma.lefevre1@example.com',digest('12345','sha1'), 'GEN2', 'CTU4'),
('Leroy', 'Luc', '1998-07-30', 'luc.leroy1@example.com',digest('12345','sha1'),'GEN2', 'CTU5');

INSERT INTO Utilisateur (nom_utilisateur, prenom_utilisateur, date_de_naissance, email, mot_de_passe, id_genre, id_categorie_utilisateur,date_insertion) VALUES
('Dupont', 'Jean', '1990-05-15', 'jean.dupont1@example.com',digest('12345','sha1'), 'GEN1', 'CTU1','2020-05-02'),
('Durand', 'Sophie', '1985-08-20', 'sophie.durand1@example.com',digest('12345','sha1'), 'GEN2', 'CTU2','2023-01-12'),
('Martin', 'Pierre', '1982-10-10', 'pierre.martin1@example.com',digest('12345','sha1'), 'GEN1', 'CTU3','2021-04-17'),
('Lefevre', 'Emma', '1995-03-25', 'emma.lefevre1@example.com',digest('12345','sha1'), 'GEN2', 'CTU4','2023-04-23'),
('Leroy', 'Luc', '1998-07-30', 'luc.leroy1@example.com',digest('12345','sha1'),'GEN2', 'CTU5','2019-08-12');

-- Insertion des données pour la table Ville
INSERT INTO Ville (nom_ville, id_region) VALUES
('Antananarivo', 'REG1'),
('Toamasina', 'REG2'),
('Antsiranana', 'REG3'),
('Fianarantsoa', 'REG4'),
('Toliara', 'REG5');

--Insertion des donnees pour la table attraction
INSERT INTO attraction(nom_attraction,description,prix,id_categorie_attraction,id_ville) VALUES 
('Test 1','lahflfdhfdhfal',10000.00,'CAT1','VIL1'),
('Test 2','lahflfdhfdhfal',10000.00,'CAT1','VIL2'),
('Test 3','lahflfdhfdhfal',23000.00,'CAT2','VIL3'),
('Test 4','lahflfdhfdhfal',21000.00,'CAT4','VIL3'),
('Test 5','lahflfdhfdhfal',45000.00,'CAT3','VIL3'),
('Test 6','lahflfdhfdhfal',123000.00,'CAT5','VIL3'),
('Test 7','lahflfdhfdhfal',31000.00,'CAT5','VIL4'),
('Test 8','lahflfdhfdhfal',89000.00,'CAT3','VIL5')
;

-- Insertion des données pour la table Hotel
INSERT INTO Hotel ( nom_hotel, description, adresse_hotel, id_partenaire, id_categorie_hotel, id_ville) VALUES
('Hôtel Luxe Ivato', 'Hôtel de luxe près de aéroport', 'Ivato', 'PAR1', 'CHO1', 'VIL1'),
('Auberge de la Côte Est', 'Auberge économique avec vue sur la mer', 'Route du Littoral', 'PAR2', 'CHO2', 'VIL2'),
('Villa Sérénité', 'Villa avec piscine privée', 'Baie de Diego-Suarez', 'PAR3', 'CHO3', 'VIL3'),
('Résidence Hôtelière Betsileo', 'Résidence hôtelière au centre-ville', 'Fianarantsoa Centre', 'PAR1', 'CHO4', 'VIL4'),
('Hôtel Plage Tsimelahy', 'Hôtel près de la plage de Tsimelahy', 'Plage Tsimelahy', 'PAR2', 'CHO5', 'VIL5');

INSERT INTO Hotel ( nom_hotel, description, adresse_hotel, id_partenaire, id_categorie_hotel, id_ville) VALUES
('Hotel pain compz', 'Pain compz', 'Andoharanofotsy', 'PAR1', 'CHO1', 'VIL1')
;

-- Insertion des données pour la table chambre
INSERT INTO chambre ( capacite, tarif, id_categorie_chambre, id_hotel) VALUES
(2, 200.00, 'CCH1', 'HOT1'),
(1, 150.00, 'CCH2', 'HOT2'),
(4, 300.00, 'CCH3', 'HOT3'),
(2, 250.00, 'CCH4', 'HOT4'),
(2, 180.00, 'CCH5', 'HOT5');

-- Insertion des données pour la table transport
INSERT INTO transport (nom_transport, description,tarif, id_partenaire, id_categorie_transport) VALUES
('Voiture de Location Aéroport', 'Service de location de voiture à aéroport', 120000.00,'PAR1', 'CTR1'),
('Bus Touristique Madagascar', 'Circuit touristique en bus à Madagascar', 89000.00,'PAR2', 'CTR2'),
('Train Express Central', 'Train express entre les grandes villes',1200000.00 ,'PAR2','CTR3'),
('Vol Local Madagascar', 'Vol intérieur entre les villes de Madagascar',300000.00 ,'PAR2','CTR4'),
('Croisière Fluviale Pangalanes', 'Croisière fluviale sur le canal des Pangalanes', 430000.00, 'PAR2', 'CTR5');

-- Insertion des données pour la table Avis
INSERT INTO Avis (avis_utilisateur, id_partenaire, id_utilisateur, id_categorie_avis) VALUES
('Très bon service!', 'PAR1', 'USR1', 'CAV1'),
('À améliorer la propreté.', 'PAR2', 'USR2', 'CAV2'),
('Expérience neutre.', 'PAR3', 'USR3', 'CAV3'),
('Réclamation sur la réservation.', 'PAR4', 'USR4', 'CAV4'),
('Suggestion pour plus de commodités.', 'PAR5', 'USR5', 'CAV5');

-- Insertion des données pour la table Evaluation
INSERT INTO Evaluation (id_utilisateur,evaluation, id_partenaire, id_categorie_evaluation) VALUES
('USR1',4.5, 'HOT1', 'CEV1'),
('USR2',4.5, 'HOT1', 'CEV1'),
('USR3',3.0, 'HOT2', 'CEV1'),
('USR4',2.5, 'HOT2', 'CEV1'),
('USR1',4.4, 'HOT3', 'CEV1');

INSERT INTO Evaluation (id_utilisateur,evaluation, id_partenaire, id_categorie_evaluation) VALUES
('USR2',5, 'ATT1', 'CEV4'),
('USR3',1, 'ATT1', 'CEV4'),
('USR4',3, 'ATT1', 'CEV4')
;

INSERT INTO pack(nom_pack,id_categorie_pack,id_transport,id_attraction,id_hotel) VALUES 
('Pack Explorer','CPK1','TRN1','ATT2','HOT1'),
('Pack Master','CPK2','TRN2','ATT1','HOT2'),
('Pack Ultra','CPK3','TRN3','ATT3','HOT3');

-- Insertion des données pour la table Achat_pack
INSERT INTO achat_pack (prix, date_achat, id_utilisateur, id_pack) VALUES
(500.00, '2023-01-15', 'USR1', 'PAK1'),
(700.00, '2023-02-28', 'USR2', 'PAK2'),
(900.00, '2023-03-10', 'USR3', 'PAK3'),
(1200.00, '2023-04-20', 'USR4', 'PAK2'),
(800.00, '2023-05-05', 'USR5', 'PAK1');

-- Insertion des données pour la table Reservation
INSERT INTO Reservation (date_debut_reservation, date_fin_reservation, nombre_personne,prix_unitaire, id_utilisateur, id_categorie_reservation,id_partenaire) VALUES
('2023-06-01', '2023-06-07', 2, 120000.00,'USR1', 'CRS1','HOT1'),
('2023-07-10', '2023-07-15', 1, 12000.00,'USR1', 'CRS2','PAK1'),
('2023-08-20', '2023-08-25', 4, 340000.00,'USR2', 'CRS3','TRN1'),
('2023-09-05', '2023-09-10', 3, 640000.00,'USR1', 'CRS4','ATT1'),
('2023-10-15', '2023-10-20', 2, 10000.00,'USR2', 'CRS5','HOT1');

-- Insertion des données pour la table Evenement
INSERT INTO Evenement (description, lieu_evenement, titre_evenement, id_hotel, id_ville, id_categorie_evenement) VALUES
('Conférence sur le développement durable', 'Salle de Conférence A', 'Conférence Éco', 'HOT1', 'VIL1', 'CEV1'),
('Concert de Jazz en plein air', 'Place des Arts', 'Jazz sous les Étoiles', 'HOT1', 'VIL2', 'CEV2'),
('Festival de été', 'Parc Festival', 'Festival Été 2023', 'HOT3', 'VIL3', 'CEV3'),
('Match de Hockey', 'Centre Sportif A', 'Hockey sur Glace', 'HOT4', 'VIL4', 'CEV4'),
('Exposition  Art Moderne', 'Musée des Beaux-Arts', 'Expo Art Moderne','HOT5', 'VIL5', 'CEV5');

INSERT INTO Evenement (description, lieu_evenement, titre_evenement, id_ville, id_categorie_evenement,date_debut_evenement,date_fin_evenement) VALUES
('Fete des enfants', 'Salle 1', 'en_fete','VIL1', 'CEV1','2022-06-23','2022-06-24'),
('Manalazy milay', 'Plage', 'revirevy', 'VIL2', 'CEV2','2023-01-23','2023-01-24'),
('Sport pour tous', 'Terrain', 'let it go', 'VIL3', 'CEV3','2024-01-12','2024-01-23'),
('Concours de boisson', 'Cafeterie', 'buvons ', 'VIL4', 'CEV4','2024-04-24','2024-04-25'),
('Exposition  huhu', 'Huhu', 'huhu', 'VIL5', 'CEV5','2024-05-01','2024-05-30');

INSERT INTO Evenement (description, lieu_evenement, titre_evenement, id_ville, id_categorie_evenement,date_debut_evenement,date_fin_evenement) VALUES
('Fete des enfants', 'Salle 1', 'en_fete','VIL1', 'CEV1','2024-06-19','2024-06-24')
;

-- Insertion des données pour la table Preference_utilisateur
INSERT INTO Preference_utilisateur (id_utilisateur, id_categorie_attraction) VALUES
('USR1', 'CAT1'),
('USR2', 'CAT2'),
('USR3', 'CAT3'),
('USR4', 'CAT4'),
('USR5', 'CAT5');

-- Insertion des données pour la table Commodite_hotel
INSERT INTO Commodite_hotel (id_hotel, id_commodite) VALUES
('HOT1', 'COM1'),
('HOT2', 'COM2'),
('HOT3', 'COM3'),
('HOT4', 'COM4'),
('HOT5', 'COM5');

-- Insertion des données pour la table Point_interet_ville
INSERT INTO Point_interet_ville (id_ville, id_point_interet) VALUES
('VIL1', 'PIN1'),
('VIL2', 'PIN2'),
('VIL3', 'PIN3'),
('VIL4', 'PIN4'),
('VIL5', 'PIN5');

-- Insertion des données pour la table Language_guide
INSERT INTO Language_guide (id_guide, id_language) VALUES
('GUI1', 'LAN1'),
('GUI2', 'LAN2'),
('GUI3', 'LAN3'),
('GUI4', 'LAN4'),
('GUI5', 'LAN5');

-- Insertion des donnees pour la table benefice
INSERT INTO Benefice (pourcentage) VALUES 
    (10);

