package com.kevin.tabindicator.samples;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TabHost;
import android.widget.TabWidget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/5/24.
 *
 * mTabs(list)放入html
 * ------mTabs放入ViewPager中
 */
public class tablet extends Fragment {
    //tabFragment加入到viewPager中，然后返回这个viewPager的fragment
    private ViewPager mViewPager;
    //private TabFragment mtabfragmet;
    private FragmentPagerAdapter madapter;
    private List<Fragment> mTabs = new ArrayList<>();//创建Fragment的list

    private TabHost mTabhost;

    String mTitles[] = {"Firstlet", "Secondlet"};

    View view;
    //1】传递上下文引用
    Context context;

    public static tablet newInstance(String strArg){
        tablet fragment =new tablet();
        Bundle args =new Bundle();
        args.putString("strArg1",strArg);
        fragment.setArguments(args);
        return fragment;

    }


    //2】创建返回View的onCreate方法
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //context = getActivity();
    }
    //【1】!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!onCreate方法

    //【2】!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!onCreateView方法  Fragment的方法
    //onCreateView()中container参数代表该Fragment在Activity中的父控件；savedInstanceState提供了上一个实例的数据。
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }

        }
        if(view==null){
        //3】加载界面
        view = inflater.inflate(R.layout.tablet, container, false);
        //4】获取上下文
        context = getActivity();
        //初始化一些参数
        initView();
        //initViewPager();
            onResume();
        }
        return view;
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //http://bbs.csdn.net/topics/390878231?list=lz
    //这个问题终于解决了！！
    @Override
    public void onResume() {
        super.onResume();
        mViewPager.setCurrentItem(1);
        mViewPager.setCurrentItem(0);
    }

    //初始化view
    public void initView() {
        mViewPager = (ViewPager) view.findViewById(R.id.id_viewpagerlet);
        mTabhost = (TabHost) view.findViewById(android.R.id.tabhost);//!!!【这样写】!!!  android.R.id.tabhost
        mTabhost.setup();
        //5】添加上面的子标签
        mTabhost.addTab(mTabhost.newTabSpec("one").setIndicator("就业").setContent(R.id.id_viewpagerlet));
        mTabhost.addTab(mTabhost.newTabSpec("two").setIndicator("创业").setContent(R.id.id_viewpagerlet));

        //mTabhost.setCurrentTab(0);
        //6】一些处理
        TabWidget tabWidget = mTabhost.getTabWidget();
        int count = tabWidget.getChildCount();
        mTabs.clear();
        initViewPager();
        //mViewPager没有显示，反射修改行为
        // mViewPager.getClass().getMethod();
        //mViewPager.setOnPageChangeListener(new );

        //Toast.makeText(getContext(),"!!"+mTabs.size() , Toast.LENGTH_SHORT).show();
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(count);

        for (int i = 0; i != count; i++) {
            final int index = i;
            //设置方法1
            tabWidget.getChildTabViewAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //监听tab
                    //Toast.makeText(getContext(), "点击" + index, Toast.LENGTH_SHORT).show();
                    //加载本地界面
                    if (index == 0) {

                        //Toast.makeText(getContext(), "进入index==0", Toast.LENGTH_SHORT).show();
                        mTabhost.setCurrentTab(0);
                        //mTabs.clear();
//                        refreshView(0);
//                        setAdapter();
                        mViewPager.setCurrentItem(0);
                    }

                    if (index == 1) {

                        //Toast.makeText(getContext(), "进入index==1s", Toast.LENGTH_SHORT).show();
                        mTabhost.setCurrentTab(1);
                        //mTabs.clear();
//                        refreshView(1);
//                        setAdapter();
                        mViewPager.setCurrentItem(1);
                    }
                }
            });

            //设置方法2
            mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String tabId) {
                    // mTabhost.setCurrentTab(Integer.parseInt(tabId));
                }
            });


            //设置方法3
            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageSelected(int arg0) {
//                    ELog.i(TAG, "@--> onPageSelected: " + arg0);
//                    mTabHost.setCurrentTab(arg0);
                    mTabhost.setCurrentTab(mViewPager.getCurrentItem());//tabhost与viewpager同步
                    //Toast.makeText(getContext(), "mViewPager--Changed--"+mViewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {
                }

                @Override
                public void onPageScrollStateChanged(int arg0) {
                }
            });
        }
        //initViewPager();//经过多次测试，initViewPager必须在适配器配置之前
        //设置适配器
        //getSupportFragmentManager();//是FragmentActivity的方法
        getChildFragmentManager();//是Fragment扩展中的方法
    }


    //初始化ViewPager
    public void initViewPager() {
        //在里面加载fragment
        for (String title : mTitles) {
            //Toast.makeText(getContext(), title, Toast.LENGTH_SHORT).show();
            if (title.equals("Firstlet")) {
                //就业
                //这里面写一下需要展示的内容
                refreshView(0);
                //mTabs.get(1).setUserVisibleHint(false);
                //Toast.makeText(getContext(), "Fisrt fragment added", Toast.LENGTH_SHORT).show();

            } else if (title.equals("Secondlet")) {
                //Toast.makeText(getContext(), "Secondlet!!", Toast.LENGTH_SHORT).show();
                //mTabhost.setCurrentTab(1);
                //这里写一些需要展示的内容
                //创业
                refreshView(1);
            }

        }
        setAdapter();
    }



    //更新界面使用

    public void refreshView(int location) {

        if (location == 0) {
            //Toast.makeText(getContext(), "location 0", Toast.LENGTH_SHORT).show();
            //更新界面
            tablet.mWF fragment = new tablet.mWF();
            WebView webView = fragment.mWebView;
            Bundle args = new Bundle();
            //args.putString("title", title);
            fragment.setArguments(args);
            mTabs.add(fragment);
        }

        if (location == 1) {
            //Toast.makeText(getContext(), "location 1", Toast.LENGTH_SHORT).show();
            tablet.mWF2 fragment = new tablet.mWF2();
            WebView webView = fragment.mWebView;
            Bundle args = new Bundle();
            //args.putString("title", title);
            fragment.setArguments(args);
            mTabs.add(fragment);
        }
    }
    //添加适配器
    //设置适配器
    public void setAdapter(){
        madapter = new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public int getCount() {
                return mTabs.size();
            }


            @Override
            public Fragment getItem(int arg0) {
                return mTabs.get(arg0);
            }
        };
        //mViewPager.setv
        mViewPager.setAdapter(madapter);

    }

    //内部webView类

    /**
     * 内部类中
     * 1.规定了一些webview的配置
     * 2.webview客户端配置
     */
    static public class mWF extends Fragment {

        WebView mWebView;//显示web的位置

        //onCreateView方法！！！！
        //这里调用的是onCreateView三个参数的方法
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //1加载web.xml
            View view = inflater.inflate(R.layout.local_page, container, false);
            //2加载其中webview元素
            mWebView = (WebView) view.findViewById(R.id.loc_webview);
            //3一些初始化设置
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setSupportZoom(true);
            //4指定【客户端】
            mWebView.setWebViewClient(new mWebViewClient());

            //!!这里的图片展示一定要整理一下
            //4.5 设置图片的替换模式
            String html = FileUtils.readAssest(getContext(), "local.html");
            html = html.replace("@image_assets", "image001.jpg");
            html = html.replace("@image_res", "image002.png");
            //html = html.replace("@image_assets3", "image003.gif");
            html = html.replace("@image_file", "image004.png");
            //html = html.replace("@image_assets5", "image005.jpg");
            html = html.replace("@image_dd", "image006.jpg");
            html = html.replace("@image_ff", "image007.png");
            //html = html.replace("@image_assets8", "image008.jpg");
            html = html.replace("@image_qq", "image009.jpg");
            html = html.replace("@image_ww", "image010.png");
            //html = html.replace("@image_assets", "image011.jpg");
            html = html.replace("@image_ee", "image012.jpg");
            html = html.replace("@image_rr", "image013.jpg");
            html = html.replace("@image_tt", "image014.jpg");
            html = html.replace("@image_yy", "image015.png");
            //html = html.replace("@image_assets", "image016.jpg");
            html = html.replace("@image_uu", "image017.png");
            //html = html.replace("@image_ii", "image018.jpg");
            html = html.replace("@image_ii", "image019.png");
            //html = html.replace("@image_assets", "image020.gif");
            html = html.replace("@image_oo", "image021.png");
            //html = html.replace("@image_assets", "image022.jpg");
            html = html.replace("@image_pp", "image023.png");
            //html = html.replace("@image_assets", "image024.jpg");
            html = html.replace("@image_aa", "image025.png");
            //html = html.replace("@image_assets", "image026.jpg");
            html = html.replace("@image_ss", "image027.jpg");
            html = html.replace("@image_gg", "image028.jpg");
            html = html.replace("@image_hh", "image029.png");
            //html = html.replace("@image_assets", "image030.jpg");
            html = html.replace("@image_jj", "image031.jpg");
            html = html.replace("@image_kk", "image032.png");
            //html = html.replace("@image_assets", "image033.jpg");
            html = html.replace("@image_ll", "image034.jpg");
            html = html.replace("@image_zz", "image035.jpg");
            html = html.replace("@image_xx", "image036.jpg");
            html = html.replace("@image_cc", "image037.jpg");
            html = html.replace("@image_vv", "image038.jpg");
            html = html.replace("@image_bb", "image039.jpg");

            html = html.replace("@image_nn", "image040.png");
            html = html.replace("@image_qw", "image041.png");
            html = html.replace("@image_w2", "image042.png");
            html = html.replace("@image_e3", "image043.png");
            html = html.replace("@image_r4", "image044.png");
            html = html.replace("@image_t5", "image045.png");
            html = html.replace("@image_y6", "image046.png");
            html = html.replace("@image_u7", "image047.png");
            html = html.replace("@image_i8", "image048.png");
            html = html.replace("@image_o9", "image049.png");
            html = html.replace("@image_p0", "image050.png");
            html = html.replace("@image_a1", "image051.png");
            html = html.replace("@image_s2", "image052.png");
            html = html.replace("@image_d3", "image053.png");
            html = html.replace("@image_f4", "image054.png");
            html = html.replace("@image_g5", "image055.png");
            html = html.replace("@image_h6", "image056.png");
            html = html.replace("@image_j7", "image057.png");
            html = html.replace("@image_k8", "image058.png");
            html = html.replace("@image_l9", "image059.png");
            html = html.replace("@image_z0", "image060.png");
            html = html.replace("@image_x1", "image061.png");
            html = html.replace("@image_c2", "image062.png");
            String baseurl = "file:///android_asset/local.html";
            //5连接网页【加载本地页面】
            //file:///android_asset/index.html
            //mWebView.loadUrl("file:///android_asset/local.html");
            mWebView.loadDataWithBaseURL(baseurl, html, "text/html", "UTF-8", null);
            //6设置webView中的一些设置
            WebSettings settings = mWebView.getSettings();
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            settings.setUseWideViewPort(true);//推荐窗口
            settings.setLoadWithOverviewMode(true);

            return view;
        }

        //3.5内部webView客户端
        private class mWebViewClient extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }
    }

    //2.内部类
    static public class mWF2 extends Fragment {

        WebView mWebView;//显示web的位置

        //onCreateView方法！！！！
        //这里调用的是onCreateView三个参数的方法
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //1加载web.xml
            View view = inflater.inflate(R.layout.remote_page, container, false);
            //2加载其中webview元素
            mWebView = (WebView) view.findViewById(R.id.remote_webview);
            //3一些初始化设置
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setSupportZoom(true);
            //4指定【客户端】
            mWebView.setWebViewClient(new mWebViewClient());
            //5连接网页【加载本地页面】
            //file:///android_asset/index.html
            mWebView.loadUrl("http://www.apesk.com/holland2/");
            //6设置webView中的一些设置
            WebSettings settings = mWebView.getSettings();
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            settings.setUseWideViewPort(true);//推荐窗口
            settings.setLoadWithOverviewMode(true);

            return view;
        }

        //3.5内部webView客户端
        private class mWebViewClient extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }
    }
}

