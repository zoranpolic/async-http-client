package async;


import org.junit.Before;
import org.junit.Test;

public class HttpClientTest {

    private HttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = new HttpClient();
    }

    @Test
    public void client1Test() {
        httpClient.client1("https://archive.org/details/newsatendsofeart00blum/page/n",
                100, 10);
    }

    @Test
    public void client2Test() {
        httpClient.client2("https://archive.org/details/newsatendsofeart00blum/page/n",
                100, 10);
    }

    @Test
    public void client3Test() {
        httpClient.client3("https://archive.org/details/newsatendsofeart00blum/page/n",
                100, 10);
    }
}

