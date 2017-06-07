package com.kevin.tabindicator.samples;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by dell on 2016/6/10.
 */
public class First_Page_travel extends Fragment {

    View view;

    Context context;

    Button button1, button2, button3, button4, button5, button6, button7;

    public First_Page_travel() {

    }

    //1.onCreate方法
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //2.onCreateView方法
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.table_layout, container, false);
        context = getContext();
        initView();
        return view;
    }

    //初始化
    public void initView() {
        button1 = (Button) view.findViewById(R.id.first_station);
        button2 = (Button) view.findViewById(R.id.second_station);
        button3 = (Button) view.findViewById(R.id.third_station);
        button4 = (Button) view.findViewById(R.id.forth_station);
        button5 = (Button) view.findViewById(R.id.fifth_station);
        button6 = (Button) view.findViewById(R.id.sixth_station);
        button7 = (Button) view.findViewById(R.id.seventh_station);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i = new Intent(getContext(), Station_Activity.class);
                Bundle b=new Bundle();
                b.putString("station","1");
                i.putExtras(b);
                startActivity(i);

                //Activity之间传值
//                Bundle b = new Bundle();
//                b.putString("我是key", "这里就是你要传递的内容了");
//                //此处使用putExtras，接受方就响应的使用getExtra
//                intent.putExtras( b );

                //接受值
                //String rString = getIntent().getExtras().getString("我是key");
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i = new Intent(getContext(), Station_Activity.class);
                Bundle b=new Bundle();
                b.putString("station","2");
                i.putExtras(b);
                startActivity(i);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i = new Intent(getContext(), Station_Activity.class);
                Bundle b=new Bundle();
                b.putString("station","3");
                i.putExtras(b);
                startActivity(i);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i = new Intent(getContext(), Station_Activity.class);
                Bundle b=new Bundle();
                b.putString("station","4");
                i.putExtras(b);
                startActivity(i);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i = new Intent(getContext(), Station_Activity.class);
                Bundle b=new Bundle();
                b.putString("station","5");
                i.putExtras(b);
                startActivity(i);

            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i = new Intent(getContext(), Station_Activity.class);
                Bundle b=new Bundle();
                b.putString("station","6");
                i.putExtras(b);
                startActivity(i);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i = new Intent(getContext(), Station_Activity.class);
                Bundle b=new Bundle();
                b.putString("station","7");
                i.putExtras(b);
                startActivity(i);

            }
        });
    }


}
