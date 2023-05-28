package com.qkd90.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("log_user")
public class LogUser {

    // 对应数据库中的主键 (uuid、自增id、雪花算法、redis、zookeeper！)
    @TableId(value = "ID")
    private Long id;

    @TableField(value = "USERCODE")
    private String usercode;

    @TableField(value = "PASSWORD")
    private String password;

    //员工编码
    @TableField(value = "EMP_CODE")
    private String empCode;

}
