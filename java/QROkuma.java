package com.example.taner.cep_kutuphanem;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class QROkuma extends AppCompatActivity {
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
        setContentView(R.layout.activity_qrokuma);


        mButton = (Button) findViewById(R.id.buttonQR);
        mTextView = (TextView) findViewById(R.id.textQR);

        mTextView.setMovementMethod(new ScrollingMovementMethod());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            TextFileURL= bundle.getString("qr").toString();
        }
        new QROkuma.GetNotePadFileFromServer().execute();


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
    } public class GetNotePadFileFromServer extends AsyncTask<Void, Void, Void> {

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




