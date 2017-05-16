package geekypanda.com.qjr.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import geekypanda.com.qjr.Adapter.CategoryAdapter;
import geekypanda.com.qjr.Constant.AIE_C_URL;
import geekypanda.com.qjr.Model.BannerModel;
import geekypanda.com.qjr.Model.CategoryModel;
import geekypanda.com.qjr.R;
import geekypanda.com.qjr.Setting.AIE_U_REST_API;

public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,RatingBar.OnRatingBarChangeListener {

    private SliderLayout mDemoSlider;
    ImageView tbiv,tbivshare;
    RecyclerView recyclerView;
    ScrollView sv;
    ArrayList<CategoryModel> categorymodels = new ArrayList<>();
    ArrayList<BannerModel> bannerModels = new ArrayList<>();
    String[] menu;
    DrawerLayout dLayout;
    ListView dList;
    ArrayAdapter<String> adapter;
    static float txtRatingValue;
    AIE_C_URL url;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

       // setSupportActionBar(toolbar);
        aie_dcl_layout();
        aie_dcl_layout_variables();

        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        //.addTestDevice("2E0FEF051360B560DBE272FA97E84B44")
        mAdView.loadAd(adRequest);


        downloadBannner();
        downloadCategory();


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.menu, this.getTheme());
        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void setHomePage() {

        mDemoSlider = (SliderLayout)findViewById(R.id.slider);


         for (int j = 0;j<bannerModels.size();j++){

             DefaultSliderView textSliderView = new DefaultSliderView(this);
             // initialize a SliderLayout

             textSliderView.image(url.getHostname()+bannerModels.get(j).getBig_image())
                     .setScaleType(BaseSliderView.ScaleType.Fit)
                     .setOnSliderClickListener(this);


             mDemoSlider.addSlider(textSliderView);
         }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
          mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }


    private void aie_dcl_layout() {

       /* tbivshare = (ImageView)findViewById(R.id.topivshare);
        tbivshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=piggypanda.com.chennaimame");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });*/
        ScrollView scvMain = (ScrollView) findViewById(R.id.scrollView);
        scvMain.fullScroll(ScrollView.FOCUS_UP);
        scvMain.smoothScrollTo(0, 0);



    }

    private void aie_dcl_layout_variables() {

        recyclerView = (RecyclerView) findViewById(R.id.category_ist);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setFocusable(false);


    }

    private void downloadBannner() {


        AIE_C_URL urlConstants = new AIE_C_URL();
        String url = urlConstants.getHostname() +  "/api/v1/banner";


        new AIE_U_REST_API().get(url, new AIE_U_REST_API.OnRestCallback() {

            @Override
            public void onRestResponse(boolean success, JSONObject jsonObject) {
                if (success) {


                    try {
                        if (jsonObject.has("code")) {

                            if (jsonObject.getInt("code") == 0) {

                                fetchBanner(jsonObject);

                            }
                        } else {

                            Toast.makeText(MainActivity.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                            finish();

                        }

                    } catch (JSONException e) {


                        Toast.makeText(MainActivity.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                        finish();
                        e.printStackTrace();
                    }
                }


            }
        }, Request.Priority.HIGH);
    }

    private void fetchBanner(JSONObject object) {
        bannerModels.clear();
        Gson gson = new Gson();
        BannerModel bannerModel;

        try {
            JSONArray categoryJsonArray = object.getJSONArray("data");

            for(int i = 0; i < categoryJsonArray.length() ; i++){

                bannerModel = gson.fromJson(categoryJsonArray.get(i).toString(), BannerModel.class);
                bannerModels.add(bannerModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setHomePage();

    }

    private void downloadCategory() {


        AIE_C_URL urlConstants = new AIE_C_URL();
        String url = urlConstants.getHostname() +  "/api/v1/category";


        new AIE_U_REST_API().get(url, new AIE_U_REST_API.OnRestCallback() {

            @Override
            public void onRestResponse(boolean success, JSONObject jsonObject) {
                if (success) {


                    try {
                        if (jsonObject.has("code")) {

                            if (jsonObject.getInt("code") == 0) {

                                fetchCategory(jsonObject);

                            }
                        } else {

                            Toast.makeText(MainActivity.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                            finish();

                        }

                    } catch (JSONException e) {


                        Toast.makeText(MainActivity.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                        finish();
                        e.printStackTrace();
                    }
                }


            }
        }, Request.Priority.HIGH);
    }

    private void fetchCategory(JSONObject object) {
        categorymodels.clear();
        Gson gson = new Gson();
        CategoryModel categorymodel;

        try {
            JSONArray categoryJsonArray = object.getJSONArray("data");

            for(int i = 0; i < categoryJsonArray.length() ; i++){

                categorymodel = gson.fromJson(categoryJsonArray.get(i).toString(), CategoryModel.class);
                categorymodels.add(categorymodel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initAdapter();

    }



    private void initAdapter() {

        CategoryAdapter categoryAdapter = new CategoryAdapter(this,categorymodels);
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.category) {
            Intent i = new Intent(this,Main_category.class);
            startActivity(i);

            // Handle the camera action
        } else if (id == R.id.author) {
            Intent i = new Intent(MainActivity.this,Main_author.class);
            startActivity(i);

        }
        /*else if (id == R.id.bookmarks) {

        }*/
        else if (id == R.id.top100) {
            Intent i = new Intent(this,TopQuotes.class);
            startActivity(i);
        }else if (id == R.id.suggestions) {
             Intent i = new Intent(this,Suggestions.class);
            startActivity(i);
        }
        else if (id == R.id.rateus) {
            Uri uri = Uri.parse("market://details?id=piggypanda.com.chennaimame");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=piggypanda.com.chennaimame")));
            }


        }
        else if (id == R.id.share) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=piggypanda.com.chennaimame");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }
        else if (id == R.id.about) {
            Intent i = new Intent(this,AboutUs.class);
            startActivity(i);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        txtRatingValue = rating;
    }
}
