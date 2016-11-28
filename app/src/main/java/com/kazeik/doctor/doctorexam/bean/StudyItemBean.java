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

public class StudyItemBean implements Serializable{

    /**
     * re_st : success
     * re_msg : [{"id":"30","name":"政策法规"}]
     * re_url :
     */

    public String re_st;
    public String re_url;
    public List<ReMsgEntity> re_msg;

    public static class ReMsgEntity {
        /**
         * id : 30
         * name : 政策法规
         */

        public String id;
        public String name;
    }
}
