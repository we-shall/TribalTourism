package com.example.vishalsingh.villageexpandedview;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class ActivitySignup extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
     EditText name, contact, email, password,username6;


    private Button register;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private Spinner spinner;
    private String[] subject;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.name);
        contact = (EditText) findViewById(R.id.contact);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        username6 = (EditText) findViewById(R.id.usernames);
        register = (Button) findViewById(R.id.register);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }

        });
    }

    private void attemptLogin() {


        // Reset errors.
        email.setError(null);
        password.setError(null);
        name.setError(null);
        contact.setError(null);
        username6.setError(null);

        // Store values at the time of the login attempt.
      final  String email1 = email.getText().toString();
       final String password1 = password.getText().toString();
      final  String name1 = email.getText().toString();
      final  String contact1 = password.getText().toString();

       final String username = username6.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password1) && !isPasswordValid(password1)) {
            password.setError(getString(R.string.error_invalid_password));
            focusView = password;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email1)) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
        } else if (!isEmailValid(email1)) {
            email.setError(getString(R.string.error_invalid_email));
            focusView = email;
            cancel = true;
        }
        if (TextUtils.isEmpty(contact1)) {
            contact.setError(getString(R.string.error_field_required));
            focusView = contact;
            cancel = true;
        }
        if (TextUtils.isEmpty(name1)) {
            name.setError(getString(R.string.error_field_required));
            focusView = name;
            cancel = true;
        }
        if (TextUtils.isEmpty(username) ||username.length()<6) {
            Toast.makeText(this,"Username should be of more than 5 letters",Toast.LENGTH_LONG).show();
            cancel = true;

        }

        if (cancel) {


        } else {
            Random rand = new Random();


            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(ActivitySignup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText(ActivitySignup.this, "Success!", Toast.LENGTH_LONG).show();
                                    Toast.makeText(ActivitySignup.this, "Success!", Toast.LENGTH_LONG).show();
                                    myRef= FirebaseDatabase.getInstance().getReference().child("UserRelation");
                                    DatabaseReference postsRef = myRef.child(username);
                                    postsRef.child("contact").setValue(contact1);
                                    postsRef.child("email").setValue(email1);
                                    postsRef.child("name").setValue(name1);
                                    postsRef.child("APPROVED").setValue("NOT APPROVED");
                                    postsRef.child("Role").setValue("Tourist");
                                    postsRef.child("password").setValue(password1);

                                Intent i = new Intent(ActivitySignup.this, LoginActivity.class);

                                startActivity(i,
                                        ActivityOptions.makeSceneTransitionAnimation(ActivitySignup.this).toBundle());
                                finish();
                            }
                            //Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            else {
                                Toast.makeText(ActivitySignup.this, "Failed",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}