package cn.getech.test.mybatis;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setAge(18);
        user.setUsername("zhangsan");
        insert(user);
    }

    static void  insert(User user) {
        String sql = "insert into user(username,age) value(?,?);";
        Connection connection = JDBCUtils.open();

        try {
            //执行插入
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setInt(2,user.getAge());
            preparedStatement.execute();

            //如果是Query方法，对结果集硬解码
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                System.out.println("查询结果集：" + id + ",姓名：" + username);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(connection);
        }
    }
}