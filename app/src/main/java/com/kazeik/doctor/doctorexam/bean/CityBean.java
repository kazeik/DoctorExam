package com.kazeik.doctor.doctorexam.bean;/**
 * 首页城市选择
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 16 16:05
 */

import java.io.Serializable;
import java.util.List;

/**
 * 首页城市选择
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 16 16:05
 */

public class CityBean implements Serializable{

    /**
     * re_st : success
     * re_msg : [{"id":"15","sys":"99","name":"安徽省"},{"id":"6","sys":"99","name":"北京市"},{"id":"8","sys":"19","name":"重庆市"},{"id":"16","sys":"99","name":"福建省"},{"id":"25","sys":"99","name":"甘肃省"},{"id":"3","sys":"15","name":"贵州省"},{"id":"22","sys":"99","name":"广东省"},{"id":"29","sys":"99","name":"广西壮族自治区"},{"id":"19","sys":"99","name":"河南省"},{"id":"12","sys":"99","name":"黑龙江省"},{"id":"20","sys":"99","name":"湖北省"},{"id":"10","sys":"99","name":"河北省"},{"id":"21","sys":"99","name":"湖南省"},{"id":"4","sys":"16","name":"海南省"},{"id":"17","sys":"99","name":"江西省"},{"id":"1","sys":"5","name":"吉林省"},{"id":"13","sys":"99","name":"江苏省"},{"id":"5","sys":"18","name":"辽宁省"},{"id":"28","sys":"99","name":"内蒙古自治区"},{"id":"31","sys":"99","name":"宁夏回族自治区"},{"id":"26","sys":"99","name":"青海省"},{"id":"24","sys":"99","name":"陕西省"},{"id":"23","sys":"99","name":"四川省"},{"id":"9","sys":"99","name":"上海市"},{"id":"11","sys":"99","name":"山西省"},{"id":"18","sys":"99","name":"山东省"},{"id":"7","sys":"99","name":"天津市"},{"id":"30","sys":"99","name":"西藏自治区"},{"id":"27","sys":"99","name":"新疆维吾尔自治区"},{"id":"2","sys":"14","name":"云南省"},{"id":"14","sys":"99","name":"浙江省"}]
     * re_url :
     */

    public String re_st;
    public String re_url;
    /**
     * id : 15
     * sys : 99
     * name : 安徽省
     */

    public List<ReMsgEntity> re_msg;

    public static class ReMsgEntity implements Serializable{
        public String id;
        public String sys;
        public String name;
    }
}
