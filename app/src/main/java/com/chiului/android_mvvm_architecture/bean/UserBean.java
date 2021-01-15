package com.chiului.android_mvvm_architecture.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 用户实体类$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/21 09:56
 */
@Entity(tableName = "user")
public class UserBean {

    @NonNull
    @PrimaryKey
    private String id;
    private String account;
    private String psw;

//    private String id;
//    private String avatar;
//    private String banTime;
//    private String idCardNo;
//    private int marryStatus;
//    private String mobile;
//    private String password;
//    private String realName;
//    private String recordArea;
//    private String recordDate;
//    private String recordNo;
//    private String recordOffice;
//    private String recordSheng;
//    private String recordShi;
//    private String registerTime;
//    private String salt;
//    private int sex;
//    private String spouseIdCardNo;
//    private String spouseName;
//    private String status;
//    private String username;// 用户名
//    private String nickName;// 昵称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
