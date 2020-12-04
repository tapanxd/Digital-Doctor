package com.example.digitaldoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private static final String TAG = "Register";

     static final String KEY_NAME = "full_name";
     static final String KEY_EMAIL = "email";
     static final String KEY_PH_NO = "ph_no";
     static final String KEY_DOB = "dob";

    EditText mName, mEmail, mPassword, mPhone, mDob;
    Button mPatient, mDoctor, mPharmacy;
    TextView mLogin;
    FirebaseAuth mAuth;
    String Gender;
    RadioButton male, female, other;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phoneNumber);
        mPatient = findViewById(R.id.btnPatient);
        mDoctor = findViewById(R.id.btnDoctor);
        mPharmacy = findViewById(R.id.btnPharmacy);
        mLogin = findViewById(R.id.alreadyRegistered);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        other = findViewById(R.id.other);
        mAuth = FirebaseAuth.getInstance();
        mDob = findViewById(R.id.dob);



        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        //button click patient
        mPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email = mEmail.getText().toString().trim();
                String pass = mPassword.getText().toString().trim();

                String full_name = mName.getText().toString();
                String ph_no = mPhone.getText().toString();
                String dob = mDob.getText().toString();

                Map<String, Object> reg = new HashMap<>();
                reg.put(KEY_NAME, full_name);
                reg.put(KEY_EMAIL, email);
                reg.put(KEY_PH_NO, ph_no);
                reg.put(KEY_DOB, dob);

                db.collection("User").document("Patient").set(reg)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Register.this, "Data Saved", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Register.this, "Data not Saved", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, e.toString());
                            }
                        });



                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    mPassword.setError("Password is required");
                    return;
                }
                if(pass.length()<6){
                    mPassword.setError("Password must be >= 6 characters");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Successfully Registered",Toast.LENGTH_SHORT).show();
                            //save user data
                            startActivity(new Intent(getApplicationContext(),patientRegister.class));
                        }else{
                            Toast.makeText(Register.this, "Registration failed" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        //button click doctor
        mDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email = mEmail.getText().toString().trim();
                String pass = mPassword.getText().toString().trim();


                String full_name = mName.getText().toString();
                String ph_no = mPhone.getText().toString();
                String dob = mDob.getText().toString();

                Map<String, Object> reg = new HashMap<>();
                reg.put(KEY_NAME, full_name);
                reg.put(KEY_EMAIL, email);
                reg.put(KEY_PH_NO, ph_no);
                reg.put(KEY_DOB, dob);

                db.collection("User").document("Doctor").set(reg)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Register.this, "Data Saved", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Register.this, "Data not Saved", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, e.toString());
                            }
                        });


                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    mPassword.setError("Password is required");
                    return;
                }
                if(pass.length()<6){
                    mPassword.setError("Password must be >= 6 characters");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Successfully Registered",Toast.LENGTH_SHORT).show();
                            //save user data
                            startActivity(new Intent(getApplicationContext(),doctorRegister.class));
                        }else{
                            Toast.makeText(Register.this, "Registration failed" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        //button click pharmacy
        mPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email = mEmail.getText().toString().trim();
                String pass = mPassword.getText().toString().trim();

                String full_name = mName.getText().toString();
                String ph_no = mPhone.getText().toString();
                String dob = mDob.getText().toString();

                Map<String, Object> reg = new HashMap<>();
                reg.put(KEY_NAME, full_name);
                reg.put(KEY_EMAIL, email);
                reg.put(KEY_PH_NO, ph_no);
                reg.put(KEY_DOB, dob);

                db.collection("User").document("Pharmacy").set(reg)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Register.this, "Data Saved", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Register.this, "Data not Saved", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, e.toString());
                            }
                        });


                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    mPassword.setError("Password is required");
                    return;
                }
                if(pass.length()<6){
                    mPassword.setError("Password must be >= 6 characters");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Successfully Registered",Toast.LENGTH_SHORT).show();
                            //save user data
                            startActivity(new Intent(getApplicationContext(),pharmacyRegister.class));
                        }else{
                            Toast.makeText(Register.this, "Registration failed\n" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}