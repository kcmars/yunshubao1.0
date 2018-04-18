package aiyun.a56999.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import aiyun.a56999.com.R;

/**
 * @ClassName: SplashActivity
 * @Description: app启动页
 * @Author zhangping
 * @Date 2018/4/18
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
//        startActivity(intent);
//        finish();
//        setContentView(R.layout.activity_splash);
        //延迟2S跳转
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
