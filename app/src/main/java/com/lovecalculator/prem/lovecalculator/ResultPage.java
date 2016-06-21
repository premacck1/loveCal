package com.lovecalculator.prem.lovecalculator;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.MailTo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class ResultPage extends AppCompatActivity {
    private ProgressCircle progressCircle;
    private String correct =null;
    private String total = null;
    private TextView resultText ;
    private StringBuilder shareString;
    private Button b;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultpage);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
//        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#3F51B5"));
        }
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        ImageView iv1 = (ImageView) findViewById(R.id.imageViewNew);
        resultText = (TextView) this.findViewById(R.id.resultText);
        b = (Button) findViewById(R.id.button);
        relativeLayout = (RelativeLayout)findViewById(R.id.relative1);

        shareString = new StringBuilder("Hi, I have just used Love Calculator to calculate Love Compatibility between");

        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(ResultPage.this, R.anim.tween);
                b.startAnimation(anim);
               /* //INSERTED HEART ANIMATION
                Animation anim3 = AnimationUtils.loadAnimation(ResultPage.this, R.anim.heart_anim_exit);
                assert iv != null;
                iv.startAnimation(anim3);
                iv.setVisibility(View.INVISIBLE);*/
                takeScreenshot();
            }
        });

        Intent i = getIntent();
        correct = (i.getExtras().getString("love"));
        String self = i.getExtras().getString("self");
        String partner = i.getExtras().getString("partner");
        shareString.append(" "+self + " and "+ partner + ". This is really fun and amazing.!! Try it out.");

        progressCircle = (ProgressCircle) findViewById(R.id.progress_circle);
        progressCircle.setTextColor(getResources().getColor(R.color.progress));//Color.parseColor("#FF0FC9F6"));//Color.rgb(109, 221, 154));
        progressCircle.setProgressColor(getResources().getColor(R.color.progressBar));//rgb(61, 232, 32));
        progressCircle.setIncompleteColor(Color.parseColor("#FFF64B5F"));//rgb(255, 13, 13));
        animate();
        //INSERTED HEART ANIMATION
        Animation anim1 = AnimationUtils.loadAnimation(ResultPage.this, R.anim.total_animation);
        assert iv != null;
        iv.setVisibility(View.VISIBLE);
        iv.startAnimation(anim1);
        iv.setVisibility(View.INVISIBLE);
        iv1.setVisibility(View.VISIBLE);
//        Animation anim2;
/*
        HEY! PLEASE INSERT HERE THE code to loop the animation "hearts_anim_beats.xml" until the share button is not clicked.
        also, while writing the following block, the minimum SDK was changed from 11 to 16.
        while(!b.hasOnClickListeners()) {
            iv.startAnimation(anim2);
        }
         */

    }

        // Screenshot from App
        private void takeScreenshot() {
        Date now = new Date();
        String dateStr = (android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now)).toString();
            dateStr = dateStr.replace(":","_");
        try {

            Bitmap bitmap = Bitmap.createBitmap(relativeLayout.getMeasuredWidth(),relativeLayout.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            relativeLayout.draw(canvas);

           /* // create bitmap screen capture
            View v1 = relativeLayout.getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);*/

            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/.screenshots/";
            File dir = new File(mPath);
            if(!dir.exists())
                dir.mkdirs();
            File imageFile = new File(mPath, dateStr+".png");
            try {
                FileOutputStream fOut = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fOut);
                fOut.flush();
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        /*    File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 90;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();*/

            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {

        Uri uritoimage = Uri.fromFile(imageFile);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,shareString.toString());
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uritoimage);
        shareIntent.setType("image/png");
        startActivity(Intent.createChooser(shareIntent,"Love Compatibility"));
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
                            Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.fromParts(
                                    "mailto","premankur19@gmail.com", null));
                            emailIntent.setType("text/email");
                            emailIntent.putExtra(Intent.EXTRA_EMAIL, R.string.email);
                            startActivity(Intent.createChooser(emailIntent, "Send email"));
                        }
                    });
        }

        return super.onOptionsItemSelected(item);
    }
}

