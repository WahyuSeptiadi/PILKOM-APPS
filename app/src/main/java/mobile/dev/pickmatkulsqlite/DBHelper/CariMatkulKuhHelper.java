package mobile.dev.pickmatkulsqlite.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mobile.dev.pickmatkulsqlite.CariJadwal.CariJadwalModel;
import mobile.dev.pickmatkulsqlite.Mahasiswa.MahasiswaModel;

import static android.provider.BaseColumns._ID;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.TABLE_MAHASISWA;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MahasiswaColomns.NIM_MHS;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MahasiswaColomns.NAMA_MHS;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.TABLE_MATAKULIAH;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.MATKULKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.HARIKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.WAKTUKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.TEMPATKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.NIMKU;

/**
 * Created by Wahyu_Septiadi on 07,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class CariMatkulKuhHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public CariMatkulKuhHelper(Context context) {
        this.context = context;
    }

    public CariMatkulKuhHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public ArrayList<CariJadwalModel> searchData(String searchString){

        String rawQuery = "SELECT * FROM " + TABLE_MATAKULIAH + " JOIN " + TABLE_MAHASISWA +
                        " ON " + NIMKU + " = " + DatabaseContract.MahasiswaColomns.NIM_MHS +
                        " WHERE " + NIMKU + " LIKE '%" + searchString + "'" +
                        " ORDER BY " + HARIKU + " ASC ";

        Cursor cursor = database.rawQuery(rawQuery,null);

        cursor.moveToFirst();
        ArrayList<CariJadwalModel> arrayList = new ArrayList<>();

        CariJadwalModel cariNim;

        if (cursor.getCount() > 0){
            do {
                cariNim = new CariJadwalModel();
                cariNim.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                cariNim.setMatkulku(cursor.getString(cursor.getColumnIndexOrThrow(MATKULKU)));
                cariNim.setHariku(cursor.getString(cursor.getColumnIndexOrThrow(HARIKU)));
                cariNim.setWaktuku(cursor.getString(cursor.getColumnIndexOrThrow(WAKTUKU)));
                cariNim.setTempatku(cursor.getString(cursor.getColumnIndexOrThrow(TEMPATKU)));
                cariNim.setNimku(cursor.getString(cursor.getColumnIndexOrThrow(NIMKU)));

                arrayList.add(cariNim);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<MahasiswaModel> ambilNama(String searchString){
        String rawQuery = "SELECT "+NAMA_MHS+" FROM " + TABLE_MAHASISWA +
                " WHERE " + NIM_MHS + " LIKE '%" + searchString + "%'";

        Cursor cursor = database.rawQuery(rawQuery,null);

        cursor.moveToFirst();
        ArrayList<MahasiswaModel> arrayList = new ArrayList<>();

        MahasiswaModel cariNama;

        if (cursor.getCount() > 0){
            do {
                cariNama = new MahasiswaModel();
                cariNama.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAMA_MHS)));

                arrayList.add(cariNama);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
}
