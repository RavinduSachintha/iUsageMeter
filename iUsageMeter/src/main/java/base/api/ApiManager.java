package base.api;

import base.Constants;
import base.api.response.SltAuthResponse;
import base.api.response.SltUsageResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiManager {
    private SltAuthResponse sendSltAuthRequest() throws IOException {
        HttpPost post = new HttpPost(Constants.API_MANAGER_SLT_AUTH_URL);

        post.addHeader(Constants.API_MANAGER_SLT_HEADER_ACCEPT_KEY, Constants.API_MANAGER_SLT_HEADER_ACCEPT_VALUE);
        post.addHeader(Constants.API_MANAGER_SLT_HEADER_CONTENT_TYPE_KEY, Constants.API_MANAGER_SLT_HEADER_CONTENT_TYPE_VALUE);

        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair(Constants.API_MANAGER_SLT_PARAM_CLIENT_ID_KEY, Constants.API_MANAGER_SLT_PARAM_CLIENT_ID_VALUE));
        urlParameters.add(new BasicNameValuePair(Constants.API_MANAGER_SLT_PARAM_GRANT_TYPE_KEY, Constants.API_MANAGER_SLT_PARAM_GRANT_TYPE_VALUE));
        urlParameters.add(new BasicNameValuePair(Constants.API_MANAGER_SLT_PARAM_SCOPE_KEY, Constants.API_MANAGER_SLT_PARAM_SCOPE_VALUE));
        urlParameters.add(new BasicNameValuePair(Constants.API_MANAGER_SLT_PARAM_USERNAME_KEY, "**REMOVED**"));
        urlParameters.add(new BasicNameValuePair(Constants.API_MANAGER_SLT_PARAM_PASSWORD_KEY, "**REMOVED**"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = Utils.getStandardHttpClient();
             CloseableHttpResponse response = httpClient.execute(post)) {
            SltAuthResponse result = Utils.getApiMapper().readValue(response.getEntity().getContent(), SltAuthResponse.class);
            result.setStatus(response.getStatusLine().getStatusCode());
            return result;
        }
    }

    public SltUsageResponse sendSltUsageRequest() throws IOException {
        SltAuthResponse authResponse = this.sendSltAuthRequest();

        HttpGet get = new HttpGet(Constants.API_MANAGER_SLT_VAS_DATA_URL);

        get.addHeader(Constants.API_MANAGER_SLT_HEADER_AUTHORIZATION_KEY, "Bearer " + authResponse.getAccessToken());
        get.addHeader(Constants.API_MANAGER_SLT_HEADER_X_IBM_CLIENT_ID_KEY, Constants.API_MANAGER_SLT_HEADER_X_IBM_CLIENT_ID_VALUE);
        get.addHeader(Constants.API_MANAGER_SLT_HEADER_SUBSCRIBER_ID_KEY, authResponse.getMetadata());
        get.addHeader(Constants.API_MANAGER_SLT_HEADER_CONTENT_TYPE_KEY, Constants.API_MANAGER_SLT_HEADER_CONTENT_TYPE_VALUE2);

        try (CloseableHttpClient httpClient = Utils.getStandardHttpClient();
             CloseableHttpResponse response = httpClient.execute(get)) {
            SltUsageResponse result = Utils.getApiMapper().readValue(response.getEntity().getContent(), SltUsageResponse.class);
            result.setStatus(response.getStatusLine().getStatusCode());
            return result;
        }
    }
}
