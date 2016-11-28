package com.kazeik.doctor.doctorexam.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kazeik.chen on 2016/4/20 0020 13:49.
 * email:kazeik@163.com ,QQ:77132995
 */
public class MockExamBean implements Serializable{

    /**
     * re_st : success
     * re_msg : {"paper_info":{"id":"2","sys":"16","serial_code":"32e8ad08a245c3c3f90a","caption":"海南省2015年医师定期考核模拟试卷-人文（50）","category":"5","profession":"P5000000","grade":"56","item_count":"100","total_score":"100","total_minute":"60","pass_score":"0","keyword":"","remark":"","original_cost":"0.01","rate":"100","status":"1","sorting":"0","add_uid":"1","add_time":"2016-03-18 13:24:49","edit_uid":"1","edit_time":"2016-03-18 15:23:34","specialty_code":null},"keylib":{"15":"执业类别","16":"临床","17":"中医","18":"口腔","19":"公共卫生","20":"人文医学","62":"A1型","63":"A2型","64":"A3型","65":"A4型","66":"B1型","67":"X型"},"sort_list":{"1":"A","2":"B","3":"C","4":"D","5":"E","6":"F","7":"G","8":"H","9":"I","10":"J","11":"K","12":"L","13":"M","14":"N","15":"O","16":"P","17":"Q","18":"R","19":"S","20":"T","21":"U","22":"V","23":"W","24":"X","25":"Y","26":"Z"},"category_arr":{"1":"临床","2":"中医","3":"口腔","4":"公共卫生","5":"人文医学"},"profession_arr":{"P1000000":"临床","P2000000":"中医","P3000000":"口腔","P4000000":"公共卫生","P5000000":"人文医学"},"pid":"2","question":{"62":{"501":{"serial_num":1,"info":{"id":"501","sys":"16","pid":"2","serial_code":"IITEM58PLMP4OXWS8UJT","item_type":"62","caption":"下列哪项不属于保密原则对医务人员提出的要求","category":"5","profession":"5","catalog":"","item_unit":"1","item_level":"2","item_score":"1","keyword":"第一批光盘题，海南模拟","remark":"","answer":"2503","add_uid":"1","add_time":"2016-03-18 13:25:28","edit_uid":"0","edit_time":"0000-00-00 00:00:00"},"option":[{"id":"2501","sys":"16","main_qid":"501","sub_qid":"0","text":"保守在治疗中知晓的患者隐私","sort":"0","sorts":"A"},{"id":"2502","sys":"16","main_qid":"501","sub_qid":"0","text":"从诊断疾病的需要来询问病史，不有意探听患者的隐私","sort":"1","sorts":"B"},{"id":"2503","sys":"16","main_qid":"501","sub_qid":"0","text":"为患有霍乱的患者保密","sort":"2","sorts":"C"},{"id":"2504","sys":"16","main_qid":"501","sub_qid":"0","text":"保守可能会给患者带来沉重精神负担的疾病预后","sort":"3","sorts":"D"},{"id":"2505","sys":"16","main_qid":"501","sub_qid":"0","text":"保守对患者精神可能带来挫伤的肿瘤或其它危害患者生命的病情","sort":"4","sorts":"E"}]},"502":{"serial_num":2,"info":{"id":"502","sys":"16","pid":"2","serial_code":"IITEM5C4R540ARMU4NWE","item_type":"62","caption":"门诊医生治疗患者，在使用左氧氟沙星静脉滴注过程中出现了皮疹，应该如何处理","category":"5","profession":"5","catalog":"","item_unit":"1","item_level":"2","item_score":"1","keyword":"第一批光盘题，吉林考核，吉林模拟，海南模拟","remark":"","answer":"2507","add_uid":"1","add_time":"2016-03-18 13:25:28","edit_uid":"0","edit_time":"0000-00-00 00:00:00"},"option":[{"id":"2506","sys":"16","main_qid":"502","sub_qid":"0","text":"停止输液，让患者回家休息","sort":"0","sorts":"A"},{"id":"2507","sys":"16","main_qid":"502","sub_qid":"0","text":"立刻停止输液，让患者就地休息观察，将该不良反应上报","sort":"1","sorts":"B"},{"id":"2508","sys":"16","main_qid":"502","sub_qid":"0","text":"调低滴速，继续静滴","sort":"2","sorts":"C"},{"id":"2509","sys":"16","main_qid":"502","sub_qid":"0","text":"加入地塞米松继续静滴","sort":"3","sorts":"D"},{"id":"2510","sys":"16","main_qid":"502","sub_qid":"0","text":"以上都不是","sort":"4","sorts":"E"}]}}}}
     * re_url :
     */

    public String re_st;
    public String re_url;
    public ReMsgEntity re_msg;

