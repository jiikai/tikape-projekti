// Yksikko <<enum>> //

INSERT INTO Yksikko (nimi, kuvaus) VALUES ("ML","millilitraa [tilavuus]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("TL", "teelusikallista (5 ml) [tilavuus]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("RKL", "ruokalusikallista (15 ml) [tilavuus]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("DL", "desilitraa (100 ml) [tilavuus]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("L", "litraa (10 dl) [tilavuus]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("G", "grammaa [paino]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("KG", "kilogrammaa [paino]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("KPL", "[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("PKT", "[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("PIENI", "[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("KESKIKOKOINEN", "[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("ISO", "[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("RIPAUS", "[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]");
INSERT INTO Yksikko (nimi, kuvaus) VALUES ("LORAUS", "[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]");

// Tyyppi <<enum>> //

INSERT INTO Tyyppi (nimi) VALUES ("Hedelmät ja vihannekset");
INSERT INTO Tyyppi (nimi) VALUES ("Palkokasvit");
INSERT INTO Tyyppi (nimi) VALUES ("Pähkinät ja siemenet");
INSERT INTO Tyyppi (nimi) VALUES ("Viljatuotteet");
INSERT INTO Tyyppi (nimi) VALUES ("Muut kasvistuotteet");
INSERT INTO Tyyppi (nimi) VALUES ("Maitotuotteet");
INSERT INTO Tyyppi (nimi) VALUES ("Lihatuotteet");
INSERT INTO Tyyppi (nimi) VALUES ("Kala ja äyriäiset");
INSERT INTO Tyyppi (nimi) VALUES ("Rasvat ja öljyt");
INSERT INTO Tyyppi (nimi) VALUES ("Mausteet ja yrtit");
INSERT INTO Tyyppi (nimi) VALUES ("Kastikkeet");
INSERT INTO Tyyppi (nimi) VALUES ("Makeutus");
INSERT INTO Tyyppi (nimi) VALUES ("Muut");

UPDATE Tyyppi SET nimi="Makeutus" WHERE id='12';

// Aines //

INSERT INTO Aines (tyyppi_id, nimi, vegaaninen) VALUES (1, "Paprika", "true");
INSERT INTO Aines (tyyppi_id, nimi, vegaaninen) VALUES (2, "Kidneypapu", "true");
INSERT INTO Aines (tyyppi_id, nimi, vegaaninen) VALUES (8, "Kuha", "false");

INSERT INTO Aines (tyyppi_id, nimi, vegaaninen) VALUES (4, "Kaurahiutale", "true");
INSERT INTO Aines (tyyppi_id, nimi, vegaaninen) VALUES (13, "Vesi", "true");
INSERT INTO Aines (tyyppi_id, nimi, vegaaninen) VALUES (9, "Oliiviöljy", "true");
INSERT INTO Aines (tyyppi_id, nimi, vegaaninen) VALUES (12, "Tumma siirappi", "true");
INSERT INTO Aines (tyyppi_id, nimi, vegaaninen) VALUES (10, "Suola", "true");
INSERT INTO Aines (tyyppi_id, nimi, vegaaninen) VALUES (2, "Pellavansiemen", "true");
INSERT INTO Aines (tyyppi_id, nimi, vegaaninen) VALUES (2, "Hampunsiemen", "true");

// Resepti //

INSERT INTO Resepti (nimi, annoksia, ohje, vegaaninen) VALUES ("Helppo kauraleipä", 16, "Sekoita kaikki aineet keskenään kulhossa ja levitä taikina tasaisesti leivinpaperin päälle uunipellille. Paista 250-asteisessa uunissa noin 12-15 minuuttia, kunnes leipä on hieman ruskistunut reunoilta. Leipä on parhaimmillaan tuoreena.", "true");

// ReseptiAines //

INSERT INTO ReseptiAines (resepti_id, aines_id, yksikko_id, maara, jarjestys) VALUES (1, 4, 4, 6, 1);
INSERT INTO ReseptiAines (resepti_id, aines_id, yksikko_id, maara, jarjestys) VALUES (1, 5, 4, 4, 2);
INSERT INTO ReseptiAines (resepti_id, aines_id, yksikko_id, maara, jarjestys) VALUES (1, 6, 4, 0.5, 3);
INSERT INTO ReseptiAines (resepti_id, aines_id, yksikko_id, maara, jarjestys) VALUES (1, 7, 3, 1, 4);
INSERT INTO ReseptiAines (resepti_id, aines_id, yksikko_id, maara, jarjestys) VALUES (1, 8, 2, 1, 5);
INSERT INTO ReseptiAines (resepti_id, aines_id, yksikko_id, maara, jarjestys) VALUES (1, 9, 3, 1, 6);
INSERT INTO ReseptiAines (resepti_id, aines_id, yksikko_id, maara, jarjestys) VALUES (1, 10, 3, 1, 7);
