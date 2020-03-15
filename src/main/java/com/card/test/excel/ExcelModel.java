package com.card.test.excel;

/**
 * Created by cuihp on 2020/3/12.
 */
public class ExcelModel {
    private String activity_id;
    private String activity_code;
    private String activity_name;
    private String activity_endtime;
    private String left_value;
    private String right_value;
    private String left_value1;
    private String right_value1;

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getActivity_code() {
        return activity_code;
    }

    public void setActivity_code(String activity_code) {
        this.activity_code = activity_code;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_endtime() {
        return activity_endtime;
    }

    public void setActivity_endtime(String activity_endtime) {
        this.activity_endtime = activity_endtime;
    }

    public String getLeft_value() {
        return left_value;
    }

    public void setLeft_value(String left_value) {
        this.left_value = left_value;
    }

    public String getRight_value() {
        return right_value;
    }

    public void setRight_value(String right_value) {
        this.right_value = right_value;
    }

    public String getLeft_value1() {
        return left_value1;
    }

    public void setLeft_value1(String left_value1) {
        this.left_value1 = left_value1;
    }

    public String getRight_value1() {
        return right_value1;
    }

    public void setRight_value1(String right_value1) {
        this.right_value1 = right_value1;
    }


    @Override
    public String toString() {
        return "ExcelModel{" +
                "activity_id='" + activity_id + '\'' +
                ", activity_code='" + activity_code + '\'' +
                ", activity_name='" + activity_name + '\'' +
                ", activity_endtime='" + activity_endtime + '\'' +
                ", left_value='" + left_value + '\'' +
                ", right_value='" + right_value + '\'' +
                ", left_value1='" + left_value1 + '\'' +
                ", right_value1='" + right_value1 + '\'' +
                '}';
    }
}
