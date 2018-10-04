package com.demo.gener;

import com.demo.gener.controller.TestController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author mr.yang.demo
 * @date - 2018/09/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainApplication.class})
public class TestControllerTest {

    @Autowired
    private TestController testController;

    @Test
    public void testHello() {
        String hello = testController.hello();
        Assert.assertEquals("与期望结果不一致！", "hello world", hello);
    }
}