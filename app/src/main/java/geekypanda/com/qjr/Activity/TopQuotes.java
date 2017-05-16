package geekypanda.com.qjr.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import geekypanda.com.qjr.Adapter.TopQuoteAdapter;
import geekypanda.com.qjr.Constant.AIE_C_URL;
import geekypanda.com.qjr.Model.BannerModel;
import geekypanda.com.qjr.Model.FCategoryQuoteModel;
import geekypanda.com.qjr.Model.Top100Model;
import geekypanda.com.qjr.R;
import geekypanda.com.qjr.Setting.AIE_U_REST_API;

/**
 * Created by Prasath on 3/28/2017.
 */
public class TopQuotes extends FragmentActivity{

    ImageView topback;
    RecyclerView recyclerView;
    InterstitialAd mInterstitialAd;
    private AdView mAdView;
    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<Top100Model>top100Models = new ArrayList<>();
    ArrayList<Integer> stringArray = new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.topquoterecycler);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        //.addTestDevice("2E0FEF051360B560DBE272FA97E84B44")
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {

                mInterstitialAd.show();
            }
        });
        mInterstitialAd.loadAd(adRequest);


        setVariables();
        downloadTop100();




    }

    private void setVariables() {

        recyclerView = (RecyclerView)findViewById(R.id.topquoterecycler);
        topback = (ImageView)findViewById(R.id.topback);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(TopQuotes.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        topback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }

    private void downloadTop100() {


        AIE_C_URL urlConstants = new AIE_C_URL();
        String url = urlConstants.getHostname() +  "/api/v1/topquote";


        new AIE_U_REST_API().get(url, new AIE_U_REST_API.OnRestCallback() {

            @Override
            public void onRestResponse(boolean success, JSONObject jsonObject) {
                if (success) {


                    try {
                        if (jsonObject.has("code")) {

                            if (jsonObject.getInt("code") == 0) {

                                fetchTop100(jsonObject);

                            }
                        } else {

                            Toast.makeText(TopQuotes.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                            finish();

                        }

                    } catch (JSONException e) {


                        Toast.makeText(TopQuotes.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                        finish();
                        e.printStackTrace();
                    }
                }


            }
        }, Request.Priority.HIGH);
    }

    private void fetchTop100(JSONObject object) {
        top100Models.clear();
        Gson gson = new Gson();
        Top100Model top100Model;

        try {
            JSONArray categoryJsonArray = object.getJSONArray("data");

            for(int i = 0; i < categoryJsonArray.length() ; i++){

                top100Model = gson.fromJson(categoryJsonArray.get(i).toString(), Top100Model.class);
                top100Models.add(
                        top100Model
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initAdapter();

    }

    private void initAdapter() {
        TopQuoteAdapter categoryQuoteAdapter = new TopQuoteAdapter(this,top100Models);
        recyclerView.setAdapter(categoryQuoteAdapter);

    }




}
