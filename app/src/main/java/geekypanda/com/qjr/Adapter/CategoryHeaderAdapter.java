package geekypanda.com.qjr.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TreeSet;

import geekypanda.com.qjr.Activity.CategoryTab;
import geekypanda.com.qjr.Activity.Main_category;
import geekypanda.com.qjr.Constant.AIE_APP_CONSTANTS;
import geekypanda.com.qjr.Constant.AIE_C_URL;
import geekypanda.com.qjr.Model.CategoryModel;
import geekypanda.com.qjr.Model.FAuthorQuoteModel;
import geekypanda.com.qjr.Model.FCategoryQuoteModel;
import geekypanda.com.qjr.R;
import geekypanda.com.qjr.Setting.AIE_U_REST_API;

/**
 * Created by Prasath on 4/6/2017.
 */
public class CategoryHeaderAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    Context context;
    Activity activity;
    private ArrayList<String> mData = new ArrayList<String>();
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();
    ArrayList<FCategoryQuoteModel>finalCategoryQuoteModels=new ArrayList<FCategoryQuoteModel>();
    ArrayList<FCategoryQuoteModel>fCategoryQuoteModels = new ArrayList<FCategoryQuoteModel>();
    ArrayList<CategoryModel>categorymodels = new ArrayList<CategoryModel>();

    private LayoutInflater mInflater;


    public CategoryHeaderAdapter(Main_category context, ArrayList<CategoryModel> categorymodels) {
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        downloadCategoryQuote();
        this.categorymodels = categorymodels;
    }

    public void addItem(final String item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final String item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.author_tv_header, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.text);
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.author_tv_items, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.textSeparator);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(mData.get(position));
        final ViewHolder finalHolder = holder;

        final ViewHolder finalHolder1 = holder;
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /*     Intent i = new Intent(v.getContext(),SoloAuthor.class);
                i.putExtra("authorname", finalHolder.textView.getText());
                v.getContext().startActivity(i);*/

                Intent i = new Intent(v.getContext(), CategoryTab.class);

                Prefs.putInt(AIE_APP_CONSTANTS.positioncat, position);
                Prefs.putString(AIE_APP_CONSTANTS.positionname, finalHolder1.textView.getText().toString());
                i.putExtra("placemodels", categorymodels);
                i.putExtra("positionname", finalHolder1.textView.getText().toString());
                v.getContext().startActivity(i);
            }
        });



        return convertView;
    }


    public static class ViewHolder {
        public TextView textView;
    }

    private void downloadCategoryQuote() {


        AIE_C_URL urlConstants = new AIE_C_URL();
        String url = urlConstants.getHostname() +  "/api/v1/allcategory";


        new AIE_U_REST_API().get(url, new AIE_U_REST_API.OnRestCallback() {

            @Override
            public void onRestResponse(boolean success, JSONObject jsonObject) {
                if (success) {


                    try {
                        if (jsonObject.has("code")) {

                            if (jsonObject.getInt("code") == 0) {

                                fetchCategoryQuote(jsonObject);

                            }
                        } else {

                            Toast.makeText(context, "Technical error, please retry", Toast.LENGTH_LONG).show();
                            activity.finish();

                        }

                    } catch (JSONException e) {


                        Toast.makeText(context, "Technical error, please retry", Toast.LENGTH_LONG).show();
                        activity.finish();
                        e.printStackTrace();
                    }
                }


            }
        }, Request.Priority.HIGH);
    }

    private void fetchCategoryQuote(JSONObject object) {
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



    }

}

