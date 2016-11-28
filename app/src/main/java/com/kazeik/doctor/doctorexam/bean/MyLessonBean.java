package com.kazeik.doctor.doctorexam.bean;/**
 * 我的课程
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 15 23:37
 */

import java.io.Serializable;
import java.util.List;

/**
 * 我的课程
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 15 23:37
 */

public class MyLessonBean implements Serializable{


    /**
     * re_st : success
     * re_msg : [{"id":"66935","name":"海南省2015年医师定期考核模拟试卷-人文（41）","order_id":"1656199146234169531","add_time":"2016-05-04 14:01:35","total_amount":"0.01","pay_type":"混合支付","order_status":"未支付","ost":"1","security_code":"40f570ddf4218f8fe2228f940c2b6b0c","order_status_id":"1"},{"id":"66899","name":"海南省2015年医师定期考核模拟试卷-人文（44）","order_id":"1656199146036428769","add_time":"2016-04-11 16:44:47","total_amount":"0.01","pay_type":"混合支付","order_status":"交易完成","ost":"2","security_code":"40f570ddf4218f8fe2228f940c2b6b0c","order_status_id":"2"},{"id":"66864","name":"海南省2015年医师定期考核模拟试卷-人文（45）","order_id":"1656199145872537294","add_time":"2016-03-23 17:29:32","total_amount":"0.01","pay_type":"混合支付","order_status":"交易完成","ost":"2","security_code":"40f570ddf4218f8fe2228f940c2b6b0c","order_status_id":"2"},{"id":"66863","name":"海南省2015年医师定期考核模拟试卷-人文（46）","order_id":"1656199145872482853","add_time":"2016-03-23 17:20:28","total_amount":"0.01","pay_type":"混合支付","order_status":"交易完成","ost":"2","security_code":"40f570ddf4218f8fe2228f940c2b6b0c","order_status_id":"2"},{"id":"66853","name":"海南省2015年医师定期考核模拟试卷-人文（50）","order_id":"1656199145871413514","add_time":"2016-03-23 14:22:15","total_amount":"0.01","pay_type":"账户余额","order_status":"交易完成","ost":"2","security_code":"40f570ddf4218f8fe2228f940c2b6b0c","order_status_id":"2"},{"id":"66828","name":"口腔+人文","order_id":"1656199145370650019","add_time":"2016-01-25 15:21:40","total_amount":"100.00","pay_type":"账户余额","order_status":"交易完成","ost":"2","security_code":"40f570ddf4218f8fe2228f940c2b6b0c","order_status_id":"2"},{"id":"60192","name":"人文医学<br/>临床+人文","order_id":"1656199144481132494","add_time":"2015-10-14 16:28:44","total_amount":"150.00","pay_type":"账户余额","order_status":"交易完成","ost":"2","security_code":"40f570ddf4218f8fe2228f940c2b6b0c","order_status_id":"2"},{"id":"60143","name":"测试","order_id":"1656199144429678409","add_time":"2015-10-08 17:33:04","total_amount":"100.00","pay_type":"账户余额","order_status":"交易完成","ost":"2","security_code":"40f570ddf4218f8fe2228f940c2b6b0c","order_status_id":"2"},{"id":"60142","name":"公卫+人文","order_id":"1656199144428926674","add_time":"2015-10-08 15:27:46","total_amount":"100.00","pay_type":"账户余额","order_status":"交易完成","ost":"2","security_code":"40f570ddf4218f8fe2228f940c2b6b0c","order_status_id":"2"},{"id":"60141","name":"中医+人文","order_id":"1656199144428487010","add_time":"2015-10-08 14:14:30","total_amount":"100.00","pay_type":"账户余额","order_status":"交易完成","ost":"2","security_code":"40f570ddf4218f8fe2228f940c2b6b0c","order_status_id":"2"}]
     * re_url :
     */

    public String re_st;
    public String re_url;
    public List<ReMsgEntity> re_msg;

    public static class ReMsgEntity implements Serializable{
        /**
         * id : 66935
         * name : 海南省2015年医师定期考核模拟试卷-人文（41）
         * order_id : 1656199146234169531
         * add_time : 2016-05-04 14:01:35
         * total_amount : 0.01
         * pay_type : 混合支付
         * order_status : 未支付
         * ost : 1
         * security_code : 40f570ddf4218f8fe2228f940c2b6b0c
         * order_status_id : 1
         */

        public String id;
        public String name;
        public String order_id;
        public String add_time;
        public String total_amount;
        public String pay_type;
        public String order_status;
        public int ost;
        public String security_code;
        public String order_status_id;
    }
}
