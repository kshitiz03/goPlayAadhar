package com.example.lenovo.goplaybook;
import android.os.Build.*;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.util.Log;
import com.bumptech.glide.Glide;
import static android.content.Context.MODE_PRIVATE;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;
import java.util.jar.Attributes;

import static java.util.function.Function.*;

public class ProfileActivity extends AppCompatActivity {
    private TextView textViewName;
    private SharedPreferences pref;
    private ImageView profilePhoto;
    private Button signout;
    private int level_type2;
    private int level_type1;
     String Name;
     String email,ll1,ll2,pri1,pri2,lev1,lev2,add,date1;
     String s;
    private static final String TAG = "Testing: ";
     EditText dob1,p1,p2;
    static ProfileActivity act;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        act=this;

        textViewName = (TextView) findViewById(R.id.textViewName);
        profilePhoto = (ImageView) findViewById(R.id.profileImage);
        dob1= (EditText)act.findViewById(R.id.dob);
        p1= (EditText)act.findViewById(R.id.priority1);
        p2= (EditText)act.findViewById(R.id.priority2);
        signout = (Button) findViewById(R.id.profout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                    File cacheDirectory = getCacheDir();
//                    File applicationDirectory = new File(cacheDirectory.getParent());
//                    if (applicationDirectory.exists()) {
//                        String[] fileNames = applicationDirectory.list();
//                        for (String fileName : fileNames) {
//                            if (!fileName.equals("lib")) {
//                                deleteFile(new File(applicationDirectory, fileName));
//                            }
//                        }
//                    }
//
//
//                public static boolean deleteFile(File file) {
//                    boolean deletedAll = true;
//                    if (file != null) {
//                        if (file.isDirectory()) {
//                            String[] children = file.list();
//                            for (int i = 0; i < children.length; i++) {
//                                deletedAll = deleteFile(new File(file, children[i])) && deletedAll;
//                            }
//                        } else {
//                            deletedAll = file.delete();
//                        }
//                    }
//
//                    return deletedAll;
//                }

//                if (VERSION_CODES.KITKAT <= VERSION.SDK_INT) {
//                    (ProfileActivity.this.getSystemService(ACTIVITY_SERVICE))
//                            .clearApplicationUserData(); // note: it has a return value!
//                } else {
//                    // use old hacky way, which can be removed
//                    // once minSdkVersion goes above 19 in a few years.
//                }

                SharedPreferences preferences =getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
             SharedPreferences.Editor editor = preferences.edit();
             editor.clear();
                editor.apply();

                Intent lognew = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(lognew);
                ProfileActivity.this.finish();
            }


        });

        Bundle extras = getIntent().getExtras();
        if(extras == null) {

        } else {
            Name = extras.getString("STRING_I_NEED");
            textViewName.setText(Name);
            email = extras.getString("EMAIL_I_NEED");

            s = extras.getString("URL_I_NEED");
            if(s==null){

            }
            else {
                Glide.with(this).load(s).into(profilePhoto);
            }
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,R.layout.spinner_textview, memberall);
        Spinner tv = this.findViewById(R.id.level1);

        tv.setAdapter(adapter);
        tv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                level_type1 = (int)l;
//                Functions.showToast(getContext(),String.valueOf(l));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<>(this, R.layout.spinner_textview, memberall);

        Spinner tv1 = this.findViewById(R.id.level2);

        tv1.setAdapter(adapter1);
        tv1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                level_type2 = (int)l;

//                Functions.showToast(getContext(),String.valueOf(l));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner l1= findViewById(R.id.level1);
        Spinner l2= findViewById(R.id.level2);

        date1 = act.dob1.getText().toString();
        pri1 = act.p1.getText().toString();
        pri2 = act.p2.getText().toString();
        lev1= l1.getSelectedItem().toString();
        lev2 = l2.getSelectedItem().toString();;
        add = ((EditText)findViewById(R.id.addr)).getText().toString();
        Log.d(TAG,date1);
        Log.d(TAG,pri1);
        Log.d(TAG,pri2);
        Log.d(TAG,add);


        Button sub = findViewById(R.id.submit);
        sub.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                final String[] arr = new String[]{Name,email,s,date1,pri1,lev1,pri2,lev2,add};
//                for(int i=0;i<9;i++){
        //        Log.d(TAG,arr[i].toString());}
                Log.d(TAG,email);
                Log.d(TAG,date1);
                Log.d(TAG,pri1);
                Log.d(TAG,pri2);
                Log.d(TAG,add);

                Intent i = new Intent(ProfileActivity.this,SportsAadhar.class);
                i.putExtra("STRING", Name);
                i.putExtra("EMAIL", email);
                i.putExtra("URL", s);
                i.putExtra("DOB", date1);
                i.putExtra("P1", pri1);
                i.putExtra("L1", lev1);
                i.putExtra("P2", pri2);
                i.putExtra("L2", lev2);
                i.putExtra("ADDRESS", add);

                startActivity(i);
            }
        });
    }

    private static final String[] memberall = new String[] {
            "International Level", "National Level", "State Level", "City Level", "Local Level"
    };

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
//        a.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(a);

    }
}
