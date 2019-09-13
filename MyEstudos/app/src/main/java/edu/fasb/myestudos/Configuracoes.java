package edu.fasb.myestudos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

public class Configuracoes extends PreferenceActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource( R.xml.configuracao );
    }

    /**
     * para devolver os dados gravados.
     * @param context getApplication()
     * @return
     */
    public static SharedPreferences getConfig(Context context) {
        return PreferenceManager.getDefaultSharedPreferences( context );
    }

}
