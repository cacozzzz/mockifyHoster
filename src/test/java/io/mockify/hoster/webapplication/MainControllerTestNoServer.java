package io.mockify.hoster.webapplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
public class MainControllerTestNoServer {
    //@Autowired
    //private MockMvc mockMvc;
    @Autowired
    private MainController controller;

    @Test
    public void getHelloPage() {
        //mockMvc.perform(get("/api")).andDo()
    }
}
