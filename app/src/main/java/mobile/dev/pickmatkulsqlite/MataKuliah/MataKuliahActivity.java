package mobile.dev.pickmatkulsqlite.MataKuliah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mobile.dev.pickmatkulsqlite.R;
import mobile.dev.pickmatkulsqlite.DBHelper.DatabaseHelper;
import mobile.dev.pickmatkulsqlite.DBHelper.MataKuliahHelper;

/**
 * Created by Wahyu_Septiadi on 09,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class MataKuliahActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    private MataKuliahHelper mataKuliahHelper;
    private MataKuliahAdapter mataKuliahAdapter;
    private DatabaseHelper databaseHelper;

    ArrayList<MataKuliahModel> mataKuliahModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_matakuliah);
        setTitle("");

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        recyclerView = findViewById(R.id.rv_test);

        mataKuliahModelArrayList = new ArrayList<>();
        mataKuliahAdapter = new MataKuliahAdapter(this);
        mataKuliahHelper = new MataKuliahHelper(this);
        databaseHelper = new DatabaseHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mataKuliahAdapter);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setVisibility(View.VISIBLE);
        getAllData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditMataKuliahActivity.class);
                startActivity(intent);
                finish();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getAllData() {
        mataKuliahHelper.open();
        ArrayList<MataKuliahModel> mahasiswaModels = mataKuliahHelper.getAllData();
        mataKuliahHelper.close();
        mataKuliahAdapter.addItem(mahasiswaModels);
        recyclerView.setAdapter(mataKuliahAdapter);
    }

//    private void searchData(String nim){
//        mataKuliahHelper.open();
//        ArrayList<MataKuliahModel> search = mataKuliahHelper.searchData(nim);
//        mataKuliahHelper.close();
//        mataKuliahAdapter.addItem(search);
//        recyclerView.setAdapter(mataKuliahAdapter);
//    }
}
