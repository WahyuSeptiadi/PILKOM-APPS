package mobile.dev.pickmatkulsqlite.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mobile.dev.pickmatkulsqlite.MataKuliah.MataKuliahModel;

import static android.provider.BaseColumns._ID;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.TABLE_MATAKULIAH;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.MATKULKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.HARIKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.WAKTUKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.TEMPATKU;
import static mobile.dev.pickmatkulsqlite.DBHelper.DatabaseContract.MataKuliahColomns.NIMKU;

/**
 * Created by Wahyu_Septiadi on 09,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class MataKuliahHelper {

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public MataKuliahHelper(Context context) {
        this.context = context;
    }

    public MataKuliahHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public long insert(MataKuliahModel test) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(MATKULKU, test.getMatkulku());
        initialValues.put(HARIKU, test.getHariku());
        initialValues.put(WAKTUKU, test.getWaktuku());
        initialValues.put(TEMPATKU, test.getTempatku());
        initialValues.put(NIMKU, test.getNimku());

        return database.insert(TABLE_MATAKULIAH, null, initialValues);
    }

    public int update(MataKuliahModel mataKuliahModel) {
        ContentValues args = new ContentValues();
        args.put(MATKULKU, mataKuliahModel.getMatkulku());
        args.put(HARIKU, mataKuliahModel.getHariku());
        args.put(WAKTUKU, mataKuliahModel.getWaktuku());
        args.put(TEMPATKU, mataKuliahModel.getTempatku());
        args.put(NIMKU, mataKuliahModel.getNimku());

        return database.update(TABLE_MATAKULIAH, args, _ID + "= '" + mataKuliahModel.getId() + "'", null);
    }

    public int delete(int id) {
        return database.delete(TABLE_MATAKULIAH, _ID + " = '" + id + "'", null);
    }

    public void close() {
        databaseHelper.close();
    }

    public ArrayList<MataKuliahModel> getAllData(){
        Cursor cursor = database.query(TABLE_MATAKULIAH, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        ArrayList<MataKuliahModel> arrayList = new ArrayList<>();
        MataKuliahModel mataKuliahModel;
        if (cursor.getCount() > 0){
            do{
                mataKuliahModel = new MataKuliahModel();
                mataKuliahModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                mataKuliahModel.setMatkulku(cursor.getString(cursor.getColumnIndexOrThrow(MATKULKU)));
                mataKuliahModel.setHariku(cursor.getString(cursor.getColumnIndexOrThrow(HARIKU)));
                mataKuliahModel.setWaktuku(cursor.getString(cursor.getColumnIndexOrThrow(WAKTUKU)));
                mataKuliahModel.setTempatku(cursor.getString(cursor.getColumnIndexOrThrow(TEMPATKU)));
                mataKuliahModel.setNimku(cursor.getString(cursor.getColumnIndexOrThrow(NIMKU)));

                arrayList.add(mataKuliahModel);
                cursor.moveToNext();

            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<MataKuliahModel> searchData(String searchString){
//        String rawQuery = "SELECT * FROM " + TABLE_TEST + " JOIN " + TABLE_MAHASISWA +
//                        " ON " + NIMKU + " = " + DatabaseContract.MahasiswaColomns.NIM_MHS +
//                        " WHERE " + NIMKU + "LIKE '%" + searchString + "%'";

        String rawQuery = "SELECT * FROM " + TABLE_MATAKULIAH +
                " WHERE " + NIMKU + " LIKE '%" + searchString + "%'";

        Cursor cursor = database.rawQuery(rawQuery,null);

        cursor.moveToFirst();
        ArrayList<MataKuliahModel> arrayList = new ArrayList<>();

        MataKuliahModel cariNim;

        if (cursor.getCount() > 0){
            do {
                cariNim = new MataKuliahModel();
                cariNim.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                cariNim.setMatkulku(cursor.getString(cursor.getColumnIndexOrThrow(MATKULKU)));
                cariNim.setHariku(cursor.getString(cursor.getColumnIndexOrThrow(HARIKU)));
                cariNim.setWaktuku(cursor.getString(cursor.getColumnIndexOrThrow(WAKTUKU)));
                cariNim.setTempatku(cursor.getString(cursor.getColumnIndexOrThrow(TEMPATKU)));
                cariNim.setNimku(cursor.getString(cursor.getColumnIndexOrThrow(NIMKU)));

                arrayList.add(cariNim);
                cursor.moveToFirst();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
}
