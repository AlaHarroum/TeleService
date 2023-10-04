package com.example.mypfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class CodeFormulaire extends AppCompatActivity {
    TextView codeform;
    String Code,Email;
    Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_formulaire);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_code);
        getSupportActionBar().setTitle("           Code de Formulaire");

        codeform=findViewById(R.id.codeform);

        Bundle bcode=getIntent().getExtras();
        Code= bcode.getString("Code");
        Email= bcode.getString("Email");


        codeform.setText(Code);


        retour=findViewById(R.id.back);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentaccueil= new Intent(getApplicationContext(), FirstStep.class);
                startActivity(intentaccueil);
            }
        });





        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[2];
                field[0] = "email";
                field[1] = "code";

                //Creating array for data
                String[] data = new String[2];
                data[0] = Email;
                data[1] = Code;

                PutData putData = new PutData("http://192.168.1.33/LoginRegister/sendcode.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Code envoyer avec succee")){
                            Toast.makeText(getApplicationContext(),"Votre Code de formulaire est envoyée avec succée",Toast.LENGTH_SHORT).show();


                        }
                        else{
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                //End Write and Read data with URL
            }
        });


    }
}