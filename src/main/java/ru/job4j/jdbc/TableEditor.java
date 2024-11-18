package ru.job4j.jdbc;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        connection =  DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"));
    }

    public void createTable(String tableName) throws Exception {
        execute(
                "CREATE TABLE IF NOT EXISTS %s();",
                tableName
        );
    }

    public void dropTable(String tableName) throws Exception {
        execute(
                "DROP TABLE %s;",
                tableName
        );
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        execute(
                "ALTER TABLE %s%sADD COLUMN IF NOT EXISTS %s %s",
                tableName,
                System.lineSeparator(),
                columnName,
                type
        );
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        execute(
                "ALTER TABLE %s%sDROP COLUMN IF EXISTS %s ",
                tableName,
                System.lineSeparator(),
                columnName
        );
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        execute(
                "ALTER TABLE %s%s RENAME COLUMN %s TO %s",
                tableName,
                System.lineSeparator(),
                columnName,
                newColumnName
        );
    }

    private void execute(String... strings) throws Exception {
        String sql;
        try (Statement statement = connection.createStatement()) {
            switch (strings.length) {
                case 2:
                    sql = String.format(
                            strings[0],
                            strings[1]
                    );
                    break;
                case 4:
                    sql = String.format(
                            strings[0],
                            strings[1],
                            strings[2],
                            strings[3]
                    );
                    break;
                case 5:
                    sql = String.format(
                            strings[0],
                            strings[1],
                            strings[2],
                            strings[3],
                            strings[4]
                    );
                    break;
                default:
                    sql = String.format(
                            ""
                    );
                    break;
            }
            statement.execute(sql);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader("./data/app6.properties"));
        TableEditor tableEditor = new TableEditor(properties);

        tableEditor.createTable("students");
        System.out.println(tableEditor.getTableScheme("students"));

        tableEditor.addColumn("students", "name", "text");
        System.out.println(tableEditor.getTableScheme("students"));

        tableEditor.addColumn("students", "course", "int");
        System.out.println(tableEditor.getTableScheme("students"));

        tableEditor.addColumn("students", "avg_mark", "float");
        System.out.println(tableEditor.getTableScheme("students"));

        tableEditor.dropColumn("students", "avg_mark");
        System.out.println(tableEditor.getTableScheme("students"));

        tableEditor.renameColumn("students", "name", "full_name");
        System.out.println(tableEditor.getTableScheme("students"));

        tableEditor.dropTable("students");
        tableEditor.close();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}