package com.apppartner.androidprogrammertest.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created on 12/23/14.
 *
 * @author Thomas Colligan
 */
public class ChatData
{
    private static final String LOG_TAG = "ChatData";

    public int userID;
    public String username;
    public String avatarURL;
    public String message;

    public ChatData(JSONObject jsonObject)
    {
        if (jsonObject != null)
        {
            try
            {
                userID = jsonObject.getInt("user_id");
                username = jsonObject.getString("username");
                avatarURL = jsonObject.getString("avatar_url");
                message = jsonObject.getString("message");
            }
            catch (JSONException e)
            {
                Log.w(LOG_TAG, e);
            }
        }
    }
}
