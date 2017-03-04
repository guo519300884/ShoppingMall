package shoppingmall.type.bean;

import java.util.List;

/**
 * Created by 皇 上 on 2017/3/3.
 */

public class TagBean {
    /**
     * code : 200
     * msg : 请求成功
     * result : [{"name":"尚硅谷","tag_id":"1"}]
     */

    private int code;
    private String msg;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * name : 尚硅谷
         * tag_id : 1
         */

        private String name;
        private String tag_id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTag_id() {
            return tag_id;
        }

        public void setTag_id(String tag_id) {
            this.tag_id = tag_id;
        }
    }

    @Override
    public String toString() {
        return "TagBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
