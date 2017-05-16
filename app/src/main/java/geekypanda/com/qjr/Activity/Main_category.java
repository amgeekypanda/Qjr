package geekypanda.com.qjr.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import geekypanda.com.qjr.Adapter.AuthorHeaderAdapter;
import geekypanda.com.qjr.Adapter.CategoryHeaderAdapter;
import geekypanda.com.qjr.Constant.AIE_C_URL;
import geekypanda.com.qjr.Model.AuthorGroupModel;
import geekypanda.com.qjr.Model.CategoryGroupModel;
import geekypanda.com.qjr.Model.CategoryModel;
import geekypanda.com.qjr.Model.FAuthorQuoteModel;
import geekypanda.com.qjr.Model.FCategoryQuoteModel;
import geekypanda.com.qjr.R;
import geekypanda.com.qjr.Setting.AIE_U_REST_API;

/**
 * Created by Prasath on 3/28/2017.
 */
public class Main_category extends FragmentActivity {

    ImageView back;
    RecyclerView recyclerView;
    ArrayList<CategoryModel> categorymodels = new ArrayList<>();
    EditText categorysearch;


    ListView listView;
    ImageView topback;
    private CategoryHeaderAdapter mAdapter;

    ArrayList<CategoryGroupModel>categoryGroupModels = new ArrayList<CategoryGroupModel>();
    ArrayList<FCategoryQuoteModel>fCategoryQuoteModels = new ArrayList<FCategoryQuoteModel>();

   /* String[] a = {"a","b","m"};
    String[]b = {"Ability","Achievement","Attitude","Brave","Inspirational","Motivational"};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.maincat_recylerview);
        listView = (ListView) findViewById(R.id.list);
        topback = (ImageView)findViewById(R.id.topback);

        topback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        categorysearch = (EditText)findViewById(R.id.categorysearch);
        categorysearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                return false;
            }
        });

       /* getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );*/

        downloadCategoryGroup();


                categorysearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        if (s.toString().length() >= 1) {

                               mAdapter = new CategoryHeaderAdapter(Main_category.this,categorymodels);
                            for (int j = 0; j < categorymodels.size(); j++) {
                                if (categorymodels.get(j).getName().toString().toLowerCase().contains(s.toString())|| categorymodels.get(j).getName().toString().toUpperCase().contains(s.toString())) {
                                    mAdapter.addItem(categorymodels.get(j).getName().toString());
                                }
                            }

                            listView.setAdapter(mAdapter);

                        } else {
                               initAdapter();

                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });




    }


    private void downloadCategoryGroup() {


        AIE_C_URL urlConstants = new AIE_C_URL();
        String url = urlConstants.getHostname() +  "/api/v1/categorygroup";


        new AIE_U_REST_API().get(url, new AIE_U_REST_API.OnRestCallback() {

            @Override
            public void onRestResponse(boolean success, JSONObject jsonObject) {
                if (success) {


                    try {
                        if (jsonObject.has("code")) {

                            if (jsonObject.getInt("code") == 0) {

                                fetchCategoryGroup(jsonObject);

                            }
                        } else {

                            Toast.makeText(Main_category.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                            finish();

                        }

                    } catch (JSONException e) {


                        Toast.makeText(Main_category.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                        finish();
                        e.printStackTrace();
                    }
                }


            }
        }, Request.Priority.HIGH);
    }

    private void fetchCategoryGroup(JSONObject object) {
        categoryGroupModels.clear();
        Gson gson = new Gson();
        CategoryGroupModel categoryGroupModel;

        try {
            JSONArray categoryJsonArray = object.getJSONArray("data");

            for(int i = 0; i < categoryJsonArray.length() ; i++){

                categoryGroupModel = gson.fromJson(categoryJsonArray.get(i).toString(), CategoryGroupModel.class);
                categoryGroupModels.add(categoryGroupModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        downloadCategory();

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

                            Toast.makeText(Main_category.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                            finish();

                        }

                    } catch (JSONException e) {


                        Toast.makeText(Main_category.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
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
        CategoryModel fCategoryModel;

        try {
            JSONArray categoryJsonArray = object.getJSONArray("data");

            for(int i = 0; i < categoryJsonArray.length() ; i++){

                fCategoryModel = gson.fromJson(categoryJsonArray.get(i).toString(), CategoryModel.class);
                categorymodels.add(fCategoryModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initAdapter();

    }

    private void initAdapter() {

        mAdapter = new CategoryHeaderAdapter(this,categorymodels);

        for(int i=0;i<categoryGroupModels.size();i++) {
            mAdapter.addSectionHeaderItem(categoryGroupModels.get(i).getGroup_value().toString());
            for (int j = 0; j < categorymodels.size(); j++) {
                if (categoryGroupModels.get(i).getGroup_value().equalsIgnoreCase(categorymodels.get(j).getGroup_value().substring(0, 1))){
                    mAdapter.addItem(categorymodels.get(j).getName().toString());
                }
            }
        }
        // recyclerView.setAdapter(mAdapter);
        //  ListView list = new ListView(this);
        listView.setAdapter(mAdapter);
    }
}
