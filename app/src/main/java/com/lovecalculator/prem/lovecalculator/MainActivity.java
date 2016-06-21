package com.lovecalculator.prem.lovecalculator;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.uncopt.android.widget.text.justify.JustifiedTextView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private EditText self_edit;
    private EditText partner_edit;
    private RadioButton selfM;
    private RadioButton selfF;
    private RadioButton partnerM;
    private RadioButton partnerF;
    private Button b;
    private boolean doubleBackToExitPressedOnce;

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

        b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tween);
                b.startAnimation(anim);
                return false;
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String self = self_edit.getText().toString();
                String partner = partner_edit.getText().toString();
                if(checkSecurity(self,partner,selfM.isChecked(),selfF.isChecked(),partnerF.isChecked(),partnerM.isChecked()))
                {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tween);
                String lovePer = getLovePercentInteger(self,partner);
                Intent in = new Intent(MainActivity.this, ResultPage.class);
                in.putExtra("love",lovePer);
                in.putExtra("self",self);
                in.putExtra("partner",partner);
                startActivity(in);
                }
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

    public String getLovePercentInteger(String self, String partner){
        ArrayList<Integer> intArr = new ArrayList<>();
        ArrayList<Integer> intArrNew = new ArrayList<>();

        String[] loveArr = {"l","o","v","e","s"};
        for (String str : loveArr) {
            int count_1 = StringUtils.countMatches(self, str);
            int count_2 = StringUtils.countMatches(partner, str);
            int totalCount = count_1 + count_2;
            intArr.add(totalCount);
        }

        for (int i=0; i<intArr.size()-1;i++){
            intArrNew.add(i,(intArr.get(i)+intArr.get(i+1)));
        }
        intArr.clear();
        for (int i=0; i<intArrNew.size()-1;i++){
            intArr.add(i,intArrNew.get(i)+intArrNew.get(i+1));
        }
        intArrNew.clear();
        for (int i=0; i<intArr.size()-1;i++){
            int a = intArr.get(i)+intArr.get(i+1);
            if(a>9){
                int sum = a%10;
                sum = sum+(a/10);
                a= sum;
            }
            intArrNew.add(i,a);
        }
        //CHeck if greater than or equal to 10
        String totalPerc = intArrNew.get(0).toString()+intArrNew.get(1).toString();
        if(Integer.parseInt(totalPerc)<=40){
            totalPerc = String.valueOf(Integer.parseInt(totalPerc) + 30);
        }
        return totalPerc;
    }

    public Boolean checkSecurity(String s, String p, Boolean s_m, Boolean s_f, Boolean p_m, Boolean p_f){
        if (s.isEmpty() || p.isEmpty()){

            Toast.makeText(this,"Please write your full name and Patner's name.",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!(s_m||s_f)){
            Toast.makeText(this,"Please select gender.",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!(p_m||p_f)){
            Toast.makeText(this,"Please select gender.",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.info_butt) {
            Dialog d = new Dialog(this);
            d.setContentView(R.layout.about_us);
            d.setTitle(R.string.info_heading);
            d.show();
            JustifiedTextView aboutText = (JustifiedTextView) d.findViewById(R.id.aboutDetailsText);
            aboutText.setText(R.string.Info_text);
            TextView premBros = (TextView) d.findViewById(R.id.creditName1);
            premBros.setTextColor(Color.BLUE);
            premBros.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("text/email");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, R.string.email);
                    startActivity(Intent.createChooser(emailIntent, "Send email"));
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}