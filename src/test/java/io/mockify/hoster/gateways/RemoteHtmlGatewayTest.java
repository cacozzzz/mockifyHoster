package io.mockify.hoster.gateways;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureWebMvc
public class RemoteHtmlGatewayTest {
    @Autowired
    private RemoteHtmlGateway remoteHtmlGateway;
    @SpyBean
    private RestTemplate restTemplateMock;
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    String testHtml = "<html><body>test</body></html>";

    @Test
    public void get_OpenBox() throws Exception {
        String testUtl = "http://test.com";
        byte[] testBytes = testHtml.getBytes("UTF-8");

        ResponseEntity<byte[]> testResponseEntity = new ResponseEntity<>(
                testBytes, HttpStatus.OK
        );
        mock_getForEntity(testUtl, testResponseEntity);

        assertArrayEquals(testBytes, remoteHtmlGateway.get(testUtl));
    }

    @Test
    public void get_Open_responseNotOk() throws Exception {
        String testUtl = "http://test.com";
        byte[] testBytes = testHtml.getBytes("UTF-8");

        ResponseEntity<byte[]> testResponseEntity = new ResponseEntity<>(
                testBytes, HttpStatus.NOT_FOUND
        );
        mock_getForEntity(testUtl, testResponseEntity);

        assertNull(remoteHtmlGateway.get(testUtl));
    }

    @Test
    public void get_BlackBox() throws Exception {
        String urlPart = "/wiremock/teststub";
        String testUrl = "http://localhost:"+wireMockRule.port()+urlPart;
        StubMapping stubMapping = stubFor(get(urlEqualTo(urlPart))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "text/html; charset=UTF-8")
                        .withBody(testHtml)));
        when(restTemplateMock.getForEntity(testUrl, byte[].class)).thenCallRealMethod();

        byte[] actual = remoteHtmlGateway.get(testUrl);
        byte[] expected = testHtml.getBytes();
        assertArrayEquals(expected, actual);
    }


    private void mock_getForEntity(String testUtl, ResponseEntity<byte[]> testResponseEntity) {
        when(restTemplateMock.getForEntity(testUtl, byte[].class)).thenReturn(testResponseEntity);
    }
}