package com.kazeik.doctor.doctorexam.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kazeik.chen on 2016/4/21 0021 13:51.
 * email:kazeik@163.com ,QQ:77132995
 */
public class TalkListBean implements Serializable{

    /**
     * re_st : success
     * re_msg : [{"uid":"126"},{"uid":"125"}]
     * re_url :
     */

    public String re_st;
    public String re_url;
    public List<ReMsgEntity> re_msg;

    public static class ReMsgEntity {
        /**
         * uid : 126
         */

        public String uid;
    }
}
