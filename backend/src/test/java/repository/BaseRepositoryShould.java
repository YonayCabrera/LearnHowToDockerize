package repository;

import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

public class BaseRepositoryShould {
    protected static String dbPassword = "1234";
    protected static String dbUser = "backend";
    private static String productionDb = "backend";
    protected static String testDb = "test_backend";
    protected static String connectionBaseUrl = "jdbc:postgresql://localhost:8000/";
    protected static String connectionTestDatabase = connectionBaseUrl + testDb + "?user=" + dbUser + "&password=" + dbPassword;

    @BeforeClass
    public static void createDatabase() {
        Sql2o sql2o = getSql2o(connectionBaseUrl);
        try (Connection connection = sql2o.open()) {
            connection.createQuery("DROP DATABASE IF EXISTS " + testDb)
                    .executeUpdate();
            connection.createQuery("CREATE DATABASE " + testDb + " WITH TEMPLATE " + productionDb + " OWNER " + dbUser)
                    .executeUpdate();
        }
    }

    @AfterClass
    public static void dropDatabase() {
        Sql2o sql2o = getSql2o(connectionBaseUrl);
        try (Connection connection = sql2o.open()) {
            connection.createQuery("DROP DATABASE IF EXISTS " + testDb)
                    .executeUpdate();
        }
    }

    @Before
    public void setUpCascade() throws Exception {
        flushDatabase();
    }

    @After
    public void tearDownCascade() throws Exception {
        flushDatabase();
    }

    private void flushDatabase() {
        Sql2o sql2o = new Sql2o(connectionBaseUrl + testDb, dbUser, dbPassword);
        try (Connection connection = sql2o.open()) {
            deleteFromTables().forEach(tableName ->
                    connection.createQuery(
                            "DELETE FROM " + tableName)
                            .executeUpdate()
            );
        }
    }

    protected List<String> deleteFromTables() {
        return new ArrayList<>();
    }

    private static Sql2o getSql2o(String url) {
        return new Sql2o(url, dbUser, dbPassword);
    }
}
