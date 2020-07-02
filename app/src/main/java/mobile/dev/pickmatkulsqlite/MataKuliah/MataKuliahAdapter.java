package mobile.dev.pickmatkulsqlite.MataKuliah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import mobile.dev.pickmatkulsqlite.DBHelper.MataKuliahHelper;
import mobile.dev.pickmatkulsqlite.R;
import mobile.dev.pickmatkulsqlite.DBHelper.DatabaseHelper;

/**
 * Created by Wahyu_Septiadi on 09,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class MataKuliahAdapter extends RecyclerView.Adapter<MataKuliahAdapter.CustomViewHolder> implements Filterable {

    private LayoutInflater layoutInflater;
    private Context context;

    private DatabaseHelper mydb;
    private MataKuliahHelper mataKuliahHelper;

    private ArrayList<MataKuliahModel> mataKuliahModels;

    public MataKuliahAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mataKuliahHelper = new MataKuliahHelper(context);
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public MataKuliahAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mydb = new DatabaseHelper(parent.getContext());

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_matakuliah, parent, false);

        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MataKuliahAdapter.CustomViewHolder holder, final int position) {
        final String matkulku = mataKuliahModels.get(position).getMatkulku();
        final String hariku = mataKuliahModels.get(position).getHariku();
        final String waktuku = mataKuliahModels.get(position).getWaktuku();
        final String tempatku = mataKuliahModels.get(position).getTempatku();

        holder.matkul.setText(matkulku);
        holder.hari.setText(hariku);
        holder.waktu.setText(waktuku);
        holder.tempat.setText(tempatku);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(mataKuliahModels.get(position).getId());
                mataKuliahModels.remove(position);
                notifyDataSetChanged();

                holder.matkul.setEnabled(false);
                holder.hari.setEnabled(false);
                holder.waktu.setEnabled(false);
                holder.tempat.setEnabled(false);

                holder.edit.setVisibility(View.VISIBLE);
                holder.update.setVisibility(View.INVISIBLE);
                holder.delete.setVisibility(View.INVISIBLE);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.matkul.setEnabled(true);
                holder.hari.setEnabled(true);
                holder.waktu.setEnabled(true);
                holder.tempat.setEnabled(true);

                holder.edit.setVisibility(View.INVISIBLE);
                holder.update.setVisibility(View.VISIBLE);
                holder.delete.setVisibility(View.VISIBLE);
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mataKuliahModels.get(position).setMatkulku(holder.matkul.getText().toString());
                mataKuliahModels.get(position).setHariku(holder.hari.getText().toString());
                mataKuliahModels.get(position).setWaktuku(holder.waktu.getText().toString());
                mataKuliahModels.get(position).setTempatku(holder.tempat.getText().toString());

                mataKuliahHelper.open();
                mataKuliahHelper.update(mataKuliahModels.get(position));
                mataKuliahHelper.close();

                holder.matkul.setEnabled(false);
                holder.hari.setEnabled(false);
                holder.waktu.setEnabled(false);
                holder.tempat.setEnabled(false);

                Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();

                holder.update.setVisibility(View.INVISIBLE);
                holder.delete.setVisibility(View.INVISIBLE);
                holder.edit.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mataKuliahModels.size();
    }

    public void addItem(ArrayList<MataKuliahModel> mData) {
        this.mataKuliahModels = mData;
        notifyDataSetChanged();
    }

    private void deleteItem(int id) {
        mataKuliahHelper.open();
        mataKuliahHelper.delete(id);
        mataKuliahHelper.close();

        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        EditText matkul, hari, waktu, tempat, nim;
        ImageView delete, update, edit;
        CardView cv;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            matkul = itemView.findViewById(R.id.tvmatkul);
            hari = itemView.findViewById(R.id.tvhari);
            waktu = itemView.findViewById(R.id.tvwaktu);
            tempat = itemView.findViewById(R.id.tvtempat);

            cv = itemView.findViewById(R.id.cardView);
            delete = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);
            edit = itemView.findViewById(R.id.enableEdit);
        }
    }
}
