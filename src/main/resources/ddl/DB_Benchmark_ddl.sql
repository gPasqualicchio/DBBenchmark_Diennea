CREATE SEQUENCE CAR_SEQ
    INCREMENT 1
    START 1;

CREATE TABLE "CAR"
(
    "ID" integer NOT NULL DEFAULT nextval('CAR_SEQ'),
    "MODEL" text NOT NULL,
    "USR_INS" text NOT NULL DEFAULT 'DFLT_USR',
    "USR_MOD" text,
    "DATE_INS" timestamp with time zone NOT NULL DEFAULT now(),
    "DATE_MOD" timestamp with time zone,
    "ACTIVE" boolean NOT NULL DEFAULT true,
    PRIMARY KEY ("ID")
);