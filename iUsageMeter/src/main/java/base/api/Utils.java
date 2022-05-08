package base.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Utils {
    private static final ObjectMapper mapper = new ObjectMapper();

    static ObjectMapper getApiMapper() {
        return mapper;
    }

    static CloseableHttpClient getStandardHttpClient() {
        return HttpClients
                .custom()
                .setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
    }
}
