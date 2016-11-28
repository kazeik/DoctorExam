package com.kazeik.doctor.doctorexam.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kazeik.chen on 2016/5/9 0009 14:38.
 * email:kazeik@163.com ,QQ:77132995
 */
public class ShopCarBean implements Serializable{

    /**
     * re_st : success
     * re_msg : {"cart_list":[{"id":"79892","sys":"16","good_id":"82","order_type":"课程","uid":"62840","status":"1","order_id":null,"add_time":"2016-05-09 13:38:49","edit_time":"0000-00-00 00:00:00","resinfo":{"id":"82","title":"口腔+人文","need_handbook":"2","original_cost":"400.00","rate":"25","key_category":"口腔","prices":100}},{"id":"79893","sys":"16","good_id":"83","order_type":"课程","uid":"62840","status":"1","order_id":null,"add_time":"2016-05-09 14:20:50","edit_time":"0000-00-00 00:00:00","resinfo":{"id":"83","title":"临床+人文","need_handbook":"2","original_cost":"400.00","rate":"25","key_category":"临床","prices":100}},{"id":"79894","sys":"16","good_id":"84","order_type":"课程","uid":"62840","status":"1","order_id":null,"add_time":"2016-05-09 14:20:51","edit_time":"0000-00-00 00:00:00","resinfo":{"id":"84","title":"人文医学","need_handbook":"2","original_cost":"200.00","rate":"25","key_category":"人文医学","prices":50}},{"id":"79895","sys":"16","good_id":"2","order_type":"模拟考试卷","uid":"62840","status":"1","order_id":null,"add_time":"2016-05-09 14:21:11","edit_time":"0000-00-00 00:00:00","resinfo":{"id":"2","title":"海南省2015年医师定期考核模拟试卷-人文（50）","original_cost":"0.01","rate":"100","key_category":null,"prices":0.01}}]}
     * re_url :
     */

    public String re_st;
    public ReMsgEntity re_msg;
    public String re_url;

    public static class ReMsgEntity implements Serializable{
        /**
         * cart_list : [{"id":"79892","sys":"16","good_id":"82","order_type":"课程","uid":"62840","status":"1","order_id":null,"add_time":"2016-05-09 13:38:49","edit_time":"0000-00-00 00:00:00","resinfo":{"id":"82","title":"口腔+人文","need_handbook":"2","original_cost":"400.00","rate":"25","key_category":"口腔","prices":100}},{"id":"79893","sys":"16","good_id":"83","order_type":"课程","uid":"62840","status":"1","order_id":null,"add_time":"2016-05-09 14:20:50","edit_time":"0000-00-00 00:00:00","resinfo":{"id":"83","title":"临床+人文","need_handbook":"2","original_cost":"400.00","rate":"25","key_category":"临床","prices":100}},{"id":"79894","sys":"16","good_id":"84","order_type":"课程","uid":"62840","status":"1","order_id":null,"add_time":"2016-05-09 14:20:51","edit_time":"0000-00-00 00:00:00","resinfo":{"id":"84","title":"人文医学","need_handbook":"2","original_cost":"200.00","rate":"25","key_category":"人文医学","prices":50}},{"id":"79895","sys":"16","good_id":"2","order_type":"模拟考试卷","uid":"62840","status":"1","order_id":null,"add_time":"2016-05-09 14:21:11","edit_time":"0000-00-00 00:00:00","resinfo":{"id":"2","title":"海南省2015年医师定期考核模拟试卷-人文（50）","original_cost":"0.01","rate":"100","key_category":null,"prices":0.01}}]
         */

        public List<CartListEntity> cart_list;

        public static class CartListEntity implements Serializable{
            /**
             * id : 79892
             * sys : 16
             * good_id : 82
             * order_type : 课程
             * uid : 62840
             * status : 1
             * order_id : null
             * add_time : 2016-05-09 13:38:49
             * edit_time : 0000-00-00 00:00:00
             * resinfo : {"id":"82","title":"口腔+人文","need_handbook":"2","original_cost":"400.00","rate":"25","key_category":"口腔","prices":100}
             */

            public String id;
            public String sys;
            public String good_id;
            public String order_type;
            public String uid;
            public String status;
            public Object order_id;
            public String add_time;
            public String edit_time;
            public ResinfoEntity resinfo;

            public static class ResinfoEntity implements Serializable{
                /**
                 * id : 82
                 * title : 口腔+人文
                 * need_handbook : 2
                 * original_cost : 400.00
                 * rate : 25
                 * key_category : 口腔
                 * prices : 100
                 */

                public String id;
                public String title;
                public String need_handbook;
                public String original_cost;
                public String rate;
                public String key_category;
                public double prices;
            }
        }
    }
}
