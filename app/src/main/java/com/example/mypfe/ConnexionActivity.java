package com.example.mypfe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ConnexionActivity extends AppCompatActivity {

    EditText etemail,etpassword;
    Button btnconnexion;
    TextView tvinscrire, tvoublier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        tvinscrire=findViewById(R.id.inscrire);
        etemail=findViewById(R.id.emaillog);
        etpassword=findViewById(R.id.passwordlog);
        btnconnexion=findViewById(R.id.btnconnexion);
        tvoublier=findViewById(R.id.oublier);


        btnconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  email, password;

                email= String.valueOf(etemail.getText());
                password= String.valueOf(etpassword.getText());

                if( !email.equals("") && !password.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "password";

                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = password;

                            PutData putData = new PutData("http://192.168.1.33/LoginRegister/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(),"Connexion réussi, Bienvenu",Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(getApplicationContext(), FirstStep.class);

                                        Bundle b= new Bundle();
                                        b.putString("email",email);
                                        b.putString("password",password);
                                        intent.putExtras(b);
                                        startActivity(intent);

                                        finish();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"Email ou Mot de passe invalide !",Toast.LENGTH_SHORT).show();
                                        etpassword.setError("mot de passe est incorrect !");
                                        etemail.setError("Email est Incorrect !");
                                        etemail.setText("");
                                        etpassword.setText("");
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    etpassword.setError("mot de passe est vide !");
                    etemail.setError("Email est Vide !");
                    Toast.makeText(getApplicationContext(), "Email ou mot de passe est vide !", Toast.LENGTH_SHORT).show();
                }
            }
        });




        tvoublier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oublierintent= new Intent(getApplicationContext(), OublierMotDePasse.class);
                startActivity(oublierintent);
            }
        });




        tvinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscrireintent= new Intent(getApplicationContext(),InscriptionActivity.class);
                startActivity(inscrireintent);
            }
        });




    }

}