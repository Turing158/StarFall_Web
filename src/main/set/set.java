package main.set;

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
@WebServlet("/set")
public class set extends ViewBaseServlet {
    private String name;
    private String date;
    private String introduce;
    private String email;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("display_me") == null){
            session.setAttribute("display_me","block");
            session.setAttribute("display_i","none");
            session.setAttribute("display_p","none");
        }
        String user_session = (String) session.getAttribute("user");
        try {
            getMassage(user_session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.setAttribute("name",this.name);
        session.setAttribute("date",this.date);
        session.setAttribute("introduce",this.introduce);
        session.setAttribute("email",this.email);
        super.processTemplate("set",req,resp);
    }
    public void getMassage(String user) throws SQLException {
        Connection con = new Connection_SQL().getCon();
        String cmd = "select * from web_user where user=\""+user+"\"";
        PreparedStatement run = con.prepareStatement(cmd);
        ResultSet result = run.executeQuery(cmd);
        while(result.next()){
            this.name = result.getString("name");
            this.date = result.getString("date");
            this.introduce = result.getString("introduce");
            this.email = result.getString("email");
        }
        result.close();
        run.close();
        con.close();
    }
}
