package bg.haemimont.coursera.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class CompletionTimeInclude implements CommandLineRunner {

    @Value("${spring.datasource.username}")
    private String USER;

    @Value("${spring.datasource.password}")
    private String PASSWORD;

    @Value("${spring.datasource.url}")
    private String JDBC_URL;

    private void checkAndAddField() {
        Connection connection = null;
        Statement statement = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            statement = connection.createStatement();

            // Check if the field exists in the table using information_schema
            ResultSet resultSet = statement.executeQuery(
                    "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS " +
                            "WHERE TABLE_NAME = 'students_courses_xref' AND COLUMN_NAME = 'completion_date'"
            );

            boolean fieldExists = resultSet.next();

            if (!fieldExists) {
                // If the field doesn't exist, add it to the table
                statement.executeUpdate("ALTER TABLE students_courses_xref ADD completion_date DATETIME");
                System.out.println("Field 'completion_date' added successfully.");
            } else {
                System.out.println("Field 'completion_date' already exists.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        checkAndAddField();
    }
}
