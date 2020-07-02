package mobile.dev.pickmatkulsqlite.Mahasiswa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mobile.dev.pickmatkulsqlite.R;
import mobile.dev.pickmatkulsqlite.DBHelper.DatabaseHelper;
import mobile.dev.pickmatkulsqlite.DBHelper.MahasiswaHelper;

/**
 * Created by Wahyu_Septiadi on 06,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class MahasiswaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView listMahasiswa;

    private MahasiswaHelper mahasiswaHelper;
    private MahasiswaAdapter mahasiswaAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_mahasiswa);
        setTitle("");
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        recyclerView = findViewById(R.id.rv_mhs);
        listItem = new ArrayList<>();
        listMahasiswa = findViewById(R.id.listView);

        mahasiswaAdapter = new MahasiswaAdapter(this);
        mahasiswaHelper = new MahasiswaHelper(this);
        databaseHelper = new DatabaseHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        getAllData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditMahasiswaActivity.class);
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
        mahasiswaHelper.open();
        ArrayList<MahasiswaModel> mahasiswaModels = mahasiswaHelper.getAllData();
        mahasiswaHelper.close();
        mahasiswaAdapter.addItem(mahasiswaModels);
        recyclerView.setAdapter(mahasiswaAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerView.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> mahasiswaList = new ArrayList<>();

                for (String user : listItem){
                    if (user.toLowerCase().contains(newText.toLowerCase())){
                        mahasiswaList.add(user);
                    }
                }
                recyclerView.setVisibility(View.GONE);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MahasiswaActivity.this,
                        android.R.layout.simple_list_item_1, mahasiswaList);
                listMahasiswa.setAdapter(adapter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
