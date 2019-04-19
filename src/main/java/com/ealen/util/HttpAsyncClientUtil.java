package com.ealen.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
//<dependency>
//<groupId>org.apache.httpcomponents</groupId>
//<artifactId>httpasyncclient</artifactId>
//<version>4.1</version>
//</dependency>

/**
 * @author lizhedao
 * @since 2/20/17
 */
public class HttpAsyncClientUtil {
    private final static Logger logger = LoggerFactory.getLogger(HttpAsyncClientUtil.class);
    private static CloseableHttpAsyncClient httpclient;
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

    }
    static {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30 * 1000).setConnectTimeout(30 * 1000).build();
        httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig).setMaxConnTotal(100).setMaxConnPerRoute(100).build();
        httpclient.start();
    }
    public static void post(String url, List<String> requests) throws Exception {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000).setConnectTimeout(30000).build();
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig).build();
        httpclient.start();
        ArrayList<HttpPost> httpPosts = new ArrayList<HttpPost>();
        for (String s : requests) {
            HttpPost post = new HttpPost(url);
            StringEntity postingString = new StringEntity(s);// json传递
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            httpPosts.add(post);
        }
        final CountDownLatch latch = new CountDownLatch(httpPosts.size());
        ArrayList<Future<HttpResponse>> futures = new ArrayList<>();
        for (HttpPost httpPost : httpPosts) {
            Future<HttpResponse> execute = httpclient.execute(httpPost, new FutureCallback<HttpResponse>() {
                //无论完成还是失败都调用countDown()
                @Override
                public void completed(final HttpResponse response) {
                    latch.countDown();
                    System.out.println(httpPost.getRequestLine() + "->"
                            + response.getStatusLine());
                }

                @Override
                public void failed(final Exception ex) {
                    latch.countDown();
                    System.out.println(httpPost.getRequestLine() + "->" + ex);
                }

                @Override
                public void cancelled() {
                    latch.countDown();
                    System.out.println(httpPost.getRequestLine()
                            + " cancelled");
                }
            });
            futures.add(execute);
        }
        for (Future<HttpResponse> future : futures) {
            System.out.println(EntityUtils.toString(future.get().getEntity()));
        }

        latch.await();
        System.out.println("Done");
    }

    public static List<String> postFormBatch(List<HttpPost> httpPosts) throws Exception {
        ArrayList<String> resList = new ArrayList<>();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(20 * 1000).setConnectTimeout(20 * 1000).build();
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig).setMaxConnTotal(20).setMaxConnPerRoute(20).build();
        httpclient.start();
//        final CountDownLatch latch = new CountDownLatch(httpPosts.size());
        ArrayList<Future<HttpResponse>> futures = new ArrayList<>();
        for (HttpPost httpPost : httpPosts) {
            Future<HttpResponse> execute = httpclient.execute(httpPost, new FutureCallback<HttpResponse>() {
                //无论完成还是失败都调用countDown()
                @Override
                public void completed(final HttpResponse response) {
//                    latch.countDown();
//                    System.out.println(httpPost.getRequestLine() + "->"
//                            + response.getStatusLine());
                }

                @Override
                public void failed(final Exception ex) {
//                    latch.countDown();
//                    System.out.println(httpPost.getRequestLine() + "->" + ex);
                }

                @Override
                public void cancelled() {
//                    latch.countDown();
//                    System.out.println(httpPost.getRequestLine()
//                            + " cancelled");
                }
            });
            futures.add(execute);
        }
//        latch.await();
        for (Future<HttpResponse> future : futures) {
            resList.add(EntityUtils.toString(future.get().getEntity()));
        }
        return resList;
    }

    public static List<String> postFormBatchOpt(List<HttpPost> httpPosts) throws Exception {
        ArrayList<String> resList = new ArrayList<>();

        BlockingQueue<HttpResponse> httpResponses = new ArrayBlockingQueue<>(httpPosts.size());
        for (HttpPost httpPost : httpPosts) {
            Future<HttpResponse> execute = httpclient.execute(httpPost, new FutureCallback<HttpResponse>() {
                //无论完成还是失败都调用countDown()
                @Override
                public void completed(final HttpResponse response) {
                    httpResponses.add(response);
                }

                @Override
                public void failed(final Exception ex) {
                    logger.error("postFormBatch failed:"+httpPost.getRequestLine() + "->" + ex);
                }

                @Override
                public void cancelled() {
                }
            });
        }
        int responseCount=0;
        while (true) {
            HttpResponse poll = httpResponses.poll(15, TimeUnit.SECONDS);
            if (poll != null) {
                resList.add(EntityUtils.toString(poll.getEntity()));
                responseCount++;
            }else {
                break;
            }
            if(responseCount==httpPosts.size()){
                break;
            }
        }
        return resList;
    }

    public static void getbatch() throws InterruptedException, IOException {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000).setConnectTimeout(3000).build();
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig).build();
        try {
            httpclient.start();
            final HttpGet[] requests = new HttpGet[]{
                    new HttpGet("http://www.apache.org/"),
                    new HttpGet("https://www.verisign.com/"),
                    new HttpGet("http://www.google.com/"),
                    new HttpGet("http://www.baidu.com/")};
            final CountDownLatch latch = new CountDownLatch(requests.length);
            for (final HttpGet request : requests) {
                httpclient.execute(request, new FutureCallback<HttpResponse>() {
                    //无论完成还是失败都调用countDown()
                    @Override
                    public void completed(final HttpResponse response) {
                        latch.countDown();
                        System.out.println(request.getRequestLine() + "->"
                                + response.getStatusLine());
                    }

                    @Override
                    public void failed(final Exception ex) {
                        latch.countDown();
                        System.out.println(request.getRequestLine() + "->" + ex);
                    }

                    @Override
                    public void cancelled() {
                        latch.countDown();
                        System.out.println(request.getRequestLine()
                                + " cancelled");
                    }
                });
            }
            latch.await();
            System.out.println("Shutting down");
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }
}
