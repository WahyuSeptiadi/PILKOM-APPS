package mobile.dev.pickmatkulsqlite.Mahasiswa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import mobile.dev.pickmatkulsqlite.R;
import mobile.dev.pickmatkulsqlite.DBHelper.DatabaseHelper;
import mobile.dev.pickmatkulsqlite.DBHelper.MahasiswaHelper;

/**
 * Created by Wahyu_Septiadi on 06,February 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.CustomViewHolder> implements Filterable {

    private LayoutInflater mInflater;
    private ArrayList<MahasiswaModel> mahasiswa;
    private Context context;
    private DatabaseHelper mydb;
    private MahasiswaHelper mahasiswaHelper;

    private List<MahasiswaModel> exampleListFull;

    public MahasiswaAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mahasiswaHelper = new MahasiswaHelper(context);
    }

    @Override
    public MahasiswaAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mydb = new DatabaseHelper(parent.getContext());
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_mahasiswa, parent, false);

        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MahasiswaAdapter.CustomViewHolder holder, final int position) {
        final String nama = mahasiswa.get(position).getName();
        final String nim = mahasiswa.get(position).getNim();
        final String prodi = mahasiswa.get(position).getProdi();
        final String fakultas = mahasiswa.get(position).getFakultas();

        holder.nama.setText(nama);
        holder.nim.setText(nim);
        holder.prodi.setText(prodi);
        holder.fakultas.setText(fakultas);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(mahasiswa.get(position).getId());
                mahasiswa.remove(position);
                notifyDataSetChanged();
                holder.nama.setEnabled(false);
                holder.nim.setEnabled(false);
                holder.prodi.setEnabled(false);
                holder.fakultas.setEnabled(false);
                holder.edit.setVisibility(View.VISIBLE);
                holder.update.setVisibility(View.INVISIBLE);
                holder.delete.setVisibility(View.INVISIBLE);
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mahasiswa.get(position).setName(holder.nama.getText().toString());
                mahasiswa.get(position).setNim(holder.nim.getText().toString());
                mahasiswa.get(position).setProdi(holder.prodi.getText().toString());
                mahasiswa.get(position).setFakultas(holder.fakultas.getText().toString());

                mahasiswaHelper.open();
                mahasiswaHelper.update(mahasiswa.get(position));
                mahasiswaHelper.close();

                holder.nama.setEnabled(false);
                holder.nim.setEnabled(false);
                holder.prodi.setEnabled(false);
                holder.fakultas.setEnabled(false);

                Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                holder.update.setVisibility(View.INVISIBLE);
                holder.delete.setVisibility(View.INVISIBLE);
                holder.edit.setVisibility(View.VISIBLE);
            }
        });

//        holder.cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.nama.setEnabled(true);
//                holder.nim.setEnabled(true);
//            }
//        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.update.setVisibility(View.VISIBLE);
                holder.delete.setVisibility(View.VISIBLE);
                holder.edit.setVisibility(View.INVISIBLE);
                holder.nama.setEnabled(true);
                holder.nim.setEnabled(true);
                holder.prodi.setEnabled(true);
                holder.fakultas.setEnabled(true);
            }
        });

        holder.showCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "belum jadi bro", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        EditText nama, nim, prodi, fakultas;
        TextView showCourse;
        ImageView profile, delete, update, edit;
        CardView cv;

        public CustomViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tvmatkul);
            nim = itemView.findViewById(R.id.tvnim);
            prodi = itemView.findViewById(R.id.tvwaktu);
            fakultas = itemView.findViewById(R.id.fakultas_mhs);

            cv = itemView.findViewById(R.id.cardView);
            delete = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);
            edit = itemView.findViewById(R.id.enableEdit);
            showCourse = itemView.findViewById(R.id.show_course);
        }
    }

    @Override
    public int getItemCount() {
        return mahasiswa.size();
    }

    public void addItem(ArrayList<MahasiswaModel> mData) {
        this.mahasiswa = mData;
        notifyDataSetChanged();
    }

    private void deleteItem(int id) {
        mahasiswaHelper.open();
        mahasiswaHelper.delete(id);
        mahasiswaHelper.close();

        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
    }

    MahasiswaAdapter(ArrayList<MahasiswaModel> exampleList) {
        this.mahasiswa = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }

    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MahasiswaModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (MahasiswaModel item : exampleListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mahasiswa.clear();
            results.values = new ArrayList<>();
            mahasiswa.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
