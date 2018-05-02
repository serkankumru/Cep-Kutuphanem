package com.example.taner.cep_kutuphanem;

import android.media.AudioManager;
import android.os.AsyncTask;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;

public class InternetActivity extends AppCompatActivity {
    TextView mTextView;
    Button mButton;
    TextToSpeech tts;
    String TextFileURL;
    URL url ;
    String TextHolder = "" , TextHolder2 = "";
    BufferedReader bufferReader ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        mButton = (Button) findViewById(R.id.buttonInt);
        mTextView = (TextView) findViewById(R.id.textInt);

        mTextView.setMovementMethod(new ScrollingMovementMethod());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Amcanin Ruyasi - Dostoyevski")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/a73.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Baskasinin Karisi - Dostoyevski")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/b1.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Beyaz Gemi - Cengiz Aytmatov")) {
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/b2.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Degirmenimden Mektuplar - Alphonse Daudet")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/d2.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Fareler ve İnsanlar - John Steinbeck")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/f2.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Genc Wertherin Acilari - Johann Wolfgang Goethe")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/g2.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Hayvan Çiftliği - George Orwell")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/h2.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Karanlikta Sabah Kuslari - Ahmet Altan")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/k2.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Kin ve Sevgi - Solohov")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/k3.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Kiralik Konak - Yakup Kadri Karaosmanoglu")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/k4.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Mavi Oktav Defterleri - Franz Kafka")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/m3.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Oteki - Dostoyevski")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/o2.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Siradan Delilik Oykuleri - Charles Bukowski")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/s13.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Visne Bahcesi - Anton Cehov")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/v2.doc";
            }
            if(bundle.getString("ktp2").toString().equalsIgnoreCase("Yasak Iliski - Barbara Taylor")){
                TextFileURL = "https://androkutuphane.files.wordpress.com/2018/03/y2.doc";
            }
            new GetNotePadFileFromServer().execute();


            tts = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        tts.setLanguage(new Locale("tr","TR"));

                    }
                    else { Log.e("HATA","Okuma Hatası Oluştu"); }
                }
            });
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        String D = TextHolder.toString();
                        tts.speak(D.substring(0,3500).toString(),TextToSpeech.QUEUE_FLUSH,null,null);
                    }
                }
            });
        }
    }
    public class GetNotePadFileFromServer extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                url = new URL(TextFileURL);
                bufferReader = new BufferedReader(new InputStreamReader(url.openStream()));
                while ((TextHolder2 = bufferReader.readLine()) != null) {

                    TextHolder += TextHolder2;
                }
                bufferReader.close();

            } catch (MalformedURLException malformedURLException) {

                // TODO Auto-generated catch block
                malformedURLException.printStackTrace();
                TextHolder = malformedURLException.toString();

            } catch (IOException iOException) {

                // TODO Auto-generated catch block
                iOException.printStackTrace();

                TextHolder = iOException.toString();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void finalTextHolder) {

            mTextView.setText(TextHolder);

            super.onPostExecute(finalTextHolder);
        }

    }
    public void onPause(){
        if(tts!=null){
            tts.stop();
        }
        super.onPause();
    }
}



