package com.example.new_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class login2 extends AppCompatActivity {
    EditText e1,e2;
    Button getotp,verifyotp;
    String codesent;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        getotp=findViewById(R.id.bn1);
        verifyotp=findViewById(R.id.bn2);
        mAuth=FirebaseAuth.getInstance();
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String phone=e1.getText().toString();
                if(phone.isEmpty())
                {
                    e1.setError("This Field should not be empty");
                    e1.requestFocus();
                    return;
                }
                else
                    {
                           int l=phone.length();
                           if(l==13)
                           {
                               Toast.makeText(login2.this, "Sending", Toast.LENGTH_SHORT).show();
                               generateotp();
                           }
                           else if(l==10)
                           {
                               Toast.makeText(login2.this,"Enter country code before mobile number",Toast.LENGTH_LONG).show();
                           }
                           else
                           {
                               Toast.makeText(login2.this,"Enter valid phone number",Toast.LENGTH_LONG).show();
                           }
                }
            }
        });
        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String otp=e2.getText().toString();
                if(otp.isEmpty())
                {
                    e2.setError("This Field should not be empty");
                    e2.requestFocus();
                    return;
                }
                else {
                    verifyotp();
                }
            }
        });
    }
    public void generateotp()
    {
        String pn=e1.getText().toString();
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder()
                        .setPhoneNumber(pn)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(login2.this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
    {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential)
        {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            if (e instanceof FirebaseAuthInvalidCredentialsException)
            {

            }
            else if (e instanceof FirebaseTooManyRequestsException)
            {

            }
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                @NonNull PhoneAuthProvider.ForceResendingToken token)
        {
             codesent= verificationId;
        }
    };
    public void verifyotp()
    {
        String code=e2.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codesent, code);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(login2.this,"Login Successful",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(login2.this,otpverification.class);
                            startActivity(intent);
                        }
                        else
                            {
                                Toast.makeText(login2.this,"Login Failed",Toast.LENGTH_LONG).show();

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                            {
                                  Toast.makeText(login2.this,"Invalid Code",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
}
