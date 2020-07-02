package mobile.dev.pickmatkulsqlite;

import androidx.appcompat.app.AppCompatActivity;
import mobile.dev.pickmatkulsqlite.Mahasiswa.MahasiswaActivity;
import mobile.dev.pickmatkulsqlite.CariJadwal.CariJadwalActivity;
import mobile.dev.pickmatkulsqlite.MataKuliah.MataKuliahActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mhs, mkl, test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("PILKOM APPS");

        mhs = findViewById(R.id.mahasiswa);
        mkl = findViewById(R.id.matakuliah);
        test = findViewById(R.id.tes);

        mhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MahasiswaActivity.class);
                startActivity(intent);
            }
        });

        mkl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CariJadwalActivity.class);
                startActivity(intent);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MataKuliahActivity.class);
                startActivity(intent);
            }
        });
    }
}
