package com.backend.repository;

import com.backend.domain.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class BackEndRepositoryPostgreSql implements BackEndRepository{
    private final Sql2o sql2o;
    public BackEndRepositoryPostgreSql(String connection) {
        this.sql2o = sql2o(connection);
    }

    private Sql2o sql2o(String connectionUrl) {
        System.out.println(connectionUrl);
        return new Sql2o(connectionUrl, "backend", "1234");
    }

    @Override
    public List<User> getAll() {
        final String query = "SELECT * FROM users";
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(query).executeAndFetch(User.class);
        }
    }
}
