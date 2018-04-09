package aiyun.a56999.com.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

/** * @ClassName: SystemUtil * @Description: 系统工具类 * @Author zhangping * @Date 2018/4/9 */
public class SystemUtil {

    /**
     * 保存用户信息
     *
     * @param context
     * @param key
     * @param value
     */
    public static void savePreference(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     *清除用户信息
     *
     * @param context
     */
    public static void clearPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        String identify = preferences.getString("identify", null);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putString("identify", identify);
        editor.commit();
    }

    /**
     *清除用户某个信息
     *
     * @param context
     */
    public static void removePreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 获取用户信息
     *
     * @param context
     * @param newKey
     * @return
     */
    public static String getPreference(Context context, String newKey) {
        SharedPreferences preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        String value = preferences.getString(newKey, null);
        return value;
    }

    /**
     * 判断网络连接是否已开
     * 2017-09-21
     * true 已打开  false 未打开
     */
    public static boolean isConn(Context context) {
        boolean bisConnFlag = false;
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if (network != null) {
            bisConnFlag = conManager.getActiveNetworkInfo().isAvailable();
        }
        return bisConnFlag;
    }

    /**
     * 打开设置网络界面
     */
    public static void openNetworkSettings(final Activity context, int request) {
        try {
            Intent intent = new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
            context.startActivityForResult(intent, request);
        } catch (Exception e) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivityForResult(intent, request);
            e.printStackTrace();
        }
    }

    /**
     * 是否开启了GPS定位
     */
    public static boolean isOpenGPS(Context context){
        try {
            LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            boolean isOpen = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            return isOpen;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 是否开启定位服务
     *
     * @param context
     * @return
     */
    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    /**
     * 打开位置权限界面
     */
    public static void openLocationSettings(final Activity context, int request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivityForResult(intent, request);
        } else {
            try {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivityForResult(intent, request);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 打开定位服务
     *
     * @param context
     * @param request
     */
    public static void openLocationServiceSettings(final Activity context, int request) {
        try {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivityForResult(intent, request);
        } catch (Exception e) {

        }
    }
}
