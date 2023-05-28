package com.qkd90.mybatisplus;

import com.qkd90.mybatisplus.mapper.TestUserMapper;
import com.qkd90.mybatisplus.pojo.TestUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

	@Resource
	private TestUserMapper testUserMapper;

	@Test
	public void testInsert() {
		TestUser user = new TestUser();
		user.setName("张三");
		user.setAge(23);
		user.setEmail("test01@gamil.con");
		int result = testUserMapper.insert(user);
		System.out.println(result);
	}

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<TestUser> userList = testUserMapper.selectList(null);
		Assertions.assertEquals(5, userList.size());
		userList.forEach(System.out::println);
	}



}
