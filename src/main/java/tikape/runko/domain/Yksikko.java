/* @author joa */

package tikape.runko.domain;

public enum Yksikko {
    ML("millilitraa [tilavuus]"),
    TL("teelusikallista (5 ml) [tilavuus]"),
    RKL("ruokalusikallista (15 ml) [tilavuus]"),
    DL("desilitraa (100 ml) [tilavuus]"),
    L("litraa (10 dl) [tilavuus]"),
    
    G("grammaa [paino]"),
    KG("kilogrammaa [paino]"),
    
    KPL("[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]"),
    PKT("[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]"), 
    PIENI("[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]"), 
    KESKIKOKOINEN ("[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]"),
    ISO("[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]"), 
    RIPAUS("[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]"),
    LORAUS ("[ruoka-ainekohtaisia/suhteellisia mittayksiköitä]");

    private final String kuvaus;    

    Yksikko(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public String getKuvaus() {
        return kuvaus;
    }
    
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
