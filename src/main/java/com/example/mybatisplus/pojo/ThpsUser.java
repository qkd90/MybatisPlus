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
@TableName("thps_user")
public class ThpsUser {

    @TableId(value = "ID")
    private Long id;

    @TableField(value = "USERCODE")
    private String usercode;

    @TableField(value = "PASSWORD")
    private String password;

    @TableField(value = "AUTOEXITTIME")
    private Integer autoExitTime;

}
