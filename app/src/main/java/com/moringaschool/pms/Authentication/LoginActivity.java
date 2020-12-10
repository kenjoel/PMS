package com.moringaschool.pms.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.moringaschool.pms.HomePageActivity;
import com.moringaschool.pms.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mLoginButton;
    private TextView mCreatetxt, Resend;
    private String mEmail, mPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @BindView(R.id.btnlogin) Button Login;
    @BindView(R.id.createnewac) TextView createtxt;
    @BindView(R.id.etemail) EditText etEmail;
    @BindView(R.id.mypass) EditText myPass;
    @BindView(R.id.resend_verification_email) TextView mResend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themainlogin);
        ButterKnife.bind(this);

        mLoginButton = Login;
        mCreatetxt = createtxt;
        Resend = mResend;
        mLoginButton.setOnClickListener(this);
        mCreatetxt.setOnClickListener(this);
        Resend.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        checkVerifiedMail();
    }
    @Override
    public void onClick(View v) {
        if(v == mLoginButton){
            mEmail = etEmail.getText().toString().trim();
            mPassword = myPass.getText().toString().trim();
            validateEmail(mEmail);
            validatePassword(mPassword);

            mAuth.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                private static final String TAG ="Anonymous" ;
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Ok", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(LoginActivity.this, "Failed Sign In", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            checkVerifiedMail();
        }

        if(v == mCreatetxt){
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }

//        if(v == Resend){
//            ResendVerificationFragment resendVerificationFragment = new ResendVerificationFragment();
//            resendVerificationFragment.show(getSupportFragmentManager(), "dialog_resend_email_verification");
//        }

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

    public void redirectToHomePage(){
        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }

    public void checkVerifiedMail(){
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            private static final String TAG = "see";

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mUser = mAuth.getCurrentUser();
                if(mUser != null ){
                    if(mUser.isEmailVerified()){
                        Log.d(TAG, "onAuthStateChanged:signed_in:" + mUser.getUid());
                        Toast.makeText(LoginActivity.this, "Authenticated with: " + mUser.getEmail(), Toast.LENGTH_SHORT).show();
                        redirectToHomePage();
                    }else{
                        Toast.makeText(LoginActivity.this, "Please ensure that you have verified your email",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Sign in", Toast.LENGTH_SHORT).show();
                }
            }
        };

        // Enables Always-on
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthStateListener);
    }

}

