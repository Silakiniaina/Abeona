CREATE OR REPLACE FUNCTION generer_id_genre()
RETURNS TEXT AS $$
DECLARE
    genre_seqval TEXT;
    genre_id TEXT;
BEGIN
    SELECT nextval('genre_seq') INTO genre_seqval;
    genre_id := 'GEN' || genre_seqval;
    RETURN genre_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_categorie_utilisateur()
RETURNS TEXT AS $$
DECLARE
    categorie_utilisateur_seqval TEXT;
    categorie_utilisateur_id TEXT;
BEGIN
    SELECT nextval('categorie_utilisateur_seq') INTO categorie_utilisateur_seqval;
    categorie_utilisateur_id := 'CTU' || categorie_utilisateur_seqval;
    RETURN categorie_utilisateur_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_categorie_attraction()
RETURNS TEXT AS $$
DECLARE
    categorie_attraction_seqval TEXT;
    categorie_attraction_id TEXT;
BEGIN
    SELECT nextval('categorie_attraction_seq') INTO categorie_attraction_seqval;
    categorie_attraction_id := 'CAT' || categorie_attraction_seqval;
    RETURN categorie_attraction_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_province()
RETURNS TEXT AS $$
DECLARE
    province_seqval TEXT;
    province_id TEXT;
BEGIN
    SELECT nextval('province_seq') INTO province_seqval;
    province_id := 'PRO' || province_seqval;
    RETURN province_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_point_interet()
RETURNS TEXT AS $$
DECLARE
    point_interet_seqval TEXT;
    point_interet_id TEXT;
BEGIN
    SELECT nextval('point_interet_seq') INTO point_interet_seqval;
    point_interet_id := 'PIN' || point_interet_seqval;
    RETURN point_interet_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_categorie_evenement()
RETURNS TEXT AS $$
DECLARE
    categorie_evenement_seqval TEXT;
    categorie_evenement_id TEXT;
BEGIN
    SELECT nextval('categorie_evenement_seq') INTO categorie_evenement_seqval;
    categorie_evenement_id := 'CEV' || categorie_evenement_seqval;
    RETURN categorie_evenement_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_categorie_hotel()
RETURNS TEXT AS $$
DECLARE
    categorie_hotel_seqval TEXT;
    categorie_hotel_id TEXT;
BEGIN
    SELECT nextval('categorie_hotel_seq') INTO categorie_hotel_seqval;
    categorie_hotel_id := 'CHO' || categorie_hotel_seqval;
    RETURN categorie_hotel_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_partenaire()
RETURNS TEXT AS $$
DECLARE
    partenaire_seqval TEXT;
    partenaire_id TEXT;
BEGIN
    SELECT nextval('partenaire_seq') INTO partenaire_seqval;
    partenaire_id := 'PAR' || partenaire_seqval;
    RETURN partenaire_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_commodite()
RETURNS TEXT AS $$
DECLARE
    commodite_seqval TEXT;
    commodite_id TEXT;
BEGIN
    SELECT nextval('commodite_seq') INTO commodite_seqval;
    commodite_id := 'COM' || commodite_seqval;
    RETURN commodite_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_categorie_chambre()
RETURNS TEXT AS $$
DECLARE
    categorie_chambre_seqval TEXT;
    categorie_chambre_id TEXT;
BEGIN
    SELECT nextval('categorie_chambre_seq') INTO categorie_chambre_seqval;
    categorie_chambre_id := 'CCH' || categorie_chambre_seqval;
    RETURN categorie_chambre_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_categorie_transport()
RETURNS TEXT AS $$
DECLARE
    categorie_transport_seqval TEXT;
    categorie_transport_id TEXT;
BEGIN
    SELECT nextval('categorie_transport_seq') INTO categorie_transport_seqval;
    categorie_transport_id := 'CTR' || categorie_transport_seqval;
    RETURN categorie_transport_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_region()
RETURNS TEXT AS $$
DECLARE
    region_seqval TEXT;
    region_id TEXT;
BEGIN
    SELECT nextval('region_seq') INTO region_seqval;
    region_id := 'REG' || region_seqval;
    RETURN region_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_guide()
RETURNS TEXT AS $$
DECLARE
    guide_seqval TEXT;
    guide_id TEXT;
BEGIN
    SELECT nextval('guide_seq') INTO guide_seqval;
    guide_id := 'GUI' || guide_seqval;
    RETURN guide_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_categorie_pack()
RETURNS TEXT AS $$
DECLARE
    categorie_pack_seqval TEXT;
    categorie_pack_id TEXT;
BEGIN
    SELECT nextval('categorie_pack_seq') INTO categorie_pack_seqval;
    categorie_pack_id := 'CPK' || categorie_pack_seqval;
    RETURN categorie_pack_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_language()
RETURNS TEXT AS $$
DECLARE
    language_seqval TEXT;
    language_id TEXT;
