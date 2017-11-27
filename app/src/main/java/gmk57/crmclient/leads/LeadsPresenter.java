package gmk57.crmclient.leads;

import javax.inject.Inject;

import gmk57.crmclient.data.ModelRepository;
import gmk57.crmclient.di.ActivityScope;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class LeadsPresenter implements LeadsContract.Presenter {

    private final ModelRepository mModelRepository;
    private LeadsContract.View mLeadsView;
    private Disposable mSubscription;

    @Inject
    LeadsPresenter(ModelRepository modelRepository) {
        mModelRepository = modelRepository;
    }

    @Override
    public void takeView(LeadsContract.View view) {
        mLeadsView = view;
        loadLeads();
    }

    @Override
    public void dropView() {
        mSubscription.dispose();
        mLeadsView = null;
    }

    private void loadLeads() {
        mSubscription = mModelRepository.getLeads()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(leads -> mLeadsView.showLeads(leads),
                        error -> mLeadsView.showError(error));
    }
}
