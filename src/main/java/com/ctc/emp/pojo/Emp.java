package com.ctc.emp.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 21:25
 * @version:1.0
 * Serializable:序列化
 **/
@Data
public class Emp implements Serializable {
    private Integer empId;
    private String name;
    private Integer sex; //0 : 男  1:  女
    private Integer age;
    private Double sal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String address;
    private Integer deptId;

    private Dept dept;
}
