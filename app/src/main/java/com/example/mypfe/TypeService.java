package com.example.mypfe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TypeService extends AppCompatActivity {

    String Email, Nom, Prenom,Password;
    String Cin;
    Button btnretrait,btnexpedition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_service);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_check);
        getSupportActionBar().setTitle("                 Type de service");

        Bundle bremplir=getIntent().getExtras();
        Email= bremplir.getString("Email");
        Nom= bremplir.getString("Nom");
        Prenom= bremplir.getString("Prenom");
        Password= bremplir.getString("Password");
        Cin= bremplir.getString("Cin");


        btnretrait=findViewById(R.id.btnretrait);
        btnexpedition=findViewById(R.id.btnexpedition);


        btnexpedition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent expedition= new Intent(getApplicationContext(), Expedition.class);
                Bundle bexpedition= new Bundle();
                bexpedition.putString("Email",Email);
                expedition.putExtras(bexpedition);
                startActivity(expedition);
                finish();
            }
        });

        btnretrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retrait= new Intent(getApplicationContext(), Retrait.class);
                Bundle bretrait= new Bundle();
                bretrait.putString("Email",Email);
                retrait.putExtras(bretrait);
                startActivity(retrait);
                finish();
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.exemple_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Intent intent1= new Intent(getApplicationContext(), ProfilActivity.class);

                Bundle bprofile= new Bundle();
                bprofile.putString("Email",Email);
                bprofile.putString("Password",Password);
                intent1.putExtras(bprofile);
                startActivity(intent1);


                return true;
            case R.id.about:
                Toast.makeText(this, "About me", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}