package shoppingmall.cart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import shoppingmall.home.bean.GoodsBean;
import shoppingmall.utils.CacheUtils;

/**
 * Created by 皇 上 on 2017/2/27.
 */

public class CartStorage {

    public static final String GJW = "gjw";
    private static CartStorage instace;
    private final Context context;
    //SparseArray替代 HashMap
    private SparseArray<GoodsBean> sparseArray;

    public CartStorage(Context context) {
        this.context = context;
        sparseArray = new SparseArray<>();
        //从本地获取数据
        listToSparseArray();
    }

    //将List的数据转换为 SparseArray的数据
    private void listToSparseArray() {

        List<GoodsBean> beanList = getAllData();
        //利用循环  将数据保存到sparseArray
        for (int i = 0; i < beanList.size(); i++) {
            GoodsBean goodsBean = beanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        }
    }

    /**
     * 获取到全部数据
     *
     * @return
     */
    public List<GoodsBean> getAllData() {
        return getLoaclData();
    }

    /**
     * 获取到本地数据
     *
     * @return
     */
    private List<GoodsBean> getLoaclData() {

        List<GoodsBean> goodsBeens = new ArrayList<>();
        //从本地获取数据

        //获取到本地保存的JSON数据  sp中取得数据
        String json = CacheUtils.getString(context, GJW);
        if (!TextUtils.isEmpty(json)) {
            //将json数据转换为列表
            goodsBeens = new Gson().fromJson(json, new TypeToken<List<GoodsBean>>() {
            }.getType());
        }
        //把json数据解析为List集合
        return goodsBeens;
    }

    /**
     * 单例->懒汉模式
     *
     * @param context
     * @return
     */
    public static CartStorage getInstance(Context context) {
        if (instace == null) {
            //加锁
            synchronized (CartStorage.class) {
                if (instace == null) {
                    instace = new CartStorage(context);
                }
            }
        }
        return instace;
    }

    /**
     * 添加数据
     *
     * @param goodsBean
     */
    public void addData(GoodsBean goodsBean) {
        //数据添加到sparseArray中
        GoodsBean tempGoodsBean = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        //判断是否保存过
        if (tempGoodsBean != null) {
            //添加过 保存过
            tempGoodsBean.setNumber(tempGoodsBean.getNumber() + goodsBean.getNumber());
        } else {
            //没有添加保存过
            tempGoodsBean = goodsBean;
        }
        //添加到集合中
        sparseArray.put(Integer.parseInt(tempGoodsBean.getProduct_id()), tempGoodsBean);

        //保存到本地
        saveLocal();
    }

    /**
     * 保存到本地
     */
    private void saveLocal() {
        //1.将sparseArray转换为List
        List<GoodsBean> goodsBeanList = sparseArrayToList();
        //2.用Gson将List转json的String 类型数据
        String saveJson = new Gson().toJson(goodsBeanList);
        //使用CacheUtils缓存数据
        CacheUtils.setString(context, GJW, saveJson);
    }

    /**
     * 删除数据
     *
     * @param goodsBean
     */
    public void deleteData(GoodsBean goodsBean) {
        //删除数据
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        //保存到本地
        saveLocal();
    }

    /**
     * 修改、更新
     *
     * @param goodsBean
     */
    public void updataData(GoodsBean goodsBean) {
        //更新
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        //保存到本地
        saveLocal();
    }

    /**
     * 将sparseArray转换成List
     *
     * @return
     */
    private List<GoodsBean> sparseArrayToList() {
        //列表数据
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }
}
