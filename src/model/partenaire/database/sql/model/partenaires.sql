--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: varemail; Type: DOMAIN; Schema: public; Owner: admin_abeona
--

CREATE DOMAIN public.varemail AS text
	CONSTRAINT varemail_check CHECK ((VALUE ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'::text));


ALTER DOMAIN public.varemail OWNER TO admin_abeona;

--
-- Name: generate_id_partenaire(); Type: FUNCTION; Schema: public; Owner: admin_abeona
--

CREATE FUNCTION public.generate_id_partenaire() RETURNS text
    LANGUAGE plpgsql
    AS $$
DECLARE
    partenaire_seq TEXT;
    partenaire_id TEXT;
BEGIN
    SELECT nextval('partenaire_sequence') INTO partenaire_seq;
    partenaire_id := 'PART' || partenaire_seq;
    RETURN partenaire_id;
END;
$$;


ALTER FUNCTION public.generate_id_partenaire() OWNER TO admin_abeona;

--
-- Name: partenaire_sequence; Type: SEQUENCE; Schema: public; Owner: admin_abeona
--

CREATE SEQUENCE public.partenaire_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.partenaire_sequence OWNER TO admin_abeona;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: partenaires; Type: TABLE; Schema: public; Owner: admin_abeona
--

CREATE TABLE public.partenaires (
    id_partenaire text DEFAULT public.generate_id_partenaire(),
    email_contact public.varemail NOT NULL,
    nom_partenaire character varying(255) NOT NULL,
    mot_de_passe character varying(256) NOT NULL,
    date_insertion date DEFAULT now()
);


ALTER TABLE public.partenaires OWNER TO admin_abeona;


--
-- Data for Name: partenaires; Type: TABLE DATA; Schema: public; Owner: admin_abeona
--

COPY public.partenaires (id_partenaire, email_contact, nom_partenaire, mot_de_passe, date_insertion) FROM stdin;
\.


--
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: admin_abeona
--

COPY public.utilisateur (id_utilisateur, nom_utilisateur, prenom_utilisateur, email, numero_telephone, date_de_naissance, gender, id_categorie_utilisateur, mot_de_passe, adresse) FROM stdin;
\.


--
-- Name: partenaire_sequence; Type: SEQUENCE SET; Schema: public; Owner: admin_abeona
--

SELECT pg_catalog.setval('public.partenaire_sequence', 1, false);


--
-- PostgreSQL database dump complete
--

