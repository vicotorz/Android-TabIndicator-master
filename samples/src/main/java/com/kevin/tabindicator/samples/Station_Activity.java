package com.kevin.tabindicator.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//！！思路不清楚！！ 重新梳理
public class Station_Activity extends AppCompatActivity {

    WebView mWebView;//显示web的位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_);
        //加载layout
        setContentView(R.layout.station_layout);
        mWebView = (WebView) findViewById(R.id.station_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        //4指定客户端
        mWebView.setWebViewClient(new mWebViewClient());
        //5连接网页
        //mWebView.loadUrl("http://www.whcm.edu.cn/");
        //6设置webView中的一些设置
        WebSettings settings = mWebView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);//推荐窗口
        settings.setLoadWithOverviewMode(true);

        String station_number = getIntent().getExtras().getString("station");
        //Toast.makeText(getBaseContext(),station_number,Toast.LENGTH_LONG).show();

        if (station_number.equals("1")) {
            String html = FileUtils.readAssest(getBaseContext(), "station1.html");
            html = html.replace("@image_assets", "station1-1.jpg");
            html = html.replace("@image_file", "station1-2.jpg");
            String baseurl = "file:///android_asset/station1.html";
            mWebView.loadDataWithBaseURL(baseurl, html, "text/html", "UTF-8", null);
        }

        if (station_number.equals("2")) {
            String html = FileUtils.readAssest(getBaseContext(), "station2.html");
            html = html.replace("@image_a", "station2-1.jpg");
            html = html.replace("@image_b", "station2-2.jpg");
            html = html.replace("@image_c", "station2-3.jpg");
            html = html.replace("@image_d", "station2-4.jpg");
            String baseurl = "file:///android_asset/station2.html";
            mWebView.loadDataWithBaseURL(baseurl, html, "text/html", "UTF-8", null);
        }

        if (station_number.equals("3")) {
            String html = FileUtils.readAssest(getBaseContext(), "station3.html");
            html = html.replace("@image_a", "station3-1.jpg");
            html = html.replace("@image_b", "station3-2.jpg");
            String baseurl = "file:///android_asset/station3.html";
            mWebView.loadDataWithBaseURL(baseurl, html, "text/html", "UTF-8", null);
        }
        if (station_number.equals("4")) {
            String html = FileUtils.readAssest(getBaseContext(), "station4.html");
            html = html.replace("@image_a", "station4-1.jpg");
            String baseurl = "file:///android_asset/station4.html";
            mWebView.loadDataWithBaseURL(baseurl, html, "text/html", "UTF-8", null);
        }
        if (station_number.equals("5")) {
            String html = FileUtils.readAssest(getBaseContext(), "station5.html");
            html = html.replace("@image_a", "station5-1.jpg");
            html = html.replace("@image_b", "station5-2.jpg");
            String baseurl = "file:///android_asset/station5.html";
            mWebView.loadDataWithBaseURL(baseurl, html, "text/html", "UTF-8", null);
        }
        if (station_number.equals("6")) {
            String html = FileUtils.readAssest(getBaseContext(), "station6.html");
            html = html.replace("@image_a", "station6-1.jpg");
            html = html.replace("@image_b", "station6-2.jpg");
            String baseurl = "file:///android_asset/station6.html";
            mWebView.loadDataWithBaseURL(baseurl, html, "text/html", "UTF-8", null);
        }
        if (station_number.equals("7")) {
            String html = FileUtils.readAssest(getBaseContext(), "station7.html");
            html = html.replace("@image_a", "station7-1.jpg");
            String baseurl = "file:///android_asset/station7.html";
            mWebView.loadDataWithBaseURL(baseurl, html, "text/html", "UTF-8", null);
        }



    }

    private class mWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        //再次说明--onCreate方法是Fragment中使用的
//    //这里调用的是onCreateView三个参数的方法
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        //1加载web.xml
//        View view = inflater.inflate(R.layout.web, container, false);
//        //2加载其中webview元素
//        mWebView = (WebView) view.findViewById(R.id.id_webview);
//        //3一些初始化设置
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.getSettings().setSupportZoom(true);
//        //4指定客户端
//        mWebView.setWebViewClient(new mWebViewClient());
//        //5连接网页
//        //mWebView.loadUrl("http://www.whcm.edu.cn/");
//        //6设置webView中的一些设置
//        WebSettings settings = mWebView.getSettings();
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings.setUseWideViewPort(true);//推荐窗口
//        settings.setLoadWithOverviewMode(true);
//
//        String html = FileUtils.readAssest(getBaseContext(), "local.html");
//        html = html.replace("@image_assets", "image001.jpg");
//        String baseurl = "file:///android_asset/local.html";
//        //5连接网页【加载本地页面】
//        //file:///android_asset/index.html
//        //mWebView.loadUrl("file:///android_asset/local.html");
//        mWebView.loadDataWithBaseURL(baseurl, html, "text/html", "UTF-8", null);
//
//        return view;
//    }

        //3.5内部webView客户端

    }
}


