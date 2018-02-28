package tikape.runko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.*;
import tikape.runko.domain.*;

public class Main {
    
    static int order = 1;
    static Resepti uusiResepti = null;
    static int aineksiaUudessaReseptissa = 0;
    static List<String> yksikot = Arrays.stream(Yksikko.values())
            .map(e -> e.toString())
            .collect(Collectors.toCollection(ArrayList::new));

    public static void main(String[] args) throws Exception {
			
        // asetetaan portti jos heroku antaa PORT-ympäristömuuttujan
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }
        // asetetaan database
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        Database database = new Database(dbUrl);
        
				
        AinesDao ainesDao = new AinesDao(database);
        ReseptiDao reseptiDao = new ReseptiDao(database);
        ReseptiAinesDao reseptiAinesDao = new ReseptiAinesDao(database);
        
        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervehdys");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/reseptit", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("reseptit", reseptiDao.findAll());
            return new ModelAndView(map, "reseptit");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/reseptit", (req, res) -> {
            Resepti resepti = new Resepti(database.tableSize("Resepti") + 1, req.queryParams("nimi"), Integer.parseInt(req.queryParams("annostenMaara")), "");
            uusiResepti = resepti;
            reseptiDao.saveOrUpdate(resepti);

            res.redirect("/uusiresepti");
            return "";
        });
        
        Spark.get("/uusiresepti", (req, res) -> {
            HashMap map = new HashMap<>();
            List<String> resainekset = reseptiDao.resainekset(uusiResepti.getId());
            map.put("resainekset", resainekset);
            map.put("ainekset", ainesDao.findAll());
            map.put("resepti", uusiResepti);
            map.put("yksikot", yksikot);
            return new ModelAndView(map, "uusiresepti");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/uusiresepti", (req, res) -> {
            Resepti resepti = uusiResepti;
            int yksikkoId = Yksikko.valueOf(req.queryParams("yksikkoId").toUpperCase()).ordinal() + 1;
            ReseptiAines resaines = new ReseptiAines(resepti.getId(), Integer.parseInt(req.queryParams("ainesId")), 
                    yksikkoId, Float.parseFloat(req.queryParams("ainesMaara")), aineksiaUudessaReseptissa + 1);
            aineksiaUudessaReseptissa++;
            reseptiAinesDao.saveOrUpdate(resaines);
            uusiResepti.setOhje(req.queryParams("ohje"));
            if (!(ainesDao.findOne(resaines.getAines_id()).isVegaaninen())) {
                uusiResepti.setVegaaninen(false);
            }
            res.redirect("/uusiresepti");
            return "";
        });
        
        Spark.post("/uusireseptitallenna", (req, res) -> {
            uusiResepti.setOhje(req.queryParams("ohje"));
            reseptiDao.saveOrUpdate(uusiResepti);
            uusiResepti = null;
            aineksiaUudessaReseptissa = 0;
            res.redirect("/reseptit");
            return "";
        });

        Spark.get("/resepti/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            List<String> resainekset = reseptiDao.resainekset(Integer.parseInt(req.params("id")));
            Resepti recipe = reseptiDao.findOne(Integer.parseInt(req.params("id")));
            map.put("resepti", recipe);
            map.put("ainesosat", resainekset);
            return new ModelAndView(map, "resepti");
        }, new ThymeleafTemplateEngine());
        
        Spark.get("/ainekset", (req, res) -> {
            HashMap map = new HashMap<>();
            List<Aines> findAll = ainesDao.findAll();
            if (order == 2) {
                findAll.sort(Comparator.comparing(a -> a.getTyyppi().ordinal()));
            }
            map.put("ainekset", findAll);

            return new ModelAndView(map, "ainekset");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/ainekset", (req, res) -> {
            Aines aines = new Aines(database.tableSize("Aines") + 1, Integer.parseInt(req.queryParams("tyyppi")), req.queryParams("nimi"));
            ainesDao.saveOrUpdate(aines);
            
            res.redirect("/ainekset");
            return "";
        });
        
         Spark.post("/aineksetuusijarjestys", (req, res) -> {
            int neworder = Integer.parseInt(req.queryParams("order"));
            order = neworder;         
            res.redirect("/ainekset");
            return "";
        });

        Spark.get("/aines/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("aines", ainesDao.findOne(Integer.parseInt(req.params("id"))));
            map.put("reseptit", reseptiAinesDao.findAllByIngredient(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "aines");
        }, new ThymeleafTemplateEngine());
    }
}
