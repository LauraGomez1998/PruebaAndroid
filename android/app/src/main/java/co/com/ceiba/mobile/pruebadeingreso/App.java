package co.com.ceiba.mobile.pruebadeingreso;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();
        //this.initDagger();
        Realm.init(this);
        /*byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("users.realm")
                .deleteRealmIfMigrationNeeded()
                .encryptionKey(key)
                .build();
        try {
            Realm realm = Realm.getInstance(configuration);
            realm.close();
        } catch (RealmFileException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    private void initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this);
    }
}
