package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupRozmiar;
    private Spinner spinnerRozmiar;
    private CheckBox checkGrube;
    private EditText editIlosc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioGroupRozmiar = findViewById(R.id.radioGroupRozmiar);
        spinnerRozmiar = findViewById(R.id.spinnerRozmiar);
        checkGrube = findViewById(R.id.checkGrube);
        editIlosc = findViewById(R.id.editIlosc);
        Button btnPrzejdz = findViewById(R.id.btnPrzejdz);


        if (spinnerRozmiar != null) {
            String[] opcjeRozmiarów = {"Mała 25 zł", "Średnia 35 zł", "Duża 45 zł"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_spinner_item,
                    opcjeRozmiarów
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerRozmiar.setAdapter(adapter);
        }

        btnPrzejdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String iloscStr = editIlosc.getText().toString().trim();
                if (iloscStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Podaj liczbę sztuk!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int ilosc = Integer.parseInt(iloscStr);
                if (ilosc <= 0) {
                    Toast.makeText(MainActivity.this, "Liczba sztuk musi być większa od 0!", Toast.LENGTH_SHORT).show();
                    return;
                }


                String rozmiar = "";
                if (radioGroupRozmiar != null) {
                    int selectedId = radioGroupRozmiar.getCheckedRadioButtonId();
                    if (selectedId == -1) {
                        Toast.makeText(MainActivity.this, "Wybierz rozmiar pizzy!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    RadioButton selectedRadio = findViewById(selectedId);
                    rozmiar = selectedRadio.getText().toString();
                } else if (spinnerRozmiar != null) {
                    rozmiar = spinnerRozmiar.getSelectedItem().toString();
                }


                boolean grube = checkGrube.isChecked();


                Intent intent = new Intent(MainActivity.this, pizzeria2.class);
                intent.putExtra("rozmiar", rozmiar);
                intent.putExtra("grube", grube);
                intent.putExtra("ilosc", ilosc);
                startActivity(intent);
            }
        });
    }
}