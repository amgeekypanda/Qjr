package geekypanda.com.qjr.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import geekypanda.com.qjr.Adapter.CategoryQuoteAdapter;
import geekypanda.com.qjr.Constant.AIE_C_URL;
import geekypanda.com.qjr.Model.FCategoryQuoteModel;
import geekypanda.com.qjr.R;
import geekypanda.com.qjr.Setting.AIE_U_REST_API;

/**
 * Created by Dell on 3/28/2017.
 */
public class QuoteFrag extends Fragment implements Serializable {

    ImageView iv1,share,download,copy;
    RecyclerView recyclerView;
    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<String> texts = new ArrayList<>();

    ArrayList<FCategoryQuoteModel> fCategoryQuoteModels = new ArrayList<>();
    ArrayList<FCategoryQuoteModel> finalCategoryQuoteModels = new ArrayList<>();
    String categoryname;
    private AdView mAdView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.quotefragrecycler, container, false);
        setVariables(rootView);
        downloadCategoryToQuote();

        mAdView = (AdView)rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
      //  .addTestDevice("2E0FEF051360B560DBE272FA97E84B44")
        mAdView.loadAd(adRequest);

        return  rootView;
    }

    private void setVariables(View rootView) {

        recyclerView = (RecyclerView)rootView.findViewById(R.id.quotefragrecycler);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
    }



    private void downloadCategoryToQuote() {


        AIE_C_URL urlConstants = new AIE_C_URL();
        String url = urlConstants.getHostname() +  "/api/v1/allcategory";


        new AIE_U_REST_API().get(url, new AIE_U_REST_API.OnRestCallback() {

            @Override
            public void onRestResponse(boolean success, JSONObject jsonObject) {
                if (success) {


                    try {
                        if (jsonObject.has("code")) {

                            if (jsonObject.getInt("code") == 0) {
                                fetchCategoryToQuote(jsonObject);
                            }
                        } else {

                            Toast.makeText(getActivity(), "Technical error, please retry", Toast.LENGTH_LONG).show();
                            getActivity();

                        }

                    } catch (JSONException e) {


                        Toast.makeText(getActivity(), "Technical error, please retry", Toast.LENGTH_LONG).show();
                        getActivity().finish();
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getActivity(), " error, please retry", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }


            }
        }, Request.Priority.HIGH);
    }

    private void fetchCategoryToQuote(JSONObject object) {
        fCategoryQuoteModels.clear();
        Gson gson = new Gson();
        FCategoryQuoteModel fCategoryQuoteModel;

        try {
            JSONArray categoryJsonArray = object.getJSONArray("data");

            for(int i = 0; i < categoryJsonArray.length() ; i++){

                fCategoryQuoteModel = gson.fromJson(categoryJsonArray.get(i).toString(), FCategoryQuoteModel.class);
                fCategoryQuoteModels.add(fCategoryQuoteModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
              setValues();
    }

    private void setValues() {
        categoryname = getArguments().getString("stringArray");

        finalCategoryQuoteModels.clear();
        for(int y = 0;y<fCategoryQuoteModels.size();y++){
            if(fCategoryQuoteModels.get(y).getName().equals(categoryname)){
                finalCategoryQuoteModels.add(fCategoryQuoteModels.get(y));
            }
        }

        initAdapter();

    }
    private void initAdapter() {
        CategoryQuoteAdapter categoryQuoteAdapter = new CategoryQuoteAdapter(getActivity(),finalCategoryQuoteModels);
        recyclerView.setAdapter(categoryQuoteAdapter);

    }

}
