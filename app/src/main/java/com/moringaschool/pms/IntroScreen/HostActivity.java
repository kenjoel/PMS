package com.moringaschool.pms.IntroScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.pms.HomePageActivity;
import com.moringaschool.pms.R;

public class HostActivity extends AppCompatActivity {

    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mUser != null){
            Intent intent = new Intent(HostActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }

    }
}