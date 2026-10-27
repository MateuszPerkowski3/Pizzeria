package com.example.pizzeria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class pizzeria2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzeria2);

        TextView txtRozmiar = findViewById(R.id.txtRozmiar);
        TextView txtCiasto = findViewById(R.id.txtCiasto);
        TextView txtIlosc = findViewById(R.id.txtIlosc);
        TextView txtRazem = findViewById(R.id.txtRazem);
        Button btnPowrot = findViewById(R.id.btnPowrot);

        // Odbierz dane z Intent
        String rozmiar = getIntent().getStringExtra("rozmiar");
        boolean grube = getIntent().getBooleanExtra("grube", false);
        int ilosc = getIntent().getIntExtra("ilosc", 1);

        // Wyciągnij cenę z rozmiaru (np. "Średnia 35 zł" -> 35)
        int cenaBazowa = 0;
        if (rozmiar != null && rozmiar.contains(" ")) {
            String[] parts = rozmiar.split(" ");
            // parts[1] to np. "35", parts[2] to "zł"
            if (parts.length >= 2) {
                cenaBazowa = Integer.parseInt(parts[1]);
            }
        }

        // Oblicz cenę
        int cenaZaSztuke = cenaBazowa + (grube ? 5 : 0);
        int cenaRazem = cenaZaSztuke * ilosc;

        // Wyświetl dane
        txtRozmiar.setText("• Rozmiar: " + rozmiar);
        txtCiasto.setText("• Ciasto: " + (grube ? "Grube" : "Cienkie"));
        txtIlosc.setText("• Sztuk: " + ilosc);
        txtRazem.setText("Razem: " + cenaRazem + ",00 PLN");

        btnPowrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Zamyka drugą aktywność i wraca do pierwszej
            }
        });
    }
}