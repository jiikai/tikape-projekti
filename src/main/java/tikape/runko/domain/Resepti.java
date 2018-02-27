/*@author joa*/

package tikape.runko.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Resepti {
    private Integer id;
    private String nimi;
    private Integer annoksia;
    private String ohje;
    private Map<Aines, ReseptiAines> ainesosat;
    private Boolean vegaaninen;

    public Resepti(int id, String nimi, int annoksia, String ohje) {
        this.id = id;
        this.nimi = nimi;
        this.annoksia = annoksia;
        this.ohje = ohje;
        this.ainesosat = new HashMap<>();
        this.vegaaninen = true;
    }
    
    public Resepti(String nimi, int annoksia, String ohje) {
        this.id = -1;
        this.nimi = nimi;
        this.annoksia = annoksia;
        this.ohje = ohje;
        this.ainesosat = new HashMap<>();
        this.vegaaninen = true;
    }

    public Integer getId() {
        return id;
    }
    
    public String getVegaaninenString() {
        if (this.vegaaninen) {
            return "Vegaaninen. ";
        } else {
            return "Ei vegaaninen. ";
        }
    }
    
    public Map<Aines, ReseptiAines> getAinesosat() {
        return ainesosat;
    }
    
    public List<String> getAinesosatList() {
        List<String> list = this.ainesosat.entrySet().stream()
                .map(e -> e.getKey().getNimi() + "  " + e.getValue().toString())
                .collect(Collectors.toCollection(ArrayList::new));
        return list;
    }

    public String getNimi() {
        return nimi;
    }

    public int getAnnoksia() {
        return annoksia;
    }

    public String getOhje() {
        return ohje;
    }
    
    public void setOhje(String ohjerivi) {
        this.ohje = ohjerivi;
    }
    
    public void setAinesosat(Map<Aines, ReseptiAines> map) {
        this.ainesosat = map;
    }

    public void setVegaaninen(boolean vegaaninen) {
        this.vegaaninen = vegaaninen;
    }
    
    public boolean isVegaaninen() {
        return !ainesosat.keySet().stream().anyMatch(e -> !(e.isVegaaninen()));
    }
    
    public void addAinesosa(int id, int tyyppi, String nimi, int yksikko, int maara) {
        Aines aines = new Aines(id, tyyppi, nimi);
        ReseptiAines resaines = new ReseptiAines(this.id, id, yksikko, maara, this.ainesosat.entrySet().size());
        this.ainesosat.put(aines, resaines);
    }
    
    @Override
    public String toString() {
        return this.nimi + " (" + this.annoksia + " annosta)\n" + this.ohje + "\n";
    }
    
}
