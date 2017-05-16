package geekypanda.com.qjr.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import geekypanda.com.qjr.Adapter.AuthorQuoteAdapter;

import geekypanda.com.qjr.Model.FAuthorQuoteModel;
import geekypanda.com.qjr.Model.FCategoryQuoteModel;
import geekypanda.com.qjr.R;

/**
 * Created by Prasath on 4/5/2017.
 */
public class SoloAuthor extends FragmentActivity {

    ImageView topback, wiki;
    TextView authorname,authoroccupation;
    RecyclerView recyclerView;
    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<FAuthorQuoteModel>authorquotemodels = new ArrayList<>();
    ArrayList<Integer> stringArray = new ArrayList<>();
    String name;
    private AdView mAdView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.soloauthorrecycler);

        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
       // .addTestDevice("2E0FEF051360B560DBE272FA97E84B44")
        mAdView.loadAd(adRequest);

        setVariables();
        setValues();




    }

    private void setVariables() {

        recyclerView = (RecyclerView)findViewById(R.id.topquoterecycler);
        authorname = (TextView)findViewById(R.id.authourname);
        authoroccupation = (TextView)findViewById(R.id.authoroccupation);
        wiki = (ImageView)findViewById(R.id.wiki);
        topback = (ImageView)findViewById(R.id.topback);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(SoloAuthor.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        topback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }


    private void setValues() {
        authorquotemodels = getIntent().getParcelableArrayListExtra("authordetail");
        // name = getIntent().getExtras().getString("authorname");
        authorname.setText(authorquotemodels.get(0).getName());
        authoroccupation.setText(authorquotemodels.get(0).getOccupation());
        wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String url = "http://en.wikipedia.org/wiki/" +name;
                String url = authorquotemodels.get(0).getWiki();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


       /* authorquotemodels.clear();
        for(int i = 0; i < stringArray.size() ; i++){
            FCategoryQuoteModel categoryquotemodel = new FCategoryQuoteModel();
            categoryquotemodel.setBig_image(String.valueOf(stringArray.get(i)));
            categoryquotemodels.add(categoryquotemodel);
        }*/
        initAdapter();

    }

    private void initAdapter() {
        AuthorQuoteAdapter categoryQuoteAdapter = new AuthorQuoteAdapter(this,authorquotemodels);
        recyclerView.setAdapter(categoryQuoteAdapter);

    }
}
