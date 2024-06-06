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