package com.ctc.emp.service.impl;

import com.ctc.emp.mapper.DeptMapper;
import com.ctc.emp.mapper.EmpMapper;
import com.ctc.emp.pojo.Dept;
import com.ctc.emp.pojo.Emp;
import com.ctc.emp.service.EmpService;
import com.ctc.emp.vo.EmpQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 21:38
 * @version:1.0
 **/
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Emp> getEmpList (EmpQuery params) {
        return empMapper.getEmpList(params);
    }

    @Override
    public Long countEmpList (EmpQuery params) {
        return empMapper.countEmpList(params);
    }

    @Override
    public int addEmp (Emp emp) {
        int i = empMapper.addEmp ( emp );
        return i;
    }

    @Override
    public List<Dept> getAllDept ( ) {
        return  deptMapper.getAllDept();
    }


    @Override
    public int deleteEmpByIds (int[] ids) {
        int i = empMapper.deleteEmpByIds(ids);
        return i;
    }

    @Override
    public Emp selectEmpById (int id) {
       Emp emp = empMapper.selectEmpById(id);
        return emp;
    }

    @Override
    public int updateEmp (Emp emp) {
        int i = empMapper.updateEmp(emp);
        return i;
    }
}
