package shoppingmall.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 皇 上 on 2017/2/27.
 */

public class CacheUtils {

    private static SharedPreferences sp;

    /**
     * 得到String类型的数据
     *
     * @param context
     * @param key
     * @return 没找到返回空字符串
     */
    public static String getString(Context context, String key) {
        if (sp == null) {
            if (context != null) {
                sp = context.getSharedPreferences("gjw", Context.MODE_PRIVATE);
            }
        }
        return sp.getString(key, "");
    }


    /**
     * 保存文本数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences("gjw", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    /**
     * 得到boolean类型数据
     *
     * @param context
     * @param key
     * @return 没找到就返回false
     */
    public static boolean getBoolean(Context context, String key) {
        if (sp == null) {
            if(context != null) {
                sp = context.getSharedPreferences("gjw", context.MODE_PRIVATE);
            }
        }
        return sp.getBoolean(key, false);
    }

    /**
     * 保存boolean数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            if (context != null) {
                sp = context.getSharedPreferences("gjw", Context.MODE_PRIVATE);
            }
        }
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 保存int类型数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setInt(Context context, String key, int value) {
        if (sp == null) {
            if(context != null) {
                sp = context.getSharedPreferences("gjw", Context.MODE_PRIVATE);
            }
        }
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 得到int类型数据
     *
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context,String key){
        if(sp == null) {
            sp = context.getSharedPreferences("gjw",Context.MODE_PRIVATE);
        }
        return sp.getInt(key,0);
    }
}
