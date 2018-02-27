/* @author joa */

package tikape.runko.domain;

public class ReseptiAines implements Comparable {
    private Integer resepti_id;
    private Integer aines_id;
    private Yksikko yksikko_id;
    private Float maara;
    private Integer jarjestys;
    
    public ReseptiAines(int resepti_id, int aines_id, int yksikko_id, float maara, int jarjestys) {
        this.resepti_id = resepti_id;
        this.aines_id = aines_id;
        this.yksikko_id = Yksikko.values()[yksikko_id - 1];
        this.maara = maara;
        this.jarjestys = jarjestys;
    }

    public Integer getResepti_id() {
        return resepti_id;
    }

    public Integer getAines_id() {
        return aines_id;
    }

    public Integer getJarjestys() {
        return jarjestys;
    }

    public Float getMaara() {
        return maara;
    }

    public Yksikko getYksikko() {
        return yksikko_id;
    }

    public void setResepti_id(Integer resepti_id) {
        this.resepti_id = resepti_id;
    }

    public void setAines_id(Integer aines_id) {
        this.aines_id = aines_id;
    }

    public void setJarjestys(Integer jarjestys) {
        this.jarjestys = jarjestys;
    }

    public void setMaara(Float maara) {
        this.maara = maara;
    }

    public void setYksikko_id(int yksikko) {
        this.yksikko_id = Yksikko.values()[yksikko];
    }
    
    @Override 
    public String toString() {
        return this.maara + " " + this.yksikko_id.toString().toLowerCase();
    }

    @Override
    public int compareTo(Object o) {
        ReseptiAines r =  (ReseptiAines) o;
        if (r.jarjestys < this.jarjestys) {
            return 1;
        } else if (r.jarjestys > this.jarjestys) {
            return -1;
        } else {
            return 0;
        }
    }

    
}
