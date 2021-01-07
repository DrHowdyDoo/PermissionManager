package com.bug.apx.util;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermissionManager {

    private final Context context;
    private final Activity activity;

    public PermissionManager(Context context) {
        this.context = context;
        this.activity = (Activity) context;
    }

    public boolean permissionCheck(String per){

        String permission = "Manifest.permission." + per;

        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;

    }

    public void askPermission(String per,int reqCode){

        String permission = "Manifest.permission." + per;
        ActivityCompat.requestPermissions(activity, new String[]{permission}, reqCode);

    }

    public boolean specialPermissionCheck(String per,String opCode){

        String permission = "android.Manifest.permission." + per;
        String op = "android:" + opCode;
        boolean granted = false;

        try{
            AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
             int mode = appOps.unsafeCheckOpNoThrow(op, android.os.Process.myUid(), context.getPackageName());
            if (mode == AppOpsManager.MODE_DEFAULT)
                granted = (context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            else
                granted = (mode == AppOpsManager.MODE_ALLOWED);

        }catch (SecurityException | NullPointerException e){
            e.printStackTrace();
        }

        return granted;

    }

    public void askSpecialPermission(String per,int reqCode){

        String permission = "android.settings." + per;
        activity.startActivityForResult(new Intent(permission).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY),reqCode);

    }



}
