package com.kevin.tabindicator.samples;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 2016/6/25.
 * 自动生成用户信息的控件类
 */

public class CreateViewUtil {
//    private LinearLayout contentLl;
//    private List<RowItem> mList;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //context = getActivity();
//        setContentView(R.layout.common_info_page);
//        contentLl = (LinearLayout) findViewById(R.id.ll_content);//加载要填充的位置
//        mList=new ArrayList<RowItem>();
//        mList.add(new RowItem("姓名","张三"));
//        mList.add(new RowItem("性别","男"));
//        mList.add(new RowItem("年龄","28"));
//        mList.add(new RowItem("学历","博士"));
//        createContentView(getContext(),contentLl,mList);
//    }
//设置fonts
//   TextView mTextView = (TextView)view.findViewById(R.id.two_fragment);
//   Typeface type = Typeface.createFromAsset(mTextView.getContext().getAssets(), "fonts/fzpty.TTF");
//    mTextView.setTypeface(type);

    public View createContentView(Context context, LinearLayout contentLayout, List<RowItem> rowitemList) {
        View childview = null;//childview --每个小条目
        TextView keyTv;
        TextView valueTv;
        LayoutInflater layoutInflater;
        //inflater.inflate
        //获得一个布局填充器，这样你就可以使用这个填充器来把xml布局文件转为View对象了
        //加载布局管理器
        layoutInflater = LayoutInflater.from(context);
        //通过layoutInflater连接各个小条目


        for (int i = 0; i < rowitemList.size(); i++) {
            childview = layoutInflater.inflate(R.layout.info_context, null);
            keyTv = (TextView) childview.findViewById(R.id.tv_key);
            valueTv = (TextView) childview.findViewById(R.id.tv_value);
    //===========================一定要记一下
            Typeface type = Typeface.createFromAsset(keyTv.getContext().getAssets(), "font/STANK.ttf");
            keyTv.setTypeface(type);

            Typeface type2 = Typeface.createFromAsset(valueTv.getContext().getAssets(), "font/STANK.ttf");
            valueTv.setTypeface(type);
    //================================一定要记一下
            keyTv.setText(rowitemList.get(i).getKey());
            valueTv.setText(rowitemList.get(i).getValue());
            Log.e("!!!!!!!", keyTv.getText().toString() + "--" + valueTv.getText().toString());
            //将这一个小部分加进去
            contentLayout.addView(childview);
        }
        //这个返回值有问题，返回这个就解决了
        return contentLayout;
    }


}
