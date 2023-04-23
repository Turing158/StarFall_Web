package main.set;

import Util.GetCode;
import Util.MailUtil;
import Util.ViewBaseServlet;
import org.apache.commons.mail.EmailException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/set_send")
public class setSandCode extends ViewBaseServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("display_me","none");
        session.setAttribute("display_p","block");
        session.setAttribute("display_i","none");
        String email = (String) session.getAttribute("email");
        MailUtil mail = new MailUtil();
        GetCode getCode = new GetCode();
        String code = getCode.getcode();
        try {
            mail.set_mail(email,code);
        } catch (EmailException e) {
            e.printStackTrace();
        }
        super.processTemplate("set",req,resp);
    }
}
