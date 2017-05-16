package geekypanda.com.qjr.Adapter;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import geekypanda.com.qjr.Constant.AIE_C_URL;
import geekypanda.com.qjr.Model.Top100Model;
import geekypanda.com.qjr.R;

import static android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Created by Prasath on 07-May-17.
 */
public class TopQuoteAdapter  extends RecyclerView.Adapter<TopQuoteAdapter.ViewHolder> {


  Activity activity;

    ArrayList<Top100Model>top100Models;
    private long mLastClickTime = 0;
    private AIE_C_URL url;



    public TopQuoteAdapter(Activity activity, ArrayList<Top100Model> top100Models) {
        this.activity = activity;
        this.top100Models = top100Models;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.quotefraglayout, parent, false);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);


        }
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(final TopQuoteAdapter.ViewHolder holder, final int position) {


        Picasso.with(activity)
                .load(url.getHostname()+top100Models.get(position).getBig_image())
                .placeholder(R.drawable.background)
                .error(R.drawable.background)
                .into(holder.iv1);

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PermissionChecker.checkSelfPermission(activity,Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    new AlertDialog.Builder(activity)
                            .setTitle("Android Image Permission")
                            .setMessage("You need to provide permission to share and save images.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.mipmap.quotejr)
                            .show();



                }else {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    // intent.putExtra(Intent.EXTRA_TEXT, top100Models.get(position).getText());

                    URL urll = null;
                    try {
                        urll = new URL(url.getHostname() + top100Models.get(position).getBig_image());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    HttpURLConnection connection = null;
                    try {
                        connection = (HttpURLConnection) urll.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream input = connection.getInputStream();
                        Bitmap myBitmap = BitmapFactory.decodeStream(input);
                        String path = MediaStore.Images.Media.insertImage(activity.getContentResolver(), myBitmap, "", null);
                        Uri screenshotUri = Uri.parse(path);
                        intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        intent.setType("image/*");
                        activity.startActivity(Intent.createChooser(intent, "Share image via..."));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });



        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sdk_Version = android.os.Build.VERSION.SDK_INT;
                if(sdk_Version < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(top100Models.get(position).getText());   // Assuming that you are copying the text from a TextView
                    Toast.makeText(activity.getApplicationContext(), "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
                }
                else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Text Label", top100Models.get(position).getText());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(activity.getApplicationContext(), "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uurl = url.getHostname()+top100Models.get(position).getBig_image();

                File direct = new File(Environment.getExternalStorageDirectory()
                        + "/quotejr");

                if (!direct.exists()) {
                    direct.mkdirs();
                }

                DownloadManager mgr = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);

                Uri downloadUri = Uri.parse(uurl);
                DownloadManager.Request request = new DownloadManager.Request(
                        downloadUri);

                request.setAllowedNetworkTypes(
                        DownloadManager.Request.NETWORK_WIFI
                                | DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false).setTitle("Demo")
                        .setDescription("Something useful. No, really.")
                        .setDestinationInExternalPublicDir("/quotejrFiles", "fileName.jpg");

                mgr.enqueue(request);
                Toast.makeText(activity.getApplicationContext(), "Saved to gallery", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return top100Models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv1,download,copy,share;

        public ViewHolder(View view) {
            super(view);
            iv1 = (ImageView) view.findViewById(R.id.qfliv1);
            download = (ImageView) view.findViewById(R.id.download);
            share = (ImageView) view.findViewById(R.id.share);
            copy = (ImageView) view.findViewById(R.id.copy);

        }
    }
}

