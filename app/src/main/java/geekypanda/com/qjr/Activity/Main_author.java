package geekypanda.com.qjr.Activity;

import android.app.Activity;
import android.os.Bundle;
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
import geekypanda.com.qjr.Constant.AIE_C_URL;
import geekypanda.com.qjr.Model.AuthorGroupModel;
import geekypanda.com.qjr.Model.AuthorModel;
import geekypanda.com.qjr.Model.CategoryModel;
import geekypanda.com.qjr.Model.FAuthorQuoteModel;
import geekypanda.com.qjr.R;
import geekypanda.com.qjr.Setting.AIE_U_REST_API;

/**
 * Created by Prasath on 4/5/2017.
 */
public class Main_author extends Activity {
    ListView listView;
    ImageView topback;
    EditText authorsearch;
    private AuthorHeaderAdapter mAdapter;

    ArrayList<AuthorGroupModel>authorGroupModels = new ArrayList<AuthorGroupModel>();
    ArrayList<FAuthorQuoteModel>fAuthorQuoteModels = new ArrayList<FAuthorQuoteModel>();
    ArrayList<AuthorModel>AuthorModels = new ArrayList<AuthorModel>();
  //  String[] a = {"a","b","c","d"};
  //  String[]b = {"A.P.J.Abdul Kalam","Alan Watts","Albert Camus","Anne Frank","Barrack Obama","Bill Gates","Bob Marley","Cat","Cow","Dog","Deer"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_author);

        listView = (ListView) findViewById(R.id.list);
        topback = (ImageView) findViewById(R.id.topback);
        authorsearch = (EditText) findViewById(R.id.authorsearch);
        authorsearch.setOnTouchListener(new View.OnTouchListener() {
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

        downloadAuthorGroup();



                authorsearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.toString().length() >= 1) {


                            mAdapter = new AuthorHeaderAdapter(Main_author.this,AuthorModels);

                                for (int j = 0; j < authorGroupModels.size(); j++) {

                                      if (AuthorModels.get(j).getName().toString().toLowerCase().contains(s.toString())|| AuthorModels.get(j).getName().toString().toUpperCase().contains(s.toString())){
                                        mAdapter.addItem(AuthorModels.get(j).getName().toString());
                                    }
                                }


                           /* mAdapter = new AuthorHeaderAdapter(Main_author.this,AuthorModels);
                            for (int j = 0; j < fAuthorQuoteModels.size(); j++) {
                                if (fAuthorQuoteModels.get(j).toString().toLowerCase().contains(s.toString())|| fAuthorQuoteModels.get(j).toString().toUpperCase().contains(s.toString())) {
                                    mAdapter.addItem(fAuthorQuoteModels.get(j).toString());
                                }
                            }*/
                            listView.setAdapter(mAdapter);
                        } else

                        {
                            initAdapter();

                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });




        topback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void downloadAuthorGroup() {


        AIE_C_URL urlConstants = new AIE_C_URL();
        String url = urlConstants.getHostname() +  "/api/v1/authorgroup";


        new AIE_U_REST_API().get(url, new AIE_U_REST_API.OnRestCallback() {

            @Override
            public void onRestResponse(boolean success, JSONObject jsonObject) {
                if (success) {


                    try {
                        if (jsonObject.has("code")) {

                            if (jsonObject.getInt("code") == 0) {

                                fetchAuthorGroup(jsonObject);

                            }
                        } else {

                            Toast.makeText(Main_author.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                            finish();

                        }

                    } catch (JSONException e) {


                        Toast.makeText(Main_author.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                        finish();
                        e.printStackTrace();
                    }
                }


            }
        }, Request.Priority.HIGH);
    }

    private void fetchAuthorGroup(JSONObject object) {
        authorGroupModels.clear();
        Gson gson = new Gson();
        AuthorGroupModel authorGroupModel;

        try {
            JSONArray categoryJsonArray = object.getJSONArray("data");

            for(int i = 0; i < categoryJsonArray.length() ; i++){

                authorGroupModel = gson.fromJson(categoryJsonArray.get(i).toString(), AuthorGroupModel.class);
                authorGroupModels.add(authorGroupModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        downloadAuthor();

    }

    private void downloadAuthor() {


        AIE_C_URL urlConstants = new AIE_C_URL();
        String url = urlConstants.getHostname() +  "/api/v1/author";


        new AIE_U_REST_API().get(url, new AIE_U_REST_API.OnRestCallback() {

            @Override
            public void onRestResponse(boolean success, JSONObject jsonObject) {
                if (success) {


                    try {
                        if (jsonObject.has("code")) {

                            if (jsonObject.getInt("code") == 0) {

                                fetchAuthor(jsonObject);

                            }
                        } else {

                            Toast.makeText(Main_author.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                            finish();

                        }

                    } catch (JSONException e) {


                        Toast.makeText(Main_author.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                        finish();
                        e.printStackTrace();
                    }
                }


            }
        }, Request.Priority.HIGH);
    }

    private void fetchAuthor(JSONObject object) {
        AuthorModels.clear();
        Gson gson = new Gson();
        AuthorModel AuthorModel;

        try {
            JSONArray categoryJsonArray = object.getJSONArray("data");

            for(int i = 0; i < categoryJsonArray.length() ; i++){

                AuthorModel = gson.fromJson(categoryJsonArray.get(i).toString(), AuthorModel.class);
                AuthorModels.add(AuthorModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initAdapter();

    }

    private void initAdapter() {

        mAdapter = new AuthorHeaderAdapter(this,AuthorModels);

          for(int i=0;i<authorGroupModels.size();i++) {
            mAdapter.addSectionHeaderItem(authorGroupModels.get(i).getGroup_value().toString());
            for (int j = 0; j < authorGroupModels.size(); j++) {
                if (authorGroupModels.get(i).getGroup_value().equalsIgnoreCase(AuthorModels.get(j).getGroup_value().substring(0, 1))){
                    mAdapter.addItem(AuthorModels.get(j).getName().toString());
                }
            }
        }
        // recyclerView.setAdapter(mAdapter);
        //  ListView list = new ListView(this);
        listView.setAdapter(mAdapter);
    }


}
