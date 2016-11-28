package com.kazeik.doctor.doctorexam.activity;

import java.util.List;

/**
 * Created by kazeik.chen on 2016/5/10 0010 16:51.
 * email:kazeik@163.com ,QQ:77132995
 */
public class OrderDetailBean {

    /**
     * re_st : success
     * re_msg : {"order_info_id":"66943","order_id":"1662840146286922462","uid":"62840","order_status":"1","delivery_status":"1","pay_type":"混合支付","total_amount":"300.00","balance_amount":"0.00","pay_amount":"300.00","add_time":"2016-05-10 16:33:44","pay_time":"0000-00-00 00:00:00","detail":{"67004":{"good_id":"KC0082","good_name":"口腔+人文","price":"100.00"},"67005":{"good_id":"KC0083","good_name":"临床+人文","price":"100.00"},"67006":{"good_id":"KC0086","good_name":"中医+人文","price":"100.00"}}}
     * re_url :
     */

    public String re_st;
    public ReMsgEntity re_msg;
    public static class ReMsgEntity{

        /**
         * order_info_id : 66943
         * order_id : 1662840146286922462
         * uid : 62840
         * order_status : 1
         * delivery_status : 1
         * pay_type : 混合支付
         * total_amount : 300.00
         * balance_amount : 0.00
         * pay_amount : 300.00
         * add_time : 2016-05-10 16:33:44
         * pay_time : 0000-00-00 00:00:00
         */

        public String order_info_id;
        public String order_id;
        public String uid;
        public String order_status;
        public String delivery_status;
        public String pay_type;
        public String total_amount;
        public String balance_amount;
        public String pay_amount;
        public String add_time;
        public String pay_time;

        public List<Detail> detail;

        @Override
        public String toString() {
            return "ReMsgEntity{" +
                    "add_time='" + add_time + '\'' +
                    ", order_info_id='" + order_info_id + '\'' +
                    ", order_id='" + order_id + '\'' +
                    ", uid='" + uid + '\'' +
                    ", order_status='" + order_status + '\'' +
                    ", delivery_status='" + delivery_status + '\'' +
                    ", pay_type='" + pay_type + '\'' +
                    ", total_amount='" + total_amount + '\'' +
                    ", balance_amount='" + balance_amount + '\'' +
                    ", pay_amount='" + pay_amount + '\'' +
                    ", pay_time='" + pay_time + '\'' +
                    ", detail=" + detail +
                    '}';
        }
    }
    public static class Detail{

        /**
         * good_id : KC0083
         * good_name : 临床+人文
         * price : 100.00
         */

        public String good_id;
        public String good_name;
        public String price;


        @Override
        public String toString() {
            return "Detail{" +
                    "good_id='" + good_id + '\'' +
                    ", good_name='" + good_name + '\'' +
                    ", price='" + price + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrderDetailBean{" +
                "re_msg=" + re_msg +
                ", re_st='" + re_st + '\'' +
                '}';
    }
}
