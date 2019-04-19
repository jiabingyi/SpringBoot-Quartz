package com.ealen.util;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//<dependency>
//<groupId>javax.ws.rs</groupId>
//<artifactId>javax.ws.rs-api</artifactId>
//<version>2.0.1</version>
//</dependency>
public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(20);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        configBuilder.setAuthenticationEnabled(true);
        requestConfig = configBuilder.build();
    }

    public static String get(String url) {
        CloseableHttpClient httpClient = null;
        String res = null;
        try {
            // 用get方法发送http请求
            HttpGet get = new HttpGet(url);
            httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = null;
            // 发送get请求
            httpResponse = httpClient.execute(get);
            try {
                // response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    res = EntityUtils.toString(entity);
                }
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static String postString(String url, String request) {
        String content = null;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            StringEntity postingString = new StringEntity(request);// json传递
//            System.out.println("request:" + request);
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode() == Response.Status.OK.getStatusCode() && response.getEntity() != null){
                content = EntityUtils.toString(response.getEntity());
            }else{
                int respCode = response.getStatusLine().getStatusCode();
                String errorJson = "{\"code\":\"" + respCode + "\",\"msg\":\"Error occured when post url\"}";
//                JSON.parse(errorJson);
                if( Response.Status.BAD_GATEWAY.getStatusCode() == respCode){
                    errorJson = Constants.CODE_502_MSG;
                }else if(Response.Status.NOT_FOUND.getStatusCode() == respCode){
                    errorJson = Constants.CODE_404_MSG;
                }
                return errorJson;
            }
            
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    
    public static String postObject(String url, Object obj){
        
        String reqest = FastjsonUtil.toJSONString(obj);
        String result = postString(url, reqest);
        
        return result;
    }
    


    public static String postForm(String url, HashMap<String, String> params) {
        String res = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Builder builder = RequestConfig.custom();
        builder.setConnectionRequestTimeout(60000);
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if (params != null) {
            for (String param : params.keySet()) {
                formparams.add(new BasicNameValuePair(param, params.get(param)));
            }
        }
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (response.getStatusLine().getStatusCode() == Response.Status.OK.getStatusCode() && entity != null) {
                    res = EntityUtils.toString(entity, "UTF-8");
                }else{
                    int respCode = response.getStatusLine().getStatusCode();
                    res = "{\"code\":\"" + respCode + "\",\"msg\":\"Error occured when post url\"}";
//                    JSON.parse(errorJson);
                    if( Response.Status.BAD_GATEWAY.getStatusCode() == respCode){
                        res = Constants.CODE_502_MSG;
                    }else if(Response.Status.NOT_FOUND.getStatusCode() == respCode){
                        res = Constants.CODE_404_MSG;
                    }
                    return res;
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static String doPostSSL(String apiUrl, HashMap<String, String> params) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;
        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                    pairList.add(pair);
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 创建SSL安全连接
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }
}