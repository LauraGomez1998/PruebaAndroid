package co.com.ceiba.mobile.pruebadeingreso.base.di.module;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepository;
import co.com.ceiba.mobile.pruebadeingreso.usecase.UserUseCase;
import co.com.ceiba.mobile.pruebadeingreso.usecase.UserUseCaseImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Component ID: UseCaseModule.java <br>
 * Description: Provides dependency injection to access to all use cases in the app. <br>
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
@Module(includes = RepositoryModule.class)
public class UseCaseModule {

    @Provides
    @Singleton
    UserUseCase provideUserUseCase(UserRepository userRepository) {
        return new UserUseCaseImpl(userRepository);
    }
}
