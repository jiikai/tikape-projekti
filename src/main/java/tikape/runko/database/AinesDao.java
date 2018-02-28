/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tikape.runko.domain.Aines;
import tikape.runko.domain.Tyyppi;

public class AinesDao implements Dao<Aines, Integer> {

    private Database database;

    public AinesDao(Database database) {
        this.database = database;
    }

    @Override
    public Aines findOne(Integer key) throws SQLException {
        try {
            Connection connection = database.getConnection();
            
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aines WHERE id = ?");
            stmt.setObject(1, key);
            ResultSet rs = stmt.executeQuery();
            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }
            int id = rs.getInt("id");
            int tyyppi_id = rs.getInt("tyyppi_id");
            String nimi = rs.getString("nimi");
            
            Aines o = new Aines(id, tyyppi_id, nimi);
            
            rs.close();
            stmt.close();
            connection.close();
            
            return o;
        } catch (Exception ex) {
            Logger.getLogger(AinesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Aines> findAll() throws SQLException {

        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aines");
            
            ResultSet rs = stmt.executeQuery();
            List<Aines> ainekset = new ArrayList<>();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                int tyyppi_id = rs.getInt("tyyppi_id");
                String nimi = rs.getString("nimi");
                
                ainekset.add(new Aines(id, tyyppi_id, nimi));
            }
            
            rs.close();
            stmt.close();
            connection.close();
            ainekset.sort(Comparator.comparing(a -> a.getNimi()));
            return ainekset;
        } catch (Exception ex) {
            Logger.getLogger(AinesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Aines saveOrUpdate(Aines object) throws SQLException {
        if (this.findOne(object.getId()) == null) {
            try (Connection conn = database.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO Aines VALUES (?, ?, ?, ?)");
                stmt.setInt(1, object.getId());
                stmt.setInt(2, object.getTyyppi().ordinal() + 1);
                stmt.setString(3, object.getNimi());
                stmt.setBoolean(4, object.isVegaaninen());
                stmt.executeUpdate();
                stmt.close();
                conn.close();
            } catch (Exception ex) {
                Logger.getLogger(AinesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        } else {
            return this.findOne(object.getId());
        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM Aines WHERE id=?");
            stmt.setInt(1, key);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AinesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}
