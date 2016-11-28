package com.kazeik.doctor.doctorexam.bean;/**
 * 定考百问类型
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 20 20:26
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 定考百问类型
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 20 20:26
 */

public class HelpTypeBean {

    /**
     * re_st : success
     * re_msg : [{"id":"32","title":"新手入门"},{"id":"33","title":"业务水平测评答疑"},{"id":"34","title":"常见问题F&amp;Q"}]
     * re_url :
     */

    public String re_st;
    public String re_url;
    /**
     * id : 32
     * title : 新手入门
     */

    public List<ReMsgEntity> re_msg;

    public static class ReMsgEntity {
        public String id;
        public String title;

        public List<SubInfo> allSub = new ArrayList<>();
    }

    public static class SubInfo implements Serializable{

        /**
         * id : 234
         * title : 帮助答疑篇
         * c_id : 32
         * add_time : 2016-04-15 15:48:30
         */

        public int id;
        public String title;
        public String c_id;
        public String add_time;

        @Override
        public String toString() {
            return "SubInfo{" +
                    "add_time='" + add_time + '\'' +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", c_id='" + c_id + '\'' +
                    '}';
        }
    }
}
