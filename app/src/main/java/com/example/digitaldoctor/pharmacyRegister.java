package com.example.digitaldoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class pharmacyRegister extends AppCompatActivity {

    private static final String TAG = "pharmacyRegister";

    private static final String KEY_P_NAME = "name";
    private static final String KEY_P_LICENSE = "license";
    private static final String KEY_P_ADDRESS = "address";
    private static final String KEY_P_TIME_FROM = "time_from";
    private static final String KEY_P_TIME_TO = "time_to";

    private EditText pharmacy_name;
    private EditText license_no;
    private EditText address_p;
    private EditText p_time_from;
    private EditText p_time_to;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_register);

        pharmacy_name = findViewById(R.id.pharmName);
        license_no = findViewById(R.id.licenseNumP);
        address_p = findViewById(R.id.addressP);
        p_time_from = findViewById(R.id.timeFromP);
        p_time_to = findViewById(R.id.timeToP);


    }

    public void register_pharmacy(View v){
        String name = pharmacy_name.getText().toString();
        String license = license_no.getText().toString();
        String address = address_p.getText().toString();
        String time_from = p_time_from.getText().toString();
        String time_to = p_time_to.getText().toString();

        Map<String, Object>  regp = new HashMap<>();
        regp.put(KEY_P_NAME, name);
        regp.put(KEY_P_LICENSE, license);
        regp.put(KEY_P_ADDRESS, address);
        regp.put(KEY_P_TIME_FROM, time_from);
        regp.put(KEY_P_TIME_TO, time_to);

        db.collection("User").document("Pharmacy").update(regp)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(pharmacyRegister.this, "Data Saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(pharmacyRegister.this, "Data not Saved", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });

    }
}