package top.a5focus.www.ideasaver;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class IdeasActivity extends AppCompatActivity {
private DrawerLayout ideaDrawerLayout;
    private Toolbar ideaToolbar;
    private NavigationView ideaNav;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            ideaDrawerLayout.openDrawer(GravityCompat.START);
            return true;
            }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);

        Toolbar toolbar=(Toolbar)findViewById(R.id.idea_toolbar);
        toolbar.setTitle("创意列表");
        setSupportActionBar(toolbar);

        ideaDrawerLayout=(DrawerLayout)findViewById(R.id.idea_drawerLayout);
        ideaNav=(NavigationView)findViewById(R.id.idea_nav);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu2);

        }

        ideaNav.setCheckedItem(R.id.idea_lsit_menu);
        ideaNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                ideaDrawerLayout.closeDrawers();
                return  true;
            }
        });
    }
}
