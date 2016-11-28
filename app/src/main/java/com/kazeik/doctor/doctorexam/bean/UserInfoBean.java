package com.kazeik.doctor.doctorexam.bean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kazeik.chen on 2016/5/10 0010 15:55.
 * email:kazeik@163.com ,QQ:77132995
 */
public class UserInfoBean {
    /**
     * re_st : success
     * re_msg : {"user_info":{"id":"62840","sys":"16","username":"15821804473","password":"1a100d2c0dab19c4430e7d73762b3423","mobile":"15821804473","balance":"0.00","serial_code":"","doctor_name":"","gender":"","org_area":"","institution":"","working_time":"","education":"","degree":"","graduate_school":"","graduate_date":"","credentials_no":"","date_of_get_credential":"","license_no":"","date_of_get_license":"","position":"","date_of_hold_post":"","job_title":"","date_of_get_job_title":"","department":"","working_status":"","work_years":"0","grade":"","medic_sort":"0","specialty":"","summary":"","remark":"","status":"1","login_count":"0","last_login_time":"2016-05-10 09:38:20","last_login_ip":"123.127.244.6","last_login_loction":"中国-北京-北京","indentity_code":"0","equipment":"2","uuid":"865854027306889","nationality":"","category":"","certificate_type":""},"user_info_base":{"id":"62840","sys":"16","username":"15821804473","password":"1a100d2c0dab19c4430e7d73762b3423","mobile":"15821804473","balance":"0.00","serial_code":"","doctor_name":"","gender":"0","org_area":"","institution":"","working_time":"","education":"0","degree":"0","graduate_school":"","graduate_date":"","credentials_no":"","date_of_get_credential":"","license_no":"","date_of_get_license":"","position":"0","date_of_hold_post":"","job_title":"0","date_of_get_job_title":"","department":"","working_status":"0","work_years":"0","grade":"0","medic_sort":"0","specialty":"0","summary":"","remark":"","status":"1","login_count":"0","last_login_time":"2016-05-10 09:38:20","last_login_ip":"123.127.244.6","last_login_loction":"中国-北京-北京","indentity_code":"0","re_pwd":"333333","equipment":"2","uuid":"865854027306889"},"certificate_type":{"452":"其它","451":"医疗美容主诊医师资格证书","450":"无"},"nationality":{"208":"保安族","209":"布依族","207":"白族","189":"汉族","190":"蒙古族","191":"回族","192":"苗族","193":"傣族","194":"傈僳族","195":"藏族","196":"壮族","197":"朝鲜族","198":"高山族","199":"纳西族","200":"布朗族","201":"阿昌族","202":"怒族","203":"鄂温克族","204":"鄂伦春族","205":"赫哲族","206":"门巴族","244":"穿青族","243":"彝族","242":"裕固族","241":"瑶族","240":"锡伯族","239":"乌孜别克族","238":"维吾尔族","237":"佤族","236":"土族","235":"仡佬族","234":"土家族","233":"塔塔尔族","232":"塔吉克族","231":"水族","230":"畲族","229":"撒拉族","228":"羌族","227":"普米族","226":"仫佬族","225":"毛南族","224":"满族","223":"黎族","222":"拉祜族","221":"柯尔克孜族","220":"景颇族","219":"京族","218":"基诺族","217":"哈萨克族","216":"哈尼族","215":"俄罗斯族","214":"独龙族","213":"侗族","212":"东乡族","211":"德昂族","210":"达斡尔族"},"professional":{"47":"中西医结合专业","48":"蒙医专业","49":"藏医专业","50":"维医专业","51":"傣医专业","52":"全科医学专业","53":"省级规定的其他情况","54":"省级规定的其他情况","33":"职业病专业（含放射病专业）","34":"预防保健专业","35":"特种医学与军事医学专业","36":"计划生育技术服务专业","46":"中医专业","45":"省级规定的其他情况","44":"公共卫生专业","43":"省级规定的其他情况","42":"口腔影像专业","40":"口腔麻醉专业","41":"口腔病理专业","38":"省级规定的其他情况","39":"口腔专业","37":"康复医学专业","32":"全科医学专业","31":"医学检验、病理专业","27":"皮肤病与性病专业","28":"眼耳鼻咽喉科专业","29":"精神卫生专业（含精神病专业、心理卫生专业）","30":"医学影像和放射治疗专业（含核医学专业）","26":"急救医学专业","25":"儿科专业（含儿童保健专业）","24":"妇产科专业（含妇女保健专业）","23":"外科专业（含运动医学专业、麻醉专业）","22":"内科专业（含老年医学专业、传染病专业）"},"gender":{"186":"男","187":"女"},"work_status":{"276":"返聘","275":"在职"},"job_title":{"273":"其他","272":"主任医师","271":"副主任医师","270":"主治医师","269":"住院医师"},"position":{"267":"其它","266":"办事员","265":"科员","264":"副科长(部、室副主任)","263":"科长(部、室主任)","262":"副院长(副所长)","261":"院长(所长)"},"doctor_grade":{"259":"执业医师","258":"执业助理医师"},"degree":{"256":"无","255":"其他","254":"学士","253":"硕士","252":"博士"},"education":{"250":"其他","249":"研究生","248":"大学本科","247":"大学专科","246":"专科以下"},"category":{"20":"人文医学","19":"公共卫生","18":"口腔","16":"临床","17":"中医"}}
     * re_url :
     */

