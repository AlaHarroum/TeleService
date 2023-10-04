package com.example.mypfe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


public class OublierMotDePasse extends AppCompatActivity {
    EditText etcode, etemail;
    Button btnenvoyer,btnvérifier;
    String Email, Code, vérif;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oublier_mot_de_passe);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_baseline_vpn_key_24);
        getSupportActionBar().setTitle("           Oublier Mot de Passe");

        etemail=findViewById(R.id.oublieremail);
        btnenvoyer=findViewById(R.id.btnenvoyer);
        etcode=findViewById(R.id.code);
        btnvérifier=findViewById(R.id.btnvérifier);
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        btnenvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email=String.valueOf(etemail.getText());

                if( !Email.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[1];
                            field[0] = "Email";

                            //Creating array for data
                            String[] data = new String[1];
                            data[0] = Email;



                            PutData putData = new PutData("http://192.168.1.33/LoginRegister/mobile.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Email invalide"))
                                    {
                                        etemail.setError("Votre Adresse mail est invalide, Réessayer !");
                                        etemail.setText("");
                                    }
                                    else{
                                        Code=result;
                                        etcode.setVisibility(View.VISIBLE);
                                        btnvérifier.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });



                }else{
                    etemail.setError("Email est Vide !");
                }

            }
        });


        btnvérifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vérif=String.valueOf(etcode.getText());
                if (vérif.equals(Code))
                {
                    Intent intentpassword= new Intent(getApplicationContext(), reinitialisation.class);

                    Bundle bun= new Bundle();
                    bun.putString("Email",Email);
                    intentpassword.putExtras(bun);
                    startActivity(intentpassword);
                    finish();
                }else
                    {
                        etcode.setError("Votre code est incorrect !");
                        etcode.setText("");
                    }
            }
        });
    }
}