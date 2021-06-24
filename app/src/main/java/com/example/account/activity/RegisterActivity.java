package com.example.account.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.account.R;
import com.example.account.dao.UserDataBaseOpenHelper;

/**
 * 此类 implements View.OnClickListener 之后，就可以把onClick事件写到onCreate()方法之外，这样，onCreate()方法中的代码就不会显得很冗余。
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    private UserDataBaseOpenHelper mUserDataBaseOpenHelper;
    private Button mBtRegisteractivityRegister;
    private RelativeLayout mRlRegisteractivityTop;
    private ImageView mIvRegisteractivityBack;
    private LinearLayout mLlRegisteractivityBody;
    private EditText mEtRegisteractivityUsername;
    private EditText mEtRegisteractivityPassword1;
    private EditText mEtRegisteractivityPassword2;
    private RelativeLayout mRlRegisteractivityBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        mUserDataBaseOpenHelper = new UserDataBaseOpenHelper(this);


    }

    private void initView(){
        mBtRegisteractivityRegister = findViewById(R.id.bt_registeractivity_register);
        mRlRegisteractivityTop = findViewById(R.id.rl_registeractivity_top);
        mIvRegisteractivityBack = findViewById(R.id.iv_registeractivity_back);
        mLlRegisteractivityBody = findViewById(R.id.ll_registeractivity_body);
        mEtRegisteractivityUsername = findViewById(R.id.et_registeractivity_username);
        mEtRegisteractivityPassword1 = findViewById(R.id.et_registeractivity_password1);
        mEtRegisteractivityPassword2 = findViewById(R.id.et_registeractivity_password2);
        mRlRegisteractivityBottom = findViewById(R.id.rl_registeractivity_bottom);

        /**
         * 注册页面能点击的就两个地方，top处返回箭头、注册按钮
         */
        mIvRegisteractivityBack.setOnClickListener(this);

        mBtRegisteractivityRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_registeractivity_back: //返回登录页面
                Intent intent1 = new Intent(this, loginActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.bt_registeractivity_register:    //注册按钮
                //获取用户输入的用户名、密码
                String username = mEtRegisteractivityUsername.getText().toString().trim();
                String password1 = mEtRegisteractivityPassword1.getText().toString().trim();
                String password2 = mEtRegisteractivityPassword2.getText().toString().trim();
                String password = mEtRegisteractivityPassword1.getText().toString().trim();
                //注册验证
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && password1.equals(password2)) {
                {
                        //将用户名和密码加入到数据库中
                        mUserDataBaseOpenHelper.add(username, password);
                        Intent intent2 = new Intent(this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(this,  "注册成功", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}