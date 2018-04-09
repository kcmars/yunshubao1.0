package aiyun.a56999.com.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/** * @ClassName: FormatUtil * @Description: 格式化工具类 * @Author Wangnan * @Date 2018/4/9 */
public class FormatUtil {

    /**
     * 判断手机号是否合法
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147)|(145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 格式化时间戳
     * 年-月-日 时-分
     * @param timeStamp
     * @return
     */
    public static String formatTime(String timeStamp) {
        String dateString = null;
        Calendar cd = Calendar.getInstance();
        int nowDayOfYear = cd.get(Calendar.DAY_OF_YEAR);
        cd.setTime(new Date(Double.valueOf(timeStamp).longValue() * 1000L));
        int date = cd.get(Calendar.DAY_OF_YEAR);
        int value = nowDayOfYear - date;
        // 获取指定日期转换成星期几
        if (value == -2) {
            dateString = "后天";
        } else if (value == -1) {
            dateString = "明天";
        } else if (value == 0) {
            dateString = "今天";
        } else if (value == 1) {
            dateString = "昨天";
        } else if (value == 2) {
            dateString = "前天";
        } else {
            dateString = cd.get(Calendar.YEAR) + "-" + ((cd.get(Calendar.MONTH) + 1) > 9 ? (cd.get(Calendar.MONTH) + 1) : "0" + (cd.get(Calendar.MONTH) + 1)) + "-" + (cd.get(Calendar.DAY_OF_MONTH) > 9 ? cd.get(Calendar.DAY_OF_MONTH) : "0" + cd.get(Calendar.DAY_OF_MONTH));
        }
        dateString += " " + (cd.get(Calendar.HOUR_OF_DAY) > 9 ? cd.get(Calendar.HOUR_OF_DAY) : "0" + cd.get(Calendar.HOUR_OF_DAY)) + ":" + (cd.get(Calendar.MINUTE) > 9 ? cd.get(Calendar.MINUTE) : "0" + cd.get(Calendar.MINUTE));
        return dateString;
    }

    /**
     * 格式化时间戳
     * 年-月-日
     * @param timeStamp
     * @return
     */
    public static String formatTime1(String timeStamp) {
        String dateString = null;
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(Double.valueOf(timeStamp).longValue() * 1000L));
        dateString = cd.get(Calendar.YEAR) + "-" + ((cd.get(Calendar.MONTH) + 1) > 9 ? (cd.get(Calendar.MONTH) + 1) : "0" + (cd.get(Calendar.MONTH) + 1)) + "-" + (cd.get(Calendar.DAY_OF_MONTH) > 9 ? cd.get(Calendar.DAY_OF_MONTH) : "0" + cd.get(Calendar.DAY_OF_MONTH));
        return dateString;
    }

    /**
     * 格式化时间戳
     * 时-分
     * @param timeStamp
     * @return
     */
    public static String formatTime2(String timeStamp) {
        String dateString = null;
        Calendar cd = Calendar.getInstance();
        int nowDayOfYear = cd.get(Calendar.DAY_OF_YEAR);
        cd.setTime(new Date(Double.valueOf(timeStamp).longValue() * 1000L));
        int date = cd.get(Calendar.DAY_OF_YEAR);
        dateString = " " + (cd.get(Calendar.HOUR_OF_DAY) > 9 ? cd.get(Calendar.HOUR_OF_DAY) : "0" + cd.get(Calendar.HOUR_OF_DAY)) + ":" + (cd.get(Calendar.MINUTE) > 9 ? cd.get(Calendar.MINUTE) : "0" + cd.get(Calendar.MINUTE));
        return dateString;
    }

    // 判断一个字符串是否都为数字
    public static boolean isDigit(String strNum) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher((CharSequence) strNum);
        return matcher.matches();
    }

    //截取数字
    public static int getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return Integer.valueOf(matcher.group(0));
        }
        return 0;
    }

    // 截取非数字
    public static String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     * 是否是中文字符
     *
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        if (str == null) {
            return false;
        }
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 将图片转换成Base64编码的字符串
     * @param path
     * @return base64编码的字符串
     */
    public static String getBase64(String path){
        String mBase64 = "";
        if(TextUtils.isEmpty(path)){
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        try{
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            mBase64 = Base64.encodeToString(data, Base64.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null !=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mBase64;
    }
}
