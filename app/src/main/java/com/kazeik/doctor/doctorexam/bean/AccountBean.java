package com.kazeik.doctor.doctorexam.bean;/**
 * 我的帐户帐单
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 17 20:24
 */

import java.io.Serializable;
import java.util.List;

/**
 * 我的帐户帐单
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 17 20:24
 */

public class AccountBean implements Serializable{
    /**
     * re_st : success
     * re_msg : {"detail_array":[{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"111542","order_id":"1656199146622614795","amount":"200.00","order_time":"2016-06-18 13:02:28"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"111541","order_id":"1656199146614469245","amount":"0.00","order_time":"2016-06-17 14:24:52"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"111540","order_id":"1656199146614398265","amount":"200.00","order_time":"2016-06-17 14:13:02"},{"pay_type":"支付宝","in_out_amount":"支出","in_out_amount_st":"2","id":"111523","order_id":"1656199146476212522","amount":"0.01","order_time":"2016-06-01 14:22:37"},{"pay_type":"支付宝","in_out_amount":"存入","in_out_amount_st":"1","id":"111522","order_id":"1656199146476212522","amount":"0.01","order_time":"2016-06-01 14:22:37"},{"pay_type":"支付宝","in_out_amount":"支出","in_out_amount_st":"2","id":"111433","order_id":"1656199146036428769","amount":"0.01","order_time":"2016-04-11 16:45:10"},{"pay_type":"支付宝","in_out_amount":"存入","in_out_amount_st":"1","id":"111432","order_id":"1656199146036428769","amount":"0.01","order_time":"2016-04-11 16:45:10"},{"pay_type":"支付宝","in_out_amount":"支出","in_out_amount_st":"2","id":"111401","order_id":"1656199145872537294","amount":"0.01","order_time":"2016-03-23 17:29:59"},{"pay_type":"支付宝","in_out_amount":"存入","in_out_amount_st":"1","id":"111400","order_id":"1656199145872537294","amount":"0.01","order_time":"2016-03-23 17:29:59"},{"pay_type":"支付宝","in_out_amount":"支出","in_out_amount_st":"2","id":"111399","order_id":"1656199145872482853","amount":"0.01","order_time":"2016-03-23 17:22:28"},{"pay_type":"支付宝","in_out_amount":"存入","in_out_amount_st":"1","id":"111398","order_id":"1656199145872482853","amount":"0.01","order_time":"2016-03-23 17:22:28"},{"pay_type":"账户余额","in_out_amount":"存入","in_out_amount_st":"1","id":"111381","order_id":"16001000145258351267","amount":"100.00","order_time":"2016-02-04 13:08:20"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"111379","order_id":"1656199145370650019","amount":"100.00","order_time":"2016-01-25 15:21:40"},{"pay_type":"账户余额","in_out_amount":"存入","in_out_amount_st":"1","id":"111372","order_id":"16001000145258351265","amount":"100.00","order_time":"2016-01-13 16:30:30"},{"pay_type":"账户余额","in_out_amount":"存入","in_out_amount_st":"1","id":"111371","order_id":"16001000145258351264","amount":"100.00","order_time":"2016-01-13 15:06:09"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"100402","order_id":"1656199144481132494","amount":"150.00","order_time":"2015-10-14 16:28:45"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"100333","order_id":"1656199144429678409","amount":"100.00","order_time":"2015-10-08 17:33:04"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"100332","order_id":"1656199144428926674","amount":"100.00","order_time":"2015-10-08 15:27:46"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"100331","order_id":"1656199144428487010","amount":"100.00","order_time":"2015-10-08 14:14:30"}],"balance":"850.00","learn_card":{"useful_life":"2018-01-12","activation_date":"2016-01-13 15:06:09","card_num":"16001000145258351264","activation_ip":"123.177.21.157","initial_limit":"100"}}
     * re_url :
     */

