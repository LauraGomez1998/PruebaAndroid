package co.com.ceiba.mobile.pruebadeingreso.base.di;

import android.app.Application;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.base.App;
import co.com.ceiba.mobile.pruebadeingreso.base.di.module.ActivityModule;
import co.com.ceiba.mobile.pruebadeingreso.base.di.module.NetworkModule;
import co.com.ceiba.mobile.pruebadeingreso.base.di.module.RepositoryModule;
import co.com.ceiba.mobile.pruebadeingreso.base.di.module.UseCaseModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Component ID: AppComponent.java <br>
 * Description: Single Dagger unit to resolve dependencies. Dagger modules must be included here.
 * Also, include related module injection methods at ViewModel level as well. <br>
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
@Singleton
@Component(modules = {NetworkModule.class, RepositoryModule.class, ActivityModule.class, UseCaseModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
