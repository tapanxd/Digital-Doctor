package com.example.digitaldoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class patientRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "patientRegister";

    private static final String KEY_BLOOD = "blood";
    private static final String KEY_ADDRESS = "pt_address";
    private static final String KEY_DIET = "diet";

    private EditText bd_grp;
    private EditText address;
    private EditText pt_diet;
    private Button register_patient;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        Spinner spinner = findViewById(R.id.blood);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bloodGroups,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        address = findViewById(R.id.addressD);
        register_patient = findViewById(R.id.btnRegister);

        register_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pt_address = address.getText().toString();
                String blood = "B+";
                String diet = "Veg";

                Map<String, Object> regpt = new HashMap<>();
                regpt.put(KEY_BLOOD, blood);
                regpt.put(KEY_ADDRESS, pt_address);
                regpt.put(KEY_DIET, diet);

                db.collection("User").document("Patient").update(regpt)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(patientRegister.this, "Data Saved", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(patientRegister.this, "Data not Saved", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, e.toString());
                            }
                        });
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String gender = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}