BEGIN
    SELECT nextval('language_seq') INTO language_seqval;
    language_id := 'LAN' || language_seqval;
    RETURN language_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_categorie_avis()
RETURNS TEXT AS $$
DECLARE
    categorie_avis_seqval TEXT;
    categorie_avis_id TEXT;
BEGIN
    SELECT nextval('categorie_avis_seq') INTO categorie_avis_seqval;
    categorie_avis_id := 'CAV' || categorie_avis_seqval;
    RETURN categorie_avis_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_categorie_evaluation()
RETURNS TEXT AS $$
DECLARE
    categorie_evaluation_seqval TEXT;
    categorie_evaluation_id TEXT;
BEGIN
    SELECT nextval('categorie_evaluation_seq') INTO categorie_evaluation_seqval;
    categorie_evaluation_id := 'CEV' || categorie_evaluation_seqval;
    RETURN categorie_evaluation_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_categorie_reservation()
RETURNS TEXT AS $$
DECLARE
    categorie_reservation_seqval TEXT;
    categorie_reservation_id TEXT;
BEGIN
    SELECT nextval('categorie_reservation_seq') INTO categorie_reservation_seqval;
    categorie_reservation_id := 'CRS' || categorie_reservation_seqval;
    RETURN categorie_reservation_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_utilisateur()
RETURNS TEXT AS $$
DECLARE
    utilisateur_seqval TEXT;
    utilisateur_id TEXT;
BEGIN
    SELECT nextval('utilisateur_seq') INTO utilisateur_seqval;
    utilisateur_id := 'USR' || utilisateur_seqval;
    RETURN utilisateur_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_ville()
RETURNS TEXT AS $$
DECLARE
    ville_seqval TEXT;
    ville_id TEXT;
BEGIN
    SELECT nextval('ville_seq') INTO ville_seqval;
    ville_id := 'VIL' || ville_seqval;
    RETURN ville_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_hotel()
RETURNS TEXT AS $$
DECLARE
    hotel_seqval TEXT;
    hotel_id TEXT;
BEGIN
    SELECT nextval('hotel_seq') INTO hotel_seqval;
    hotel_id := 'HOT' || hotel_seqval;
    RETURN hotel_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_chambre()
RETURNS TEXT AS $$
DECLARE
    chambre_seqval TEXT;
    chambre_id TEXT;
BEGIN
    SELECT nextval('chambre_seq') INTO chambre_seqval;
    chambre_id := 'CHM' || chambre_seqval;
    RETURN chambre_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_transport()
RETURNS TEXT AS $$
DECLARE
    transport_seqval TEXT;
    transport_id TEXT;
BEGIN
    SELECT nextval('transport_seq') INTO transport_seqval;
    transport_id := 'TRN' || transport_seqval;
    RETURN transport_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_attraction()
RETURNS TEXT AS $$
DECLARE
    attraction_seqval TEXT;
    attraction_id TEXT;
BEGIN
    SELECT nextval('attraction_seq') INTO attraction_seqval;
    attraction_id := 'ATT' || attraction_seqval;
    RETURN attraction_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_pack()
RETURNS TEXT AS $$
DECLARE
    pack_seqval TEXT;
    pack_id TEXT;
BEGIN
    SELECT nextval('pack_seq') INTO pack_seqval;
    pack_id := 'PAK' || pack_seqval;
    RETURN pack_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_avis()
RETURNS TEXT AS $$
DECLARE
    avis_seqval TEXT;
    avis_id TEXT;
BEGIN
    SELECT nextval('avis_seq') INTO avis_seqval;
    avis_id := 'AVI' || avis_seqval;
    RETURN avis_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_evaluation()
RETURNS TEXT AS $$
DECLARE
    evaluation_seqval TEXT;
    evaluation_id TEXT;
BEGIN
    SELECT nextval('evaluation_seq') INTO evaluation_seqval;
    evaluation_id := 'EVA' || evaluation_seqval;
    RETURN evaluation_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_achat_pack()
RETURNS TEXT AS $$
DECLARE
    achat_pack_seqval TEXT;
    achat_pack_id TEXT;
BEGIN
    SELECT nextval('achat_pack_seq') INTO achat_pack_seqval;
    achat_pack_id := 'ACP' || achat_pack_seqval;
    RETURN achat_pack_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_reservation()
RETURNS TEXT AS $$
DECLARE
    reservation_seqval TEXT;
    reservation_id TEXT;
BEGIN
    SELECT nextval('reservation_seq') INTO reservation_seqval;
    reservation_id := 'RES' || reservation_seqval;
    RETURN reservation_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_evenement()
RETURNS TEXT AS $$
DECLARE
    evenement_seqval TEXT;
    evenement_id TEXT;
BEGIN
    SELECT nextval('evenement_seq') INTO evenement_seqval;
    evenement_id := 'EVT' || evenement_seqval;
    RETURN evenement_id;
END;
$$
LANGUAGE plpgsql;
