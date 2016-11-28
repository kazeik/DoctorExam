package com.kazeik.doctor.doctorexam.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kazeik.chen on 2016/4/22 0022 15:17.
 * email:kazeik@163.com ,QQ:77132995
 */
public class PinMoneyBean {

    /**
     * re_st : success
     * re_msg : {"user_join_info":{"uid":"56199","total":"67.00","exchange_out":"89.19","exchange_balance":"0.00","survey":"156.19"},"survey_list":{"25":{"id":"25","survey_link":"http://www.ipanelonline.com/assign2.html?pid=P0017020&partner=331&gno=1&uid=","ippid":"17020","pj_info":"注意：PM必须填写项目最新的配额需求明细（禁止为空，一旦发现将会受到处罚，描述简单清晰，只需填写缺少的即可，便于其他部门工作协调）：","title":"Diabetes Physician Study","loi":"20","start_time":"2016-04-13","end_time":"2016-06-12","c_point":"74.06","img":"http://www.ipanelonline.com/images/healthyimg/26.jpg"}}}
     * re_url :
     */

    public String re_st;
    public ReMsgEntity re_msg;

    public static class  ReMsgEntity implements Serializable{
        public UserJoinBean user_join_info;
        public List<SurveyListBean> survey_list;
    }

    public static class SurveyListBean implements Serializable{


        /**
         * id : 25
         * survey_link : http://www.ipanelonline.com/assign2.html?pid=P0017020&partner=331&gno=1&uid=
         * ippid : 17020
         * pj_info : 注意：PM必须填写项目最新的配额需求明细（禁止为空，一旦发现将会受到处罚，描述简单清晰，只需填写缺少的即可，便于其他部门工作协调）：
         * title : Diabetes Physician Study
         * loi : 20
         * start_time : 2016-04-13
         * end_time : 2016-06-12
         * c_point : 74.06
         * img : http://www.ipanelonline.com/images/healthyimg/26.jpg
         */

        public String id;
        public String survey_link;
        public String ippid;
        public String pj_info;
        public String title;
        public String loi;
        public String start_time;
        public String end_time;
        public String c_point;
        public String img;
    }


    public static class UserJoinBean implements Serializable{

        /**
         * uid : 56199
         * total : 67.00
         * exchange_out : 89.19
         * exchange_balance : 0.00
         * survey : 156.19
         */

        public String uid;
        public String total;
        public String exchange_out;
        public String exchange_balance;
        public String survey;
    }




}
