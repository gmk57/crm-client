package gmk57.crmclient.leads;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import gmk57.crmclient.di.ActivityScope;
import gmk57.crmclient.di.FragmentScope;

@Module
public interface LeadsModule {

    @FragmentScope
    @ContributesAndroidInjector
    LeadsFragment leadsFragment();

    @ActivityScope
    @Binds
    LeadsContract.Presenter leadsPresenter(LeadsPresenter leadsPresenter);
}
