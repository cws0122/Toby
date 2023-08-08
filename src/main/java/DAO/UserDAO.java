package DAO;

import VO.UserVO;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import util.ConnectionMaker;
import util.SimpleConnectionMaker;

import java.sql.*;

public class UserDAO implements ConnectionMaker {
    Logger log = LoggerFactory.getLogger(getClass());
    String url = "jdbc:mariadb://localhost:3306/Toby";
    String user = "root";
    String password = "1234";

    public UserDAO() {
//        Connection conn = getConnection();
    }

    private ConnectionMaker cm;
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
    public void add(UserVO vo) throws ClassNotFoundException , SQLException{
        String sql = "insert into user(id, password, name) values(?,?,?);";

        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1 , vo.getId());
        ps.setString(2 , vo.getPassword());
        ps.setString(3 , vo.getName());
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public UserVO get(String id) throws ClassNotFoundException , SQLException {
        UserVO vo = new UserVO();

        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(
                "select * from user where id = ?"
        );
        ps.setString(1 , id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        vo.setId(rs.getString("id"));
        vo.setPassword(rs.getString("password"));
        vo.setName(rs.getString("name"));

        rs.close();
        ps.close();
        conn.close();

        return vo;
    }

}
