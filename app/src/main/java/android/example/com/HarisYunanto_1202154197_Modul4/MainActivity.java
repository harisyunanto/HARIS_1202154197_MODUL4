package android.example.com.HarisYunanto_1202154197_Modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Deklarasikan Variabel
    Button mListNama, mPencariGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referensikan Variabel
        mListNama       = (Button) findViewById(R.id.button_listnama);
        mPencariGambar  = (Button) findViewById(R.id.button_pencarigambar);

        //Berikan Listener untuk Setiap Button
        mListNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Membuat Intent dari Aktivitas ini ke Aktivitas List Nama
                Intent intent = new Intent(MainActivity.this,ListNamaActivity.class);
                //Jalankan intent
                startActivity(intent);
            }
        });
        mPencariGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Membuat Intent dari Aktivitas ini ke Akativitas Pencari Gambar
                Intent intent = new Intent(MainActivity.this,PencariGambarActivity.class);
                //Jalankan Intent
                startActivity(intent);
            }
        });
    }
}
