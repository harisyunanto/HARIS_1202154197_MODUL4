package android.example.com.HarisYunanto_1202154197_Modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;


public class ListNamaActivity extends AppCompatActivity {
    //Deklarasikan Variabel
    private ListView mListView;
    private ProgressBar mProgressBar;
    private ItemListView itemListView;
    private Button mMulai;

    //Membuat ArrayList
    private String[] mNamaMahasiswa = {
            "Haris Yunanto",
            "Andhika Saputra",
            "Hifzhil Lisan",
            "Ghali Palito",
            "Nikmatul Akbar",
            "Ilvander",
            "Muhammad Novriyanda",
            "Hafiz Ulya",
            "Kevindra"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama);

        //Referensikan Objek
        mListView       = (ListView) findViewById(R.id.listview_Mahasiswa);
        mProgressBar    = (ProgressBar) findViewById(R.id.pbMahasiswa);
        mMulai          = (Button) findViewById(R.id.button_mulai);

        /*Setting Untuk Setiap Variabel*/

        //Setting ListView menjadi Tidak Tampak
        mListView.setVisibility(View.GONE);

        //Setting Adapter untuk Array
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                new ArrayList<String>()));

        //Setting Listener untuk Button
        mMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListView = new ItemListView();
                itemListView.execute();
            }
        });
    }

    /*Class untuk itemListView*/
    public class ItemListView extends AsyncTask<Void, String, Void> {
        //Deklarasikan dan Inisiasikan Variabel
        private ArrayAdapter<String> mAdapter;
        private int counter = 1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListNamaActivity.this);


        @Override
        protected void onPreExecute() {
            //casting suggestion
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            //Setting untuk Progress Dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

            //Setting untuk Button Cancel saat Diklik
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    itemListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }

        //Memuat Data dengan Durasi Selama 100 milisecond
        @Override
        protected Void doInBackground(Void... params) {
            for (String item : mNamaMahasiswa) {
                publishProgress(item);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isCancelled()) { //jika di klik tombol cancel maka
                    itemListView.cancel(true);
                }
            }
            return null;
        }

        /*Saat Data sedang Dimuat*/
        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            //Menampilkan Status Progress Pemuatan Data dari Array dalam Persen
            Integer current_status = (int) ((counter / (float) mNamaMahasiswa.length) * 100);
            mProgressBar.setProgress(current_status);

            //Mensetting Progress dan Message yang Ditampilkan
            mProgressDialog.setProgress(current_status);
            mProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //Menyembunyikan Progress Bar
            mProgressBar.setVisibility(View.GONE);

            //Menghapus Progress Dialog
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }
    }
}
