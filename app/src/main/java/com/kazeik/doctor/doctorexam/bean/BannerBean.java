package com.kazeik.doctor.doctorexam.bean;

import java.util.List;

/**
 * Created by kazeik.chen on 2016/4/26 0026 18:01.
 * email:kazeik@163.com ,QQ:77132995
 */
public class BannerBean {

    /**
     * re_st : success
     * re_msg : [{"id":"1","img":"hainan.kaohewang.com/uploads/management/2016/04/21/810b9d46f024a1abce7af9f3f39baa10.png","title":"banner1"},{"id":"2","img":"hainan.kaohewang.com/uploads/management/2016/04/21/2f8d9b42ca1ebe4b104406a1d74c7798.png","title":"banner2"},{"id":"4","img":"hainan.kaohewang.com/uploads/management/2016/04/21/63a4a87a3ce588162032e363f19ea80a.png","title":"banner3"},{"id":"5","img":"hainan.kaohewang.com/uploads/management/2016/04/21/c6bc8c8d8047e3c75beaa6c7329fe214.png","title":"banner4"}]
     * re_url :
     */

    public String re_st;
    public String re_url;
    public List<ReMsgEntity> re_msg;

    public static class ReMsgEntity {
        /**
         * id : 1
         * img : hainan.kaohewang.com/uploads/management/2016/04/21/810b9d46f024a1abce7af9f3f39baa10.png
         * title : banner1
         */

        public String id;
        public String img;
        public String title;
    }
}
