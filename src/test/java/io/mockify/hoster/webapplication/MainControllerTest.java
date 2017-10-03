package io.mockify.hoster.webapplication;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Ignore
    @Test
    public void getHelloPage() throws Exception {
        assertThat(testRestTemplate.getForObject(String.format("http://localhost:%d/api", port),
                String.class)).contains("<!DOCTYPE HTML>\n" +
                "\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Getting Started: Serving Web Content</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>Hello, app!</p>\n" +
                "</body>\n" +
                "</html>");
    }

}