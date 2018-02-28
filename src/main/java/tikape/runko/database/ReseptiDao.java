package tikape.runko.database;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import tikape.runko.domain.Aines;
import tikape.runko.domain.Resepti;
import tikape.runko.domain.ReseptiAines;


public class ReseptiDao implements Dao<Resepti, Integer> {
    
    private Database database;

    public ReseptiDao(Database database) {
        this.database = database;
    }

    @Override
    public Resepti findOne(Integer key) throws SQLException {
        try {
            Connection connection = database.getConnection();
            
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Resepti WHERE id = ?");
            stmt.setObject(1, key);
            ResultSet rs = stmt.executeQuery();
            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }
            Integer id = key;
            String nimi = rs.getString("nimi");
            int annoksia = rs.getInt("annoksia");
            String ohje = rs.getString("ohje");
            
            Resepti o = new Resepti(id, nimi, annoksia, ohje);
            
            rs.close();
            stmt.close();
            connection.close();
            
            return o;
        } catch (Exception ex) {
            Logger.getLogger(ReseptiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Resepti> findAll() throws SQLException {
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Resepti");
            
            ResultSet rs = stmt.executeQuery();
            List<Resepti> reseptit = new ArrayList<>();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nimi = rs.getString("nimi");
                int annoksia = rs.getInt("annoksia");
                String ohje = rs.getString("ohje");
                
                reseptit.add(new Resepti(id, nimi, annoksia, ohje));
            }
            
            rs.close();
            stmt.close();
            connection.close();
            
            return reseptit;
        } catch (Exception ex) {
            Logger.getLogger(ReseptiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Resepti saveOrUpdate(Resepti object) throws SQLException {
        if (this.findOne(object.getId()) == null) {
            try (Connection conn = database.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO Resepti VALUES (?, ?, ?, ?)");
								stmt.setInt(1, object.getId());				
                stmt.setString(2, object.getNimi());
                stmt.setInt(3, object.getAnnoksia());
                stmt.setString(4, object.getOhje());
                stmt.executeUpdate();
                stmt.close();
                conn.close();
            } catch (Exception ex) {
                Logger.getLogger(ReseptiDao.class.getName()).log(Level.SEVERE, null, ex);
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
                    "DELETE FROM Resepti WHERE key=?");
            stmt.setInt(1, key);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(ReseptiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String> resainekset(Integer key) throws SQLException, Exception {
        Resepti o = this.findOne(key);
        
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ReseptiAines WHERE resepti_id=?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        List<ReseptiAines> resainekset = new ArrayList<>();
        while (rs.next()) {
            int resepti_id = key;
            int aines_id = rs.getInt("aines_id");
            int yksikko_id = rs.getInt("yksikko_id");
            float maara = rs.getFloat("maara");
            int jarjestys = rs.getInt("jarjestys");

            resainekset.add(new ReseptiAines(resepti_id, aines_id, yksikko_id, maara, jarjestys));
        }
        Map<Aines, ReseptiAines> map = new HashMap<>();
        for (int i = 0; i < resainekset.size(); i++) {
            stmt = connection.prepareStatement("SELECT * FROM Aines WHERE id=?");
            stmt.setInt(1, resainekset.get(i).getAines_id());
            rs = stmt.executeQuery();
            rs.next();
            Aines aines = new Aines(rs.getInt("id"), rs.getInt("tyyppi_id"), rs.getString("nimi"));
            map.put(aines, resainekset.get(i));
        }
        o.setAinesosat(map);
        List<String> list = map.entrySet().stream()
                .map(e -> e.getKey().getNimi() + "  " + e.getValue().toString())
                .collect(Collectors.toCollection(ArrayList::new));
        stmt.close();
        connection.close();
        return list;
    }

    
}
