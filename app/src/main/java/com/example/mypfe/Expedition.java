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

public class Expedition extends AppCompatActivity {

    EditText Num_Compte_Exp,Montant_Exp, Montant2, Adresse;
    String Type_Exp, Email, num_compte, montant_compte, montant_mondat, adresse, Code;
    Button envoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expedition);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_form);
        getSupportActionBar().setTitle("          Formulaire d'expédition");

        Num_Compte_Exp=findViewById(R.id.numcompteexp);
        Montant_Exp=findViewById(R.id.montantexp);
        Montant2=findViewById(R.id.montant2);
        Adresse=findViewById(R.id.adresse);

        Bundle r=getIntent().getExtras();
        Email= r.getString("Email");

        Spinner Type_Expedition= findViewById(R.id.typeexpedition);

        ArrayAdapter<String> adaptertype =new ArrayAdapter<>( this, R.layout.custom_spinner, getResources().getStringArray(R.array.Type_Expedition));
        adaptertype.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        Type_Expedition.setAdapter(adaptertype);

        Type_Expedition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 Type_Exp = parent.getItemAtPosition(position).toString();
                if (Type_Exp.equals("Recharger Compte")){
                    Num_Compte_Exp.setVisibility(View.VISIBLE);
                    Montant_Exp.setVisibility(View.VISIBLE);
                    Montant2.setVisibility(View.INVISIBLE);
                }else{
                    Num_Compte_Exp.setVisibility(View.INVISIBLE);
                    Montant_Exp.setVisibility(View.INVISIBLE);
                    Montant2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        envoyer=findViewById(R.id.envoyerbtn);

        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num_compte= String.valueOf(Num_Compte_Exp.getText());
                montant_compte= String.valueOf(Montant_Exp.getText());
                montant_mondat= String.valueOf(Montant2.getText());
                adresse= String.valueOf(Adresse.getText());







                if (Type_Exp.equals("Recharger Compte")){


                    if( !adresse.equals("") && !num_compte.equals("") && !montant_compte.equals("")) {

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
                                field[4] = "type";

                                //Creating array for data
                                String[] data = new String[5];
                                data[0] = adresse;
                                data[1] = Email;
                                data[2] = num_compte;
                                data[3] = montant_compte;
                                data[4] = Type_Exp;


                                PutData putData = new PutData("http://192.168.1.33/LoginRegister/expeditioncompte.php", "POST", field, data);
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
                        Adresse.setError("Votre adresse postale peut étre vide");
                        Num_Compte_Exp.setError("Le numéro de compte peut étre vide !");
                        Montant_Exp.setError("Le montant peut étre vide !");

                    }



                }
                else{

                    if( !adresse.equals("") && !montant_mondat.equals("")) {

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Starting Write and Read data with URL
                                //Creating array for parameters
                                String[] field = new String[4];
                                field[0] = "adresse";
                                field[1] = "email";
                                field[2] = "type";
                                field[3] = "montant";


                                //Creating array for data
                                String[] data = new String[4];
                                data[0] = adresse;
                                data[1] = Email;
                                data[2] = Type_Exp;
                                data[3] = montant_mondat;



                                PutData putData = new PutData("http://192.168.1.33/LoginRegister/expeditionmondat.php", "POST", field, data);
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
                        Adresse.setError("Votre adresse postale peut étre vide !");
                        Montant2.setError("Le montant du mondat peut étre vide !");
                    }
                }

            }
        });




    }
}