package com.lovecalculator.prem.lovecalculator;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class ResultPage extends AppCompatActivity {
    ProgressCircle progressCircle;
    String correct =null;
    String total = null;
    TextView resultText ;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#3F51B5"));
        }
        resultText = (TextView) this.findViewById(R.id.resultText);
        b = (Button) findViewById(R.id.button);

        b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Animation anim = AnimationUtils.loadAnimation(ResultPage.this, R.anim.tween);
                b.startAnimation(anim);
                return false;
            }
        });

        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(ResultPage.this, R.anim.tween);
                b.startAnimation(anim);

            }
        });

        Intent i = getIntent();
        correct = (i.getExtras().getString("love"));
        progressCircle = (ProgressCircle) findViewById(R.id.progress_circle);
        progressCircle.setTextColor(getResources().getColor(R.color.progress));//Color.parseColor("#FF0FC9F6"));//Color.rgb(109, 221, 154));
        progressCircle.setProgressColor(getResources().getColor(R.color.progressBar));//rgb(61, 232, 32));
        progressCircle.setIncompleteColor(Color.parseColor("#FFF64B5F"));//rgb(255, 13, 13));
        animate();


    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result, menu);
        return true;
    }*/

//    @Override
   /* public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.home_page)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }*/

    public void animate() {
        float val = Float.parseFloat(correct)/100;
        if(val>=0.5){
            resultText.setTextColor(getResources().getColor(R.color.progress));//Color.parseColor("#FF0FC9F6"));//rgb(0, 204,102));
        }else{
            resultText.setTextColor(Color.parseColor("#FFF64B5F"));//rgb(255, 80, 80));
        }
        progressCircle.setProgress(val);
        progressCircle.startAnimation();
    }
}

