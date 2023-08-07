package DAO;

import VO.UserVO;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;

import java.sql.*;

public class UserDAO {
    Logger log = LoggerFactory.getLogger(getClass());
    String url = "jdbc:mariadb://localhost:3306/Toby";
    String user = "root";
    String password = "1234";
    public void add(UserVO vo) throws ClassNotFoundException , SQLException{
        String sql = "insert into user(id, password, name) values(?,?,?);";

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (ClassNotFoundException e){
            log.debug("###### MariaDB JDBC를 불러오는데 실패 했습니다.");
            e.printStackTrace();
            return;
        }
        Connection conn = DriverManager.getConnection(url , user , password);
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
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (ClassNotFoundException e){
            log.debug("###### MariaDB JDBC를 불러오는데 실패 했습니다.");
            e.printStackTrace();
        }

        Connection conn = DriverManager.getConnection(url , user , password);
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
