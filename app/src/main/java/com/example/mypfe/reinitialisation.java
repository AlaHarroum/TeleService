package com.example.mypfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class reinitialisation extends AppCompatActivity {

    String Email, password, password2;
    TextView tvemail;
    EditText etpassword, etpassword2;
    Button btnconfirmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reinitialisation);


        Bundle bcode=getIntent().getExtras();
        Email= bcode.getString("Email");

        tvemail=findViewById(R.id.verifieremail);
        etpassword=findViewById(R.id.password);
        etpassword2=findViewById(R.id.confirmerpassword);
        btnconfirmer=findViewById(R.id.btnconfirmer);

        tvemail.setText(Email);


        btnconfirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password=String.valueOf(etpassword.getText());
                password2=String.valueOf(etpassword2.getText());
                if (!password.equals("") && !password2.equals("")) {
                    if (password.equals(password2)) {

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
                                data[0] = Email;
                                data[1] = password;

                                PutData putData = new PutData("http://192.168.1.33/LoginRegister/editmdp.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("edit succes")){
                                            Toast.makeText(getApplicationContext(),"Votre mot de passe est changer avec succée",Toast.LENGTH_SHORT).show();
                                            Intent intent= new Intent(getApplicationContext(), ConnexionActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),result+ " :Le changement de mot de passe est échoué",Toast.LENGTH_SHORT).show();
                                            etpassword.setError("Mot de passe invalide !");
                                            etpassword2.setError("Mot de passe invalide !");
                                            etpassword2.setText("");
                                            etpassword.setText("");
                                        }
                                    }
                                }
                                //End Write and Read data with URL
                            }
                        });


                    }else
                        {
                            etpassword.setError("les deux mots de passe ne sont pas compatibles");
                            etpassword2.setError("les deux mots de passe ne sont pas compatibles");
                            etpassword2.setText("");
                            etpassword.setText("");
                        }
                }else
                    {
                        etpassword.setError("Certains champs sont vides !");
                        etpassword2.setError("Certains champs sont vides !");
                        etpassword2.setText("");
                        etpassword.setText("");
                    }
            }
        });


    }
}