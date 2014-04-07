package za.co.prescient.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import za.co.prescient.model.UserDetail;
import za.co.prescient.repository.UserDetailRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    UserDetailRepository userDetailRepository;

    LoginService loginService;
    String userName ;
    String password ;

    @Before
    public void setup() {
        userName = "testUserName";
        password = "testPassword";
        loginService = new LoginService();
        setField(loginService, "userDetailRepository", userDetailRepository);
    }

    @Test
    public void shouldCallUserRepositoryForLogin() {
        UserDetail userDetail = mock(UserDetail.class);
        when(userDetailRepository.findByUserNameAndPassword(userName, password)).thenReturn(userDetail);
        loginService.login(userName, password);
        verify(userDetailRepository, times(1)).findByUserNameAndPassword(userName, password);
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenUserRepositoryReturnsNullForLogin() {
        when(userDetailRepository.findByUserNameAndPassword(userName, password)).thenReturn(null);
        loginService.login(userName, password);
    }


    @Test
    public void shouldReturnSameObjectWhenUserRepositoryReturnsNotNullForLogin() {
        UserDetail userDetail = mock(UserDetail.class);
        when(userDetailRepository.findByUserNameAndPassword(userName, password)).thenReturn(userDetail);
        UserDetail result = loginService.login(userName, password);
        assertEquals(result, userDetail);
    }


}
