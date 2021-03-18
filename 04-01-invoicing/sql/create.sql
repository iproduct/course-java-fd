--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 13.2

-- Started on 2021-03-18 12:44:33

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 49287)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--
DROP TABLE IF EXISTS public.products;
CREATE TABLE public.products (
     id SERIAL PRIMARY KEY,
     code character varying(5) NOT NULL,
     name character varying(80) NOT NULL,
     description character varying(2048) NOT NULL,
     price real NOT NULL,
     unit integer NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 49295)
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.products ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2816 (class 0 OID 49287)
-- Dependencies: 202
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (id, code, name, description, price, unit) FROM stdin;
\.


--
-- TOC entry 2823 (class 0 OID 0)
-- Dependencies: 203
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 1, false);


--
-- TOC entry 2689 (class 2606 OID 49294)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


-- Completed on 2021-03-18 12:44:34

--
-- PostgreSQL database dump complete
--

