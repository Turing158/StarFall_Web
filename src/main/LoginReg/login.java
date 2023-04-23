package main.LoginReg;

import SQL.Connection_SQL;
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
import java.util.Objects;

@WebServlet("/confirm_login")
public class login extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        String code = req.getParameter("login_code");
        session.setAttribute("user",user);
        String name = null;
        int level = 0;
        String flag = null;
        try {
            flag = check_login(user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(Objects.equals(code, "")){
            session.setAttribute("tips","验证码不能为空");
            resp.sendRedirect("/login");
            super.processTemplate("login",req,resp);

        }
        else if(!Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("tips","验证码错误");
            resp.sendRedirect("/login");
            super.processTemplate("login",req,resp);
        }
        else if(Objects.equals(flag, "user_err")){
            session.setAttribute("tips","用户名不存在");
            resp.sendRedirect("/login");
            super.processTemplate("login",req,resp);
        }
        else if(Objects.equals(flag, "password_err")){
            session.setAttribute("tips","密码错误!");
            resp.sendRedirect("/login");
            super.processTemplate("login",req,resp);
        }
        else if(Objects.equals(flag, "success") && Objects.equals(code,session.getAttribute("code"))){
            try {
                name = getName(user);
                level = getLevel(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.setAttribute("user",user);
            session.setAttribute("password",password);
            session.setAttribute("login","1");
            session.setAttribute("name",name);
            session.setAttribute("level",level);
            resp.sendRedirect("/home");
        }
        session.setAttribute("code",null);
    }

    public String check_login(String user, String password) throws SQLException {
        Connection con = new Connection_SQL().getCon();
        String cmd = "select user,password from web_user where user=\""+user+"\"";
        String flag = "user_err";
        PreparedStatement run = con.prepareStatement(cmd);
        ResultSet result = run.executeQuery(cmd);
        String user_SQL = null;
        String password_SQL = null;
        while(result.next()){
            user_SQL = result.getString("user");
            password_SQL = result.getString("password");
        }
        if(Objects.equals(password,password_SQL) && Objects.equals(user,user_SQL)){
            flag = "success";
        }
        else if(Objects.equals(user, user_SQL)){
            flag = "password_err";
        }
        result.close();
        run.close();
        con.close();
        return flag;
    }
    public String getName(String user) throws SQLException{
        Connection con = new Connection_SQL().getCon();
        String cmd = "select name from web_user where user=\""+user+"\"";
        String name = null;
        PreparedStatement run = con.prepareStatement(cmd);
        ResultSet result = run.executeQuery(cmd);
        while (result.next()){
            name = result.getString("name");
        }
        result.close();
        run.close();
        con.close();
        return name;
    }
    public int getLevel(String user)throws SQLException{
        Connection con = new Connection_SQL().getCon();
        String cmd = "select level from web_user where user=\""+user+"\"";
        int level = 0;
        PreparedStatement run = con.prepareStatement(cmd);
        ResultSet result = run.executeQuery(cmd);
        while (result.next()){
            level = Integer.parseInt(result.getString("level"));
        }
        result.close();
        run.close();
        con.close();
        return level;
    }
}
