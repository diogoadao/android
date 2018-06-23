package com.example.asus.myapplication.Login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.asus.myapplication.Menu.MainMenuActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginController {
    public boolean LoginCheck(JSONObject Obj, Context context) throws JSONException {
        int i = Integer.parseInt(Obj.getString("acess_level"));
        if (i == 1) {
            Log.d("Info", "Login Approved");
            GoToMenu(context);
            return true;
        } else {
            Log.d("Info", "Login Denied");
            return false;
        }
    }
    public void GoToMenu(Context context){
        Intent intent = new Intent(context, MainMenuActivity.class);
        context.startActivity(intent);
    }
}
