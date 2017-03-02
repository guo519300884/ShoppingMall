package shoppingmall.home.bean;

/**
 * Created by 皇 上 on 2017/3/1.
 */

public class H5Bean {

    /**
     * option : 1
     * type : 1
     * value : {"product_id":9875}
     */

    private int option;
    private int type;
    private ValueBean value;

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ValueBean getValue() {
        return value;
    }

    public void setValue(ValueBean value) {
        this.value = value;
    }

    public static class ValueBean {
        /**
         * product_id : 9875
         */

        private int product_id;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }
    }
}