    public static class ReMsgEntity implements Serializable{
        public int pid;
        public PaperInfo paper_info = new MockExamBean.PaperInfo();
        public Keylib keylib;
        public SortList sort_list;
        public List<String> category = new ArrayList<>();
        public HashMap<String,String> profession_arr = new HashMap<>() ;
        public List<Entity501> entity501 =new ArrayList<>();
    }

    public static class PaperInfo implements Serializable{

        /**
         * id : 2
         * sys : 16
         * serial_code : 32e8ad08a245c3c3f90a
         * caption : 海南省2015年医师定期考核模拟试卷-人文（50）
         * category : 5
         * profession : P5000000
         * grade : 56
         * item_count : 100
         * total_score : 100
         * total_minute : 60
         * pass_score : 0
         * keyword :
         * remark :
         * original_cost : 0.01
         * rate : 100
         * status : 1
         * sorting : 0
         * add_uid : 1
         * add_time : 2016-03-18 13:24:49
         * edit_uid : 1
         * edit_time : 2016-03-18 15:23:34
         * specialty_code : null
         */

        public String id;
        public String sys;
        public String serial_code;
        public String caption;
        public String category;
        public String profession;
        public String grade;
        public String item_count;
        public String total_score;
        public String total_minute;
        public String pass_score;
        public String keyword;
        public String remark;
        public String original_cost;
        public String rate;
        public String status;
        public String sorting;
        public String add_uid;
        public String add_time;
        public String edit_uid;
        public String edit_time;
        public Object specialty_code;
    }
    public static class Keylib implements Serializable{

    }
    public static class SortList implements Serializable{

    }
    public static class CategoryArr implements Serializable{


    }


    public static class Entity501 implements Serializable{
        public int serial_num;
        public Info info;
        public List<Option> option;

        @Override
        public String toString() {
            return "Entity501{" +
                    "info=" + info +
                    ", serial_num=" + serial_num +
                    ", option=" + option +
                    '}';
        }
    }

    public static class Info implements Serializable{
        /**
         * id : 598
         * sys : 16
         * pid : 2
         * serial_code : IITEM5C4QAKFCG5WB6WN
         * item_type : 63
         * caption : 某医院的医护人员工作疏忽造成患者重度残疾，经鉴定机构认定为医疗事故，则下列费用中哪项不属于该医院应该承担的
         * category : 5
         * profession : 5
         * catalog :
         * item_unit : 1
         * item_level : 2
         * item_score : 1
         * keyword : 2013年增加,200题，吉林考核，吉林模拟，海南模拟
         * remark :
         * answer : 2987
         * add_uid : 1
         * add_time : 2016-03-18 13:25:28
         * edit_uid : 0
         * edit_time : 0000-00-00 00:00:00
         */

        public String id;
        public String sys;
        public String pid;
        public String serial_code;
        public String item_type;
        public String caption;
        public String category;
        public String profession;
        public String catalog;
        public String item_unit;
        public String item_level;
        public String item_score;
        public String keyword;
        public String remark;
        public String answer;
        public String add_uid;
        public String add_time;
        public String edit_uid;
        public String edit_time;
        public String u_answer;
        public String right_answer;
        public String user_answer;

        @Override
        public String toString() {
            return "Info{" +
                    "add_time='" + add_time + '\'' +
                    ", id='" + id + '\'' +
                    ", sys='" + sys + '\'' +
                    ", pid='" + pid + '\'' +
                    ", serial_code='" + serial_code + '\'' +
                    ", item_type='" + item_type + '\'' +
                    ", caption='" + caption + '\'' +
                    ", category='" + category + '\'' +
                    ", profession='" + profession + '\'' +
                    ", catalog='" + catalog + '\'' +
                    ", item_unit='" + item_unit + '\'' +
                    ", item_level='" + item_level + '\'' +
                    ", item_score='" + item_score + '\'' +
                    ", keyword='" + keyword + '\'' +
                    ", remark='" + remark + '\'' +
                    ", answer='" + answer + '\'' +
                    ", add_uid='" + add_uid + '\'' +
                    ", edit_uid='" + edit_uid + '\'' +
                    ", edit_time='" + edit_time + '\'' +
                    '}';
        }
    }
    public static class Option implements Serializable{
        /**
         * id : 2541
         * sys : 16
         * main_qid : 509
         * sub_qid : 0
         * text : 保障医疗安全，提高服务水平
         * sort : 0
         * sorts : A
         */

        public String id;
        public String sys;
        public String main_qid;
        public String sub_qid;
        public String text;
        public String sort;
        public String sorts;

        @Override
        public String toString() {
            return "Option{" +
                    "id='" + id + '\'' +
                    ", sys='" + sys + '\'' +
                    ", main_qid='" + main_qid + '\'' +
                    ", sub_qid='" + sub_qid + '\'' +
                    ", text='" + text + '\'' +
                    ", sort='" + sort + '\'' +
                    ", sorts='" + sorts + '\'' +
                    '}';
        }
    }

}
