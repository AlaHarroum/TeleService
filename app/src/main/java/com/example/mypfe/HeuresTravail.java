package com.example.mypfe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONObject;

public class HeuresTravail extends AppCompatActivity {

    TextView tvDJour, tvFJour, tvDMidi, tvFMidi, tvDsamedi, tvFsamedi;
    int DJour, FJour, DMidi, FMidi, Dsamedi, Fsamedi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heures_travail);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.clock);
        getSupportActionBar().setTitle("               Horaires de Travail");


        tvDJour= findViewById(R.id.DJour);
        tvFJour=findViewById(R.id.FJour);
        tvDMidi=findViewById(R.id.DMidi);
        tvFMidi=findViewById(R.id.FMidi);
        tvDsamedi=findViewById(R.id.Dsamedi);
        tvFsamedi=findViewById(R.id.Fsamedi);







        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[0];

                //Creating array for data
                String[] data = new String[0];




                PutData putData = new PutData("http://192.168.1.33/LoginRegister/time.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        try {

                            // JSONArray array = new JSONArray(putData.getResult());

                            JSONObject object = new JSONObject(putData.getResult());

                            DJour = object.getInt("DJour");
                            FJour = object.getInt("FJour");
                            DMidi = object.getInt("DMidi");
                            FMidi=object.getInt("FMidi");
                            Dsamedi=object.getInt("Dsamedi");
                            Fsamedi=object.getInt("Fsamedi");


                            tvDJour.setText(Integer.toString(DJour));
                            tvFJour.setText(Integer.toString(FJour));
                            tvDMidi.setText(Integer.toString(DMidi));
                            tvFMidi.setText(Integer.toString(FMidi));
                            tvDsamedi.setText(Integer.toString(Dsamedi));
                            tvFsamedi.setText(Integer.toString(Fsamedi));


                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                //End Write and Read data with URL
            }
        });
    }
}