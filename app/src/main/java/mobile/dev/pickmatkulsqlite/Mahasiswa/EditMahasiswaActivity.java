package mobile.dev.pickmatkulsqlite.Mahasiswa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import mobile.dev.pickmatkulsqlite.R;
import mobile.dev.pickmatkulsqlite.DBHelper.MahasiswaHelper;

/**
 * Created by Wahyu_Septiadi on 06,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class EditMahasiswaActivity extends AppCompatActivity {

    private EditText nama, nim, prodi, fakultas;
    private Button tambah;
    private MahasiswaAdapter mahasiswaAdapter;
    private MahasiswaHelper mahasiswaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mahasiswa);

        nama = findViewById(R.id.edit_nama);
        nim = findViewById(R.id.tvnim);
        prodi = findViewById(R.id.edit_prodi);
        fakultas = findViewById(R.id.edit_fakultas);

        tambah = findViewById(R.id.btn_add);

        mahasiswaHelper = new MahasiswaHelper(this);
        mahasiswaAdapter = new MahasiswaAdapter(this);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                Intent intent = new Intent(view.getContext(), MahasiswaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void insertData() {
        mahasiswaHelper.open();
        MahasiswaModel mahasiswa = new MahasiswaModel(nim.getText().toString(),
                                                    nama.getText().toString(),
                                                    prodi.getText().toString(),
                                                    fakultas.getText().toString());
        mahasiswaHelper.insert(mahasiswa);
        mahasiswaHelper.close();
    }
}
