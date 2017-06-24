package com.shanglan.exam;

import com.shanglan.exam.controller.IndexController;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by cuishiying on 2017/6/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring-context.xml"})
public class TestController {

    @Autowired
    protected WebApplicationContext wac;

//    @Autowired
//    private IndexController indexController;


    @InjectMocks
    private IndexController indexController;       //需要测试的Controller
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

//    @Test
//    public void testIndex() throws Exception{
//        indexController.index("张三","111111",null,null);
//    }
}
