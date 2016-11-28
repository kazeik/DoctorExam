package com.kazeik.doctor.doctorexam.bean;/**
 * 考务信息
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 17 20:03
 */

import java.io.Serializable;
import java.util.List;

/**
 * 考务信息
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 17 20:03
 */

public class StudyInfoBean implements Serializable{


    /**
     * re_st : success
     * re_msg : {"58":{"id":"58","title":"关于规范实施国家卫计委389号文的通知","source":"全国医师定期考核工作办公室","description":"","click_num":"2323","recommend":"1","istop":"0","add_time":"2015-10-10 14:28:33"},"59":{"id":"59","title":"医师定期考核管理办法","source":"医师之家","description":"","click_num":"3719","recommend":"0","istop":"0","add_time":"2015-10-10 14:33:11"}}
     * re_url :
     */

    public String re_st;
    public List<ReMsgEntity> re_msg;

    public static class ReMsgEntity implements Serializable{

        /**
         * id : 58
         * title : 关于规范实施国家卫计委389号文的通知
         * source : 全国医师定期考核工作办公室
         * description :
         * click_num : 2323
         * recommend : 1
         * istop : 0
         * add_time : 2015-10-10 14:28:33
         */

        public String id;
        public String title;
        public String source;
        public String description;
        public String click_num;
        public String recommend;
        public String istop;
        public String add_time;
    }
}
