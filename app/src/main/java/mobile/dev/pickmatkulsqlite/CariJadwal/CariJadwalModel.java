package mobile.dev.pickmatkulsqlite.CariJadwal;

/**
 * Created by Wahyu_Septiadi on 07,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class CariJadwalModel {
    private int id;
    private String matkulku, hariku, waktuku, tempatku, nimku;

    public CariJadwalModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatkulku() {
        return matkulku;
    }

    public void setMatkulku(String matkulku) {
        this.matkulku = matkulku;
    }

    public String getHariku() {
        return hariku;
    }

    public void setHariku(String hariku) {
        this.hariku = hariku;
    }

    public String getWaktuku() {
        return waktuku;
    }

    public void setWaktuku(String waktuku) {
        this.waktuku = waktuku;
    }

    public String getTempatku() {
        return tempatku;
    }

    public void setTempatku(String tempatku) {
        this.tempatku = tempatku;
    }

    public String getNimku() {
        return nimku;
    }

    public void setNimku(String nimku) {
        this.nimku = nimku;
    }
}
