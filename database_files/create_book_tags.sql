-- Table: public.BOOK_TAGS

-- DROP TABLE public."BOOK_TAGS";

CREATE TABLE public."BOOK_TAGS"
(
    tag_name "char"[] NOT NULL,
    isbn_13 VARCHAR(20),
	
    CONSTRAINT "tag_name" PRIMARY KEY (tag_name),
	CONSTRAINT "isbn_13" FOREIGN KEY (isbn_13) REFERENCES books(isbn_13)
)

TABLESPACE pg_default;

ALTER TABLE public."BOOK_TAGS"
    OWNER to postgres;