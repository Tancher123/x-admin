package com.ctc.emp.service;

import com.ctc.emp.pojo.Dept;
import com.ctc.emp.pojo.Emp;
import com.ctc.emp.vo.EmpQuery;

import java.util.List;

public interface EmpService {
    List<Emp> getEmpList (EmpQuery params);

    Long countEmpList (EmpQuery params);

    int addEmp(Emp emp);

    List<Dept> getAllDept ( );

    int deleteEmpByIds (int[] ids);

    Emp selectEmpById (int id);

    int updateEmp (Emp emp);
}
