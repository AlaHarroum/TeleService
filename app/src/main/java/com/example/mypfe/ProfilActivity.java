package com.example.mypfe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONObject;

import java.util.Calendar;

public class ProfilActivity extends AppCompatActivity {

    String Email, Nom, Prenom, Password, Date_Naissance;
    int Id, Cin, Mobile;
    TextView tvdate_naissance, tvemail;
    EditText etnom, etprenom, etpassword, etcin, etmobile;
    Button editbutton, deletebutton, deconnexionbutton;
    String nom, prenom, password, cin, mobile, date_naissance;

    DatePickerDialog.OnDateSetListener setListener;

    Dialog dialog,dialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_baseline_contacts_24);
        getSupportActionBar().setTitle("                        Profile");

        editbutton=findViewById(R.id.btnupdate);
        deletebutton=findViewById(R.id.btndelete);

        etnom= findViewById(R.id.editname);
        etprenom= findViewById(R.id.editprenom);
        tvemail= findViewById(R.id.editemail);
        etpassword= findViewById(R.id.editpassword);
        etcin= findViewById(R.id.editcin);
        etmobile= findViewById(R.id.editPhone);
        tvdate_naissance=findViewById(R.id.editdatenaissance);



        Bundle b=getIntent().getExtras();
        Email= b.getString("Email");
        Password= b.getString("Password");








        //Dialogue de boutton Modifier

        dialog = new Dialog(ProfilActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;


        Button confirmer = dialog.findViewById(R.id.btn_confirmer);
        Button annuler = dialog.findViewById(R.id.btn_annuler);


        //Dialogue de boutton Supprimer

        dialog2 = new Dialog(ProfilActivity.this);
        dialog2.setContentView(R.layout.custum_dialog2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog2.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog2.setCancelable(false);
        dialog2.getWindow().getAttributes().windowAnimations = R.style.animation;


        Button confirmersup = dialog2.findViewById(R.id.btn_confirmer);
        Button annulersup = dialog2.findViewById(R.id.btn_annuler);









        //Cliquer sur boutton Confirmer suppression

          confirmersup.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(!Email.equals("")  ) {

                      Handler handler = new Handler(Looper.getMainLooper());
                      handler.post(new Runnable() {
                          @Override
                          public void run() {
                              //Starting Write and Read data with URL
                              //Creating array for parameters
                              String[] field = new String[1];
                              field[0] = "Email";

                              //Creating array for data
                              String[] data = new String[1];
                              data[0] = Email;

                              PutData putData = new PutData("http://192.168.1.33/LoginRegister/deletecompte.php", "POST", field, data);
                              if (putData.startPut()) {
                                  if (putData.onComplete()) {
                                      String result = putData.getResult();
                                      if (result.equals("Delete Success")){
                                          Toast.makeText(getApplicationContext(),"Suppression réussi avec succée",Toast.LENGTH_SHORT).show();
                                          Intent intentdelete= new Intent(getApplicationContext(), ConnexionActivity.class);
                                          startActivity(intentdelete);
                                      }
                                      else{
                                          Toast.makeText(getApplicationContext(),"Suppression échoué",Toast.LENGTH_SHORT).show();
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


        //Cliquer sur boutton Annuler suppression

        annulersup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "La suppression de compte a été anuulé", Toast.LENGTH_SHORT).show();
                dialog2.dismiss();
            }
        });














        //Cliquer sur button Confirmer la modification

        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nom= String.valueOf(etnom.getText());
                prenom= String.valueOf(etprenom.getText());
                password= String.valueOf(etpassword.getText());
                cin= String.valueOf(etcin.getText());
                mobile= String.valueOf(etmobile.getText());
                date_naissance= String.valueOf(tvdate_naissance.getText());



                if(!nom.equals("") && !prenom.equals("") && !Email.equals("") && !password.equals("") && !cin.equals("") && !mobile.equals("") && !date_naissance.equals("")  ) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[7];
                            field[0] = "Email";
                            field[1] = "nom";
                            field[2] = "prenom";
                            field[3] = "password";
                            field[4] = "cin";
                            field[5] = "mobile";
                            field[6] = "date_naissance";

                            //Creating array for data
                            String[] data = new String[7];
                            data[0] = Email;
                            data[1] = nom;
                            data[2] = prenom;
                            data[3] = password;
                            data[4] = cin;
                            data[5] = mobile;
                            data[6] = date_naissance;

                            PutData putData = new PutData("http://192.168.1.33/LoginRegister/update.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Update Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(getApplicationContext(), FirstStep.class);
                                        startActivity(intent);
                                        finish();
                                        dialog.dismiss();
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
                else{
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Cliquer sur button annuler la modification

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), FirstStep.class);
                Toast.makeText(getApplicationContext(), "Votre modification a été anuulé", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                dialog.dismiss();
            }
        });









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



                    PutData putData = new PutData("http://192.168.1.33/LoginRegister/detail.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            try {

                                // JSONArray array = new JSONArray(putData.getResult());

                                JSONObject object = new JSONObject(putData.getResult());

                                Nom = object.getString("nom");
                                Prenom = object.getString("prenom");
                                Id = object.getInt("id");
                                Cin=object.getInt("cin");
                                Mobile=object.getInt("mobile");
                                Date_Naissance=object.getString("date_naissance");


                                etnom.setText(Nom);
                                etprenom.setText(Prenom);
                                tvemail.setText(Email);
                                etpassword.setText(Password);
                                etcin.setText(Integer.toString(Cin));
                                etmobile.setText(Integer.toString(Mobile));
                                tvdate_naissance.setText(Date_Naissance);


                            }catch (Exception e) {
                                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    //End Write and Read data with URL
                }
            });
        }






    editbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.show();
        }
    });

    deletebutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog2.show();
        }
    });




    //déconnexion du client
    deconnexionbutton=findViewById(R.id.deconnexion);
    deconnexionbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentlogout= new Intent(getApplicationContext(), ConnexionActivity.class);
            Toast.makeText(getApplicationContext(), "N'oubliez pas de retourner chez nous", Toast.LENGTH_SHORT).show();
            finishAffinity();
            startActivity(intentlogout);
            finish();
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
                        ProfilActivity.this,
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
}