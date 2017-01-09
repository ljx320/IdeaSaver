package top.a5focus.www.ideasaver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.a5focus.www.ideasaver.adapter.IdeaRecyclerAdapter;
import top.a5focus.www.ideasaver.db.Idea;
import top.a5focus.www.ideasaver.util.HttpUtil;

public class IdeasActivity extends AppCompatActivity  {
    private DrawerLayout ideaDrawerLayout;
    private Toolbar ideaToolbar;
    private NavigationView ideaNav;
    private SharedPreferences pref;
    private TextView userTelText;
    private TextView userNameText;
    private String userID;
private ProgressBar ideas_Progressbar;
    private FloatingActionButton addIdeasFAB;
    private RecyclerView recyclerideas;
    private SwipeRefreshLayout mIdeaSwipeRefreshLayout;

    private  List<Idea> mIdeas=new ArrayList<Idea>();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            ideaDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.idea_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.idea_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ideaDrawerLayout = (DrawerLayout) findViewById(R.id.idea_drawerLayout);
        ideaNav = (NavigationView) findViewById(R.id.idea_nav);
        addIdeasFAB=(FloatingActionButton)findViewById(R.id.fab_addidea);
        recyclerideas=(RecyclerView)findViewById(R.id.recycler_ideas);
        mIdeaSwipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.idea_swipeRefrshLayout);
        ideas_Progressbar=(ProgressBar)findViewById(R.id.ideas_Progressbar);


        mIdeaSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestIdeas();
                mIdeaSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mIdeaSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_orange_light,android.R.color.holo_green_light);


        View headerlayout = ideaNav.getHeaderView(0);

        userTelText = (TextView) headerlayout.findViewById(R.id.nav_user_tel);
        userNameText = (TextView) headerlayout.findViewById(R.id.nav_user_username);

        pref = getSharedPreferences("saverData", MODE_PRIVATE);
        String username = pref.getString("username", "");
        String tel = pref.getString("tel", "");

        userID = pref.getString("userid", "");

        userNameText.setText(username);
        userTelText.setText(tel);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_list);

        }

        ideaNav.setCheckedItem(R.id.idea_lsit_menu);
        ideaNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.project_lsit_menu:
                        Intent intent=new Intent(IdeasActivity.this,ProjectsActivity.class);
                        startActivity(intent);
                        ideaDrawerLayout.closeDrawers();
                        break;
                    default:
                        ideaDrawerLayout.closeDrawers();
                        break;
                }


                return true;
            }
        });

        addIdeasFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IdeasActivity.this,AddIdeaActivity.class);
                startActivity(intent);
            }
        });

        requestIdeas();
    }

    private void requestIdeas() {

        ideas_Progressbar.setVisibility(View.VISIBLE);


        HttpUtil.sendOkHttpRequest("Idea?userid=" + userID + "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ideas_Progressbar.setVisibility(View.GONE);
                        Toast.makeText(IdeasActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {



                if (response.isSuccessful()) {
                    String responseResult = response.body().string();

                    Gson gson = new Gson();
                    mIdeas = gson.fromJson(responseResult, new TypeToken<List<Idea>>() {
                    }.getType());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ideas_Progressbar.setVisibility(View.GONE);

                            LinearLayoutManager layoutManager=new LinearLayoutManager(IdeasActivity.this);
                            recyclerideas.setLayoutManager(layoutManager);
                            IdeaRecyclerAdapter adapter=new IdeaRecyclerAdapter(mIdeas);
                            recyclerideas.setAdapter(adapter);


                        }
                    });



                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ideas_Progressbar.setVisibility(View.GONE);

                            Toast.makeText(IdeasActivity.this, "查无数据..", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

    }


}
