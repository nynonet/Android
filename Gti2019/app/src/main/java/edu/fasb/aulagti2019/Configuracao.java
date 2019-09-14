package edu.fasb.aulagti2019;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

public class Configuracao extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource( R.xml.configuracao );
    }

    public static SharedPreferences getConfig(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
