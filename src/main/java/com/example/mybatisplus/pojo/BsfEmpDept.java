package com.example.mybatisplus.pojo;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author renq@trasen.cn
 * @date 2023/4/11 11:11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("bsf_emp_dept")
public class BsfEmpDept {

    @TableId(value = "ID")
    private Long id;

    @TableField(value = "EMP_ID")
    private Long empid;

    @TableField(value = "DEPT_ID")
    private Long deptid;

    @TableField(value = "PRIMARY_FLAG")
    private String primaryflag;

    @TableField(value = "CREATE_TIME")
    private DateTime createTime;

    @TableField(value = "UPDATE_TIME")
    private DateTime updateTime;
}
