package ir.allahverdi.session14webservice.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import ir.allahverdi.session14webservice.Const;
import ir.allahverdi.session14webservice.FaNum;
import ir.allahverdi.session14webservice.R;

public class DetailsActivity extends AppCompatActivity {

    TextView tv_tittle_details, tv_id_details, tv_price_details;
    ImageView iv_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();
        setupNavigationView();
        setupToolbar();
        setIntents();

    }

    private void setIntents() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }

        String imgId = intent.getStringExtra(Const.INTENT_PARAMETERS_IMAGE);
        Picasso.get().load(imgId).into(iv_details);

        tv_tittle_details.setText(intent.getStringExtra(Const.INTENT_PARAMETERS_TITTLE));
        tv_id_details.setText(FaNum.convert(String.valueOf(intent.getIntExtra(Const.INTENT_PARAMETERS_ID, 0))));
        tv_price_details.setText(FaNum.convert(String.valueOf(intent.getIntExtra(Const.INTENT_PARAMETERS_PRICE, 0))));
    }

    private void init() {
        tv_tittle_details = findViewById(R.id.tv_tittle_details);
        tv_id_details = findViewById(R.id.tv_id_details);
        tv_price_details = findViewById(R.id.tv_price_details);
        iv_details = findViewById(R.id.iv_details);
    }

    public void onclickSms(View view) {
        String phoneNumber = "sms:+989359172200";
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(phoneNumber));
        intent.putExtra("sms_body", "متن مورد نظر");

        startActivity(intent);
    }

    public void onclickCall(View view) {
        String phoneNumber = "tel:+989359172200";
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(phoneNumber));

        startActivity(intent);
    }

    private void setupNavigationView() {
        NavigationView navigationView = findViewById(R.id.nav_view_details);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_menu_priceChart:
                        Toast.makeText(DetailsActivity.this, "price chart clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_compare:
                        Toast.makeText(DetailsActivity.this, "compare clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_share:
                        Toast.makeText(DetailsActivity.this, "share clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_alert:
                        Toast.makeText(DetailsActivity.this, "alert clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_like:
                        Toast.makeText(DetailsActivity.this, "like clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_cart:
                        Toast.makeText(DetailsActivity.this, "shopping cart clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    private void setupToolbar() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_DetailsActivity);
        Toolbar toolbar = findViewById(R.id.toolbar_details);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, 0, 0);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

    }

}