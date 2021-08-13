package com.heqiao.admin.entity;

/**
 * @author heq3
 * @create 2021/8/10 17:24
 */
public enum StatusEnum {
    STATUS1(-1,"点击额度测算"),
    STATUS2(0,"额度测算中"),
    STATUS7(1,"额度激活成功"),
    STATUS3(2,"请上次手术知情同意书"),
    STATUS4(3,"手术知情同意书审核中"),
    STATUS5(4,"放款中"),
    STATUS6(5,"查看还款计划表"),
    STATUS8(6);


    private int code;
    private String message;

    private StatusEnum(int val,String message) {
        this.code = val;
        this.message = message;
    }

    private StatusEnum(int val) {
        this.code = val;

    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }


}
