package com.kevin.tabindicator.samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dell on 2016/6/11.
 */
public class register extends Activity {

    private TextView txtview;
    private TextView passview;
    private TextView repassview;
    private RadioButton rmen;
    private RadioButton rwomen;
    private RadioGroup radioGroup;
    private Button register_button;

    private String name;
    private String password;
    private String repassword;
    private String sex;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载页面
        setContentView(R.layout.register_page);
        txtview = (TextView) findViewById(R.id.user_input);
        passview = (TextView) findViewById(R.id.pass_input);
        repassview = (TextView) findViewById(R.id.re_pass_input);
        rmen = (RadioButton) findViewById(R.id.sex_chmen);
        rwomen = (RadioButton) findViewById(R.id.sex_chwomen);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        register_button = (Button) findViewById(R.id.register);

        radioGroup.check(R.id.sex_chmen);
        radioGroup.check(R.id.sex_chwomen);

        name = txtview.getText().toString();
        password = passview.getText().toString();
        repassword = repassview.getText().toString();


        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = txtview.getText().toString();
                password = passview.getText().toString();
                repassword = repassview.getText().toString();
                //异常情况
                boolean flag = true;
                if (name.equals(" ") || password.equals(" ") || repassword.equals(" ")) {
                    flag = false;
                    Toast.makeText(getBaseContext(), "不能为空！", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(repassword)) {
                    flag = false;
                    Toast.makeText(getBaseContext(), "请确认密码，前后输入不一致！", Toast.LENGTH_SHORT).show();
                }

                if (flag) {
                    LauncherActivity.N=1;
                    Toast.makeText(getBaseContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getBaseContext(), LauncherActivity.class);
                    Bundle b = new Bundle();
                    b.putString("name", name);
                    b.putString("password", password);
                    b.putString("sex", sex);
                    i.putExtras(b);
                    startActivity(i);
                }
            }
        });

        //检查radioGroup   实现互斥！！
        //错误原因：因为调整界面布局，radioGroup没有包围radioButton
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                Toast.makeText(getBaseContext(),"Radio_Group" , Toast.LENGTH_SHORT).show();
                // if(checkedId==R.id.sex_chmen){
//                    Toast.makeText(getBaseContext(),"你选择了男" , Toast.LENGTH_SHORT).show();
//                }
//                if(checkedId==R.id.sex_chwomen){
//                    Toast.makeText(getBaseContext(),"你选择了女" , Toast.LENGTH_SHORT).show();
//                }
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.sex_chmen:
//                        if (rmen.isSelected()) {
//                            rmen.setSelected(false);
//                            rwomen.setSelected(true);
//                        } else {
//                            rmen.setSelected(true);
//                            rwomen.setSelected(false);
//                        }
                        //Toast.makeText(getBaseContext(), "男", Toast.LENGTH_SHORT).show();
                        sex = "男";
                        break;
                    case R.id.sex_chwomen:
//                        if (rwomen.isSelected()) {
//                            rmen.setSelected(true);
//                            rwomen.setSelected(false);
//                        } else {
//                            rmen.setSelected(false);
//                            rwomen.setSelected(true);
//                        }
                        // Toast.makeText(getBaseContext(), "女", Toast.LENGTH_SHORT).show();
                        sex = "女";
                        break;
                }

            }
        });


    }


}
