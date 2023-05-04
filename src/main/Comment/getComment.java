package main.Comment;

import Util.Connection_SQL;
import org.thymeleaf.util.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class getComment {
    public List<Comment> getcomment(int page, String only_user)throws SQLException {
        Connection con = new Connection_SQL().getCon();
        List<Comment> list = new ArrayList<>();
        String cmd = "select * from login_web.discuss limit "+(page-1)*5+",5";
        if(!StringUtils.isEmpty(only_user)){
            cmd = "select * from login_web.discuss where user=\""+only_user+"\" limit "+(page-1)*5+",5";
//                 select * from  discuss          where user=         "a"      limit        6      ,5
        }
        PreparedStatement run = con.prepareStatement(cmd);
        ResultSet result = run.executeQuery(cmd);
        while(result.next()){
            String name = result.getString("name");
            String date = result.getString("date");
            String content = result.getString("content");
            String user = result.getString("user");
            list.add(new Comment(name,date,content,user));
        }
        result.close();
        run.close();
        con.close();
        return list;
    }
}
