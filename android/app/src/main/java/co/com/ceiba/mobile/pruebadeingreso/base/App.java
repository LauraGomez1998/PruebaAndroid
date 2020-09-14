package co.com.ceiba.mobile.pruebadeingreso.base;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.base.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;

/**
 * Component ID: App.java <br>
 * Description: Global component for defining application level instances, like Realm and Dependency Injection. <br>
 * Author: <a href="mailto:lauragomez.lg247@gmail.com">Laura Gómez</a>
 * <p/>
 * Revision Change
 * <table>
 * <tr>
 * <th>Author</th><th>Date</th><th>Version</th><th>Change-Description</th>
 * </tr>
 * <tr>
 * <td>Laura Gómez</td><td>9/12/2020</td><td>1.0</td><td>Initial</td>
 * </tr>
 * </table>
 */
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
