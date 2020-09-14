package co.com.ceiba.mobile.pruebadeingreso.base.di.module;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.base.di.annotation.ApplicationContext;
import co.com.ceiba.mobile.pruebadeingreso.remote.UserService;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepository;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepositoryImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Component ID: RepositoryModule.java <br>
 * Description: Provides dependency injection to access to all datasources connected to the app. <br>
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
@Module(includes = UseCaseModule.class)
public class RepositoryModule {

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }


    @Provides
    @Singleton
    UserRepository provideUserRepository(UserService userService) {
        return new UserRepositoryImpl(userService);
    }
}
