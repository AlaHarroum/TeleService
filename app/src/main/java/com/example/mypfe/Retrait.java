package com.example.mypfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONObject;

public class Retrait extends AppCompatActivity {

    String Nature, Email, num_mondat, adresse, type, Type1, Type2, Type3, montant, num_compte, Code;
    EditText Num_Compte, Montant, Num_Mondat, adressepostale;
    Button btnenvoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrait);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_form);
        getSupportActionBar().setTitle("           Formulaire de Retrait");


        Bundle r=getIntent().getExtras();
        Email= r.getString("Email");

        adressepostale=findViewById(R.id.adressepostale);

        Num_Compte=findViewById(R.id.numcompte);
        Montant=findViewById(R.id.montant);
        Num_Mondat=findViewById(R.id.nummondat);

        Spinner spinnerNature= findViewById(R.id.naturemondat);
        Spinner spinnertype1= findViewById(R.id.typenational);
        Spinner spinnertype2= findViewById(R.id.typeinternationnal);
        Spinner spinnertype3=findViewById(R.id.typeorganisme);

        ArrayAdapter<String> adapternature =new ArrayAdapter<>( this, R.layout.custom_spinner, getResources().getStringArray(R.array.Nature_Mondat));
        adapternature.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnerNature.setAdapter(adapternature);

        spinnerNature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 Nature = parent.getItemAtPosition(position).toString();
                 if (Nature.equals("Mondat National")){
                     spinnertype1.setVisibility(View.VISIBLE);
                     spinnertype2.setVisibility(View.INVISIBLE);
                     spinnertype3.setVisibility(View.INVISIBLE);
                     Num_Compte.setVisibility(View.INVISIBLE);
                     Montant.setVisibility(View.INVISIBLE);
                     Num_Mondat.setVisibility(View.VISIBLE);

                 }else if (Nature.equals("Mondat Internationnal")){
                     spinnertype1.setVisibility(View.INVISIBLE);
                     spinnertype2.setVisibility(View.VISIBLE);
                     spinnertype3.setVisibility(View.INVISIBLE);
                     Num_Compte.setVisibility(View.INVISIBLE);
                     Montant.setVisibility(View.INVISIBLE);
                     Num_Mondat.setVisibility(View.VISIBLE);

                 }else if (Nature.equals("Mondat Organisme")){
                     spinnertype1.setVisibility(View.INVISIBLE);
                     spinnertype2.setVisibility(View.INVISIBLE);
                     spinnertype3.setVisibility(View.VISIBLE);
                     Num_Compte.setVisibility(View.INVISIBLE);
                     Montant.setVisibility(View.INVISIBLE);
                     Num_Mondat.setVisibility(View.VISIBLE);

                 }else{
                     spinnertype1.setVisibility(View.INVISIBLE);
                     spinnertype2.setVisibility(View.INVISIBLE);
                     spinnertype3.setVisibility(View.INVISIBLE);
                     Num_Mondat.setVisibility(View.INVISIBLE);
                     Num_Compte.setVisibility(View.VISIBLE);
                     Montant.setVisibility(View.VISIBLE);

                 }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter<String> adaptertype1 =new ArrayAdapter<>( this, R.layout.custom_spinner, getResources().getStringArray(R.array.Type_National));
        adaptertype1.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnertype1.setAdapter(adaptertype1);

        spinnertype1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 Type1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter<String> adaptertype2 =new ArrayAdapter<>( this, R.layout.custom_spinner, getResources().getStringArray(R.array.Type_Internationnal));
        adaptertype2.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnertype2.setAdapter(adaptertype2);

        spinnertype2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 Type2 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter<String> adaptertype3 =new ArrayAdapter<>( this, R.layout.custom_spinner, getResources().getStringArray(R.array.Type_Organisme));
        adaptertype3.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnertype3.setAdapter(adaptertype3);

        spinnertype3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 Type3 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btnenvoyer=findViewById(R.id.envoyer);


        btnenvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num_mondat= String.valueOf(Num_Mondat.getText());
                adresse= String.valueOf(adressepostale.getText());
                montant= String.valueOf(Montant.getText());
                num_compte= String.valueOf(Num_Compte.getText());




                if (!Nature.equals("Compte Poste")){

                    if (Nature.equals("Mondat National")){
                        type= Type1;
                    }else if (Nature.equals("Mondat Internationnal")){
                        type= Type2;
                    }else{
                        type= Type3;
                    }




                    if( !adresse.equals("") && !num_mondat.equals("")) {

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Starting Write and Read data with URL
                                //Creating array for parameters
                                String[] field = new String[5];
                                field[0] = "adresse";
                                field[1] = "email";
                                field[2] = "num_mondat";
                                field[3] = "nature";
                                field[4] = "type";

                                //Creating array for data
                                String[] data = new String[5];
                                data[0] = adresse;
                                data[1] = Email;
                                data[2] = num_mondat;
                                data[3] = Nature;
                                data[4] = type;


                                PutData putData = new PutData("http://192.168.1.33/LoginRegister/retraitmondat.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        try {

                                            // JSONArray array = new JSONArray(putData.getResult());

                                            JSONObject object = new JSONObject(putData.getResult());

                                            Code = object.getString("code");

                                            Intent pagecode= new Intent(getApplicationContext(), CodeFormulaire.class);
                                            Bundle pcode= new Bundle();
                                            pcode.putString("Code",Code);
                                            pcode.putString("Email",Email);
                                            pagecode.putExtras(pcode);
                                            startActivity(pagecode);
                                            finish();

                                        }catch (Exception e) {
                                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                //End Write and Read data with URL
                            }
                        });
                    }
                    else{
                        adressepostale.setError("Votre adresse postale est vide");
                        Num_Mondat.setError("Le numéro de mondat est vide !");
                        Toast.makeText(getApplicationContext(), "Certains champs sont vides !", Toast.LENGTH_SHORT).show();
                    }



                }
                else{

                    if( !montant.equals("") && !num_compte.equals("")) {

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Starting Write and Read data with URL
                                //Creating array for parameters
                                String[] field = new String[5];
                                field[0] = "adresse";
                                field[1] = "email";
                                field[2] = "num_compte";
                                field[3] = "montant";
                                field[4] = "nature";

                                //Creating array for data
                                String[] data = new String[5];
                                data[0] = adresse;
                                data[1] = Email;
                                data[2] = num_compte;
                                data[3] = montant;
                                data[4] = Nature;


                                PutData putData = new PutData("http://192.168.1.33/LoginRegister/retraitcompte.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        try {

                                            // JSONArray array = new JSONArray(putData.getResult());

                                            JSONObject object = new JSONObject(putData.getResult());

                                            Code = object.getString("code");

                                            Intent pagecode= new Intent(getApplicationContext(), CodeFormulaire.class);
                                            Bundle pcode= new Bundle();
                                            pcode.putString("Code",Code);
                                            pcode.putString("Email",Email);
                                            pagecode.putExtras(pcode);
                                            startActivity(pagecode);
                                            finish();

                                        }catch (Exception e) {
                                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                //End Write and Read data with URL
                            }
                        });
                    }
                    else{
                        adressepostale.setError("Votre adresse postale est vide");
                        Num_Compte.setError("Le numéro de compte est vide !");
                        Montant.setError("Le montant est vide !");
                    }
                }





            }
        });


    }
}