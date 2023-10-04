package com.example.mypfe;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONObject;

public class FirstStep extends AppCompatActivity {
    TextView tvusername,  tvemail, tvcin;
    String email;
    String Email, Nom, Prenom, Password,Date_Naissance;
    int Id, Cin, Mobile;
    ImageView profile_img;
    Button btnremplir, btnhoraire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_step);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_baseline_home_24);
        getSupportActionBar().setTitle("                       Accueil");

        btnremplir=findViewById(R.id.btnremplir);
        btnhoraire=findViewById(R.id.heures);



        Bundle b=getIntent().getExtras();
        Email= b.getString("email");
        Password=b.getString("password");

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
                                     Email =object.getString("email");
                                     Cin=object.getInt("cin");
                                     Mobile=object.getInt("mobile");
                                     Date_Naissance=object.getString("date_naissance");

                               String Nom1 = Nom.substring(0, 1).toUpperCase() + Nom.substring(1).toLowerCase();
                               String Prenom1 = Prenom.substring(0, 1).toUpperCase() + Prenom.substring(1).toLowerCase();


                                    tvusername = findViewById(R.id.username);
                                    tvemail= findViewById(R.id.textemail);
                                    tvcin= findViewById(R.id.textcin);

                                    tvusername.setText("« "+Prenom1 + " " + Nom1+" »");

                                    tvemail.setText(Email);
                                    tvcin.setText("CIN: "+Integer.toString(Cin));

                           }catch (Exception e) {
                               Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                           }
                        }
                    }
                    //End Write and Read data with URL
                }
            });
        }


        btnremplir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent remplirintent= new Intent(getApplicationContext(),TypeService.class);
                Bundle bremplir= new Bundle();
                bremplir.putString("Email",Email);
                bremplir.putString("Nom",Nom);
                bremplir.putString("Prenom",Prenom);
                bremplir.putString("Password",Password);
                bremplir.putInt("Cin", Cin);
                remplirintent.putExtras(bremplir);
                startActivity(remplirintent);

            }
        });



       btnhoraire.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent inthoraire= new Intent(getApplicationContext(),HeuresTravail.class);
               startActivity(inthoraire);
           }
       });



        profile_img= findViewById(R.id.imgprofile);
        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentimage= new Intent(getApplicationContext(), ProfilActivity.class);

                Bundle b= new Bundle();
                b.putString("Email",Email);
                b.putString("Password",Password);

                intentimage.putExtras(b);
                startActivity(intentimage);
            }
        });




    }




}