package co.com.ceiba.mobile.pruebadeingreso.usecase;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.LinkedList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.builder.PostBuilder;
import co.com.ceiba.mobile.pruebadeingreso.builder.UserBuilder;
import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepository;
import io.reactivex.Single;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class UserUseCaseImplTest {

    private UserUseCaseImpl userUseCase;

    @Mock
    private UserRepository userRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userUseCase = new UserUseCaseImpl(userRepository);
    }

    @Test
    public void getUserListLocal() {
        User user = new UserBuilder().build();
        List<User> userList = new LinkedList<>();
        userList.add(user);

        when(userRepository.getLocalUserList()).thenReturn(userList);

        List<User> response = userUseCase.getUserList().blockingGet();

        verify(userRepository).getLocalUserList();
        Assert.assertEquals(response, userList);
    }

    @Test
    public void getUserListWebService() {
        List<User> userListLocal = new LinkedList<>();

        User user = new UserBuilder().build();
        List<User> userList = new LinkedList<>();
        userList.add(user);

        when(userRepository.getLocalUserList()).thenReturn(userListLocal);
        when(userRepository.getUserList()).thenReturn(Single.just(userList));

        List<User> response = userUseCase.getUserList().blockingGet();

        verify(userRepository).getUserList();
        Assert.assertEquals(userList, response);
    }

    @Test
    public void getUserPostsList() {
        Post post = new PostBuilder().build();
        List<Post> postList = new LinkedList<>();
        postList.add(post);

        User user = new UserBuilder().build();

        when(userRepository.getUserPostsList(user.getId())).thenReturn(Single.just(postList));

        List<Post> response = userUseCase.getUserPostsList(user.getId()).blockingGet();

        verify(userRepository).getUserPostsList(user.getId());
        Assert.assertEquals(postList, response);
    }
}