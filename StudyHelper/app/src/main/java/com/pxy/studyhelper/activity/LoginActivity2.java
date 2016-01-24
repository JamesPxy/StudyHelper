package com.pxy.studyhelper.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.pxy.studyhelper.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(value = R.layout.activity_login2)
public class LoginActivity2 extends AppCompatActivity {
    @ViewInject(value = R.id.email)
    private AutoCompleteTextView email;
    @ViewInject(value = R.id.password)
    private AutoCompleteTextView  password;
    @ViewInject(value = R.id.email_sign_in_button)
    private Button  email_sign_in_button;
    @ViewInject(R.id.button1)
    private Button button1;
//    private AutoCompleteTextView  tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login2);
        x.view().inject(this);

//button1= (Button) findViewById(R.id.button1);
//        tv= (AutoCompleteTextView) findViewById(R.id.email);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(LoginActivity2.this, email.getText().toString() + "----" + password.getText().toString(), Toast.LENGTH_LONG).show();
//                Toast.makeText(LoginActivity2.this, tv.getText().toString()+"你好我是Xutils的IOC功能", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
//    @Event(value = R.id.button1,type = View.OnClickListener.class)
//    private void onButtonClick(View v) {
//        switch (v.getId()) {
//            case R.id.button1:
//                Toast.makeText(this, "你好我是Xutils的IOC功能", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }

    @Event(value = R.id.button1,type = View.OnClickListener.class)
    private  void  test(View  v){
                Toast.makeText(LoginActivity2.this, email.getText().toString() + "你好我是Xutils的IOC功能", Toast.LENGTH_SHORT).show();
    }


}
