package com.qkd90.mybatisplus.pojo;

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
@TableName("fake_org")
public class FakeOrg {

    @TableId(value = "ID")
    private int id;

    @TableField(value = "orgname")
    private String orgname;
}
