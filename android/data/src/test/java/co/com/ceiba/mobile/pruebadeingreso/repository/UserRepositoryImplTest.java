package co.com.ceiba.mobile.pruebadeingreso.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.builder.UserEntityBuilder;
import co.com.ceiba.mobile.pruebadeingreso.local.model.UserEntity;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.remote.UserService;
import co.com.ceiba.mobile.pruebadeingreso.remote.model.user.GetUsersResp;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.internal.RealmCore;
import io.realm.log.RealmLog;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(RobolectricTestRunner.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@SuppressStaticInitializationFor("io.realm.internal.Util")
@PrepareForTest({Realm.class, RealmConfiguration.class, RealmQuery.class, RealmResults.class, RealmCore.class, RealmLog.class})
public class UserRepositoryImplTest {

    private UserRepositoryImpl userRepository;

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Mock
    private UserService userService;

    private Gson gson;

    private Realm realmMock;

    private RealmQuery<UserEntity> userQuery;

    private RealmResults<UserEntity> userResults;

    private List<UserEntity> entityList;


    @Before
    public void setUp() throws Exception {
        gson = new GsonBuilder().create();
        MockitoAnnotations.initMocks(this);
        userRepository = new UserRepositoryImpl(userService);

        mockStatic(Realm.class);
        mockStatic(RealmConfiguration.class);
        mockStatic(RealmCore.class);
        realmMock = PowerMockito.mock(Realm.class);
        RealmCore.loadLibrary(any(Context.class));
        whenNew(RealmConfiguration.class).withAnyArguments().thenReturn(PowerMockito.mock(RealmConfiguration.class));
        when(Realm.getInstance(any(RealmConfiguration.class))).thenReturn(realmMock);
        when(Realm.getInstance(null)).thenReturn(realmMock);
        when(Realm.getDefaultInstance()).thenReturn(realmMock);

        // UserEntity
        entityList = new ArrayList<>();
        UserEntity userEntity = new UserEntityBuilder().build();
        entityList.add(userEntity);

        userQuery = mock(RealmQuery.class);
        userResults = mock(RealmResults.class);
        when(userResults.iterator()).thenReturn(entityList.iterator());
        when(userResults.size()).thenReturn(entityList.size());
        when(realmMock.where(UserEntity.class)).thenReturn(userQuery);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUserList() throws Exception {
        Type listType = new TypeToken<List<GetUsersResp>>() {
        }.getType();
        List<GetUsersResp> resp = gson.fromJson(readFromFile("/GetUsersResp.json"), listType);

        when(userService.getUsersList()).thenReturn(Single.just(resp));

        List<User> response = userRepository.getUserList().blockingGet();

        verify(userService).getUsersList();
        assert (response != null);
    }

    private String readFromFile(String filename) throws IOException {
        InputStream is = getClass().getResourceAsStream(filename);
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        byte[] b = new byte[4096];

        while ((i = is.read(b)) != -1) {
            stringBuilder.append(new String(b, 0, i));
        }

        return stringBuilder.toString();
    }

    @Test
    public void getLocalUserList() {
        when(realmMock.where(UserEntity.class).findAll()).thenReturn(userResults);

        List<User> response = userRepository.getLocalUserList();

        assert (!response.isEmpty());
        assert (response.size() == userResults.size());
    }
}