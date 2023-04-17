CREATE TABLE person (
                        id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
                        fullname varchar NOT NULL UNIQUE,
                        year_of_birth int CHECK (year_of_birth > 1923)
);

CREATE TABLE book
(
    id               int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    person_id        int REFERENCES person(id) ON DELETE SET NULL,
    name             varchar(200)                        NOT NULL,
    author           varchar(200)                        NOT NULL,
    publication_date int CHECK (publication_date > 1900) NOT NULL,
    date_of_issue TIMESTAMP
);