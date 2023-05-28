package com.qkd90.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("bsf_organization")
public class BsfOrganization {

    @TableId(value = "ID")
    private Long id;

    @TableField(value = "PARENT_ID")
    private Long parentid;

    @TableField(value = "MANAGE_TYPE")
    private String managetype;

    @TableField(value = "ORG_CODE")
    private String orgcode;

    @TableField(value = "ORG_NAME")
    private String orgname;

}
