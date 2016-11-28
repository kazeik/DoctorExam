package com.kazeik.doctor.doctorexam.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kazeik.chen on 2016/5/9 0009 16:01.
 * email:kazeik@163.com ,QQ:77132995
 */
public class OrderInfoBean {

    /**
     * re_st : success
     * re_msg : {"order_details":[{"id":"79893","sys":"16","good_id":"83","order_type":"课程","uid":"62840","status":"1","order_id":null,"add_time":"2016-05-09 14:20:50","edit_time":"0000-00-00 00:00:00","resinfo":{"id":"83","title":"临床+人文","need_handbook":"2","original_cost":"400.00","rate":"25","key_category":"临床","price":100},"good_num":"KC0083"},{"id":"79894","sys":"16","good_id":"84","order_type":"课程","uid":"62840","status":"1","order_id":null,"add_time":"2016-05-09 14:20:51","edit_time":"0000-00-00 00:00:00","resinfo":{"id":"84","title":"人文医学","need_handbook":"2","original_cost":"200.00","rate":"25","key_category":"人文医学","price":50},"good_num":"KC0084"}],"need_handbook":0,"security_code":"6025d535232a62039fed0f27e04bd806","good_ids":"79893_79894","member_base":{"id":"62840","sys":"16","username":"15821804473","password":"1a100d2c0dab19c4430e7d73762b3423","mobile":"15821804473","balance":"0.00","serial_code":"","doctor_name":"","gender":"0","org_area":"","institution":"","working_time":"","education":"0","degree":"0","graduate_school":"","graduate_date":"","credentials_no":"","date_of_get_credential":"","license_no":"","date_of_get_license":"","position":"0","date_of_hold_post":"","job_title":"0","date_of_get_job_title":"","department":"","working_status":"0","work_years":"0","grade":"0","medic_sort":"0","specialty":"0","summary":"","remark":"","status":"1","login_count":"0","last_login_time":"2016-05-09 14:20:42","last_login_ip":"223.104.5.229","last_login_loction":"中国-上海-上海","equipment":"1"},"order_pay_type":{"1":"账户余额","2":"支付宝","3":"混合支付"}}
     * re_url :
     */

    public String re_st;
    /**
     * order_details : [{"id":"79893","sys":"16","good_id":"83","order_type":"课程","uid":"62840","status":"1","order_id":null,"add_time":"2016-05-09 14:20:50","edit_time":"0000-00-00 00:00:00","resinfo":{"id":"83","title":"临床+人文","need_handbook":"2","original_cost":"400.00","rate":"25","key_category":"临床","price":100},"good_num":"KC0083"},{"id":"79894","sys":"16","good_id":"84","order_type":"课程","uid":"62840","status":"1","order_id":null,"add_time":"2016-05-09 14:20:51","edit_time":"0000-00-00 00:00:00","resinfo":{"id":"84","title":"人文医学","need_handbook":"2","original_cost":"200.00","rate":"25","key_category":"人文医学","price":50},"good_num":"KC0084"}]
     * need_handbook : 0
     * security_code : 6025d535232a62039fed0f27e04bd806
     * good_ids : 79893_79894
     * member_base : {"id":"62840","sys":"16","username":"15821804473","password":"1a100d2c0dab19c4430e7d73762b3423","mobile":"15821804473","balance":"0.00","serial_code":"","doctor_name":"","gender":"0","org_area":"","institution":"","working_time":"","education":"0","degree":"0","graduate_school":"","graduate_date":"","credentials_no":"","date_of_get_credential":"","license_no":"","date_of_get_license":"","position":"0","date_of_hold_post":"","job_title":"0","date_of_get_job_title":"","department":"","working_status":"0","work_years":"0","grade":"0","medic_sort":"0","specialty":"0","summary":"","remark":"","status":"1","login_count":"0","last_login_time":"2016-05-09 14:20:42","last_login_ip":"223.104.5.229","last_login_loction":"中国-上海-上海","equipment":"1"}
     * order_pay_type : {"1":"账户余额","2":"支付宝","3":"混合支付"}
     */
    public ReMsgEntity re_msg;
    public static class ReMsgEntity implements Serializable{
        public int need_handbook;
        public String security_code;
        public String good_ids;
        public double member_base;
        public List<String> order_pay_type;
        public List<OrderDetails> order_details;

