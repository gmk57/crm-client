package gmk57.crmclient.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import gmk57.crmclient.leads.LeadsActivity;
import gmk57.crmclient.leads.LeadsModule;

@Module
interface ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = LeadsModule.class)
    LeadsActivity leadsActivity();
}
