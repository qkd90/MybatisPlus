package com.example.mybatisplus.pojo;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("bsf_user")
public class BsfUser {

    @TableId(value = "ID")
    private Long id;

    @TableField(value = "LOGIN_ACCOUNT")
    private String usercode;

    @TableField(value = "LOGIN_PASSWORD")
    private String password;

    @TableField(value = "USER_NAME")
    private String username;

    @TableField(value = "USER_TYPE")
    private String usertype;

    @TableField(value = "EMP_ID")
    private Long empId;

    @TableField(value = "CREATE_TIME")
    private DateTime createTime;

    @TableField(value = "UPDATE_TIME")
    private DateTime updateTime;

}
