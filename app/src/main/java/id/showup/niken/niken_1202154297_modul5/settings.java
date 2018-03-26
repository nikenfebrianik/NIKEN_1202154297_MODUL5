package id.showup.niken.niken_1202154297_modul5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingFragment())
                .commit();

    }
}
