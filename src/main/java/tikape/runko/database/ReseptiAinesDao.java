package tikape.runko.database;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import tikape.runko.domain.Resepti;
import tikape.runko.domain.ReseptiAines;


public class ReseptiAinesDao implements Dao<ReseptiAines, Integer> {

    private Database database;

    public ReseptiAinesDao(Database database) {
        this.database = database;
    }
    
    public List<ReseptiAines> findAllbyRecipe(Integer key) throws SQLException, Exception {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ReseptiAines WHERE resepti_id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        List<ReseptiAines> reseptiAinekset = new ArrayList<>();
        while (rs.next()) {
            int resepti_id = key;
            int aines_id = rs.getInt("aines_id");
            int yksikko = rs.getInt("yksikko_id");
            float maara = rs.getFloat("maara");
            int jarjestys = rs.getInt("jarjestys");
            
            reseptiAinekset.add(new ReseptiAines(resepti_id, aines_id, yksikko, maara, jarjestys));
        }

        rs.close();
        stmt.close();
        connection.close();

        return reseptiAinekset;
    }
    
    public List<Resepti> findAllByIngredient(Integer key) throws SQLException, Exception {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT resepti_id FROM ReseptiAines WHERE aines_id = ?");
		stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        List<Resepti> reseptit = new ArrayList<>();
        ReseptiDao rDao = new ReseptiDao(this.database);
        while (rs.next()) {
            int resepti_id = rs.getInt("resepti_id");
            Resepti resepti = rDao.findOne(resepti_id);
            if (resepti != null) {
                reseptit.add(resepti);
            }
        }
        rs.close();
        stmt.close();
        connection.close();

        return reseptit;
    }
    
    @Override
    public ReseptiAines findOne(Integer key) throws SQLException {
         throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ReseptiAines> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ReseptiAines saveOrUpdate(ReseptiAines object) throws SQLException {
            try (Connection conn = database.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO ReseptiAines VALUES (?, ?, ?, ?, ?)");
                stmt.setInt(1, object.getResepti_id());
                stmt.setInt(2, object.getAines_id());
                stmt.setInt(3, object.getYksikko().ordinal() + 1);
                stmt.setFloat(4, object.getMaara());
                stmt.setInt(5, object.getJarjestys());
                stmt.executeUpdate();
                stmt.close();
                conn.close();
            } catch (Exception ex) {
                Logger.getLogger(ReseptiAinesDao.class.getName()).log(Level.SEVERE, null, ex);
        } //updating not implemented
            return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void deleteFromRecipe(Integer key1, Integer key2) throws SQLException, Exception {
        try (Connection conn = database.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(
                        "DELETE FROM ReseptiAines WHERE resepti_id = ? AND aines_id = ?");
                stmt.setInt(1, key1);
                stmt.setInt(2, key2);
                stmt.executeUpdate();
            }
    }

}
