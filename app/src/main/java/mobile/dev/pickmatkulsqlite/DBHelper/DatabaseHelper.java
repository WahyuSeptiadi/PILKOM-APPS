package mobile.dev.pickmatkulsqlite.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.TABLE_MAHASISWA;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MahasiswaColomns.NIM_MHS;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MahasiswaColomns.NAMA_MHS;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MahasiswaColomns.PRODI;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MahasiswaColomns.FAKULTAS;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.TABLE_MATAKULIAH;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.MATKULKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.HARIKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.NIMKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.TEMPATKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.WAKTUKU;

/**
 * Created by Wahyu_Septiadi on 06,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;

    public static String CREATE_TABLE_MAHASISWA = "create table " + TABLE_MAHASISWA +
            " (" + _ID + " integer primary key autoincrement, " +
                    NIM_MHS + " text unique not null, " +
                    NAMA_MHS + " text unique not null, " +
                    PRODI + " text not null, " +
                    FAKULTAS + " text not null ); ";

    public static String CREATE_TABLE_TEST = "create table " + TABLE_MATAKULIAH +
            " (" + _ID + " integer primary key autoincrement, " +
            MATKULKU + " text not null, " +
            HARIKU + " text not null, " +
            WAKTUKU + " text not null," +
            TEMPATKU+" text not null," +
            NIMKU+" text not null," +
            " constraint fk_mahasiswa foreign key "+"("+NIMKU+")" +
            " references "+TABLE_MAHASISWA+" ("+NIM_MHS+") "+");";

    private static String DATABASE_NAME = "dbpilkomcuy";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAHASISWA);
        db.execSQL(CREATE_TABLE_TEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATAKULIAH);
        onCreate(db);
    }
}