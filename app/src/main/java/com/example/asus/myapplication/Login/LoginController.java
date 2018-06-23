package com.example.asus.myapplication.Login;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginController {
    public boolean LoginCheck(JSONObject Obj) throws JSONException {
        int i = Integer.parseInt(Obj.getString("acess_level"));
        if (i == 1) {
            Log.d("Info", "Login Approved");

            return true;
        } else {
            Log.d("Info", "Login Denied");
            return false;
        }
    }
}
