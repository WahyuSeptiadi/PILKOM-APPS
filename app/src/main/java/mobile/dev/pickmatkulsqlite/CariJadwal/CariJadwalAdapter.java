package mobile.dev.pickmatkulsqlite.CariJadwal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import mobile.dev.pickmatkulsqlite.Mahasiswa.MahasiswaModel;
import mobile.dev.pickmatkulsqlite.R;
import mobile.dev.pickmatkulsqlite.DBHelper.CariMatkulKuhHelper;
import mobile.dev.pickmatkulsqlite.DBHelper.DatabaseHelper;

/**
 * Created by Wahyu_Septiadi on 07,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class CariJadwalAdapter extends RecyclerView.Adapter<CariJadwalAdapter.CustomViewHolder>{

    private LayoutInflater mInflater;
    private ArrayList<CariJadwalModel> cariModel;
    private ArrayList<MahasiswaModel> cariNama;
    private Context context;
    private DatabaseHelper mydb;
    private CariMatkulKuhHelper cariMatkulKuhHelper;

    public CariJadwalAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cariMatkulKuhHelper = new CariMatkulKuhHelper(context);
    }

    @Override
    public CariJadwalAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mydb = new DatabaseHelper(parent.getContext());
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_carijadwal, parent, false);

        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CariJadwalAdapter.CustomViewHolder holder, final int position) {
        final String matkul = cariModel.get(position).getMatkulku();
        final String hari = cariModel.get(position).getHariku();
        final String waktu = cariModel.get(position).getWaktuku();
        final String tempat = cariModel.get(position).getTempatku();
        final String nim = cariModel.get(position).getNimku();

        holder.matkul.setText(matkul);
        holder.hari.setText(hari);
        holder.waktu.setText(waktu);
        holder.tempat.setText(tempat);
        holder.nim.setText(nim);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView matkul, hari, waktu, tempat, nim;
        CardView cv;

        public CustomViewHolder(View itemView) {
            super(itemView);
            matkul = itemView.findViewById(R.id.tvmatkul);
            hari = itemView.findViewById(R.id.tvhari);
            waktu = itemView.findViewById(R.id.tvwaktu);
            tempat = itemView.findViewById(R.id.tvtempat);
            nim = itemView.findViewById(R.id.tvnim);

            cv = itemView.findViewById(R.id.cardView);
        }
    }

    @Override
    public int getItemCount() {
        return cariModel.size();
    }

    public void addItem(ArrayList<CariJadwalModel> mData) {
        this.cariModel = mData;
        notifyDataSetChanged();
    }

    public void ambilItem(ArrayList<MahasiswaModel> mData) {
        this.cariNama = mData;
        notifyDataSetChanged();
    }
}
