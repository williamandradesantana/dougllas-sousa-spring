package io.github.williamandradesatana.rentalCompany.modules.database;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseTest {

    static Connection connection;

    @BeforeAll
    static void setUpDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        connection.createStatement().execute("create table users (id int, name varchar) ");
    }

    @BeforeEach
    void insertUserTest() throws SQLException {
        connection.createStatement().execute("insert into users(id, name) values (1, 'William')");
    }

    @Test
    void testUserExists() throws SQLException {
        var result = connection.createStatement().execute("select u.name from users u where name = 'William' ");

        assertTrue(result);
    }

    @Test
    @Disabled
    void testUserExistsDisabled() throws SQLException {
        var result = connection.createStatement().execute("select u.name from users u where name = 'William' ");

        assertTrue(result);
    }

    @AfterAll
    static void closeDatabase() throws SQLException {
        connection.close();
    }
}
