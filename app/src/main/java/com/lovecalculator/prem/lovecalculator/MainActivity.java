package com.lovecalculator.prem.lovecalculator;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity {

    EditText self_edit;
    EditText partner_edit;
    RadioButton selfM;
    RadioButton selfF;
    RadioButton partnerM;
    RadioButton partnerF;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        self_edit = (EditText) findViewById(R.id.self_editText);
        partner_edit = (EditText) findViewById(R.id.partner_editText);
        selfM = (RadioButton) findViewById(R.id.self_radioButtonM);
        selfF = (RadioButton) findViewById(R.id.self_radioButtonF);
        partnerM = (RadioButton) findViewById(R.id.partner_radioButtonM);
        partnerF = (RadioButton) findViewById(R.id.partner_radioButtonF);
        b = (Button) findViewById(R.id.button1);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tween);
                b.startAnimation(anim);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tween2);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.self_radioButtonM:
                if (checked) {
                    selfM.setTypeface(Typeface.DEFAULT_BOLD);
                    selfF.setTypeface(Typeface.DEFAULT);
                    selfM.setTextColor(Color.parseColor("#26c076"));
                    selfF.setTextColor(Color.parseColor("#d41a1d"));
                    selfM.startAnimation(anim);
                    break;
                }
            case R.id.self_radioButtonF:
                if (checked) {
                    selfM.setTypeface(Typeface.DEFAULT);
                    selfF.setTypeface(Typeface.DEFAULT_BOLD);
                    selfM.setTextColor(Color.parseColor("#d41a1d"));
                    selfF.setTextColor(Color.parseColor("#26c076"));
                    selfF.startAnimation(anim);
                    break;
                }
            case R.id.partner_radioButtonM:
                if (checked) {
                    partnerM.setTypeface(Typeface.DEFAULT_BOLD);
                    partnerF.setTypeface(Typeface.DEFAULT);
                    partnerM.setTextColor(Color.parseColor("#26c076"));
                    partnerF.setTextColor(Color.parseColor("#d41a1d"));
                    partnerM.startAnimation(anim);
                    break;
                }
            case R.id.partner_radioButtonF:
                if (checked) {
                    partnerM.setTypeface(Typeface.DEFAULT);
                    partnerF.setTypeface(Typeface.DEFAULT_BOLD);
                    partnerM.setTextColor(Color.parseColor("#d41a1d"));
                    partnerF.setTextColor(Color.parseColor("#26c076"));
                    partnerF.startAnimation(anim);
                    break;
                }
        }
    }
}