package mobile.dev.pickmatkulsqlite.Mahasiswa;

/**
 * Created by Wahyu_Septiadi on 06,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class MahasiswaModel {
    private int id;
    private String name, prodi, fakultas, nim;

    public MahasiswaModel() {

    }

    public MahasiswaModel(String nim, String name, String prodi, String fakultas) {
        this.name = name;
        this.nim = nim;
        this.prodi = prodi;
        this.fakultas= fakultas;
    }

    public MahasiswaModel(int id, String nim, String name,  String prodi, String fakultas) {
        this.id = id;
        this.name = name;
        this.nim = nim;
        this.prodi = prodi;
        this.fakultas= fakultas;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getFakultas(){
        return fakultas;
    }

    public void setFakultas(String fakultas){
        this.fakultas = fakultas;
    }
}
