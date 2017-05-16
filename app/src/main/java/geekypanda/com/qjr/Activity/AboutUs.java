package geekypanda.com.qjr.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import geekypanda.com.qjr.R;

/**
 * Created by Prasath on 3/29/2017.
 */
public class AboutUs extends FragmentActivity {



    ImageView button_back;
    private AdView mAdView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
       // .addTestDevice("2E0FEF051360B560DBE272FA97E84B44")
        mAdView.loadAd(adRequest);
        aie_dcl_variables();
        OnClickListener();
    }

    public void aie_dcl_variables() {
      //  appaie_link_address = (TextView) findViewById(R.id.appaie_link);
        button_back=(ImageView)findViewById(R.id.topback);
    }

    public void OnClickListener() {
       /* appaie_link_address.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                appaie_link_address.setText(Html.fromHtml(getString(R.string.Quote_link)));
                Linkify.addLinks(appaie_link_address, Linkify.ALL);
                appaie_link_address.setMovementMethod(LinkMovementMethod.getInstance());
            }
        });*/
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
