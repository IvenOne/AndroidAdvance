package com.iven.i7helper.main.splash;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.iven.i7helper.R;
import com.iven.i7helper.base.BaseActivity;
import com.iven.i7helper.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends BaseActivity {


    private TextView tv;


    private Timer timer = new Timer();
    private int time = 5;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what <= 0){
                jumpToActivity();
            }else {
                tv.setText(msg.what+"(s)点击跳转");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        getWindow().getDecorView().setSystemUiVisibility(option);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);


        tv = (TextView) findViewById(R.id.splash_timer);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToActivity();
            }
        });

        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = time--;
                        handler.sendMessage(msg);
                    }
                },0,1000
        );

    }


    /**
     * Jump to another activity.
     */
    private void jumpToActivity(){
        timer.cancel();
        Intent it = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(it);
        SplashActivity.this.finish();
    }
}
