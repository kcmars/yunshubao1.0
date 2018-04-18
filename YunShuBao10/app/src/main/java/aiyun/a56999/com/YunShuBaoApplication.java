package aiyun.a56999.com;

import android.app.Application;
import android.util.Log;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;


/**
 * @ClassName: YunShuBaoApplication
 * @Description: Application
 * @Author zhangping
 * @Date 2018/4/17
 */
public class YunShuBaoApplication extends Application{
    public static YunShuBaoApplication mApplication;

    public static YunShuBaoApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        //讯飞模块
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5ad5b361");
    }

}
