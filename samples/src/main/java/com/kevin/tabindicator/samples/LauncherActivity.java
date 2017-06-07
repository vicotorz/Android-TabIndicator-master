package com.kevin.tabindicator.samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zwenkai on 2016/2/26.
 * 可能设置成登陆界面
 */

public class LauncherActivity extends Activity {
    static int N = 0;
    //public static final String[] options = {"点击进入"};
    String user_name;
    String user_password;
    EditText user_name_edit_text;
    EditText user_password_edit_text;
    Button user_button;
    TextView user_register;
    dboperation db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options));
        user_name_edit_text = (EditText) findViewById(R.id.user_input);
        user_password_edit_text = (EditText) findViewById(R.id.pass_input);
        user_name = user_name_edit_text.getText().toString();//获取用户名
        user_password = user_password_edit_text.getText().toString();//获取密码

        user_button = (Button) findViewById(R.id.login);//登陆按钮
        user_register = (TextView) findViewById(R.id.no_user);//注册按钮
//        db=new dboperation();
//        Log.e("JDBC","FUCK!!");
//        //Toast.makeText(getBaseContext(),"抓取数据",Toast.LENGTH_LONG).show();
//        if(db.selectall_operation()){
//            Toast.makeText(getBaseContext(),"###",Toast.LENGTH_LONG).show();
//            //Toast.makeText(getBaseContext(),db.get_info("admin","admin").size(),Toast.LENGTH_LONG).show();
        user_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag=false;
                user_name = user_name_edit_text.getText().toString();//获取用户名
                user_password = user_password_edit_text.getText().toString();//获取密码
                if (N == 0) {
                   flag= validate0();
                }
                if (N != 0) {
                   flag= validate();
                }
               if(flag){
                   Intent intent;
                   intent = new Intent(getApplicationContext(), TabPageIndicatorExActivity.class);
                   //传入Bundle
                   Bundle b = new Bundle();
                   if (user_name.equals("admin")) {
                       b.putString("name", "admin");
                       b.putString("password", "admin");
                       b.putString("sex", "男");
                   } else if (user_name.equals("whu")) {
                       b.putString("name", "whu");
                       b.putString("password", "123");
                       b.putString("sex", "女");
                   }
                   if(N!=0){
                       String name = getIntent().getExtras().getString("name");
                       String password = getIntent().getExtras().getString("password");
                       String sex = getIntent().getExtras().getString("sex");
                       b.putString("name", name);
                       b.putString("password", password);
                       b.putString("sex", sex);
                   }
                   intent.putExtras(b);
                 startActivity(intent);
               }
               else{
                   Toast.makeText(getBaseContext(), "登陆失败！", Toast.LENGTH_SHORT).show();
               }


            }
        });

        user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "开始注册", Toast.LENGTH_SHORT).show();
                Intent intent;
                intent = new Intent(getApplicationContext(), register.class);
                startActivity(intent);
            }
        });


    }


    //验证用户1
    protected boolean validate0() {
        //Toast.makeText(getBaseContext(), "validate0()", Toast.LENGTH_SHORT).show();

        boolean flag=false;
        if ((user_name.equals("admin") && user_password.equals("admin")) || (user_name.equals("whu") && user_password.equals("123"))) {
            flag = true;
        }
        return flag;
    }



    //验证用户2
    protected boolean validate() {
        //Toast.makeText(getBaseContext(), "validate()", Toast.LENGTH_SHORT).show();
        String name = getIntent().getExtras().getString("name");
        String password = getIntent().getExtras().getString("password");
        String sex = getIntent().getExtras().getString("sex");

       boolean flag=false;
        if ((user_name.equals("admin") && user_password.equals("admin")) || (user_name.equals("whu") && user_password.equals("123"))
                || (user_name.equals(name) && user_password.equals(password))) {
            flag = true;
        }
        return flag;
    }
    //@Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
////        Intent intent;
////
////        switch (position) {
////            default:
////            case 0:
////                intent = new Intent(this, TabPageIndicatorExActivity.class);
////                break;
////        }
//
//        startActivity(intent);
//    }
}
