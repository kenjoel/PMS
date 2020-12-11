package com.moringaschool.pms.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.pms.IntroScreen.HomePageActivity;
import com.moringaschool.pms.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Button mCreateButton;

    private String mEmail, mPassword, mPassConfirm;

    @BindView(R.id.buttonAccount)
    Button create;

    @BindView(R.id.editName)
    EditText name;

    @BindView(R.id.editEmail)
    EditText editEmail;

    @BindView(R.id.editPass)
    EditText editPassword;

    @BindView(R.id.confirmPass)
    EditText confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        mCreateButton = create;
        mCreateButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v == mCreateButton ){

            mEmail = editEmail.getText().toString().trim();
            mPassword = editPassword.getText().toString().trim();
            mPassConfirm = confirm.getText().toString().trim();

            validateEmail(mEmail);
            validatePassword(mPassword);
            passwordMatch(mPassword, mPassConfirm);
        }

        mAuth.createUserWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "Please verify your email before logging in", Toast.LENGTH_LONG).show();
                    sendVerificationEmail();
                    mAuth.signOut();
                    redirectToHomepage();
                }
            }
        });

    }

    public boolean validateEmail(String email){
        if(email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }else{
            return false;
        }
    }

    public boolean validatePassword(String Password){
        if(Password.length() < 6){
            return false;
        }else {
            return true;
        }
    }

    public boolean passwordMatch(String s, String y){
        if (s.equals(y)){
            return true;
        }else {
            return false;
        }
    }

    public void sendVerificationEmail(){
        final FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "Sent verification Emal", Toast.LENGTH_SHORT);
                    }else{
                        Toast.makeText(SignUpActivity.this, "Email not sent", Toast.LENGTH_SHORT);
                    }
                }
            });
        }

    }

    public void redirectToHomepage(){
        Intent intent = new Intent(SignUpActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }
}