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

public class doctorRegister extends AppCompatActivity {

    private static final String TAG = "doctorRegister";

    private static final String KEY_D_LICENSE = "license";
    private static final String KEY_D_DEPART = "depart";
    private static final String KEY_D_SPEC = "speciality";
    private static final String KEY_D_ADDRESS = "address";
    private static final String KEY_D_TIME_FROM = "time_from";
    private static final String KEY_D_TIME_TO = "time_to";

    private EditText d_license;
    private EditText department;
    private EditText d_spec;
    private EditText d_address;
    private EditText d_time_from;
    private EditText d_time_to;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        d_license = findViewById(R.id.licenseNumD);
        department = findViewById(R.id.dept);
        d_spec = findViewById(R.id.spec);
        d_address = findViewById(R.id.addressD);
        d_time_from = findViewById(R.id.timeFromD);
        d_time_to = findViewById(R.id.timeToD);

    }
    public void register_doctor(View v){
        String license = d_license.getText().toString();
        String depart = department.getText().toString();
        String speciality = d_spec.getText().toString();
        String address = d_address.getText().toString();
        String time_from = d_time_from.getText().toString();
        String time_to = d_time_to.getText().toString();

        Map<String, Object> regd = new HashMap<>();
        regd.put(KEY_D_LICENSE, license);
        regd.put(KEY_D_DEPART, depart);
        regd.put(KEY_D_SPEC, speciality);
        regd.put(KEY_D_ADDRESS, address);
        regd.put(KEY_D_TIME_FROM, time_from);
        regd.put(KEY_D_TIME_TO, time_to);



        db.collection("User").document("Doctor").update(regd)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(doctorRegister.this, "Data Saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(doctorRegister.this, "Data not Saved", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });


    }
}