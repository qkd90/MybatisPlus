package com.qkd90.mybatisplus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qkd90.mybatisplus.pojo.TestUser;
import org.springframework.stereotype.Repository;

import java.util.Map;

// 在对应的Mapper上面继承基本的类 BaseMapper
@Repository // 代表持久层
public interface TestUserMapper extends BaseMapper<TestUser> {
    // 所有的CRUD操作都已经编写完成了
    // 你不需要像以前的配置一大堆文件了！
    Map<String, Object> selectMapByUserId(Long id);
}
