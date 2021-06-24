package com.appsinventiv.newsapp.Utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by AliAh on 12/04/2018.
 */

public class DynamicPermissions {
    private Activity mContext;
    private List<String> mPermissionList;
    public final int TAG_PERMISSION = 11;

    public DynamicPermissions(Activity mContext) {
        this.mContext = mContext;
    }

    public DynamicPermissions(Activity mContext, List<String> mPermissionList) {
        this.mContext = mContext;
        this.mPermissionList = mPermissionList;
    }
    public boolean isCompatibleOS() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkAndRequestPermissions() {
        if (isCompatibleOS()) {
            List<String> listPermissionsNeeded = new ArrayList<>();
            for (String permission :
                    mPermissionList) {
                if (!isPermissionGranted(permission)) {
                    listPermissionsNeeded.add(permission);
                }
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(mContext,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), TAG_PERMISSION);
                return false;
            }
        }
        return true;
    }

    public boolean isPermissionGranted(String permission) {
        int permissionCAMERA = ContextCompat.checkSelfPermission(mContext,
                permission);
        if (permissionCAMERA != PackageManager.PERMISSION_GRANTED)
            return false;
        else return true;

    }

    public void requestSinglePermission(String permission) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(mContext,
                permission)) {
            ActivityCompat.requestPermissions(mContext,
                    new String[]{permission},
                    TAG_PERMISSION);
        } else {
            ActivityCompat.requestPermissions(mContext,
                    new String[]{permission},
                    TAG_PERMISSION);
        }
    }
}

