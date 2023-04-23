package To;

import SQL.Connection_SQL;
import Util.ViewBaseServlet;
import main.Comment.getComment;
import org.thymeleaf.util.StringUtils;
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

@WebServlet("/home")
public class toHome extends ViewBaseServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String only_user = req.getParameter("only_user");
        if (Objects.equals(only_user,"null")){
            only_user = "";
        }
        session.setAttribute("only_user",only_user);
        Integer page = 1;
        session.setAttribute("page",1);
        String page_str = req.getParameter("page");

        if (!StringUtils.isEmpty(page_str)){
            page = Integer.valueOf(page_str);
        }
        session.getAttribute("user");
        session.getAttribute("login");
        getComment gc = new getComment();
        Integer last_page = 0;
        try {
            last_page = count_page(only_user);
            session.setAttribute("comment",gc.getcomment(page,only_user));
            session.setAttribute("last_page",last_page);
            session.setAttribute("page",page);
            session.setAttribute("page_center",page+"/"+last_page);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(page > last_page){
            session.setAttribute("page",page-1);
        }
        else if(page <= 0){
            session.setAttribute("page",1);
        }

        super.processTemplate("index",req,resp);
    }
    public int count_page(String only_user) throws SQLException{
        Connection con = new Connection_SQL().getCon();
        int count_page = 1;
        String cmd = "select count(*) from discuss";
        if (!StringUtils.isEmpty(only_user)){
            cmd = "select count(*) from discuss where user=\""+only_user+"\"";
        }
        PreparedStatement run = con.prepareStatement(cmd);
        ResultSet result = run.executeQuery(cmd);
        if (result.next()){
            count_page = (result.getInt(1) +4)/5;
        }
        result.close();
        run.close();
        con.close();
        return count_page;
    }

}

