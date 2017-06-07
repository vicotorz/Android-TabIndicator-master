package com.kevin.tabindicator.samples;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.kevin.tabindicator.TabPageIndicatorEx;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TabPageIndicatorExActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabPageIndicatorEx mTabPageIndicatorEx;
    private List<Fragment> mTabs = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;


    private Toolbar mtoolbar;

    private boolean isGradualChange;

    private String[] mTitles = new String[]{"First Fragment",
            "Second Fragment", "Third Fragment", "Fourth Fragment"};

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_tabpage_indicator_ex);

        //初始化
        initViews();
        initEvents();
    }

    //反射强制显示内容
    private void setOverflowButtonAlways() {
        //获取对象
        ViewConfiguration config = ViewConfiguration.get(this);

        try {
            Field menuKey = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKey.setAccessible(true);
            menuKey.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("异常", null);
        }

    }


    //重写方法，setting中显示图标
    //Ctrl + O
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
//        Toast.makeText(getApplicationContext(), "ONMENUOPENED!!!!!",
//                Toast.LENGTH_SHORT).show();

        //menu设置
        if (menu != null) {
//            Toast.makeText(getApplicationContext(), "entree！",
//                    Toast.LENGTH_SHORT).show();
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    //调用这个方法
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    //复写setting中按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        switch(id){
//        case R.id.about:
//            Toast.makeText(getApplicationContext(), "About",
//                    Toast.LENGTH_SHORT).show();
//        case R.id.action_settings:
//            Toast.makeText(getApplicationContext(), "Settings",
//                    Toast.LENGTH_SHORT).show();
//        }
        if (id == R.id.about) {
            //Dialog
//            Toast.makeText(getApplicationContext(), "About",
//                    Toast.LENGTH_SHORT).show();
            newdialog();

        }

        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Settings",
                    Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }

    //加载初始设定的按钮
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        setOverflowButtonAlways();

        return true;
    }

    /**
     * 初始化View
     */
    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        //mViewPager.setOffscreenPageLimit(4);
        mTabPageIndicatorEx = (TabPageIndicatorEx) findViewById(R.id.tabpage_act_tpi);
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        initTabIndicator();
        initViewPager();
    }

    private void initViewPager() {
        //添加学校主页
        for (String title : mTitles) {
            if (title.equals("First Fragment")) {
//                mWF fragment = new mWF();
//                WebView webView = fragment.mWebView;
//                Bundle args = new Bundle();
//                args.putString("title", title);
//                fragment.setArguments(args);
                First_Page_travel fragment = new First_Page_travel();
                mTabs.add(fragment);
            } else if (title.equals("Second Fragment")) {
                //刷新有问题
                //tablet t = new tablet();
                //反射？
                //t.initView();
                mTabs.add(new tablet());
            } else if (title.equals("Third Fragment")) {
                listdemo li = new listdemo();
                mTabs.add(li);
            } else if (title.equals("Fourth Fragment")){

                String name = getIntent().getExtras().getString("name");
                String password = getIntent().getExtras().getString("password");
                String sex = getIntent().getExtras().getString("sex");

                Auto_infoActivity tf = new Auto_infoActivity();

                //!!!给fragment传值!!!
                Bundle args = new Bundle();
                args.putString("name", name);
                args.putString("password", password);
                args.putString("sex", sex);
                tf.setArguments(args);
                mTabs.add(tf);
            }
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mTabs.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);
    }

    /**
     * 初始化事件
     */
    private void initEvents() {

        mTabPageIndicatorEx.setOnTabSelectedListener(new TabPageIndicatorEx.OnTabSelectedListener() {

            @Override
            public void onTabSelected(int index) {
                mViewPager.setCurrentItem(index, false);
            }
        });
    }

    private void initTabIndicator() {
        mTabPageIndicatorEx.setViewPager(mViewPager);
        //显示指示红点
        //mTabPageIndicatorEx.setIndicateDisplay(2, true);

    }

    //弹出dialog框
    private void newdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("关于我们"); //设置标题
        builder.setMessage("武汉音乐学院小组" + "\n" +
                "周芷如 张芳源 张    卓 \n" +
                "刘欣欣 陈博文 许蓉辉 \n"); //设置内容
        builder.setIcon(R.drawable.logo);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                //Toast.makeText(getBaseContext(), "确认" + which, Toast.LENGTH_SHORT).show();
            }
        });
        //参数都设置完成了，创建并显示出来
        //builder.create().show();
        builder.create().show();
    }

}


////内部webView类
//static public class mWF extends Fragment {
//
//    WebView mWebView;//显示web的位置
//
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
//        mWebView.loadUrl("http://www.whcm.edu.cn/");
//        //6设置webView中的一些设置
//        WebSettings settings = mWebView.getSettings();
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings.setUseWideViewPort(true);//推荐窗口
//        settings.setLoadWithOverviewMode(true);
//
//        return view;
//    }
//
//    //3.5内部webView客户端
//    private class mWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//    }
//}