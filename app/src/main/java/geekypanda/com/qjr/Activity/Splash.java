package geekypanda.com.qjr.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import geekypanda.com.qjr.Constant.AIE_APP_CONSTANTS;
import geekypanda.com.qjr.Constant.AIE_C_URL;
import geekypanda.com.qjr.DBQuery.AIE_D_CATEGORY_QRY;
import geekypanda.com.qjr.Model.CategoryModel;
import geekypanda.com.qjr.R;
/*import geekypanda.com.qjr.Setting.AIE_U_REST_API;*/
import geekypanda.com.qjr.Setting.AIE_U_REST_API;
import geekypanda.com.qjr.Setting.AIE_U_Singleton;

/**
 * Created by Prasath on 3/29/2017.
 */
public class Splash extends Activity {

    protected List<CategoryModel> aie_m_categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreenlay);
       /*Prefs.putInt(AIE_APP_CONSTANTS.version,0);
        fetch_details();*/
       // getVersion();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent home_page = new Intent(Splash.this, MainActivity.class);
                startActivity(home_page);
                finish();
            }
        }, 5000);
    }


    private void getVersion() {

        AIE_C_URL urlConstants = new AIE_C_URL();
        String url = urlConstants.getHostname() + "/api/v1/version";


        new AIE_U_REST_API().get(url, new AIE_U_REST_API.OnRestCallback() {

            @Override
            public void onRestResponse(boolean success, JSONObject jsonObject) {
                if (success) {

                    if (jsonObject.has("code")) {
                        int code = 0;
                        try {
                            if (jsonObject.has("code")) {
                                if (jsonObject.getInt("code") == 0) {

                                    if (Prefs.getInt(AIE_APP_CONSTANTS.version, 0) != jsonObject.getJSONArray("data").getJSONObject(0).getInt("version")) {
                                        Prefs.putInt(AIE_APP_CONSTANTS.version, jsonObject.getJSONArray("data").getJSONObject(0).getInt("version"));
                                        Prefs.putBoolean(AIE_APP_CONSTANTS.version_changed, true);

                                        System.out.println("versioncheck"+ jsonObject.getJSONArray("data").getJSONObject(0).getInt("version"));
                                        DownloadCategory();
                                       /* DownloadProduct();
                                        DownloadTax();
                                        DownloadDeliveryArea();
                                        DownloadStore_Timing();*/

                                    } else {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {

                                                AIE_D_CATEGORY_QRY aie_d_product_category_qry = new AIE_D_CATEGORY_QRY();
                                                aie_m_categories = aie_d_product_category_qry.getCategory(Splash.this);
                                                System.out.println("categorycheck"+ aie_m_categories.size());
                                                System.out.println("categorycheck1"+ aie_m_categories.get(0).getId());
                                                System.out.println("categorycheck2"+ aie_m_categories.get(0).getName());
                                                System.out.println("categorycheck3"+ aie_m_categories.get(0).getBig_image());
                                                System.out.println("categorycheck4"+ aie_m_categories.get(0).getGroup_value());
                                                System.out.println("categorycheck5"+ aie_m_categories.get(0).getAvailable());
                                               /* Intent home_page = new Intent(Splash.this, MainActivity.class);
                                                startActivity(home_page);
                                                finish();*/
                                            }
                                        }, Long.parseLong("1000"));
                                    }
                                }
                            } else {

                                Toast.makeText(Splash.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                                finish();

                            }

                        } catch (JSONException e) {


                            Toast.makeText(Splash.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                            finish();
                            e.printStackTrace();
                        }
                    }

                } else {
                    Toast.makeText(Splash.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        }, Request.Priority.HIGH);

    }

    private void DownloadCategory() {


        AIE_C_URL urlConstants = new AIE_C_URL();
        String url = urlConstants.getHostname() +  "/api/v1/category";


        new AIE_U_REST_API().get(url, new AIE_U_REST_API.OnRestCallback() {

            @Override
            public void onRestResponse(boolean success, JSONObject jsonObject) {
                if (success) {


                    try {
                        if (jsonObject.has("code")) {

                            if (jsonObject.getInt("code") == 0) {

                                insertcategoryintoDB(jsonObject);

                            }
                        } else {

                            Toast.makeText(Splash.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                            finish();

                        }

                    } catch (JSONException e) {


                        Toast.makeText(Splash.this, "Technical error, please retry", Toast.LENGTH_LONG).show();
                        finish();
                        e.printStackTrace();
                    }
                }


            }
        }, Request.Priority.HIGH);
    }

    private void insertcategoryintoDB(JSONObject jsonObject) {
        AIE_D_CATEGORY_QRY aie_d_cat_qry = new AIE_D_CATEGORY_QRY();
        aie_d_cat_qry.deleteAllCategory(this);

        try {
            JSONArray bannerJsonArray = jsonObject.getJSONArray("data");
            ArrayList<CategoryModel> m_bbq_branch_slots = new ArrayList<>();

            for (int i = 0; i < bannerJsonArray.length(); i++) {
                Gson gson = new Gson();
                CategoryModel m_bbq_branch_slot = gson.fromJson(bannerJsonArray.get(i).toString(), CategoryModel.class);
                m_bbq_branch_slots.add(m_bbq_branch_slot);

            }
            aie_d_cat_qry.insertCategory(m_bbq_branch_slots, this);
            System.out.println("category inserted successfully");


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
