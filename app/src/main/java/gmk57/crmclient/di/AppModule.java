package gmk57.crmclient.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import gmk57.crmclient.R;
import gmk57.crmclient.data.NetworkApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
abstract class AppModule {

    @Provides
    @Singleton
    static NetworkApi provideNetworkApi(Context context) {
        String subdomain = context.getString(R.string.subdomain);

        return new Retrofit.Builder()
                .baseUrl("https://" + subdomain + ".amocrm.ru/private/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NetworkApi.class);
    }

    @Binds
    abstract Context bindContext(Application application);
}
