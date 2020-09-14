package co.com.ceiba.mobile.pruebadeingreso.base.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import co.com.ceiba.mobile.pruebadeingreso.base.di.FactoryViewModel;
import co.com.ceiba.mobile.pruebadeingreso.base.di.annotation.ViewModelKey;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.MainActivityViewModel;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.PostActivityViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Component ID: ViewModelModule.java <br>
 * Description: Defines which ViewModels will use dependency injection. <br>
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
@Module
abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    abstract ViewModel bindMainActivityViewModel(MainActivityViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostActivityViewModel.class)
    abstract ViewModel bindPostActivityViewModel(PostActivityViewModel viewModel);
}
