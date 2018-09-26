package com.example.lenovo.goplaybook;

import android.content.DialogInterface;
import android.graphics.Bitmap.CompressFormat;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;
import java.io.FileOutputStream;
import com.bumptech.glide.Glide;

import java.io.FileOutputStream;
import java.io.IOException;

public class SportsAadhar extends AppCompatActivity {

    String Name;
    String email, dob, p1, l1, p2, l2;
    String s;
    ImageView profilePhoto;
    TextView namet, dobt, emailt, p1t, l1t, p2t, l2t, addt;
    Button saveBtn;
    LinearLayout idForSaveView, linearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_aadhar);

        linearLayout = (LinearLayout) findViewById(R.id.now);
        saveBtn = (Button) findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            OnClickShare(v);}
                                   });


            namet = (TextView) findViewById(R.id.name);
        dobt = (TextView) findViewById(R.id.db);

        emailt = (TextView) findViewById(R.id.email);

        p1t = (TextView) findViewById(R.id.prt1);
        l1t = (TextView) findViewById(R.id.lvl1);
        p2t = (TextView) findViewById(R.id.prt2);
        l2t = (TextView) findViewById(R.id.lvl2);
        addt = (TextView) findViewById(R.id.address);

        profilePhoto = (ImageView) findViewById(R.id.profileImage);

        Bundle extras = getIntent().getExtras();

        Intent i = getIntent();
        String text = i.getStringExtra("DOB");

        if (extras == null) {

        } else {
            Name = extras.getString("STRING");
            namet.setText(Name);

            email = extras.getString("EMAIL");
            emailt.setText(email);

            dob = extras.getString("DOB");
            dobt.setText(text);

            p1 = extras.getString("P1");
            p1t.setText(p1);

            l1 = extras.getString("L1");
            l1t.setText(l1);

            p2 = extras.getString("P2");
            p2t.setText(p2);

            l2 = extras.getString("L2");
            l2t.setText(l2);

            s = extras.getString("URL");
            if (s == null) {

            } else {
                Glide.with(this).load(s).into(profilePhoto);
            }
        }


//        view = (LinearLayout)findViewById(R.id.screen);
//        bmImage = (ImageView)findViewById(R.id.image);
//
//        view.setDrawingCacheEnabled(true);
//        // this is the important code :)
//        // Without it the view will have a dimension of 0,0 and the bitmap will be null
//
//        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//
//        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//
//        view.buildDrawingCache(true);
//        Bitmap b = Bitmap.createBitmap(view.getDrawingCache());
//        view.setDrawingCacheEnabled(false); // clear drawing cache
//
//        bmImage.setImageBitmap(b);


    }


    public void OnClickShare(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.save_btn:
                File file = saveBitMap(this, linearLayout);    //which view you want to pass that view as parameter
                if (file != null) {
                    Log.i("TAG", "Drawing saved to the gallery!");
                    file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share image via"));
                } else {
                    Log.i("TAG", "Oops! Image could not be saved.");
                }
                break;
            default:
                break;
        }
    }

        private File saveBitMap (Context context, View drawView){
            File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Handcare");
            if (!pictureFileDir.exists()) {
                boolean isDirectoryCreated = pictureFileDir.mkdirs();
                if (!isDirectoryCreated)
                    Log.i("ATG", "Can't create directory to save the image");
                return null;
            }
            String filename = pictureFileDir.getPath() + File.separator + System.currentTimeMillis() + ".jpg";
            File pictureFile = new File(filename);
            Bitmap bitmap = getBitmapFromView(drawView);
            try {
                pictureFile.createNewFile();
                FileOutputStream oStream = new FileOutputStream(pictureFile);
                bitmap.compress(CompressFormat.PNG, 100, oStream);
                oStream.flush();
                oStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("TAG", "There was an issue saving the image.");
            }
            scanGallery(context, pictureFile.getAbsolutePath());
            return pictureFile;
        }
        //create bitmap from view and returns it
        private Bitmap getBitmapFromView (View view){
            //Define a bitmap with the same size as the view
            Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            //Bind a canvas to it
            Canvas canvas = new Canvas(returnedBitmap);
            //Get the view's background
            Drawable bgDrawable = view.getBackground();
            if (bgDrawable != null) {
                //has background drawable, then draw it on the canvas
                bgDrawable.draw(canvas);
            } else {
                //does not have background drawable, then draw white background on the canvas
                canvas.drawColor(Color.WHITE);
            }
            // draw the view on the canvas
            view.draw(canvas);
            //return the bitmap
            return returnedBitmap;
        }
        // used for scanning gallery
        private void scanGallery (Context cntx, String path){
            try {
                MediaScannerConnection.scanFile(cntx, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//    public void OnClickShare(View view){
//
//        Bitmap bitmap =getBitmapFromView(idForSaveView);
//        try {
//            File file = new File(this.getExternalCacheDir(),"my.png");
//            FileOutputStream fOut = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
//            fOut.flush();
//            fOut.close();
//            file.setReadable(true, false);
//            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
//            intent.setType("image/png");
//            startActivity(Intent.createChooser(intent, "Share image via"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private Bitmap getBitmapFromView(View view) {
//        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(returnedBitmap);
//        Drawable bgDrawable =view.getBackground();
//        if (bgDrawable!=null) {
//            //has background drawable, then draw it on the canvas
//            bgDrawable.draw(canvas);
//        }   else{
//            //does not have background drawable, then draw white background on the canvas
//            canvas.drawColor(Color.WHITE);
//        }
//        view.draw(canvas);
//        return returnedBitmap;
//    }
    }

