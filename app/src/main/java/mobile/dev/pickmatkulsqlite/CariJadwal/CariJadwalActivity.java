package mobile.dev.pickmatkulsqlite.CariJadwal;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mobile.dev.pickmatkulsqlite.R;
import mobile.dev.pickmatkulsqlite.DBHelper.CariMatkulKuhHelper;
import mobile.dev.pickmatkulsqlite.DBHelper.DatabaseHelper;

/**
 * Created by Wahyu_Septiadi on 06,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class CariJadwalActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private CariMatkulKuhHelper cariHelper;
    private CariJadwalAdapter cariAdapter;
    private DatabaseHelper databaseHelper;

    EditText search;
    ImageView search_btn;
    TextView hi, ambilnama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_carijadwal);
        setTitle("");

        recyclerView = findViewById(R.id.rv_matkul);
        hi = findViewById(R.id.hi);
        ambilnama = findViewById(R.id.ambil_nama);

        cariAdapter = new CariJadwalAdapter(this);
        cariHelper = new CariMatkulKuhHelper(this);
        databaseHelper = new DatabaseHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        search = findViewById(R.id.search);
        search_btn = findViewById(R.id.search_4anything);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hi.setVisibility(View.VISIBLE);
                searchData(search.getText().toString());
                Toast.makeText(CariJadwalActivity.this, "Searching ..", Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void searchData(String nim){
        cariHelper.open();
        ArrayList<CariJadwalModel> search = cariHelper.searchData(nim);
        cariHelper.close();
        cariAdapter.addItem(search);
        recyclerView.setAdapter(cariAdapter);
    }
}
