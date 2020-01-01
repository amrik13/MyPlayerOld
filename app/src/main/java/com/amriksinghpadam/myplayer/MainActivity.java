package com.amriksinghpadam.myplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amriksinghpadam.myplayer.api.APIConstent;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.Inflater;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationBar;
    private DrawerLayout drawerLayout;
    private RelativeLayout progressBarLayout;
    private TextView appVersionTextView;
    int backFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("My Player");
        toolbar.setTitleTextColor(getResources().getColor(R.color.whiteColor));

        navigationBar = findViewById(R.id.mainNavBar);
        View v = navigationBar.getHeaderView(0);
        appVersionTextView = v.findViewById(R.id.app_version_id);
        drawerLayout = findViewById(R.id.drawerId);
        progressBarLayout = findViewById(R.id.progressBar_layout_id);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.opoen_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.statusbar));
        appVersionTextView.setText(BuildConfig.VERSION_NAME);
        new ContentTypeAPI().execute(APIConstent.SSL_SCHEME+APIConstent.BASE_URL+APIConstent.CONTENT_TYPE_URL_PARAM);
    }

    public void onGetContentTypeResponse( ArrayList<String> contentTypeList,ArrayList<String> contentTypeImgList){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MainFragment fragMain = new MainFragment(this);

        if(contentTypeList!=null && contentTypeImgList!=null&&
                contentTypeImgList.size()>0&&contentTypeList.size()>0) {
            Bundle bundle = new Bundle();
            bundle.putString(APIConstent.SONG_TITLE, contentTypeList.get(0));
            bundle.putString(APIConstent.VIDEO_TITLE, contentTypeList.get(1));
            bundle.putString(APIConstent.SONG_BANNER, contentTypeImgList.get(0));
            bundle.putString(APIConstent.VIDEO_BANNER, contentTypeImgList.get(1));
            fragMain.setArguments(bundle);
        }

        ft.add(R.id.floatingLayoutId,fragMain);
        ft.commit();

        navigationBar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent = new Intent(MainActivity.this,CommonPlayerGridView.class);
                Bundle bundle = new Bundle();
                switch (menuItem.getItemId()){
                    case R.id.nav_feature_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","Featured Artists");
                        bundle.putString("type","song");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.nav_latest_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","Latest Songs");
                        bundle.putString("type","song");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.nav_discover_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","Discover");
                        bundle.putString("type","song");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.nav_most_watch_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","Most Watched");
                        bundle.putString("type","video");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.nav_new_video_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","New Video");
                        bundle.putString("type","video");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.nav_indian_video_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","Hindi & Punjabi");
                        bundle.putString("type","video");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.nav_englishi_video_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","English Video");
                        bundle.putString("type","video");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    default:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
    }

    public class ContentTypeAPI extends AsyncTask<String,String,String>{
        private String inlineResponse = "";
        private StringBuffer stringBuffer;
        ArrayList<String> contentTypeList,contentTypeImgList;

        public ContentTypeAPI(){
            contentTypeList = new ArrayList<>();
            contentTypeImgList = new ArrayList<>();
            stringBuffer = new StringBuffer();
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarLayout.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... param) {
            if(param.length>0 && param != null && !TextUtils.isEmpty(param[0]) && param[0] != null) {
                InputStreamReader inputStreamReader = null;
                BufferedReader bufferedReader = null;
                try {
                    URL contentTypeReqURL = new URL(param[0]);
                    HttpsURLConnection connection = (HttpsURLConnection) contentTypeReqURL.openConnection();
                    if(connection!=null) {
                        connection.setConnectTimeout(15000);
                        connection.setRequestMethod("GET");
                        connection.setReadTimeout(15000);
                        connection.connect();
                        inputStreamReader = new InputStreamReader(connection.getInputStream());
                        bufferedReader = new BufferedReader(inputStreamReader);

                        while ((inlineResponse = bufferedReader.readLine()) != null) {
                            if(inlineResponse!=null) stringBuffer.append(inlineResponse);
                        }
                        Log.d("contentTypeResponse",stringBuffer.toString());
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        inputStreamReader.close();
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return stringBuffer.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result!=null && !TextUtils.isEmpty(result)){
                try {
                    JSONObject contentTypeObject = new JSONObject(result);
                    JSONArray contentTypeJsonArray = contentTypeObject.getJSONArray(APIConstent.CONTENT_TYPE_KEY);
                    for (int i = 0 ; i<contentTypeJsonArray.length();i++){
                        JSONObject object = contentTypeJsonArray.getJSONObject(i);
                        contentTypeList.add(object.getString(APIConstent.CONTENT_TYPE_KEY));
                        contentTypeImgList.add(object.getString(APIConstent.IMAGEURL));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("contenttypelist",contentTypeList.get(0)+"\n"+contentTypeList.get(1));
            }
            onGetContentTypeResponse(contentTypeList,contentTypeImgList);
            progressBarLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        backFlag=0;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            if(backFlag==1){
                super.onBackPressed();
            }else{
                backFlag++;
                Toast.makeText(MainActivity.this,"Press Back Again",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
