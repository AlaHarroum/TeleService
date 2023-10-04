package com.example.mypfe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Calendar;

import static android.app.DatePickerDialog.*;


public class   InscriptionActivity extends AppCompatActivity {

    EditText etnom, etprenom, etemail, etpassword, etcin, etmobile ;
    TextView tvdate_naissance;
    Button btninscription;
    RadioButton etgenre, btnhomme, btnfemme;
    OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        btnhomme=findViewById(R.id.btnhomme);
        btnfemme=findViewById(R.id.btnfemme);

        etnom= findViewById(R.id.nom);
        etprenom= findViewById(R.id.prenom);
        etemail= findViewById(R.id.emailinscrire);
        etpassword= findViewById(R.id.passwordinscrire);
        etcin= findViewById(R.id.cininscrire);
        etmobile= findViewById(R.id.phoneinscrire);
        tvdate_naissance= findViewById(R.id.datenaissance);
        btninscription= findViewById(R.id.btninscrire);

        btninscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom, prenom, email, password, cin, mobile, date_naissance, genre;

                nom= String.valueOf(etnom.getText());
                prenom= String.valueOf(etprenom.getText());
                email= String.valueOf(etemail.getText());
                password= String.valueOf(etpassword.getText());
                cin= String.valueOf(etcin.getText());
                mobile= String.valueOf(etmobile.getText());
                date_naissance= String.valueOf(tvdate_naissance.getText());
                genre= String.valueOf(etgenre.getText());


                if(!nom.equals("") && !prenom.equals("") && !email.equals("") && !password.equals("") && !cin.equals("") && !mobile.equals("") && !date_naissance.equals("")  && !genre.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[8];
                            field[0] = "nom";
                            field[1] = "prenom";
                            field[2] = "email";
                            field[3] = "password";
                            field[4] = "cin";
                            field[5] = "mobile";
                            field[6] = "date_naissance";
                            field[7] = "genre";
                            //Creating array for data
                            String[] data = new String[8];
                            data[0] = nom;
                            data[1] = prenom;
                            data[2] = email;
                            data[3] = password;
                            data[4] = cin;
                            data[5] = mobile;
                            data[6] = date_naissance;
                            data[7] = genre;
                            PutData putData = new PutData("http://192.168.1.33/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(),"Inscription réussi avec succée",Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(getApplicationContext(), ConnexionActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"Inscription échoué",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Partie initialisation du saisie du date de naissance
        Calendar calendar= Calendar.getInstance();
        final int year= calendar.get(Calendar.YEAR);
        final int month= calendar.get(Calendar.MONTH);
        final int day= calendar.get(Calendar.DAY_OF_MONTH);

        tvdate_naissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(
                        InscriptionActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+"/"+month+"/"+year;
                tvdate_naissance.setText(date);
            }
        };
        //Fin partie calendrier
    }


    //fixé la choix du sexe
    public void genreclick(View v){

        if(btnfemme.isChecked()){
            etgenre=btnfemme;
        }
        else{
            etgenre=btnhomme;
        }
    }
}