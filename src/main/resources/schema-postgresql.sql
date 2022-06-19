-- DROP SCHEMA public;
-- Drop tables
DROP TABLE IF EXISTS public.tbl_facility_covenant;
DROP TABLE IF EXISTS public.tbl_review_period;
DROP TABLE IF EXISTS public.tbl_covenant;
DROP TABLE IF EXISTS public.tbl_facility;


DROP SEQUENCE IF EXISTS public.tbl_covenant_id_seq;
DROP SEQUENCE IF EXISTS public.tbl_review_period_id_seq;

DROP TYPE IF EXISTS period_status;

CREATE TYPE period_status as ENUM ('UPCOMING', 'DUE', 'OVERDUE');

CREATE SEQUENCE public.tbl_covenant_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.tbl_review_period_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;-- public.tbl_covenant definition


-- public.tbl_covenant definition
CREATE TABLE public.tbl_covenant (
                                     id bigserial NOT NULL,
                                     reporter varchar(255) NULL,
                                     "type" varchar(255) NULL,
                                     first_reporting_date date NULL,
                                     reporting_expiry_date date NULL,
                                     frequency varchar(255) NULL,
                                     status varchar(255) NULL,

                                     CONSTRAINT tbl_covenant_pkey PRIMARY KEY (id)
);

-- public.tbl_facility definition
CREATE TABLE public.tbl_facility (
                                     id int8 NOT NULL,
                                     "name" varchar(255) NULL,
                                     start_date date NULL,
                                     end_date date NULL,
                                     amount float8 NULL,
                                     borrowers varchar(255) NULL,

                                     CONSTRAINT tbl_facility_pkey PRIMARY KEY (id)
);


-- public.tbl_facility_covenant definition
CREATE TABLE public.tbl_facility_covenant (
                                              facility_id int8 NOT NULL,
                                              covenant_id int8 NOT NULL,
                                              CONSTRAINT fkrwe2ypejjto8o6jf1hserkkns FOREIGN KEY (covenant_id) REFERENCES public.tbl_covenant(id),
                                              CONSTRAINT fksj9v78uvekpfeyuger6hb8qnn FOREIGN KEY (facility_id) REFERENCES public.tbl_facility(id)
);


-- public.tbl_review_period definition
CREATE TABLE public.tbl_review_period (
                                          id bigserial NOT NULL,
                                          reporting_date date NULL,
                                          due_date date NULL,
                                          status period_status NULL,
                                          covenant_id int8 NULL,
                                          CONSTRAINT tbl_review_period_pkey PRIMARY KEY (id),
                                          CONSTRAINT fk5xcmj2glxsavh0yjgekpwo0lt FOREIGN KEY (covenant_id) REFERENCES public.tbl_covenant(id)
);
