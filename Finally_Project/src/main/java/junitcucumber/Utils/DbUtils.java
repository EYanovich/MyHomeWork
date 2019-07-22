package junitcucumber.Utils;

import java.sql.*;

public class DbUtils {
    private DbUtils() {
    }

    private static String url = ConfigProperties.getProperty( "urlDb" );
    private static String user = ConfigProperties.getProperty( "userDb" );
    private static String password = ConfigProperties.getProperty( "passwordDb" );

    private static ResultSet executeSql(String query) throws SQLException {
        Connection con = DriverManager.getConnection( url, user, password );
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery( query );
        return rs;
    }

    public static String getValueByColumId(int columId, int userId) throws SQLException {
        String execute = "Select * from homework.users where id = " + userId;
        ResultSet rs = DbUtils.executeSql( execute );
        rs.next();
        return rs.getString( columId );
    }

}
