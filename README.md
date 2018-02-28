# tikape-runko

Reseptiarkisto

Tämä on kurssin Tietokantojen perusteet harjoitustyönä tehty websovellus, joka tarjoaa välineen reseptiarkiston koostamiseen tietokantaan. Arkistoon on mahdollista lisätä kategorioittain ruoka-aineita, joista voidaan edelleen koostaa reseptejä. Reseptit koostuvat useista tällaisista ainesosista, joille on määritelty määrä (jotakin tilavuus-, paino- tai kappaleyksikköä kohden) ja lisäysjärjestys. Reseptiin voidaan liittää lisäksi tekstimuotoinen ohje sen toteuttamiseen. Kunkin aineksen tiedoissa on linkki kaikkiin niihin resepteihin, joissa se esiintyy. Sovellus kertoo reseptien vegaanisuudesta ainesosien kategorian perusteella. Tässä on selkeä puute siinä, että sovellus tunnistaa ei-vegaaniksi ainoastaan kaikki maito-, liha- ja kalatuotteet; näiden ulkopuolelta ei ole mahdollista manuaalisesti lisätä tietoa aineksen ei-vegaanisuudesta (esim. hunaja).

Sovelluksessa on bugi lisättäessä reseptiin aineksia/ohjetta: sivun päivittyessä ainesosan lisäämisen yhteydessä ohjeen kirjoittamiseen tarkoitettu tekstikenttä tyhjenee. Resepti tulee kirjoittaa vasta kaikkien ainesten lisäämisen jälkeen, jolloin se myös tallentuu tietokantaan.
