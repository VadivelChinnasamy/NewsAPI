package com.newssource.Constant;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by vadivel on 9/10/17.
 */

public class Util {

    public static String ID = "ID";
    public static String NAME = "NAME";
    public static String LOGO = "LOGO";
    public static String APIKEY = "3a91cebd2d2f4741a2aeafdd758d54ee"; // Add your " newsapi " API key here,  getAPIkey from http://newsapi.org
    public static String DETAIL = "https://newsapi.org/v1/articles?source=";
    public static String BASEURL = "https://newsapi.org/v1/sources";


    public static boolean isConnected(Context ctx) {
        ConnectivityManager mgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = mgr.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();


    }
}
