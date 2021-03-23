package com.shani.repository.impl;

import com.shani.domain.Lamp;
import com.shani.exception.EntityNotFoundException;
import com.shani.repository.LampRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LampRepositoryImpl implements LampRepository<Long, Lamp> {

    private static final String POSTGRES_DRIVER_NAME = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:";
    private static final int DATABASE_PORT = 5432;
    private static final String DATABASE_NAME = "/lamp_db";
    private static final String DATABASE_LOGIN = "postgres";
    private static final String DATABASE_PASSWORD = "postgres";

    private static final String ID = "id";
    private static final String LAMP_COUNTRY = "lamp_country";
    private static final String ROOM_NAME = "room_name";
    private static final String WORK = "work";

    @Override
    public Lamp save(Lamp lamp) {

        System.out.println(lamp);
        final String insertInto = "insert into lamps(lamp_country,work,room_name) " +
                "values(?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName(POSTGRES_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DATABASE_URL).append(DATABASE_PORT).append(DATABASE_NAME);

        String jdbcURL = stringBuilder.toString();



        try {
            connection = DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);

            statement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);


            statement.setString(1, lamp.getLampCountry());
            statement.setString(2, String.valueOf(lamp.isWork()));
            statement.setString(3, lamp.getRoomName());

            int affectedRows = statement.executeUpdate();
            long id = 0;

            if (affectedRows > 0) {
                try {
                    ResultSet rs = statement.getGeneratedKeys();
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            return findById(id);


        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<Lamp> findAll() {
        final String findAllQuery = "select * from lamps " +
                "order by id;";

        List<Lamp> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            Class.forName(POSTGRES_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DATABASE_URL).append(DATABASE_PORT).append(DATABASE_NAME);

        String jdbcURL = stringBuilder.toString();



        try {
            connection = DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);

            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);

            while (rs.next()) {
                result.add(parseResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }

        return result;
    }

    @Override
    public Lamp findById(Long key) {
        final String findByIdQuery = "select * from lamps where id = ?";

        Connection connection;
        PreparedStatement statement;
        ResultSet rs;

        try {
            Class.forName(POSTGRES_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DATABASE_URL).append(DATABASE_PORT).append(DATABASE_NAME);

        String jdbcURL = stringBuilder.toString();



        try {
            connection = DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);

            statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, key);

            rs = statement.executeQuery();

            if (rs.next()) {
                return parseResultSet(rs);
            } else {
                throw new EntityNotFoundException("Lamp with ID: " + key + " not found");
            }


        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Lamp update(Lamp lamp) {
       final String findByIdQuery = "update lamps " +
               "set " +
               "lamp_country = ?, " +
               "work = ?" +
               "room_name = ?, " +
               "where id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            Class.forName(POSTGRES_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DATABASE_URL).append(DATABASE_PORT).append(DATABASE_NAME);

        String jdbcURL = stringBuilder.toString();



        try {
            connection = DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);

            statement = connection.prepareStatement(findByIdQuery);
            statement.setString(1, lamp.getLampCountry());
            statement.setString(2, String.valueOf(lamp.isWork()));
            statement.setString(3, lamp.getRoomName());
            statement.setLong(4, lamp.getId());

            statement.executeUpdate();

            return findById(lamp.getId());


        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Long delete(Lamp lamp) {
        final String findByIdQuery = "delete from lamps where id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            Class.forName(POSTGRES_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DATABASE_URL).append(DATABASE_PORT).append(DATABASE_NAME);

        String jdbcURL = stringBuilder.toString();



        try {
            connection = DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);

            statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, lamp.getId());

            int deletedRows = statement.executeUpdate();

            return (long) deletedRows;


        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    private Lamp parseResultSet(ResultSet rs) throws SQLException {
        Lamp lamp = new Lamp();
        lamp.setId(rs.getLong(ID));
        lamp.setLampCountry(rs.getString(LAMP_COUNTRY));
        lamp.setRoomName(rs.getString(ROOM_NAME));
        lamp.setWork(Boolean.parseBoolean(rs.getString(WORK)));
        return lamp;
    }

    public Lamp updateLight(Lamp lamp) {
        final String updateQuery = "update lamps " +
                "set " +
                "work = ?" +
                "where id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName(POSTGRES_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DATABASE_URL).append(DATABASE_PORT).append(DATABASE_NAME);

        String jdbcURL = stringBuilder.toString();



        try {
            connection = DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);

            statement = connection.prepareStatement(updateQuery);
            statement.setString(1, String.valueOf(!lamp.isWork()));
            statement.setLong(2, lamp.getId());

            statement.executeUpdate();

            return findById(lamp.getId());


        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }
}
