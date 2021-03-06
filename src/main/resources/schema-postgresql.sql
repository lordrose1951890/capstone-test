-- DROP SCHEMA public;
-- Drop tables
DROP TABLE IF EXISTS public.tbl_facility_covenant;
DROP TABLE IF EXISTS public.tbl_review_period;
DROP TABLE IF EXISTS public.tbl_covenant;
DROP TABLE IF EXISTS public.tbl_facility;

DROP TYPE IF EXISTS period_status;
DROP TYPE IF EXISTS covenant_type;
DROP TYPE IF EXISTS covenant_frequency;
DROP TYPE IF EXISTS covenant_status;

CREATE TYPE period_status as ENUM ('UPCOMING', 'DUE', 'OVERDUE');
CREATE TYPE covenant_type as ENUM ('REPORTING', 'FINANCIAL');
CREATE TYPE covenant_frequency as ENUM ('MONTHLY', 'QUARTERLY', 'ANNUALLY');
CREATE TYPE covenant_status as ENUM ('DRAFT', 'ACTIVE', 'CEASED');


-- public.tbl_covenant definition
CREATE TABLE public.tbl_covenant (
    id varchar(15) NOT NULL,
    reporter varchar(255) NULL,
    "type" covenant_type NULL,
    first_reporting_date date NULL,
    reporting_expiry_date date NULL,
    frequency covenant_frequency NULL,
    status covenant_status NULL,

    CONSTRAINT tbl_covenant_pkey PRIMARY KEY (id)
);

-- public.tbl_facility definition
CREATE TABLE public.tbl_facility (
    id varchar(15) NOT NULL,
    "name" varchar(255) NULL,
    start_date date NULL,
    end_date date NULL,
    amount float8 NULL,
    borrowers varchar(255) NULL,

    CONSTRAINT tbl_facility_pkey PRIMARY KEY (id)
);


-- public.tbl_facility_covenant definition
CREATE TABLE public.tbl_facility_covenant (
    facility_id varchar(15) NOT NULL,
    covenant_id varchar(15) NOT NULL,

    CONSTRAINT fkrwe2ypejjto8o6jf1hserkkns FOREIGN KEY (covenant_id) REFERENCES public.tbl_covenant(id),
    CONSTRAINT fksj9v78uvekpfeyuger6hb8qnn FOREIGN KEY (facility_id) REFERENCES public.tbl_facility(id)
);


-- public.tbl_review_period definition
CREATE TABLE public.tbl_review_period (
    id varchar(15) NOT NULL,
    reporting_date date NULL,
    due_date date NULL,
    status period_status NULL,
    covenant_id varchar(15) NULL,

    CONSTRAINT tbl_review_period_pkey PRIMARY KEY (id),
    CONSTRAINT fk5xcmj2glxsavh0yjgekpwo0lt FOREIGN KEY (covenant_id) REFERENCES public.tbl_covenant(id)
);
