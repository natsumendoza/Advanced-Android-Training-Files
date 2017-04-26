package com.example.rr.mapsbasicexercise;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by Jay-Ar Gabriel on 4/26/2017.
 */

public class AbstractMapActivity extends Activity {

    static final String TAG_ERROR_DIALOG_FRAGMENT = "errorDialog";

    protected boolean readyToGo() {

        GoogleApiAvailability checker = GoogleApiAvailability.getInstance();

        int status = checker.isGooglePlayServicesAvailable(this);

        if (status == ConnectionResult.SUCCESS) {
            // CHECK IF MAPS V2 IS SUPPORTED BY THE PHONE
            if (getVersionFromPackageManager(this) >= 2) {
                return(true);
            } else {
                Toast.makeText(this, R.string.no_maps, Toast.LENGTH_LONG).show();
                finish();
            }
        } else if (checker.isUserResolvableError(status)) {
            ErrorDialogFragment.newInstance(status).show(getFragmentManager(), TAG_ERROR_DIALOG_FRAGMENT);
        } else {
            Toast.makeText(this, R.string.no_maps, Toast.LENGTH_LONG).show();
            finish();
        }

        return false;

    }

    public static class ErrorDialogFragment extends DialogFragment {
        static final String ARG_ERROR_CODE = "errorCode";

        static ErrorDialogFragment newInstance(int errorCode) {
            Bundle args = new Bundle();
            ErrorDialogFragment result = new ErrorDialogFragment();

            args.putInt(ARG_ERROR_CODE, errorCode);
            result.setArguments(args);

            return(result);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            Bundle args = getArguments();
            GoogleApiAvailability checker = GoogleApiAvailability.getInstance();

            return checker.getErrorDialog(getActivity(), args.getInt(ARG_ERROR_CODE), 0);

        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            if (getActivity() != null) {
                getActivity().finish();
            }
        }
    }

    private static int getVersionFromPackageManager(Context context) {
        PackageManager packageManager = context.getPackageManager();
        FeatureInfo[] featureInfos = packageManager.getSystemAvailableFeatures();
        if (featureInfos != null && featureInfos.length > 0) {
            for (FeatureInfo featureInfo : featureInfos) {
                // Null feature name means this feature is open
                // gl es feature
                if (featureInfo.name == null) {
                    if (featureInfo.reqGlEsVersion != FeatureInfo.GL_ES_VERSION_UNDEFINED) {
                        return getMajorVersion(featureInfo.reqGlEsVersion);
                    } else {
                        return 1;
                    }
                }
            }
        }
        return 1;
    }

    private static int getMajorVersion(int glEsVersion) {
        return((glEsVersion & 0xffff0000) >> 16);
    }

}
