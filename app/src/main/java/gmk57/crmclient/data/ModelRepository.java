package gmk57.crmclient.data;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import gmk57.crmclient.R;
import io.reactivex.Observable;

@Singleton
public class ModelRepository {

    private final NetworkApi mNetworkApi;
    private final Context mContext;
    private final Map<String, String> mStatusNames = new HashMap<>();
    private Observable<List<Lead>> mLeadsObservable;

    @Inject
    ModelRepository(NetworkApi networkApi, Context context) {
        mNetworkApi = networkApi;
        mContext = context;
    }

    public Observable<List<Lead>> getLeads() {
        if (mLeadsObservable == null) {
            mLeadsObservable = mNetworkApi.getLeads(getLogin(), getHash())
                    .map(responseOuter -> responseOuter.response.leads)
                    .delaySubscription(getStatuses())  // We should wait for status names
                    .flatMap(leads -> Observable.fromIterable(leads)
                            .doOnNext(lead -> lead.setStatusName(mStatusNames.get(lead.getStatusId())))
                            .toList().toObservable())
                    .cache();  // Cache result to avoid re-fetching on rotation
        }
        return mLeadsObservable;
    }

    private Observable<List<LeadStatus>> getStatuses() {
        return mNetworkApi.getAccount(getLogin(), getHash())
                .map(responseOuter -> responseOuter.response.account.mLeadStatuses)
                .flatMap(leadStatuses -> Observable.fromIterable(leadStatuses)
                        .doOnNext(status -> mStatusNames.put(status.getId(), status.getName()))
                        .toList().toObservable());
    }

    @NonNull
    private String getLogin() {
        return mContext.getString(R.string.login);
    }

    @NonNull
    private String getHash() {
        return mContext.getString(R.string.hash);
    }
}
