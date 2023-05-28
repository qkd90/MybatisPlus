package com.qkd90.mybatisplus.pojo;

import cn.hutool.core.date.DateTime;
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
@TableName("test_user")
public class TestUser {

    @TableId(value = "ID")

    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Integer version;

    private Integer deleted;

    private DateTime createTime;

    private DateTime updateTime;

}
