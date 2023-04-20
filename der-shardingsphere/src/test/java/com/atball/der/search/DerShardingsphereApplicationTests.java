package com.atball.der.search;

import com.atball.der.search.entity.Course;
import com.atball.der.search.entity.Dict;
import com.atball.der.search.entity.User;
import com.atball.der.search.mapper.CourseMapper;
import com.atball.der.search.mapper.DictMapper;
import com.atball.der.search.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DerShardingsphereApplicationTests {

	@Resource
	CourseMapper courseMapper;
	@Resource
	DictMapper dictMapper;
	@Resource
	UserMapper userMapper;

	@Test
	public void addCourse() {
		for (int i = 0; i < 10; i++) {
			Course course = new Course();
			course.setCid(Long.valueOf(i));
			course.setCname("sh");
			course.setUserId(Long.valueOf(1000 + i));
			course.setCstatus("1");
			courseMapper.insert(course);
		}
	}

	@Test
	public void queryUserStatus(){
		List<User> users = userMapper.queryUserStatus();
		users.forEach(user -> System.out.println(user));
	}

	@Test
	public void queryDictByMS(){
		List<Dict> dicts = dictMapper.selectList(null);
		dicts.forEach(dict -> System.out.println(dict));
	}

}
