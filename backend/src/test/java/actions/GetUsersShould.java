package actions;

import com.backend.actions.GetAllUsers;
import com.backend.repository.BackEndRepository;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GetUsersShould {
    private BackEndRepository backEndRepository;
    private GetAllUsers getAllUsers;

    @Before
    public void setUp() {
        backEndRepository = mock(BackEndRepository.class);
        getAllUsers = new GetAllUsers(backEndRepository);
    }

    @Test
    public void get_all_users() {
        getAllUsers.execute();
        verify(backEndRepository).getAll();
    }
}
