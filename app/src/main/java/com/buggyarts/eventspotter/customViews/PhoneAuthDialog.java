package com.buggyarts.eventspotter.customViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buggyarts.eventspotter.R;

public class PhoneAuthDialog extends FrameLayout implements View.OnClickListener {

    FrameLayout rootLayout;
    LinearLayout numberForm;
    LinearLayout otpForm;

    TextInputEditText numberField;
    TextInputEditText numberField2;
    TextInputEditText otpField;

    TextView btnEditNumber;
    TextView btnSendOtp;
    TextView btnLogin;

    String mobileNumber;
    Callback callback;

    public interface Callback{
        void onWindowOutSideClick();
        void onSendOtpClick();
        void onLoginClick();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public PhoneAuthDialog(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.phone_auth_dialog,this,true);

        rootLayout = findViewById(R.id.rootLayout);
        numberField = findViewById(R.id.numberField);
        numberField2 = findViewById(R.id.numberField2);
        otpField = findViewById(R.id.otpField);

        numberForm = findViewById(R.id.enter_mobile_form);
        otpForm = findViewById(R.id.enter_otp_form);

        btnSendOtp = findViewById(R.id.btn_sendOtp);
        btnLogin = findViewById(R.id.btn_login);
        btnEditNumber = findViewById(R.id.edit_number);

        btnSendOtp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnEditNumber.setOnClickListener(this);
        rootLayout.setOnClickListener(this);

        numberField.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    callback.onSendOtpClick();
                    return true;
                }

                return false;
            }
        });

    }

    public TextInputEditText getNumberField() {
        return numberField;
    }

    public TextInputEditText getOtpField() {
        return otpField;
    }

    public TextInputEditText getNumberField2() {
        return numberField2;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void showNumberForm(){
        numberForm.setVisibility(VISIBLE);
        otpForm.setVisibility(GONE);
    }

    public void showOtpForm(){
        numberForm.setVisibility(GONE);
        otpForm.setVisibility(VISIBLE);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.rootLayout){
            callback.onWindowOutSideClick();
        }else if(view.getId() == R.id.btn_sendOtp){
            callback.onSendOtpClick();
        }else if(view.getId() == R.id.btn_login){
            if(otpField.getText().toString().length() != 0) {
                callback.onLoginClick();
            }
        }else if(view.getId() == R.id.edit_number){
            showNumberForm();
        }
    }

}
