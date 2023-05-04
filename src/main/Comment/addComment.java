package main.Comment;

import Util.Connection_SQL;
import Util.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@WebServlet("/add_comment")
public class addComment extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime ldt = LocalDateTime.now();
        HttpSession session = req.getSession();
        session.setAttribute("comment","block");
        session.setAttribute("home","none");
        req.setCharacterEncoding("utf-8");
        String content = req.getParameter("content");
        String date =LocalDate.now()+" "+ldt.getHour()+":"+ldt.getMinute()+":"+ldt.getSecond();
        String user = (String) session.getAttribute("user");
        String code = req.getParameter("comment_VerifyCode");
        session.setAttribute("comment_input",content);
        if(Objects.equals(code,session.getAttribute("code"))){
            try {
                add(date,content,user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.setAttribute("comment_tips","发话成功");
            session.setAttribute("comment_input",null);
            session.setAttribute("code",null);
        }
        else{
            session.setAttribute("comment_tips","验证码错误");
        }
        resp.sendRedirect("/home");
    }

    public void add(String date, String content, String user) throws SQLException {
        Connection con = new Connection_SQL().getCon();
        String name = null;
        String cmd_getName = "select name from login_web.web_user where user=\""+user+"\"";
        PreparedStatement run = con.prepareStatement(cmd_getName);
        ResultSet result = run.executeQuery(cmd_getName);
        while(result.next()){
            name = result.getString("name");
        }
        result.close();
        String cmd_add = "INSERT INTO login_web.discuss (date, content, user, name) VALUES ('"+date+"', '"+content+"', '"+user+"', '"+name+"')";
        run = con.prepareStatement(cmd_add);
        run.executeUpdate();
        run.close();
        con.close();
    }
}
