package gmk57.crmclient.data;

import gmk57.crmclient.data.Wrappers.ResponseOuter;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {
    @GET("v2/json/leads/list")
    Observable<ResponseOuter> getLeads(@Query("USER_LOGIN") String login,
                                       @Query("USER_HASH") String hash);

    @GET("v2/json/accounts/current")
    Observable<ResponseOuter> getAccount(@Query("USER_LOGIN") String login,
                                         @Query("USER_HASH") String hash);
}
