package com.kazeik.doctor.doctorexam.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kazeik.chen on 2016/4/19 0019 13:18.
 * email:kazeik@163.com ,QQ:77132995
 */
public class NotifyListBean implements Serializable {

    /**
     * re_st : success
     * re_msg : {"2":{"id":"2","title":"关于考核培训","image":"hainan.kaohewang.com/uploads/management/2013/12/02/20131202144355_16238.jpg","message":{"4":{"id":"4","add_time":"2016-03-16 18:28:19","title":"培训要点"},"3":{"id":"3","add_time":"2016-03-16 18:28:03","title":"培训内容"}}},"1":{"id":"1","title":"考核2.0版本上线啦","image":"hainan.kaohewang.com/uploads/management/2013/12/02/20131202144355_16238.jpg","message":{"2":{"id":"2","add_time":"2016-03-16 18:27:14","title":"看看有啥"},"1":{"id":"1","add_time":"2016-03-16 17:53:57","title":"app上线啦"}}}}
     * re_url :
     */
    public String re_url;
    public String re_st;
    public List<ReMsgEntity> re_msg;

    public static class ReMsgEntity {
        public String id;
        public String title;
        public String image;
        public List<MessageEntity> message;

    }

    public static class MessageEntity implements Serializable {

        /**
         * id : 4
         * add_time : 2016-03-16 18:28:19
         * title : 培训要点
         */

        public String id;
        public String add_time;
        public String title;
    }
}
