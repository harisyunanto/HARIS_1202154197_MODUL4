package android.example.com.HarisYunanto_1202154197_Modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class PencariGambarActivity extends AppCompatActivity {
    //Deklarasikan Variabel
    ImageView mGambar;
    EditText mUrl;
    Button mCari;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gambar);

        //Referensikan Variabel
        mGambar         = (ImageView) findViewById(R.id.imageview_gambar);
        mUrl            = (EditText) findViewById(R.id.edittext_url);
        mCari           = (Button) findViewById(R.id.button_cari);

        //Setting Button Cari Gambar
        mCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get data dari Teks yang Diinputkan dan Eksekusi Link
                String link = mUrl.getText().toString();
                new DownloadImage().execute(link);
            }
        });
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Membuat Progress Dialog
            mProgressDialog = new ProgressDialog(PencariGambarActivity.this);

            //Membuat Judul Progress Dialog
            mProgressDialog.setTitle("Downloading image");

            //Setting Message Progress Dialog
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);

            //Menampilkan Progress Dialog
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];
            Bitmap bitmap = null;

            try {
                //Download Gambar dari URL
                InputStream input = new java.net.URL(imageURL).openStream();
                //Konversian Gambar ke Bitmpap (Decode to Bitmap)
                bitmap = BitmapFactory.decodeStream(input);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // Menampung Gambar ke imageView dan Menampilkannya
            mGambar.setImageBitmap(result);

            // Menghilangkan Progress Dialog
            mProgressDialog.dismiss();
        }
    }
}
