package ir.allahverdi.session14webservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import ir.allahverdi.session14webservice.adapter.ProductAdapterGridView;
import ir.allahverdi.session14webservice.adapter.SliderAdapter;
import ir.allahverdi.session14webservice.entity.Product;
import ir.allahverdi.session14webservice.entity.SliderItem;
import ir.allahverdi.session14webservice.service.DivarApi;
import ir.allahverdi.session14webservice.ui.DetailsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    //ListView listView;
    GridView gridView;
    ProductAdapterGridView adapter;
    EditText et_search;

    SliderView sliderView;
    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setupToolbar();
        setupNavigationView();
        setupListView();
        setupBanner();

    }

    private void setupBanner() {
        sliderView = findViewById(R.id.imageSlider);
        sliderAdapter = new SliderAdapter(this);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });

        renewItems(sliderView);
        removeLastItem(sliderView);
        addNewItem(sliderView);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
    public void renewItems(View view) {
    List<SliderItem> sliderItemList = new ArrayList<>();
    //dummy data
    for (int i = 0; i < 3; i++) {
        SliderItem sliderItem = new SliderItem();
        //sliderItem.setDescription("Slider Item " + i);
        if (i % 2 == 0) {
            sliderItem.setImageUrl("https://www.digikala.com/static/files/29e1bc2b.jpg");
        } else {
            sliderItem.setImageUrl("https://www.digikala.com/static/files/9280ec38.jpg");
        }
        sliderItemList.add(sliderItem);
    }
    sliderAdapter.renewItems(sliderItemList);
}

    public void removeLastItem(View view) {
        if (sliderAdapter.getCount() - 1 >= 0)
            sliderAdapter.deleteItem(sliderAdapter.getCount() - 1);
    }

    public void addNewItem(View view) {
        SliderItem sliderItem = new SliderItem();
        //sliderItem.setDescription("Slider Item Added Manually");
        sliderItem.setImageUrl("https://www.digikala.com/static/files/9e73b970.jpg");
        sliderAdapter.addItem(sliderItem);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    private void setupToolbar() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_MainActivity);
        Toolbar toolbar = findViewById(R.id.toolbar_home);
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

    private void setupNavigationView() {
        NavigationView navigationView = findViewById(R.id.nav_view_details);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_menu_register:
                        Toast.makeText(MainActivity.this, "انتقال به صفحه ثبت نام", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_profile:
                        Toast.makeText(MainActivity.this, "انتقال به صفحه پروفایل کاربری", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_orderTracking:
                        Toast.makeText(MainActivity.this, "انتقال به صفحه پیگیری سفارش", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_reportBug:
                        Toast.makeText(MainActivity.this, "انتقال به صفحه گزارش مشکل", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_help:
                        Toast.makeText(MainActivity.this, "انتقال به صفحه راهنما", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_contactUs:
                        Toast.makeText(MainActivity.this, "انتقال به صفحه تماس با ما", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    private void setupListView() {
        // 1) make instance of Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DivarApi.BASE_CLASS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 2) make Api
        DivarApi api = retrofit.create(DivarApi.class);

        // 3) make Request
        Call<List<Product>> request = api.getAllProducts();

        // 4) callback
        request.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> request, Response<List<Product>> response) {
                Log.e(TAG, "onResponse: " + response.message());
                adapter = new ProductAdapterGridView(MainActivity.this, (ArrayList<Product>) response.body());
                gridView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

    private void init() {
        //listView = findViewById(R.id.listview);
        gridView = findViewById(R.id.gridView);
        et_search = findViewById(R.id.et_search);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Product product = (Product) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(Const.INTENT_PARAMETERS_ID, product.getId());
                intent.putExtra(Const.INTENT_PARAMETERS_IMAGE, product.getImageId());
                intent.putExtra(Const.INTENT_PARAMETERS_TITTLE , product.getTittle());
                intent.putExtra(Const.INTENT_PARAMETERS_PRICE , product.getPrice());

                startActivity(intent);

            }
        });
    }
}