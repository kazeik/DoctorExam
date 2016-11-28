package com.kazeik.doctor.doctorexam.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kazeik.chen on 2016/4/22 0022 11:23.
 * email:kazeik@163.com ,QQ:77132995
 */
public class LessonInfoBean implements Serializable{
    public String re_st;
    public ReMsgBean re_msg;

    public static class ReMsgBean implements Serializable{

        public CourseInfoBean course_info;
        public List<CourseWareItemBean> courseware;
        public List<PaperItemBean> paperItemBeans;
        public List<EbookItemBean> ebook;
    }

    public static class EbookItemBean implements Serializable{

        /**
         * id : 4
         * cid : 83
         * name : 海南医师定期考核业务水平测评考试指南—临床(分册)
         * professional_field : 16
         * year : 2015
         * key_pro_field : 临床
         * encryption : 83_4_7192de7b70d2ccabda1c5a0781948d1e
         */

        public String id;
        public String cid;
        public String name;
        public String professional_field;
        public String year;
        public String key_pro_field;
        public String encryption;
    }

    public static class PaperItemBean implements Serializable{

        /**
         * id : 1219
         * cid : 83
         * caption : 海南省2015年医师定期考核模拟试卷-临床（1）
         * category : 1
         * grade : 56
         * item_count : 100
         * key_category : 1
         * key_grade : 住院医师
         * encryption : 83_1219_6ed64385c60127da724056cad5cbaf7d
         * is_submit : 1
         */

        public String id;
        public String cid;
        public String caption;
        public String category;
        public String grade;
        public String item_count;
        public String key_category;
        public String key_grade;
        public String encryption;
        public String is_submit;

        public boolean isFinish;
    }
    public static class CourseWareItemBean implements Serializable{

        /**
         * id : 401
         * cid : 83
         * title : 人文-卫生法规
         * course_class_hour : 1
         * course_major_id : 20
         * type : 28
         * name : 人文
         * key_course_major_id : 人文医学
         * encryption : 83_401_39b409c4c818d45743a104ba2d05dc0f
         */

        public String id;
        public String cid;
        public String title;
        public String course_class_hour;
        public String course_major_id;
        public String type;
        public String name;
        public String key_course_major_id;
        public String encryption;

        public int flag;

        public int downState;
    }

    public static class CourseInfoBean implements Serializable{

        /**
         * id : 83
         * sys : 16
         * title : 临床+人文
         * pic : hainan.kaohewang.com/uploads/management/2015/09/28/64d50b5317f0f2f822f481252bb40358.png
         * category : 16
         * describe : 妇科/儿科，基础科目，内科，外科，皮肤科知识点，人文医学，卫生法规，医患沟通，医学伦理，医学心理学
         * original_cost : 400.00
         * rate : 25
         * base_class_hour : 10
         * click_num : 26233
         * makeup : 2
         * need_handbook : 2
         * need_ebook : 1
         * classes_id : 51
         * status : 1
         * course_id : 34
         * add_uid : 44
         * add_time : 2015-09-28 12:01:31
         * edit_uid : 44
         * edit_time : 2015-10-12 13:48:58
         * sort : 2
         * is_buy : 1
         */

        public String id;
        public String sys;
        public String title;
        public String pic;
        public String category;
        public String describe;
        public String original_cost;
        public String rate;
        public String base_class_hour;
        public String click_num;
        public String makeup;
        public String need_handbook;
        public String need_ebook;
        public String classes_id;
        public String status;
        public String course_id;
        public String add_uid;
        public String add_time;
        public String edit_uid;
        public String edit_time;
        public String sort;
        public String is_buy;
    }
}