    public String re_st;
    public String re_url;
    public ReMsgEntity re_msg;

    public static class ReMsgEntity {
        public UserInfo user_info;
        public UserInfoBase user_info_base;

        public List<String> certificate_type;
        public List<String> nationality;
        public List<String> professional;
        public List<String> gender;
        public List<String> work_status;
        public List<String> job_title;
        public List<String> position;
        public List<String> doctor_grade;
        public List<String> degree;
        public List<String> education;
        public List<String> category;
    }

    public static class UserInfo {

        /**
         * id : 62840
         * sys : 16
         * username : 15821804473
         * password : 1a100d2c0dab19c4430e7d73762b3423
         * mobile : 15821804473
         * balance : 0.00
         * serial_code :
         * doctor_name :
         * gender :
         * org_area :
         * institution :
         * working_time :
         * education :
         * degree :
         * graduate_school :
         * graduate_date :
         * credentials_no :
         * date_of_get_credential :
         * license_no :
         * date_of_get_license :
         * position :
         * date_of_hold_post :
         * job_title :
         * date_of_get_job_title :
         * department :
         * working_status :
         * work_years : 0
         * grade :
         * medic_sort : 0
         * specialty :
         * summary :
         * remark :
         * status : 1
         * login_count : 0
         * last_login_time : 2016-05-10 09:38:20
         * last_login_ip : 123.127.244.6
         * last_login_loction : 中国-北京-北京
         * indentity_code : 0
         * equipment : 2
         * uuid : 865854027306889
         * nationality :
         * category :
         * certificate_type :
         */

        public String id;
        public String sys;
        public String username;
        public String password;
        public String mobile;
        public String balance;
        public String serial_code;
        public String doctor_name;
        public String gender;
        public String org_area;
        public String institution;
        public String working_time;
        public String education;
        public String degree;
        public String graduate_school;
        public String graduate_date;
        public String credentials_no;
        public String date_of_get_credential;
        public String license_no;
        public String date_of_get_license;
        public String position;
        public String date_of_hold_post;
        public String job_title;
        public String date_of_get_job_title;
        public String department;
        public String working_status;
        public String work_years;
        public String grade;
        public String medic_sort;
        public String specialty;
        public String summary;
        public String remark;
        public String status;
        public String login_count;
        public String last_login_time;
        public String last_login_ip;
        public String last_login_loction;
        public String indentity_code;
        public String equipment;
        public String uuid;
        public String nationality;
        public String category;
        public String certificate_type;
    }

    public static class UserInfoBase {

        /**
         * id : 62840
         * sys : 16
         * username : 15821804473
         * password : 1a100d2c0dab19c4430e7d73762b3423
         * mobile : 15821804473
         * balance : 0.00
         * serial_code :
         * doctor_name :
         * gender : 0
         * org_area :
         * institution :
         * working_time :
         * education : 0
         * degree : 0
         * graduate_school :
         * graduate_date :
         * credentials_no :
         * date_of_get_credential :
         * license_no :
         * date_of_get_license :
         * position : 0
         * date_of_hold_post :
         * job_title : 0
         * date_of_get_job_title :
         * department :
         * working_status : 0
         * work_years : 0
         * grade : 0
         * medic_sort : 0
         * specialty : 0
         * summary :
         * remark :
         * status : 1
         * login_count : 0
         * last_login_time : 2016-05-10 09:38:20
         * last_login_ip : 123.127.244.6
         * last_login_loction : 中国-北京-北京
         * indentity_code : 0
         * re_pwd : 333333
         * equipment : 2
         * uuid : 865854027306889
         */

        public String id;
        public String sys;
        public String username;
        public String password;
        public String mobile;
        public String balance;
        public String serial_code;
        public String doctor_name;
        public String gender;
        public String org_area;
        public String institution;
        public String working_time;
        public String education;
        public String degree;
        public String graduate_school;
        public String graduate_date;
        public String credentials_no;
        public String date_of_get_credential;
        public String license_no;
        public String date_of_get_license;
        public String position;
        public String date_of_hold_post;
        public String job_title;
        public String date_of_get_job_title;
        public String department;
        public String working_status;
        public String work_years;
        public String grade;
        public String medic_sort;
        public String specialty;
        public String summary;
        public String remark;
        public String status;
        public String login_count;
        public String last_login_time;
        public String last_login_ip;
        public String last_login_loction;
        public String indentity_code;
        public String re_pwd;
        public String equipment;
        public String uuid;
    }

    public HashMap<String,String> certificate_type;
    public HashMap<String,String> nationality;
    public HashMap<String,String> professional;
    public HashMap<String,String> gender;
    public HashMap<String,String> work_status;
    public HashMap<String,String> job_title;
    public HashMap<String,String> position;
    public HashMap<String,String> doctor_grade;
    public HashMap<String,String> degree;
    public HashMap<String,String> education;
    public HashMap<String,String> category;
}
