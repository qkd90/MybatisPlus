package com.example.mybatisplus.pojo;

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
@TableName("bsf_employee")
public class BsfEmployee {

    @TableId(value = "ID")
    private Long id;

    //员工编码
    @TableField(value = "EMP_CODE")
    private String empCode;

    @TableField(value = "EMP_NAME")
    private String empName;

}
