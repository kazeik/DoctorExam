package com.kazeik.doctor.doctorexam.bean;

import java.io.Serializable;

/**
 * Created by kazeik.chen on 2016/4/22 0022 09:39.
 * email:kazeik@163.com ,QQ:77132995
 */
public class LessonItemBean implements Serializable{

    /**
     * id : 83
     * title : 临床+人文
     * pic : hainan.kaohewang.com/uploads/management/2015/09/28/64d50b5317f0f2f822f481252bb40358.png
     * category : 16
     * describe : 妇科/儿科，基础科目，内科，外科，皮肤科知识点，人文医学，卫生法规，医患沟通，医学伦理，医学心理学
     * original_cost : 400.00
     * rate : 25
     * base_class_hour : 10
     * makeup : 2
     * order_type : 1
     * security_code : ac5c3b969153a0120073197204030d02
     * is_buy : 1
     * in_cart : 2
     */

    public String id;
    public String title;
    public String pic;
    public String category;
    public String describe;
    public String original_cost;
    public String rate;
    public String base_class_hour;
    public String makeup;
    public String order_type;
    public String security_code;
    public int is_buy;
    public int in_cart;
}
