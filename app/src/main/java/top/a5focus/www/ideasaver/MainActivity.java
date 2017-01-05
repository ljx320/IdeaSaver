package top.a5focus.www.ideasaver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.a5focus.www.ideasaver.util.HttpUtil;

public class MainActivity extends AppCompatActivity {


    private Button loginButton;
    private Button registerButton;

    private EditText telText;
    private EditText psswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle("用户登陆");
        setSupportActionBar(toolbar);


        registerButton = (Button) findViewById(R.id.main_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);


            }
        });


        telText = (EditText) findViewById(R.id.tel_text);
        psswordText = (EditText) findViewById(R.id.password_text);

        loginButton = (Button) findViewById(R.id.main_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String telphone=telText.getText().toString();
                String password=psswordText.getText().toString();

                if ("".equals(telphone)) {

                    Toast.makeText(MainActivity.this, "请输入电话号码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ("".equals(password)) {
                    Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                String address="User?tel="+telphone+"&password="+password+"";

                HttpUtil.sendOkHttpRequest(address, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(MainActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){

                            String responseData=response.body().string();


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "匹配成功", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }else
                        {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                                }
                            });


                        }



                    }
                });
            }
        });
    }
}
