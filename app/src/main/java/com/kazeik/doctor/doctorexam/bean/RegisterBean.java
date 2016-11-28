package com.kazeik.doctor.doctorexam.bean;/**
 * 注册用户
 *
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 17 09:25
 */

/**
 * 注册用户
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016 04 17 09:25
 */

public class RegisterBean extends BaseBean{

    /**
     * re_st : success
     * re_msg : {"username":"18513179126","password":"96e79218965eb72c92a549dd5a330112","mobile":"18513179126","sys":"99","alert_info":"恭喜您，账号已成功注册。","id":"62812"}
     * re_url :
     */

    public String re_st;
    /**
     * username : 18513179126
     * password : 96e79218965eb72c92a549dd5a330112
     * mobile : 18513179126
     * sys : 99
     * alert_info : 恭喜您，账号已成功注册。
     * id : 62812
     */

    public ReMsgEntity re_msg;
    public String re_url;

    public static class ReMsgEntity {
        public String username;
        public String password;
        public String mobile;
        public String sys;
        public String alert_info;
        public String id;
    }
}