        @Override
        public String toString() {
            return "ReMsgEntity{" +
                    "good_ids='" + good_ids + '\'' +
                    ", need_handbook=" + need_handbook +
                    ", security_code='" + security_code + '\'' +
                    ", member_base=" + member_base +
                    ", order_pay_type=" + order_pay_type +
                    ", order_details=" + order_details +
                    '}';
        }
    }
    public static class OrderDetails{
        /**
         * id : 79893
         * sys : 16
         * good_id : 83
         * order_type : 课程
         * uid : 62840
         * status : 1
         * order_id : null
         * add_time : 2016-05-09 14:20:50
         * edit_time : 0000-00-00 00:00:00
         * resinfo : {"id":"83","title":"临床+人文","need_handbook":"2","original_cost":"400.00","rate":"25","key_category":"临床","price":100}
         * good_num : KC0083
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
        /**
         * id : 83
         * title : 临床+人文
         * need_handbook : 2
         * original_cost : 400.00
         * rate : 25
         * key_category : 临床
         * price : 100
         */

        public ResinfoEntity resinfo;
        public String good_num;

        @Override
        public String toString() {
            return "OrderDetails{" +
                    "add_time='" + add_time + '\'' +
                    ", id='" + id + '\'' +
                    ", sys='" + sys + '\'' +
                    ", good_id='" + good_id + '\'' +
                    ", order_type='" + order_type + '\'' +
                    ", uid='" + uid + '\'' +
                    ", status='" + status + '\'' +
                    ", order_id=" + order_id +
                    ", edit_time='" + edit_time + '\'' +
                    ", resinfo=" + resinfo +
                    ", good_num='" + good_num + '\'' +
                    '}';
        }
    }
    public static class ResinfoEntity {
        public String id;
        public String title;
        public String need_handbook;
        public String original_cost;
        public String rate;
        public String key_category;
        public String price;

        @Override
        public String toString() {
            return "ResinfoEntity{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", need_handbook='" + need_handbook + '\'' +
                    ", original_cost='" + original_cost + '\'' +
                    ", rate='" + rate + '\'' +
                    ", key_category='" + key_category + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    public static class MemberBaseEntity {
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
         * last_login_time : 2016-05-09 14:20:42
         * last_login_ip : 223.104.5.229
         * last_login_loction : 中国-上海-上海
         * equipment : 1
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
        public String equipment;

        @Override
        public String toString() {
            return "MemberBaseEntity{" +
                    "balance='" + balance + '\'' +
                    ", id='" + id + '\'' +
                    ", sys='" + sys + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", serial_code='" + serial_code + '\'' +
                    ", doctor_name='" + doctor_name + '\'' +
                    ", gender='" + gender + '\'' +
                    ", org_area='" + org_area + '\'' +
                    ", institution='" + institution + '\'' +
                    ", working_time='" + working_time + '\'' +
                    ", education='" + education + '\'' +
                    ", degree='" + degree + '\'' +
                    ", graduate_school='" + graduate_school + '\'' +
                    ", graduate_date='" + graduate_date + '\'' +
                    ", credentials_no='" + credentials_no + '\'' +
                    ", date_of_get_credential='" + date_of_get_credential + '\'' +
                    ", license_no='" + license_no + '\'' +
                    ", date_of_get_license='" + date_of_get_license + '\'' +
                    ", position='" + position + '\'' +
                    ", date_of_hold_post='" + date_of_hold_post + '\'' +
                    ", job_title='" + job_title + '\'' +
                    ", date_of_get_job_title='" + date_of_get_job_title + '\'' +
                    ", department='" + department + '\'' +
                    ", working_status='" + working_status + '\'' +
                    ", work_years='" + work_years + '\'' +
                    ", grade='" + grade + '\'' +
                    ", medic_sort='" + medic_sort + '\'' +
                    ", specialty='" + specialty + '\'' +
                    ", summary='" + summary + '\'' +
                    ", remark='" + remark + '\'' +
                    ", status='" + status + '\'' +
                    ", login_count='" + login_count + '\'' +
                    ", last_login_time='" + last_login_time + '\'' +
                    ", last_login_ip='" + last_login_ip + '\'' +
                    ", last_login_loction='" + last_login_loction + '\'' +
                    ", equipment='" + equipment + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrderInfoBean{" +
                "re_msg=" + re_msg +
                ", re_st='" + re_st + '\'' +
                '}';
    }
}
