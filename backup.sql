--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    customer_number bigint NOT NULL,
    id_number character varying(50),
    id_type character varying(30),
    first_name character varying(100),
    middle_name character varying(100),
    last_name character varying(100),
    gender character varying(10),
    dob timestamp with time zone,
    email character varying(150),
    mobile numeric,
    monthly_income numeric(10,2),
    status character varying(20),
    created_at timestamp with time zone,
    created_date timestamp with time zone,
    updated_at timestamp with time zone
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- Name: loan_request; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.loan_request (
    id bigint NOT NULL,
    customer_number bigint NOT NULL,
    amount numeric(19,2) NOT NULL,
    status character varying(255) NOT NULL,
    created_at timestamp without time zone NOT NULL
);


ALTER TABLE public.loan_request OWNER TO postgres;

--
-- Name: loan_request_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.loan_request_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.loan_request_id_seq OWNER TO postgres;

--
-- Name: loan_request_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.loan_request_id_seq OWNED BY public.loan_request.id;


--
-- Name: loan_request id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loan_request ALTER COLUMN id SET DEFAULT nextval('public.loan_request_id_seq'::regclass);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (customer_number, id_number, id_type, first_name, middle_name, last_name, gender, dob, email, mobile, monthly_income, status, created_at, created_date, updated_at) FROM stdin;
234774784	ID9368586	DRIVERS_LICENSE	John	A.	Doe	FEMALE	1970-06-25 03:00:00+03	user1@example.com	255724197449	7216.72	ACTIVE	2025-04-12 11:43:51.694+03	2025-04-12 11:43:51.694+03	2025-04-12 11:43:51.694+03
397178638	ID4118300	NATIONAL_ID	David	E.	Williams	MALE	1980-04-10 03:00:00+03	user5@example.com	255728533132	1885.23	ACTIVE	2025-04-12 11:43:51.694+03	2025-04-12 11:43:51.694+03	2025-04-12 11:43:51.694+03
\.


--
-- Data for Name: loan_request; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.loan_request (id, customer_number, amount, status, created_at) FROM stdin;
1	234774784	5000.00	REJECTED	2025-04-14 16:09:04.158802
2	234774784	5000.00	REJECTED	2025-04-14 21:33:13.875659
3	234774784	5.00	REJECTED	2025-04-14 21:33:23.093444
4	318411216	5.00	REJECTED	2025-04-16 04:19:41.941702
5	318411216	5.00	REJECTED	2025-04-16 04:20:28.18463
6	318411216	5.00	REJECTED	2025-04-16 04:21:03.912575
7	318411216	5.00	REJECTED	2025-04-16 04:22:52.808891
8	318411216	5.00	REJECTED	2025-04-16 04:33:40.421319
9	318411216	5.00	APPROVED	2025-04-16 04:40:12.12471
10	397178638	5.00	APPROVED	2025-04-16 05:56:28.044612
\.


--
-- Name: loan_request_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.loan_request_id_seq', 10, true);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_number);


--
-- Name: loan_request loan_request_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loan_request
    ADD CONSTRAINT loan_request_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

