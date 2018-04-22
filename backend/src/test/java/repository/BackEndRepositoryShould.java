package repository;

import com.backend.domain.User;
import com.backend.repository.BackEndRepositoryPostgreSql;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.codehaus.groovy.runtime.InvokerHelper.asList;

public class BackEndRepositoryShould extends BaseRepositoryShould{

    @Override
    protected List<String> deleteFromTables() {
        return asList("users");
    }

    private Sql2o connection;
    private BackEndRepositoryPostgreSql backEndRepository;
    private User user;


    @Before
    public void given_a_repository_and_a_database() {
        connection = new Sql2o(connectionTestDatabase, dbUser, dbPassword);
        backEndRepository = new BackEndRepositoryPostgreSql(connectionTestDatabase);
        user = new User(1, "yonay","cabrera");

    }

    @Test
    public void get_all_users(){
        insertUser(user);

        List<User>users = backEndRepository.getAll();

        assertThat(user.getId()).isEqualTo(users.get(0).getId());
    }

    private void insertUser(User user) {
        try (Connection connection = this.connection.open()) {
            connection.createQuery("INSERT INTO users(name, surname)" +
                    " VALUES (:name, :surname)")
                    .addParameter("name", user.getName())
                    .addParameter("surname", user.getSurname()).executeUpdate();
        }
    }

}
