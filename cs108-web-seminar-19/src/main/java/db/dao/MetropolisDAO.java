package db.dao;

import db.DbContract;
import db.MyDBInfo;
import db.bean.Metropolis;
import db.pool.DataSourceMeh;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SameParameterValue")
public class MetropolisDAO {

    private final DataSourceMeh connectionPool;

    public MetropolisDAO(DataSourceMeh connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Metropolis> getMetropolises() {
        List<Metropolis> result = new ArrayList<>();

        try {
            Connection con = connectionPool.getConnectionFromPool();
            Statement stmt = con.createStatement();
            stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

            String viaContinentString =
                    "SELECT * FROM " + DbContract.MetropolisTable.TABLE_NAME + ";";
            PreparedStatement viaContinentStatement =
                    con.prepareStatement(viaContinentString);

            ResultSet rs = viaContinentStatement.executeQuery();

            while (rs.next()) {
                result.add(fetchMetropolis(rs));
            }

            // Returning to pool
            connectionPool.returnConnectionToPool(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Metropolis> getMetropolises(String continent) {
        List<Metropolis> result = new ArrayList<>();

        try {
            // Getting connection from pool
            Connection con = connectionPool.getConnectionFromPool();
            Statement stmt = con.createStatement();
            stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

            String viaContinentString =
                    "SELECT * FROM " + DbContract.MetropolisTable.TABLE_NAME + " WHERE continent = ?";
            // ? in the string will be replaced by parameters later
            PreparedStatement viaContinentStatement =
                    con.prepareStatement(viaContinentString);

            viaContinentStatement.setString(1, continent);
            // here we've provided the parameter Europe

            ResultSet rs = viaContinentStatement.executeQuery();
            // this carries out the query with the parameters we've set

            while (rs.next()) {
                result.add(fetchMetropolis(rs));
            }

            // Returning connection to pool
            connectionPool.returnConnectionToPool(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Metropolis fetchMetropolis(ResultSet result) throws SQLException {
        Metropolis metropolis = new Metropolis();
        metropolis.setName(result.getString(DbContract.MetropolisTable.COLUMN_NAME_METROPOLIS));
        metropolis.setContinent(result.getString(DbContract.MetropolisTable.COLUMN_NAME_CONTINENT));
        metropolis.setPopulation(result.getInt(DbContract.MetropolisTable.COLUMN_NAME_POPULATION));
        return metropolis;
    }

}
