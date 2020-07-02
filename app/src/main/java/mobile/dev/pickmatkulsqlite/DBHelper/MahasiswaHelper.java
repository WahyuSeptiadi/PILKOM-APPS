package mobile.dev.pickmatkulsqlite.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mobile.dev.pickmatkulsqlite.Mahasiswa.MahasiswaModel;
import mobile.dev.pickmatkulsqlite.MataKuliah.MataKuliahModel;

import static android.provider.BaseColumns._ID;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.TABLE_MAHASISWA;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MahasiswaColomns.NIM_MHS;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MahasiswaColomns.NAMA_MHS;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MahasiswaColomns.PRODI;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MahasiswaColomns.FAKULTAS;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.TABLE_MATAKULIAH;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.MATKULKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.HARIKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.WAKTUKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.TEMPATKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.NIMKU;

/**
 * Created by Wahyu_Septiadi on 06,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class MahasiswaHelper {
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public MahasiswaHelper(Context context) {
        this.context = context;
    }

    public MahasiswaHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<MahasiswaModel> getAllData() {
        Cursor cursor = database.query(TABLE_MAHASISWA, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        ArrayList<MahasiswaModel> arrayList = new ArrayList<>();
        MahasiswaModel mahasiswaModel;
        if (cursor.getCount() > 0) {
            do {
                mahasiswaModel = new MahasiswaModel();
                mahasiswaModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                mahasiswaModel.setNim(cursor.getString(cursor.getColumnIndexOrThrow(NIM_MHS)));
                mahasiswaModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAMA_MHS)));
                mahasiswaModel.setProdi(cursor.getString(cursor.getColumnIndexOrThrow(PRODI)));
                mahasiswaModel.setFakultas(cursor.getString(cursor.getColumnIndexOrThrow(FAKULTAS)));

                arrayList.add(mahasiswaModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(MahasiswaModel mahasiswaModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NIM_MHS, mahasiswaModel.getNim());
        initialValues.put(NAMA_MHS, mahasiswaModel.getName());
        initialValues.put(PRODI, mahasiswaModel.getProdi());
        initialValues.put(FAKULTAS, mahasiswaModel.getFakultas());

        return database.insert(TABLE_MAHASISWA, null, initialValues);
    }

    public int update(MahasiswaModel mahasiswaModel) {
        ContentValues args = new ContentValues();
        args.put(NIM_MHS, mahasiswaModel.getNim());
        args.put(NAMA_MHS, mahasiswaModel.getName());
        args.put(PRODI, mahasiswaModel.getProdi());
        args.put(FAKULTAS, mahasiswaModel.getFakultas());

        return database.update(TABLE_MAHASISWA, args, _ID + "= '" + mahasiswaModel.getId() + "'", null);
    }

    public int delete(int id) {
        return database.delete(TABLE_MAHASISWA, _ID + " = '" + id + "'", null);
    }
}
