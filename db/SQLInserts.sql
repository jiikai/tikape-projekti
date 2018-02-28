// Yksikko <<enum>> //


INSERT INTO Yksikko  VALUES (1, 'ML','millilitraa [tilavuus]');
INSERT INTO Yksikko  VALUES (2, 'TL', 'teelusikallista (5 ml) [tilavuus]');
INSERT INTO Yksikko  VALUES (3, 'RKL', 'ruokalusikallista (15 ml) [tilavuus]');
INSERT INTO Yksikko  VALUES (4, 'DL', 'desilitraa (100 ml) [tilavuus]');
INSERT INTO Yksikko  VALUES (5, 'L', 'litraa (10 dl) [tilavuus]');
INSERT INTO Yksikko  VALUES (6, 'G', 'grammaa [paino]');
INSERT INTO Yksikko  VALUES (7, 'KG', 'kilogrammaa [paino]');
INSERT INTO Yksikko  VALUES (8, 'KPL', '[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]');
INSERT INTO Yksikko  VALUES (9, 'PKT', '[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]');
INSERT INTO Yksikko  VALUES (10,'PIENI', '[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]');
INSERT INTO Yksikko  VALUES (11, 'KESKIKOKOINEN', '[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]');
INSERT INTO Yksikko  VALUES (12, 'ISO', '[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]');
INSERT INTO Yksikko  VALUES (13,'RIPAUS', '[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]');
INSERT INTO Yksikko  VALUES (14, 'LORAUS', '[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]');

// Tyyppi <<enum>> //

INSERT INTO Tyyppi  VALUES (1, 'Hedelmät ja vihannekset');
INSERT INTO Tyyppi  VALUES (2, 'Palkokasvit');
INSERT INTO Tyyppi  VALUES (3, 'Pähkinät ja siemenet');
INSERT INTO Tyyppi  VALUES (4, 'Viljatuotteet');
INSERT INTO Tyyppi  VALUES (5, 'Muut kasvistuotteet');
INSERT INTO Tyyppi  VALUES (6, 'Maitotuotteet');
INSERT INTO Tyyppi  VALUES (7, 'Lihatuotteet');
INSERT INTO Tyyppi  VALUES (8, 'Kala ja äyriäiset');
INSERT INTO Tyyppi  VALUES (9, 'Rasvat ja öljyt');
INSERT INTO Tyyppi  VALUES (10, 'Mausteet ja yrtit');
INSERT INTO Tyyppi  VALUES (11, 'Kastikkeet');
INSERT INTO Tyyppi  VALUES (12, 'Makeutus');
INSERT INTO Tyyppi  VALUES (13, 'Muut');

// Aines //

INSERT INTO Aines  VALUES (1, 1, 'Paprika', 'true');
INSERT INTO Aines  VALUES (2, 2, 'Kidneypapu', 'true');
INSERT INTO Aines  VALUES (3, 8, 'Kuha', 'false');
INSERT INTO Aines  VALUES (4, 4, 'Kaurahiutale', 'true');
INSERT INTO Aines  VALUES (5, 13, 'Vesi', 'true');
INSERT INTO Aines  VALUES (6, 9, 'Oliiviöljy', 'true');
INSERT INTO Aines  VALUES (7, 12, 'Tumma siirappi', 'true');
INSERT INTO Aines  VALUES (8, 10, 'Suola', 'true');
INSERT INTO Aines  VALUES (9, 2, 'Pellavansiemen', 'true');
INSERT INTO Aines  VALUES (10, 2, 'Hampunsiemen', 'true');

// Resepti //

INSERT INTO Resepti VALUES (1, 'Helppo kauraleipä', 16, 'Sekoita kaikki aineet keskenään kulhossa ja levitä taikina tasaisesti leivinpaperin päälle uunipellille. Paista 250-asteisessa uunissa noin 12-15 minuuttia, kunnes leipä on hieman ruskistunut reunoilta. Leipä on parhaimmillaan tuoreena.', 'true');

// ReseptiAines //

INSERT INTO ReseptiAines VALUES (1, 4, 4, 6, 1);
INSERT INTO ReseptiAines VALUES (1, 5, 4, 4, 2);
INSERT INTO ReseptiAines VALUES (1, 6, 4, 0.5, 3);
INSERT INTO ReseptiAines VALUES (1, 7, 3, 1, 4);
INSERT INTO ReseptiAines VALUES (1, 8, 2, 1, 5);
INSERT INTO ReseptiAines VALUES (1, 9, 3, 1, 6);
INSERT INTO ReseptiAines VALUES (1, 10, 3, 1, 7);
