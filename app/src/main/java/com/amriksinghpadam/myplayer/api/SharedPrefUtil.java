package com.amriksinghpadam.myplayer.api;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SharedPrefUtil {
    final public static String MYPLAYER_SHARED_PREF = "myplayershredpref";
    final public static String ARTIST_JSON_RESPONSE = "artistjsonresponse";
    final public static String LATEST_JSON_RESPONSE = "latestjsonresponse";
    final public static String DISCOVER_JSON_RESPONSE = "discoverjsonresponse";
    final public static String MOST_WATCHED_JSON_RESPONSE = "mostwatchjsonresponse";
    final public static String NEW_ARIVAL_JSON_RESPONSE = "newarivaljsonresponse";
    final public static String HINDI_PUNJABI_JSON_RESPONSE = "hindipunjabijsonresponse";
    final public static String ENGLISH_JSON_RESPONSE = "englishjsonresponse";
    final public static String ARTIST = "artist";

    public static void setSideNavItemJsonResponse(Context mContext, String responseJson,String sharedPrefKey){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPLAYER_SHARED_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(sharedPrefKey,responseJson);
        if(editor.commit())
            APIConstent.IS_SHARED_PREF_SAVED = true;
    }
    public static ArrayList<JSONObject> getSideNavArtistJsonResponse(Context mContext){
        ArrayList<JSONObject> artistReponseList = new ArrayList<>();
        SharedPreferences sharedPref = mContext.getSharedPreferences(MYPLAYER_SHARED_PREF,Context.MODE_PRIVATE);
        String artistJsonResponse = sharedPref.getString(ARTIST_JSON_RESPONSE,"");
        if(artistJsonResponse!=null && !TextUtils.isEmpty(artistJsonResponse)) {
            try {
                JSONObject jsonObject = new JSONObject(artistJsonResponse);
                JSONArray jsonArray = jsonObject.getJSONArray(ARTIST);
                if(jsonArray!=null && jsonArray.length()>0){
                    for(int i=0;i<jsonArray.length();i++){
                        artistReponseList.add(jsonArray.getJSONObject(i));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return artistReponseList;
    }
}
