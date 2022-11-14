package com.ctc.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-08 09:32
 * @version:1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Integer aId;
    private String aCid;         //学号
    private String aName;       //姓名
    private Integer aSex;       //性别
    private String aNum;        //电话
    private String aQQ;         //qq
    private String aWx;         // 微信
    private Integer classId;
    private Integer userId;

    private User user;         //对应的用户
    private AccountClass accountClass;  //对应的班级
}
