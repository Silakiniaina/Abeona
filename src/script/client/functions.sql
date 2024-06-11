CREATE OR REPLACE FUNCTION generer_id_client()
RETURNS TEXT AS $$
DECLARE
    client_seqval TEXT;
    client_id TEXT;
BEGIN
    SELECT nextval('client_seq') INTO client_seqval;
    client_id := 'CLT' || client_seqval;
    RETURN client_id;
END;
$$
LANGUAGE plpgsql;

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
    categorie_attraction_id := 'CTA' || categorie_attraction_seqval;
    RETURN categorie_attraction_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generer_id_preference_utilisateur()
RETURNS TEXT AS $$
DECLARE
    preference_utilisateur_seqval TEXT;
    preference_utilisateur_id TEXT;
BEGIN
    SELECT nextval('preference_utilisateur_seq') INTO preference_utilisateur_seqval;
    preference_utilisateur_id := 'PRU' || preference_utilisateur_seqval;
    RETURN preference_utilisateur_id;
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
    point_interet_id := 'POI' || point_interet_seqval;
    RETURN point_interet_id;
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

CREATE OR REPLACE FUNCTION generer_id_point_interet_province()
RETURNS TEXT AS $$
DECLARE
    point_interet_seqval TEXT;
    point_interet_id TEXT;
BEGIN
    SELECT nextval('point_interet_province_seq') INTO point_interet_seqval;
    point_interet_id := 'PIP' || point_interet_seqval;
    RETURN point_interet_id;
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

CREATE OR REPLACE FUNCTION generer_id_evenement()
RETURNS TEXT AS $$
DECLARE
    evenement_seqval TEXT;
    evenement_id TEXT;
BEGIN
    SELECT nextval('evenement_seq') INTO evenement_seqval;
    evenement_id := 'EVE' || evenement_seqval;
    RETURN evenement_id;
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

CREATE OR REPLACE FUNCTION generer_id_attraction()
RETURNS TEXT AS $$
DECLARE
    attraction_seqval TEXT;
    attraction_id TEXT;
BEGIN
    SELECT nextval('attraction_seq') INTO  attraction_seqval;
    attraction_id := 'ATR' ||  attraction_seqval;
    RETURN attraction_id;
END;
$$
LANGUAGE plpgsql;
