package com.ctc.emp.mapper;


import com.ctc.emp.pojo.Emp;
import com.ctc.emp.vo.EmpQuery;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpMapper {
    /**
     * @param params:
     * @return List<Emp>
     * @author 陈天赐
     * @description 分页查询所有员工信息
     * @date 2022/11/7 11:25
     */
    List<Emp> getEmpList (EmpQuery params);

    /**
     * @param params:
     * @return Long
     * @author 陈天赐
     * @description 获取数据的总数
     * @date 2022/11/7 11:25
     */
    Long countEmpList (EmpQuery params);

    /**
     * @param emp:
     * @return int
     * @author 陈天赐
     * @description 新增员工信息
     * @date 2022/11/7 11:26
     */
    int addEmp (Emp emp);

    /**
     * @param ids:
     * @return int
     * @author 陈天赐
     * @description 批量删除
     * @date 2022/11/7 14:46
     */
    int deleteEmpByIds (int[] ids);

    /**
     * @param id:
      * @return Emp
     * @author 陈天赐
     * @description 根据id查询员工信息
     * @date 2022/11/7 16:13
     */
    @Select ( "select * from emp where emp_id = #{empId}" )
    Emp selectEmpById (int id);

    /**
     * @param emp:
      * @return int
     * @author 陈天赐
     * @description 修改员工信息
     * @date 2022/11/7 16:17
     */
    int updateEmp (Emp emp);
}
