--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

-- Started on 2023-12-16 12:38:39

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
-- TOC entry 204 (class 1259 OID 110514)
-- Name: addresses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.addresses (
    id bigint NOT NULL,
    city character varying(255),
    number character varying(255),
    street character varying(255),
    zip_code character varying(255)
);


ALTER TABLE public.addresses OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 110506)
-- Name: addresses_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.addresses_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.addresses_seq OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 110522)
-- Name: animals; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animals (
    birth timestamp(6) without time zone,
    id bigint NOT NULL,
    pet_store_id bigint,
    color character varying(255)
);


ALTER TABLE public.animals OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 110508)
-- Name: animals_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animals_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.animals_seq OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 110527)
-- Name: cats; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cats (
    id bigint NOT NULL,
    chipid character varying(255)
);


ALTER TABLE public.cats OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 110532)
-- Name: fishes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fishes (
    living_env smallint,
    id bigint NOT NULL,
    CONSTRAINT fishes_living_env_check CHECK (((living_env >= 0) AND (living_env <= 1)))
);


ALTER TABLE public.fishes OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 110538)
-- Name: pet_stores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pet_stores (
    address_id bigint,
    id bigint NOT NULL,
    manager_name character varying(255),
    name character varying(255)
);


ALTER TABLE public.pet_stores OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 110548)
-- Name: pet_stores_products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pet_stores_products (
    pet_store_id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.pet_stores_products OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 110510)
-- Name: pet_stores_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pet_stores_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pet_stores_seq OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 110551)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    price double precision,
    id bigint NOT NULL,
    code character varying(255),
    label character varying(255),
    type character varying(255),
    CONSTRAINT products_type_check CHECK (((type)::text = ANY ((ARRAY['FOOD'::character varying, 'ACCESSORY'::character varying, 'CLEANING'::character varying])::text[])))
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 110512)
-- Name: seq_products; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_products
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_products OWNER TO postgres;

--
-- TOC entry 3039 (class 0 OID 110514)
-- Dependencies: 204
-- Data for Name: addresses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.addresses (id, city, number, street, zip_code) FROM stdin;
1	city 1	123a	street1	1522a
2	city 2	123b	street2	1522b
\.


--
-- TOC entry 3040 (class 0 OID 110522)
-- Dependencies: 205
-- Data for Name: animals; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.animals (birth, id, pet_store_id, color) FROM stdin;
\.


--
-- TOC entry 3041 (class 0 OID 110527)
-- Dependencies: 206
-- Data for Name: cats; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cats (id, chipid) FROM stdin;
\.


--
-- TOC entry 3042 (class 0 OID 110532)
-- Dependencies: 207
-- Data for Name: fishes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fishes (living_env, id) FROM stdin;
\.


--
-- TOC entry 3043 (class 0 OID 110538)
-- Dependencies: 208
-- Data for Name: pet_stores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pet_stores (address_id, id, manager_name, name) FROM stdin;
\.


--
-- TOC entry 3044 (class 0 OID 110548)
-- Dependencies: 209
-- Data for Name: pet_stores_products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pet_stores_products (pet_store_id, product_id) FROM stdin;
\.


--
-- TOC entry 3045 (class 0 OID 110551)
-- Dependencies: 210
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (price, id, code, label, type) FROM stdin;
\.


--
-- TOC entry 3051 (class 0 OID 0)
-- Dependencies: 200
-- Name: addresses_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.addresses_seq', 51, true);


--
-- TOC entry 3052 (class 0 OID 0)
-- Dependencies: 201
-- Name: animals_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animals_seq', 1, false);


--
-- TOC entry 3053 (class 0 OID 0)
-- Dependencies: 202
-- Name: pet_stores_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pet_stores_seq', 1, false);


--
-- TOC entry 3054 (class 0 OID 0)
-- Dependencies: 203
-- Name: seq_products; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_products', 1, false);


--
-- TOC entry 2886 (class 2606 OID 110521)
-- Name: addresses addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);


--
-- TOC entry 2888 (class 2606 OID 110526)
-- Name: animals animals_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- TOC entry 2890 (class 2606 OID 110531)
-- Name: cats cats_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cats
    ADD CONSTRAINT cats_pkey PRIMARY KEY (id);


--
-- TOC entry 2892 (class 2606 OID 110537)
-- Name: fishes fishes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fishes
    ADD CONSTRAINT fishes_pkey PRIMARY KEY (id);


--
-- TOC entry 2894 (class 2606 OID 110547)
-- Name: pet_stores pet_stores_address_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_stores
    ADD CONSTRAINT pet_stores_address_id_key UNIQUE (address_id);


--
-- TOC entry 2896 (class 2606 OID 110545)
-- Name: pet_stores pet_stores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_stores
    ADD CONSTRAINT pet_stores_pkey PRIMARY KEY (id);


--
-- TOC entry 2898 (class 2606 OID 110559)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- TOC entry 2903 (class 2606 OID 110580)
-- Name: pet_stores_products fk67b4iiqjusbh5ughcgn5slv0m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_stores_products
    ADD CONSTRAINT fk67b4iiqjusbh5ughcgn5slv0m FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- TOC entry 2901 (class 2606 OID 110570)
-- Name: fishes fk9vkg8a8kcjw9crghanyb6deu5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fishes
    ADD CONSTRAINT fk9vkg8a8kcjw9crghanyb6deu5 FOREIGN KEY (id) REFERENCES public.animals(id);


--
-- TOC entry 2902 (class 2606 OID 110575)
-- Name: pet_stores fkffon4fyvh1n3b23rd60j0bf86; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_stores
    ADD CONSTRAINT fkffon4fyvh1n3b23rd60j0bf86 FOREIGN KEY (address_id) REFERENCES public.addresses(id);


--
-- TOC entry 2900 (class 2606 OID 110565)
-- Name: cats fkm1kvehparqhhklarvyvfr4nsw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cats
    ADD CONSTRAINT fkm1kvehparqhhklarvyvfr4nsw FOREIGN KEY (id) REFERENCES public.animals(id);


--
-- TOC entry 2904 (class 2606 OID 110585)
-- Name: pet_stores_products fkp494wae9a15qrkng2dplgpm3m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_stores_products
    ADD CONSTRAINT fkp494wae9a15qrkng2dplgpm3m FOREIGN KEY (pet_store_id) REFERENCES public.pet_stores(id);


--
-- TOC entry 2899 (class 2606 OID 110560)
-- Name: animals fkr8hh28btikx3x8axyhedrk44q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fkr8hh28btikx3x8axyhedrk44q FOREIGN KEY (pet_store_id) REFERENCES public.pet_stores(id);


-- Completed on 2023-12-16 12:38:39

--
-- PostgreSQL database dump complete
--

