package com.buggyarts.eventspotter;

import com.google.firebase.auth.FirebaseUser;

public class AppUtils {

    private static FirebaseUser esUser;

    public static FirebaseUser getEsUser() {
        return esUser;
    }

    public static void setEsUser(FirebaseUser esUser) {
        AppUtils.esUser = esUser;
    }
}
