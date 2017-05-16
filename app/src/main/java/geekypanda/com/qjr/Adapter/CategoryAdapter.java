package geekypanda.com.qjr.Adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import geekypanda.com.qjr.Activity.CategoryTab;
import geekypanda.com.qjr.Activity.MainActivity;
import geekypanda.com.qjr.Constant.AIE_APP_CONSTANTS;
import geekypanda.com.qjr.Constant.AIE_C_URL;
import geekypanda.com.qjr.Model.CategoryModel;
import geekypanda.com.qjr.R;

/**
 * Created by Prasath on 3/28/2017.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    FragmentActivity activity;
    ArrayList<CategoryModel> categorymodels;
    AIE_C_URL urlConstants = new AIE_C_URL();


    public CategoryAdapter(MainActivity mainActivity, ArrayList<CategoryModel> categorymodels) {
        this.activity = mainActivity;
        this.categorymodels = categorymodels;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.cat_adap_img, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        CategoryModel category_model = categorymodels.get(position);
        Picasso.with(activity)
                .load((urlConstants.getHostname()+category_model.getBig_image()))
                .placeholder(R.drawable.backgroundcat)
                .error(R.drawable.backgroundcat)
                .into(holder.cativ);


        holder.cativ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(activity, CategoryTab.class);
                    Prefs.putInt(AIE_APP_CONSTANTS.positioncat, position);


                    Prefs.putInt(AIE_APP_CONSTANTS.positioncat, position);
                    Prefs.putString(AIE_APP_CONSTANTS.positionname, categorymodels.get(position).getName());
                    i.putExtra("placemodels", categorymodels);
                    i.putExtra("positionname", categorymodels.get(position).getName());
                    activity.startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return categorymodels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView cativ;


        public ViewHolder(View view) {
            super(view);
            cativ = (ImageView) view.findViewById(R.id.category_image);


        }
    }
}
