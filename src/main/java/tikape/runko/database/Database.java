package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public static Connection getConnection() throws Exception {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        if (dbUrl != null && dbUrl.length() > 0) {
            return DriverManager.getConnection(dbUrl);
        }

        return DriverManager.getConnection("jdbc:sqlite:D:/Files/Dropbox/Git/db/reseptiarkisto.db");
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }
    
    public int tableSize(String tableName) throws SQLException, Exception {
        Connection c = this.getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT * FROM " + tableName);
        ResultSet rs = stmt.executeQuery();
        int rowCount = 0;
        while (rs.next()) {
            rowCount++;
        }
        System.out.println(rowCount);
        stmt.close();
        c.close();
        return rowCount;
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        lista.add("CREATE TABLE Aines (\n" +
"id SERIAL PRIMARY KEY,\n" +
"tyyppi_id integer,\n" +
"nimi varchar(255),\n" +
"vegaaninen boolean,\n" +
"FOREIGN KEY (tyyppi_id) REFERENCES Tyyppi(id)\n" +
");");
        lista.add("CREATE TABLE Tyyppi (\n" +
"id SERIAL PRIMARY KEY,\n" +
"nimi varchar(64)\n" +
");");
        lista.add("ICREATE TABLE Resepti (\n" +
"id SERIAL PRIMARY KEY,\n" +
"nimi varchar(255),\n" +
"annoksia integer,\n" +
"ohje varchar,\n" +
"vegaaninen boolean\n" +
");");
        lista.add("CREATE TABLE Yksikko (\n" +
"id SERIAL PRIMARY KEY,\n" +
"nimi varchar(16),\n" +
"kuvaus varchar(64)\n" +
");");
        lista.add("CREATE TABLE ReseptiAines (\n" +
"resepti_id integer,\n" +
"aines_id integer,\n" +
"yksikko_id integer,\n" +
"maara real,\n" +
"jarjestys integer,\n" +
"FOREIGN KEY (resepti_id) REFERENCES Resepti(id),\n" +
"FOREIGN KEY (aines_id) REFERENCES Aines(id),\n" +
"FOREIGN KEY (yksikko_id) REFERENCES Yksikko(id),\n" +
"PRIMARY KEY (resepti_id, aines_id)\n"  +
");");
lista.add("INSERT INTO Tyyppi (nimi) VALUES (\"Hedelmät ja vihannekset\");\n" +
"INSERT INTO Tyyppi (nimi) VALUES (\"Palkokasvit\");\n" +
"INSERT INTO Tyyppi (nimi) VALUES (\"Pähkinät ja siemenet\");\n" +
"INSERT INTO Tyyppi (nimi) VALUES (\"Viljatuotteet\");\n" +
"INSERT INTO Tyyppi (nimi) VALUES (\"Muut kasvistuotteet\");\n" +
"INSERT INTO Tyyppi (nimi) VALUES (\"Maitotuotteet\");\n" +
"INSERT INTO Tyyppi (nimi) VALUES (\"Lihatuotteet\");\n" +
"INSERT INTO Tyyppi (nimi) VALUES (\"Kala ja äyriäiset\");\n" +
"INSERT INTO Tyyppi (nimi) VALUES (\"Rasvat ja öljyt\");\n" +
"INSERT INTO Tyyppi (nimi) VALUES (\"Mausteet ja yrtit\");\n" +
"INSERT INTO Tyyppi (nimi) VALUES (\"Kastikkeet\");\n" +
"INSERT INTO Tyyppi (nimi) VALUES (\"Makeutus\");\n" +
"INSERT INTO Tyyppi (nimi) VALUES (\"Muut\");");
lista.add("INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"ML\",\"millilitraa [tilavuus]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"TL\", \"teelusikallista (5 ml) [tilavuus]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"RKL\", \"ruokalusikallista (15 ml) [tilavuus]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"DL\", \"desilitraa (100 ml) [tilavuus]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"L\", \"litraa (10 dl) [tilavuus]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"G\", \"grammaa [paino]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"KG\", \"kilogrammaa [paino]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"KPL\", \"[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"PKT\", \"[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"PIENI\", \"[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"KESKIKOKOINEN\", \"[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"ISO\", \"[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"RIPAUS\", \"[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]\");\n" +
"INSERT INTO Yksikko (nimi, kuvaus) VALUES (\"LORAUS\", \"[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]\");");

                                                
        return lista;
    }
}
