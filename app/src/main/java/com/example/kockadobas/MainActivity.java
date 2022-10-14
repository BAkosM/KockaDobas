package com.example.kockadobas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView kockakep1;
    private ImageView kockakep2;
    private Button btnkocka1;
    private Button btnkocka2;
    private Button btndobas;
    private Button btnreset;
    private TextView eredmeny;
    private Random rnd;
    private Boolean egykocka;
    private String dobaseredmeny;
    private int k1dobas;
    private int k2dobas;
    private int[] kockakkepek = {R.drawable.kocka1, R.drawable.kocka2, R.drawable.kocka3, R.drawable.kocka4, R.drawable.kocka5, R.drawable.kocka6};;
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addListeners();
    }
    private void init(){
        kockakep1 = findViewById(R.id.kockakep1);
        kockakep2 = findViewById(R.id.kockakep2);
        btnkocka1 = findViewById(R.id.kocka1);
        btnkocka2 = findViewById(R.id.kocka2);
        btndobas = findViewById(R.id.dobas);
        btnreset = findViewById(R.id.reset);
        eredmeny = findViewById(R.id.eredmeny);
        rnd = new Random();
        egykocka = false;
        dobaseredmeny = "";
        builder = new AlertDialog.Builder(MainActivity.this);
    }
    private void addListeners(){
        btnkocka1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kockakep2.setVisibility(View.GONE);
                egykocka = true;
            }
        });
        btnkocka2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kockakep2.setVisibility(View.VISIBLE);
                egykocka = false;
            }
        });
        btndobas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (egykocka == false){
                    k1dobas = rnd.nextInt(6);
                    kockakep1.setImageResource(kockakkepek[k1dobas]);
                    k2dobas = rnd.nextInt(6);
                    kockakep2.setImageResource(kockakkepek[k2dobas]);
                    dobaseredmeny = (eredmeny.getText()).toString();
                    eredmeny.setText(dobaseredmeny+(k1dobas+1)+"+"+(k2dobas+1)+"\n");
                } else {
                    k1dobas = rnd.nextInt(6);
                    kockakep1.setImageResource(kockakkepek[k1dobas]);
                    dobaseredmeny = (eredmeny.getText()).toString();
                    eredmeny.setText(dobaseredmeny+(k1dobas+1)+"\n");
                }
            }
        });
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Reset");
                builder.setMessage("Biztos, hogy törölni szeretné az eddigi dobásokat");
                builder.setCancelable(false);
                builder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        eredmeny.setText("");
                    }
                });
                builder.create().show();
            }
        });
    }
}