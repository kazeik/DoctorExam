package com.kazeik.doctor.doctorexam.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kazeik.chen on 2016/4/20 0020 13:10.
 * email:kazeik@163.com ,QQ:77132995
 */
public class MockItemBean implements Serializable{

    /**
     * re_st : success
     * re_msg : [{"id":"2","name":"海南省2015年医师定期考核模拟试卷-人文（50）","price":0.01,"type":"人文医学","order_type":"6","security_code":"f417088866476150480bff304b1d998e","show_type":"4"},{"id":"3","name":"海南省2015年医师定期考核模拟试卷-人文（46）","price":0.01,"type":"人文医学","order_type":"6","security_code":"45694fcf70e9906d05ec2c91aee2d304","show_type":"4"},{"id":"4","name":"海南省2015年医师定期考核模拟试卷-人文（45）","price":0.01,"type":"人文医学","order_type":"6","security_code":"90856b39b5fc6a00a26b16e25fb9ae60","show_type":"4"},{"id":"5","name":"海南省2015年医师定期考核模拟试卷-人文（44）","price":0.01,"type":"人文医学","order_type":"6","security_code":"49eaf27e735282606427c0bbfbdfd286","show_type":"4"},{"id":"6","name":"海南省2015年医师定期考核模拟试卷-人文（41）","price":0.01,"type":"人文医学","order_type":"6","security_code":"e19e955abd118482a8f44c11630ebdc6","show_type":"4"}]
     * re_url :
     */

    public String re_st;
    public String re_url;
    public List<ReMsgEntity> re_msg;

    public static class ReMsgEntity implements Serializable{
        /**
         * id : 2
         * name : 海南省2015年医师定期考核模拟试卷-人文（50）
         * price : 0.01
         * type : 人文医学
         * order_type : 6
         * security_code : f417088866476150480bff304b1d998e
         * show_type : 4
         */

        public String id;
        public String name;
        public double price;
        public String type;
        public String order_type;
        public String security_code;
        /**
         *  1 // 已经购买未作答
         *  5  // 已经购买并作答
         *  2  // 已经加入购物车
         *  3  // 等待支付
         *  4 // 未购买未加入购物车
         */
        public int show_type;
    }
}
