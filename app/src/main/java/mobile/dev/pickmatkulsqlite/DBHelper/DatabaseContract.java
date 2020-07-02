package mobile.dev.pickmatkulsqlite.DBHelper;

import android.provider.BaseColumns;

/**
 * Created by Wahyu_Septiadi on 06,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class DatabaseContract {
    public static final String TABLE_MAHASISWA = "mahasiswa";
    public static final String TABLE_MATAKULIAH = "matakuliah";

    static final class MahasiswaColomns implements BaseColumns{
        public static final String NIM_MHS = "nim_mhs";
        public static final String NAMA_MHS = "nama_mhs";
        public static final String PRODI = "prodi";
        public static final String FAKULTAS = "fakultas";
    }

    static final class MataKuliahColomns implements BaseColumns{
        public static final String MATKULKU = "matkulku";
        public static final String HARIKU = "hariku";
        public static final String WAKTUKU = "waktuku";
        public static final String TEMPATKU = "tempatku";
        public static final String NIMKU = "nimku";
    }
}
