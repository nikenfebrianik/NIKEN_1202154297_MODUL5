package id.showup.niken.niken_1202154297_modul5;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v4.app.Fragment;

/**
 * Created by nikenfebriani on 27/03/18.
 */

public class SettingFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }

}