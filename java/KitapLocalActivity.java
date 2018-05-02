package com.example.taner.cep_kutuphanem;
import android.media.AudioManager;
import android.os.Build;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.InputStreamReader;



import java.io.InputStream;

import java.util.HashMap;
import java.util.Locale;

public class KitapLocalActivity extends AppCompatActivity {
    TextToSpeech tts;
    TextView mTextView;
    String data="";
    StringBuffer s = new StringBuffer();
    InputStream is ;
    BufferedReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_local);
        Button mButton;
        mButton = (Button) findViewById(R.id.buttonLokal);
        mTextView = (TextView) findViewById(R.id.textLokal);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)

        {
            if (bundle.getString("bk1").toString().equalsIgnoreCase("6 Kogus - Anton Cehov")) {
                is = this.getResources().openRawResource(R.raw.a1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Candide - Voltaire")) {
                is = this.getResources().openRawResource(R.raw.c1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Dövüş Kulübü - Chuck Palahniuk")) {
                is = this.getResources().openRawResource(R.raw.d14);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Fahrenheit 451 - Ray Bradbury")) {
                is = this.getResources().openRawResource(R.raw.f1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Garih Öldüren Sır - Abdullah Muradoğlu")) {
                is = this.getResources().openRawResource(R.raw.g1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Hastalık Hastası - Moliere")) {
                is = this.getResources().openRawResource(R.raw.h1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("İçimizdeki Şeytan - Sabahattin Ali")) {
                is = this.getResources().openRawResource(R.raw.i1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Kar Tatili - Emmanuel Carrere")) {
                is = this.getResources().openRawResource(R.raw.k1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Madame Bovary - Gustave Flaubert")) {
                is = this.getResources().openRawResource(R.raw.m1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Matrix ve Felsefe - William Irwin")) {
                is = this.getResources().openRawResource(R.raw.m2);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Nietzsche ve Felsefe - Deleuze")) {
                is = this.getResources().openRawResource(R.raw.n1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Riza Beyin Polisiye Oykuleri - Cetin Altan")) {
                is = this.getResources().openRawResource(R.raw.r1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Saatleri Ayarlama Enstitüsü - Ahmet Hamdi Tanpınar")) {
                is = this.getResources().openRawResource(R.raw.s1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Toprak Ana - Cengiz Aytmatov")) {
                is = this.getResources().openRawResource(R.raw.t1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Uc Kisa Oyun - Luigi Pirandello")) {
                is = this.getResources().openRawResource(R.raw.u1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Ve Ayna Kirildi - Agatha Christie")) {
                is = this.getResources().openRawResource(R.raw.v1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Yabancı - Albert Camus")) {
                is = this.getResources().openRawResource(R.raw.y1);
            }
            if (bundle.getString("bk1").toString().equalsIgnoreCase("Zaman ve Varlik Uzerine - Heidegger")) {
                is = this.getResources().openRawResource(R.raw.z1);
            }

            if(reader==null){
                reader = new BufferedReader(new InputStreamReader(is));
            }
            if(is != null) {
                try {

                    while ((data = reader.readLine()) != null) {

                        s.append(data + "\n  ");
                    }
                    mTextView.setText(s);
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            tts = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        tts.setLanguage(new Locale("tr","TR"));
                      /*  */
                    }
                    else { Log.e("HATA","Okuma Hatası Oluştu"); }
                }
            });
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        String D = s.toString();
                        tts.speak(D.substring(0,3500).toString(),TextToSpeech.QUEUE_FLUSH,null,null);
                    }
                }
            });
        }

    }
    void leerTexto(String strTexto){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Bundle bundle = new Bundle();
            bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_MUSIC);
            strTexto = strTexto.substring(0,3999); //Así funciona
            tts.speak(strTexto, TextToSpeech.QUEUE_FLUSH, bundle, null);
        } else {

            HashMap<String, String> param = new HashMap<>();
            param.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_MUSIC));
            tts.speak(strTexto, TextToSpeech.QUEUE_FLUSH, param);
        }
    }
    public void ttsFunction() {
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {

                    int result = tts.setLanguage(new Locale("tr","TR"));
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "Lenguaje no soportado", Toast.LENGTH_SHORT).show();
                    } else {

                        final TextView mTextView = (TextView) findViewById(R.id.textLokal);

                        String strTexto = mTextView.getText().toString();
                        leerTexto(strTexto);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Falló la inicialización", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void onPause(){
        if(tts!=null){
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}
