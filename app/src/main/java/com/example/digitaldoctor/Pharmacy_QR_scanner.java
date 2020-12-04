package com.example.digitaldoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pharmacy_QR_scanner extends AppCompatActivity {
    Button btnScanBarcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy__q_r_scanner);
        btnScanBarcode = findViewById(R.id.btnScanBarcode);

        btnScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pharmacy_QR_scanner.this, Scanned_qr.class));
            }
        });

        }


}
