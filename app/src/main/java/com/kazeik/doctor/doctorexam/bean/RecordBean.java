package com.kazeik.doctor.doctorexam.bean;

/**
 * Created by kazeik.chen on 2016/5/27 0027 15:15.
 * email:kazeik@163.com ,QQ:77132995
 */
public class RecordBean {
    public String path;
    public int current;
    public String name;

    @Override
    public String toString() {
        return "RecordBean{" +
                "current=" + current +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
