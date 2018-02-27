package tikape.runko.domain;

public enum Tyyppi {
    HEDELMAT_JA_VIHANNEKSET,
    PALKOKASVIT, 
    PAHKINAT_JA_SIEMENET,
    VILJATUOTTEET,
    MUUT_KASVISTUOTTEET,
    MAITOTUOTTEET, 
    LIHATUOTTEET, 
    KALA_JA_ÄYRIÄISET,
    RASVAT_JA_ÖLJYT, 
    MAUSTEET_JA_YRTIT,
    KASTIKKEET,
    MAKEUTUS,
    MUUT;
    
    @Override    
    public String toString() {
        return super.toString().toLowerCase();
    }
}