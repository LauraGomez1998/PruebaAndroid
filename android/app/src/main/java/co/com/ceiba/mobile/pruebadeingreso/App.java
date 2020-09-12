package co.com.ceiba.mobile.pruebadeingreso;

import android.app.Application;

import java.security.SecureRandom;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmFileException;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //this.initDagger();

        Realm.init(this);
        byte[] key = new byte[64];
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
        }
    }
}
