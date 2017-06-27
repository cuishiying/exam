package com.shanglan.exam;

import com.shanglan.exam.controller.ExamController;
import com.shanglan.exam.entity.TestPaperRule;
import com.shanglan.exam.entity.User;
import com.shanglan.exam.service.TestPaperRulesService;
import com.shanglan.exam.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * Created by cuishiying on 2017/6/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:exam-context.xml"})
public class TestController {

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private UserService userService;
    @Autowired
    private TestPaperRulesService testPaperRulesService;


    @InjectMocks
    private ExamController indexController;       //需要测试的Controller
    private MockMvc mockMvc;        //SpringMVC提供的Controller测试类


    // 模拟request,response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
//        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(wac).build();

    }

    @Test
    public void test() {
        try {
            request.getSession().setAttribute("username","张三111");
            System.out.println("======="+request.getSession().getAttribute("username"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUser() throws Exception{
        User user = userService.findUserByUsernameAndtruename("张安世", "张安世");
        System.out.println(user.getUid());
    }

    @Test
    public void testUserList() throws Exception{
        List<User> all = userService.findAll();
        System.out.println(all.size());
    }


    @Test
    public void testUserCount() throws Exception{
        Integer count = userService.count();
        System.out.println("==="+count);
    }

    @Test
    public void testRules() throws Exception{
        List<TestPaperRule> all = testPaperRulesService.findAll();
        System.out.println("==="+all.size());
    }

    @Test
    public void testRule() throws Exception{
        TestPaperRule rule = testPaperRulesService.findByQuestionCategory(24);
        System.out.println("==="+rule.getEffectiveEndDate().toString());
    }
}
