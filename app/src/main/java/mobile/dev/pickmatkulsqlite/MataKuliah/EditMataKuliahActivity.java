package mobile.dev.pickmatkulsqlite.MataKuliah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import mobile.dev.pickmatkulsqlite.R;
import mobile.dev.pickmatkulsqlite.DBHelper.MataKuliahHelper;

/**
 * Created by Wahyu_Septiadi on 09,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class EditMataKuliahActivity extends AppCompatActivity {
    private EditText edit_matkul, edit_hari, edit_waktu, edit_tempat, edit_nim;
    private Button addbro;
    private MataKuliahAdapter mataKuliahAdapter;
    private MataKuliahHelper mataKuliahHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_matakuliah);

        edit_matkul = findViewById(R.id.edit_matkulku);
        edit_hari = findViewById(R.id.edit_hariku);
        edit_waktu = findViewById(R.id.edit_waktuku);
        edit_tempat = findViewById(R.id.edit_tempatku);
        edit_nim = findViewById(R.id.edit_nimku);
        addbro = findViewById(R.id.addbro);

        mataKuliahHelper = new MataKuliahHelper(this);
        mataKuliahAdapter = new MataKuliahAdapter(this);

        addbro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                Intent intent = new Intent(v.getContext(), MataKuliahActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void insertData() {
        mataKuliahHelper.open();
        MataKuliahModel test = new MataKuliahModel(edit_matkul.getText().toString(),
                                                edit_hari.getText().toString(),
                                                edit_waktu.getText().toString(),
                                                edit_tempat.getText().toString(),
                                                edit_nim.getText().toString());
        mataKuliahHelper.insert(test);
        mataKuliahHelper.close();
    }
}
