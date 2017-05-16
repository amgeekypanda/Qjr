package geekypanda.com.qjr.Activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

import geekypanda.com.qjr.Adapter.CategoryTabAdapter;
import geekypanda.com.qjr.Constant.AIE_APP_CONSTANTS;
import geekypanda.com.qjr.Model.CategoryModel;
import geekypanda.com.qjr.R;

/**
 * Created by Prasath on 3/28/2017.
 */
public class CategoryTab extends FragmentActivity{


    private CategoryTabAdapter categoryTabAdapter;
    protected PagerSlidingTabStrip pagerSlidingTabStrip;
    // public static Constant constant;
    private ImageView ivback;
    private LinearLayout mTabsLinearLayout;
    private static int position;
    private static String positionname;
    static Integer position_tab;
    ArrayList<CategoryModel> categorymodel;
    protected Handler handler;
    protected ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cat_tab_layout);
       // position = (int) getIntent().getExtras().get("position");
        positionname = (String) getIntent().getExtras().get("positionname");
        categorymodel = (ArrayList<CategoryModel>) getIntent().getExtras().get("placemodels");
        ivback = (ImageView)findViewById(R.id.tbback);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Initialize the ViewPager and set an adapter
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "font/OpenSans-Light.ttf");
        pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.cattab);
        pagerSlidingTabStrip.setTypeface(typeFace, 0);
        setUpTabStrip();



        viewPager = (ViewPager) findViewById(R.id.catvp);

                for (int z = 0; z < categorymodel.size(); z++) {
                    if (categorymodel.get(z).getName().equals(positionname)) {
                        position_tab = z;

                    }
                }

                viewPager.setAdapter(new CategoryTabAdapter(getSupportFragmentManager(), categorymodel, CategoryTab.this));
                viewPager.setCurrentItem(position_tab);





        pagerSlidingTabStrip.setViewPager(viewPager);

        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mTabsLinearLayout.getChildCount(); i++) {
                    TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);

                    if (i == position) {
                        tv.setTextColor(getResources().getColor(R.color.black));
                        Prefs.putInt(AIE_APP_CONSTANTS.positioncat, position);
                        Prefs.putString(AIE_APP_CONSTANTS.positionname, categorymodel.get(position).getName());
                    } else {
                        tv.setTextColor(getResources().getColor(R.color.grey));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




    public void setUpTabStrip() {

        //your other customizations related to tab strip...blahblah
        // Set first tab selected
        mTabsLinearLayout = ((LinearLayout) pagerSlidingTabStrip.getChildAt(0));
        for (int i = 0; i < mTabsLinearLayout.getChildCount(); i++) {
            TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);

            if (i == 0) {
                tv.setTextColor(Color.WHITE);
               // Prefs.putInt(AIE_APP_CONSTANTS.positioncat, position);
            } else {
                tv.setTextColor(getResources().getColor(R.color.lightgrey));
            }
        }
    }
}
