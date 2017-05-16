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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TreeSet;

import geekypanda.com.qjr.Activity.Main_author;
import geekypanda.com.qjr.Activity.SoloAuthor;
import geekypanda.com.qjr.Constant.AIE_C_URL;
import geekypanda.com.qjr.Model.AuthorModel;
import geekypanda.com.qjr.Model.FAuthorQuoteModel;
import geekypanda.com.qjr.R;
import geekypanda.com.qjr.Setting.AIE_U_REST_API;

/**
 * Created by Prasath on 4/5/2017.
 */
public class AuthorHeaderAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
   Activity activity;
    Context context;
    private ArrayList<String> mData = new ArrayList<String>();
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();
    ArrayList<FAuthorQuoteModel>finalAuthorQuoteModels=new ArrayList<FAuthorQuoteModel>();
    ArrayList<FAuthorQuoteModel>fAuthorQuoteModels = new ArrayList<FAuthorQuoteModel>();
    ArrayList<AuthorModel>AuthorModels = new ArrayList<AuthorModel>();
    private LayoutInflater mInflater;





    public AuthorHeaderAdapter(Context context, ArrayList<AuthorModel> AuthorModels) {
        this.context = context;
        this.mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        downloadAuthorQuote();
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

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                finalAuthorQuoteModels.clear();
                FAuthorQuoteModel ffAuthorQuoteModel = new FAuthorQuoteModel();
                for(int ii=0;ii<fAuthorQuoteModels.size();ii++){
                    if(fAuthorQuoteModels.get(ii).getName().equalsIgnoreCase(finalHolder.textView.getText().toString())){
                        ffAuthorQuoteModel = fAuthorQuoteModels.get(ii);
                        finalAuthorQuoteModels.add(ffAuthorQuoteModel);
                    }
                }
                Intent i = new Intent(v.getContext(),SoloAuthor.class);
                i.putParcelableArrayListExtra("authordetail", finalAuthorQuoteModels);
               // i.putExtra("authorid",fAuthorQuoteModels.get(position).getId());
                v.getContext().startActivity(i);
            }
        });



        return convertView;
    }


    public static class ViewHolder {
        public TextView textView;
    }

    private void downloadAuthorQuote() {


        AIE_C_URL urlConstants = new AIE_C_URL();
        String url = urlConstants.getHostname() +  "/api/v1/allauthor";


        new AIE_U_REST_API().get(url, new AIE_U_REST_API.OnRestCallback() {

            @Override
            public void onRestResponse(boolean success, JSONObject jsonObject) {
                if (success) {


                    try {
                        if (jsonObject.has("code")) {

                            if (jsonObject.getInt("code") == 0) {

                                fetchAuthorQuote(jsonObject);

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

    private void fetchAuthorQuote(JSONObject object) {
        fAuthorQuoteModels.clear();
        Gson gson = new Gson();
        FAuthorQuoteModel fAuthorQuoteModel;

        try {
            JSONArray categoryJsonArray = object.getJSONArray("data");

            for(int i = 0; i < categoryJsonArray.length() ; i++){

                fAuthorQuoteModel = gson.fromJson(categoryJsonArray.get(i).toString(), FAuthorQuoteModel.class);
                fAuthorQuoteModels.add(fAuthorQuoteModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

}
