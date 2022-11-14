package com.ctc.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 14:40
 * @version:1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String username;
    private String password;
    private String chName;
}