    public String re_st;
    /**
     * detail_array : [{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"111542","order_id":"1656199146622614795","amount":"200.00","order_time":"2016-06-18 13:02:28"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"111541","order_id":"1656199146614469245","amount":"0.00","order_time":"2016-06-17 14:24:52"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"111540","order_id":"1656199146614398265","amount":"200.00","order_time":"2016-06-17 14:13:02"},{"pay_type":"支付宝","in_out_amount":"支出","in_out_amount_st":"2","id":"111523","order_id":"1656199146476212522","amount":"0.01","order_time":"2016-06-01 14:22:37"},{"pay_type":"支付宝","in_out_amount":"存入","in_out_amount_st":"1","id":"111522","order_id":"1656199146476212522","amount":"0.01","order_time":"2016-06-01 14:22:37"},{"pay_type":"支付宝","in_out_amount":"支出","in_out_amount_st":"2","id":"111433","order_id":"1656199146036428769","amount":"0.01","order_time":"2016-04-11 16:45:10"},{"pay_type":"支付宝","in_out_amount":"存入","in_out_amount_st":"1","id":"111432","order_id":"1656199146036428769","amount":"0.01","order_time":"2016-04-11 16:45:10"},{"pay_type":"支付宝","in_out_amount":"支出","in_out_amount_st":"2","id":"111401","order_id":"1656199145872537294","amount":"0.01","order_time":"2016-03-23 17:29:59"},{"pay_type":"支付宝","in_out_amount":"存入","in_out_amount_st":"1","id":"111400","order_id":"1656199145872537294","amount":"0.01","order_time":"2016-03-23 17:29:59"},{"pay_type":"支付宝","in_out_amount":"支出","in_out_amount_st":"2","id":"111399","order_id":"1656199145872482853","amount":"0.01","order_time":"2016-03-23 17:22:28"},{"pay_type":"支付宝","in_out_amount":"存入","in_out_amount_st":"1","id":"111398","order_id":"1656199145872482853","amount":"0.01","order_time":"2016-03-23 17:22:28"},{"pay_type":"账户余额","in_out_amount":"存入","in_out_amount_st":"1","id":"111381","order_id":"16001000145258351267","amount":"100.00","order_time":"2016-02-04 13:08:20"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"111379","order_id":"1656199145370650019","amount":"100.00","order_time":"2016-01-25 15:21:40"},{"pay_type":"账户余额","in_out_amount":"存入","in_out_amount_st":"1","id":"111372","order_id":"16001000145258351265","amount":"100.00","order_time":"2016-01-13 16:30:30"},{"pay_type":"账户余额","in_out_amount":"存入","in_out_amount_st":"1","id":"111371","order_id":"16001000145258351264","amount":"100.00","order_time":"2016-01-13 15:06:09"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"100402","order_id":"1656199144481132494","amount":"150.00","order_time":"2015-10-14 16:28:45"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"100333","order_id":"1656199144429678409","amount":"100.00","order_time":"2015-10-08 17:33:04"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"100332","order_id":"1656199144428926674","amount":"100.00","order_time":"2015-10-08 15:27:46"},{"pay_type":"账户余额","in_out_amount":"支出","in_out_amount_st":"2","id":"100331","order_id":"1656199144428487010","amount":"100.00","order_time":"2015-10-08 14:14:30"}]
     * balance : 850.00
     * learn_card : {"useful_life":"2018-01-12","activation_date":"2016-01-13 15:06:09","card_num":"16001000145258351264","activation_ip":"123.177.21.157","initial_limit":"100"}
     */

    public ReMsgEntity re_msg;
    public String re_url;

    public static class ReMsgEntity {
        public String balance;
        /**
         * useful_life : 2018-01-12
         * activation_date : 2016-01-13 15:06:09
         * card_num : 16001000145258351264
         * activation_ip : 123.177.21.157
         * initial_limit : 100
         */

//        public LearnCardEntity learn_card;
        /**
         * pay_type : 账户余额
         * in_out_amount : 支出
         * in_out_amount_st : 2
         * id : 111542
         * order_id : 1656199146622614795
         * amount : 200.00
         * order_time : 2016-06-18 13:02:28
         */

        public List<DetailArrayEntity> detail_array;

//        public static class LearnCardEntity {
//            public String useful_life;
//            public String activation_date;
//            public String card_num;
//            public String activation_ip;
//            public String initial_limit;
//        }

        public static class DetailArrayEntity {
            public String pay_type;
            public String in_out_amount;
            public int in_out_amount_st;
            public String id;
            public String order_id;
            public String amount;
            public String order_time;
        }
    }
}
