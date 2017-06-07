package com.kevin.tabindicator.samples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/6/25.
 */
public class Auto_infoActivity extends Fragment {
    private LinearLayout contentLl;
    private List<RowItem> mList;
    dboperation DB;

    private String name;
    private String password;
    private String sex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //这里需要添加登陆数据库验证的部分
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //activity中是setContentView（） findViewById直接加载就行
        //但是普通的Fragment中 onCreateView方法，返回创建的view

        View view = inflater.inflate(R.layout.common_info_page, container,false);
        contentLl = (LinearLayout) view.findViewById(R.id.ll_content);//加载要填充的位置
        //DB=new dboperation();
        //ArrayList<User> userlist=DB.get_info("admin","admin");
        //0--女  1--男
        //Toast.makeText(getContext(),"执行返回",Toast.LENGTH_LONG).show();
        //Toast.makeText(getContext(),userlist.size(),Toast.LENGTH_LONG).show();
        Bundle args = getArguments();
        name=args.getString("name");
        password=args.getString("password");
        sex=args.getString("sex");

        mList = new ArrayList<RowItem>();
        mList.add(new RowItem("姓名", name));

        mList.add(new RowItem("性别", sex));

        CreateViewUtil cvu=new CreateViewUtil();
        //cvu.createContentView(getContext(),contentLl,mList);
        //这里抛异常
        //The specified child already has a parent. You must call removeView() on the child's parent first
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        contentLl.removeView(cvu.createContentView(getContext(),contentLl,mList));
        return view;
    }
}
