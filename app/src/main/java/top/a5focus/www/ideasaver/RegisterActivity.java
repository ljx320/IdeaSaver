package top.a5focus.www.ideasaver;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.a5focus.www.ideasaver.db.User;
import top.a5focus.www.ideasaver.util.GsonUtil;
import top.a5focus.www.ideasaver.util.HttpUtil;

public class RegisterActivity extends AppCompatActivity {
private  boolean registerResult;
    private TextView showdate;
    private Button setdate;
    private  int myear;
    private int mmonth;
    private int mday;

    private EditText telText;
    private RadioButton manRadioButton;
    private  RadioButton womenRadioButton;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private EditText userNameText;
    private  Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar=(Toolbar)findViewById(R.id.register_toolbar);
        toolbar.setTitle("用户注册");

        setSupportActionBar(toolbar);

        showdate=(TextView)findViewById(R.id.show_date);
        setdate=(Button)findViewById(R.id.set_date);

        telText=(EditText)findViewById(R.id.register_Tel);
        manRadioButton=(RadioButton)findViewById(R.id.sex_man);
        passwordText=(EditText)findViewById(R.id.register_password);
        confirmPasswordText=(EditText)findViewById(R.id.register_confirmpassword);
        womenRadioButton=(RadioButton)findViewById(R.id.sex_woman);
        userNameText=(EditText)findViewById(R.id.register_username);
        registerButton=(Button)findViewById(R.id.register_button);


        java.util.Calendar mycalendar= java.util.Calendar.getInstance(Locale.CHINA);

        myear=mycalendar.get(java.util.Calendar.YEAR)-27;
        mmonth=mycalendar.get(java.util.Calendar.MONTH);
        mday=mycalendar.get(java.util.Calendar.DAY_OF_MONTH);

        showdate.setText(myear+"-"+(mmonth+1)+"-"+mday);

        setdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dpd=new DatePickerDialog(RegisterActivity.this,Datelistener,myear,mmonth,mday);
                dpd.show();
            }
        });



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ("".equals(telText.getText().toString())){

                    Toast.makeText(RegisterActivity.this,"请输入电话号码",Toast.LENGTH_SHORT).show();
                    return;

                }

                if (!manRadioButton.isChecked()&&!womenRadioButton.isChecked()){
                    Toast.makeText(RegisterActivity.this,"请选择性别",Toast.LENGTH_SHORT).show();
                    return;
                }

                if ("".equals(userNameText.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"请输入昵称",Toast.LENGTH_SHORT).show();
                    return;
                }

                if ("".equals(passwordText.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;

                }

                if ("".equals(confirmPasswordText.getText().toString())){

                    Toast.makeText(RegisterActivity.this,"请确认密码",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!confirmPasswordText.getText().toString().equals(passwordText.getText().toString())){

                    Log.d("confirmPasswordText", confirmPasswordText.getText().toString());
                    Log.d("passwordText", passwordText.getText().toString());
                    Toast.makeText(RegisterActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }

                final User user=new User();
                user.setAvailable(1);
                user.setBirthday(showdate.getText().toString());
                user.setCreatTime("");
                user.setHeadPortrait("");
                user.setID(0);

                if (manRadioButton.isChecked()){

                    user.setSex("男");
                }
                else
                {

                    user.setSex("女");
                }
                user.setUserName(userNameText.getText().toString());
                user.setPassword(passwordText.getText().toString());
                user.setUpdateCloud(1);
                user.setTel(telText.getText().toString());

                String userJson= GsonUtil.handUserToJson(user);

                HttpUtil.sendPostUseOkhttpRequest("User", userJson, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this,"连接服务器失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        if (response.isSuccessful()){

                            user.save();
                            String responseString=response.body().string();
                            registerResult=GsonUtil.handResponseBoolen(responseString);

                            if (registerResult){

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                });
                            }
                            else
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this,"注册失败，请稍后再试",Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }


                        }
                        else
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this,"注册失败，请稍后再试",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });


            }
        });

    }

    private  DatePickerDialog.OnDateSetListener Datelistener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            myear=year;
            mmonth=month;
            mday=dayOfMonth;
            updateDate();
        }

        private void updateDate(){

            showdate.setText(myear+"-"+(mmonth+1)+"-"+mday);
        }
    };


}
