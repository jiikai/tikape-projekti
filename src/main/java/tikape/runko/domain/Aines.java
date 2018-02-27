package tikape.runko.domain;
import tikape.runko.domain.Tyyppi;

public class Aines {

    private Integer id;
    Tyyppi tyyppi;
    private String nimi;
    private boolean vegaaninen;

    public Aines(int id, int tyyppiId, String nimi) {
        this.id = id;
        this.tyyppi = Tyyppi.values()[tyyppiId - 1];
        this.nimi = nimi;
        this.vegaaninen = !(this.tyyppi == Tyyppi.MAITOTUOTTEET 
            || this.tyyppi == Tyyppi.LIHATUOTTEET
            || this.tyyppi == Tyyppi.KALA_JA_ÄYRIÄISET);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public String getTyyppiString() {
        return tyyppi.toString();
    }
    
     public Tyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(String tyypinNimi) {
        this.tyyppi = Tyyppi.valueOf(tyypinNimi);
    }

    public void setVegaaninen(boolean vegaaninen) {
        this.vegaaninen = vegaaninen;
    }

    public boolean isVegaaninen() {
        return vegaaninen;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

}
