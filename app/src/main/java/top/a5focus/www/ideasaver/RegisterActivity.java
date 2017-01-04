package top.a5focus.www.ideasaver;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private TextView showdate;
    private Button setdate;
    private  int myear;
    private int mmonth;
    private int mday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar=(Toolbar)findViewById(R.id.register_toolbar);
        toolbar.setTitle("用户注册");
        setSupportActionBar(toolbar);

        showdate=(TextView)findViewById(R.id.show_date);
        setdate=(Button)findViewById(R.id.set_date);

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
