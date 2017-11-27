package gmk57.crmclient.leads;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class LeadsActivity extends DaggerAppCompatActivity {

    @Inject
    Lazy<LeadsFragment> leadsFragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(android.R.id.content) == null) {
            Fragment fragment = leadsFragmentProvider.get();
            fm.beginTransaction()
                    .add(android.R.id.content, fragment).commit();
        }
    }
}
