import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseOperations extends DbCon{
    public String getValueByColumId(int id) throws SQLException {
        String execute = "Select * from homework.users where id = " + DbEnumUsers.id.getValue();
        ResultSet rs = executeSql( "Select * from users;" );
        rs.next();
        return rs.getString( id );
    }
}
