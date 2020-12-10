package com.moringaschool.pms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.moringaschool.pms.Authentication.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "This is in HomePageActivity";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button forcer;

    @BindView(R.id.forcer) Button Forcer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);

        forcer = Forcer;

        forcer.setOnClickListener(this);

        setupFirebaseAuth();
        //This methods will be used later on when we need to reference the current user

//        setUserDetails();
//        getUserDetails();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.optionsSignout:
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkAuthenticationState();
    }

    private void checkAuthenticationState(){
        Log.d(TAG, "checkAuthenticationState: checking authentication state.");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            Log.d(TAG, "checkAuthenticationState: user is null, navigating back to login screen.");

            Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }else{
            Log.d(TAG, "checkAuthenticationState: user is authenticated.");
        }
    }


    private void signOut(){
        Log.d(TAG, "signOut: signing out");
        FirebaseAuth.getInstance().signOut();
    }


//    private void getUserDetails(){
//        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
//        String allUserDetails = "The current User has this as credentials: ";
//
//        String name = mUser.getDisplayName();
//        String email = mUser.getEmail();
//        Uri profilePhoto = mUser.getPhotoUrl();
//        String id = mUser.getUid();
////        String anything = profilePhoto.getHost();
//
//        Log.i(TAG, "getUserDetails: " + allUserDetails + "\n" + name + "\n" +  email + "\n" + profilePhoto + id);
//
//    }



//    private void setUserDetails(){
//        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
//        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
//                .setDisplayName("Ken Joel")
//                .setPhotoUri(Uri.parse("https://ibb.co/zJ2042w"))
//                .build();
//        mUser.updateProfile(userProfileChangeRequest);
//    }


    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: started.");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        if(v == forcer){
            Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

}