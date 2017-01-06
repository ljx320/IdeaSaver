package top.a5focus.www.ideasaver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.a5focus.www.ideasaver.util.HttpUtil;

public class SplashActivity extends AppCompatActivity {
    private String SDCardRoot;
    private ImageView welcomeImage;
    private ProgressDialog progressDialog;

    private  File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        welcomeImage = (ImageView) findViewById(R.id.welcome_img);
        SDCardRoot = getApplicationContext().getFilesDir().getAbsolutePath()+ File.separator+"hz.jpg";

        File file = new File(SDCardRoot);
        if (file.exists()) {
            Glide.with(SplashActivity.this).load(SDCardRoot).into(welcomeImage);
            GoToMainActivity();
        } else {

            progressDialog = new ProgressDialog(SplashActivity.this);
            progressDialog.setTitle("绚丽的欢迎界面就要出来了");
            progressDialog.setMessage("下载中...");
            progressDialog.setCancelable(false);
            progressDialog.show();


            HttpUtil.sendOkHttpRequest("Files?fileName=hz1.jpg", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SplashActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    InputStream inputStream = response.body().byteStream();

                    FileOutputStream fileOutputStream = null;

                    try {
                        fileOutputStream = new FileOutputStream(new File(SDCardRoot));
                        byte[] buffer = new byte[2048];
                        int len = 0;

                        while ((len = inputStream.read(buffer)) != -1) {

                            fileOutputStream.write(buffer, 0, len);
                        }

                        fileOutputStream.flush();


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(SplashActivity.this).load(SDCardRoot).into(welcomeImage);
                            }
                        });

                        progressDialog.dismiss();

                        GoToMainActivity();

                    } catch (IOException e) {

                        Log.i("downfile", "IOException ");
                        e.printStackTrace();


                    }


                }
            });

        }


    }





    private void GoToMainActivity() {

        final Intent it = new Intent(this, MainActivity.class);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(it);
            }
        };

        timer.schedule(task, 1000 * 3);

    }
}
