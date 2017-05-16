package geekypanda.com.qjr.Adapter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import geekypanda.com.qjr.Activity.CategoryTab;
import geekypanda.com.qjr.Activity.MainActivity;
import geekypanda.com.qjr.Constant.AIE_C_URL;
import geekypanda.com.qjr.Fragment.QuoteFrag;
import geekypanda.com.qjr.Model.CategoryModel;
import geekypanda.com.qjr.Model.FCategoryQuoteModel;
import geekypanda.com.qjr.R;
import geekypanda.com.qjr.Setting.AIE_U_REST_API;

/**
 * Created by Prasath on 3/28/2017.
 */
public class CategoryTabAdapter extends FragmentPagerAdapter  {

    ArrayList<CategoryModel> categorymodels  = new ArrayList<>();
    ArrayList<FCategoryQuoteModel> fCategoryQuoteModels = new ArrayList<>();
    ArrayList<FCategoryQuoteModel> finalCategoryQuoteModels = new ArrayList<>();
    ArrayList<CategoryModel> categorymodel;
    private final CategoryTab categoryTab;
    FragmentManager fragmentManager;
    ArrayList<Integer> stringArray = new ArrayList<>();
    ArrayList<String> copytext = new ArrayList<>();




  //  private String[] tabs = { "Ability","Achievement","Attitude","Brave","Inspirational","Motivational" };
   public int PAGE_COUNT ;
    //= tabs.length;


    public CategoryTabAdapter(FragmentManager fm, ArrayList<CategoryModel> categorymodel, CategoryTab categoryTab) {
        super(fm);
        fragmentManager = fm;
        this.categorymodels = categorymodel;
        this.categoryTab = categoryTab;
       // downloadCategoryToQuote();


    }




    @Override
    public Fragment getItem(int position) {

                QuoteFrag quoteFrag = new QuoteFrag();
                String categoryname = Prefs.getString("positionname","");
                Bundle bundle = new Bundle();
                bundle.putString("stringArray",categorymodels.get(position).getName());
                quoteFrag.setArguments(bundle);
                return  quoteFrag;

    }

    @Override
    public int getCount() {
        PAGE_COUNT = categorymodels.size();
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return categorymodels.get(position).getName();
    }


}
