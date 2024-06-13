CREATE OR REPLACE FUNCTION generate_id_partenaire()
RETURNS TEXT AS $$
DECLARE
    partenaire_seq TEXT;
    partenaire_id TEXT;
BEGIN
    SELECT nextval('partenaire_sequence') INTO partenaire_seq;
    partenaire_id := 'PART' || partenaire_seq;
    RETURN partenaire_id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_region()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('region_sequence') INTO seq;
    id := 'REG' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_ville()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('ville_sequence') INTO seq;
    id := 'VIL' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_language()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('language_sequence') INTO seq;
    id := 'LAN' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_categorie_hotel()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('categorie_hotel_sequence') INTO seq;
    id := 'CATHO' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_categorie_chambre()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('categorie_chambre_sequence') INTO seq;
    id := 'CATCH' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_categorie_evenement()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('categorie_evenement_sequence') INTO seq;
    id := 'CATEV' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_categorie_transport()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('categorie_transport_sequence') INTO seq;
    id := 'CATR' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_categorie_attraction()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('categorie_attraction_sequence') INTO seq;
    id := 'CATAT' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_telephone()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('telephone_partenaire_sequence') INTO seq;
    id := 'TELPA' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_hotel()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('hotels_sequence') INTO seq;
    id := 'HOTEL' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_chambre()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('chambres_sequence') INTO seq;
    id := 'CHAMBRE' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_evenement()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('evenement_sequence') INTO seq;
    id := 'EVENT' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_transport()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('transport_sequence') INTO seq;
    id := 'TRANS' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_attraction()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('attractions_sequence') INTO seq;
    id := 'ATTRAC' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION generate_id_guide()
RETURNS TEXT AS $$
DECLARE
    seq TEXT;
    id TEXT;
BEGIN
    SELECT nextval('guide_sequence') INTO seq;
    id := 'GUIDE' || seq;
    RETURN id;
END;
$$
LANGUAGE plpgsql;

