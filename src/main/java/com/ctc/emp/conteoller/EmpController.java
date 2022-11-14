package com.ctc.emp.conteoller;

import com.ctc.common.vo.Result;
import com.ctc.emp.pojo.Dept;
import com.ctc.emp.pojo.Emp;
import com.ctc.emp.service.impl.EmpServiceImpl;
import com.ctc.emp.vo.EmpQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 21:28
 * @version:1.0
 **/
@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpServiceImpl empService;
    //跳转到员工分页列表
    @GetMapping("")
    public String toEmpListUi(){
        return "emp/empList";
    }

    //分页查询，模糊查询
    @GetMapping("/list")
    @ResponseBody
    public Result<Object> getEmpList (EmpQuery params) {
        List<Emp> list = empService.getEmpList ( params );//分页查询所有员工信息
        Long count = empService.countEmpList ( params);//总条数
        return Result.success ( list,count );
    }
    //跳转到新增页面
    @GetMapping("/add/ui")
    public String addUi(Model model){
        List<Dept> deptList = empService.getAllDept();
        model.addAttribute ( "deptList",deptList );
        return "emp/empAdd";
    }

    //员工新增
    @PostMapping("/add.do")
    @ResponseBody
    public Result<Object> addEmp(Emp emp){
        Date birthday = emp.getBirthday ( );
        if ( StringUtils.isEmpty ( birthday ) ){
            return Result.fail ( "不能为空！" );
        }
        int i = empService.addEmp ( emp );
        if ( i > 0 ){
            return Result.success ( "添加成功！" );
        }
        return Result.fail ( "添加失败,请联系管理员！" );
    }

    //批量删除员工
    @DeleteMapping("/{ids}")
    @ResponseBody
    public Result<Object> deleteEmpByIds(@PathVariable("ids")int[] ids){
        int i = empService.deleteEmpByIds(ids);
        if ( i > 0 ){
            return Result.success ( "删除成功！" );
        }
        return Result.fail ( "删除失败！" );
    }

    //根据id查询员工信息
    @GetMapping("/{id}")
    public String selectEmpById(@PathVariable("id")int id,Model model){
        Emp emp = empService.selectEmpById(id);
        model.addAttribute ( "emp",emp );
        model.addAttribute ( "deptList",empService.getAllDept () );
        return "emp/empUpdate";
    }

    //修改员工
    @PutMapping("")
    @ResponseBody
    public Result<Object> updateEmp(Emp emp){
        int i = empService.updateEmp(emp);
        if ( i > 0 ){
            return Result.success ( "员工信息修改成功！" );
        }
        return Result.fail ( "员工信息失败,请联系管理员！" );
    }

}
