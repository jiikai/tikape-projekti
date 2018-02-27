CREATE TABLE Aines (
id integer PRIMARY KEY,
tyyppi_id integer,
nimi varchar(255),
vegaaninen boolean,
FOREIGN KEY (tyyppi_id) REFERENCES Tyyppi(id)
);

CREATE TABLE Resepti (
id integer PRIMARY KEY,
nimi varchar(255),
annoksia integer,
ohje varchar,
vegaaninen boolean
);

CREATE TABLE Yksikko (
id integer PRIMARY KEY,
nimi varchar(16),
kuvaus varchar(64)
);

CREATE TABLE Tyyppi (
id integer PRIMARY KEY,
nimi varchar(64)
);

CREATE TABLE ReseptiAines (
resepti_id integer,
aines_id integer,
yksikko_id integer,
maara float,
jarjestys integer,
FOREIGN KEY (resepti_id) REFERENCES Resepti(id),
FOREIGN KEY (aines_id) REFERENCES Aines(id),
FOREIGN KEY (yksikko_id) REFERENCES Yksikko(id),
PRIMARY KEY (resepti_id, aines_id)
);