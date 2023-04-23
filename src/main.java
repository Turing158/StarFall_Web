import Util.MailUtil;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.Random;

public class main {

    public static void main(String args[]) throws EmailException, InterruptedException {
//        Scanner cin = new Scanner(System.in);
//
//        Meil mail = new Meil();
//        //实体化meil类
//        mail.setHost("smtp.qq.com"); //网易163信箱的端口，别的邮箱不是这个
//        mail.setSender("icethestral@vip.qq.com"); //发送者邮箱
//        mail.setReceiver("15818961209@163.com"); //接受者邮箱
//        mail.setUsername("15818961209@163.com"); //用户名
//        mail.setPassword("fbgubsatkcwxjddf"); //填自己的操作码
//        mail.setSubject("0000000000");
//        String message = cin.next();
//        System.out.println(message); //自行输入邮件内容
//        mail.setMessage("hehehe");
//        mail.setMessage(message);
//        for (int i = 0; i < 1; i++) { //不要坏坏的打这个for循环的主意
//            new MeilUtil().send(mail);
//            //发送
//            System.out.println("sueecess!");
//        }
//        SimpleEmail mail = new SimpleEmail();
//        mail.setHostName("smtp.qq.com");
//        mail.setAuthenticator(new DefaultAuthenticator("1322621134@qq.com", "fbgubsatkcwxjddf"));
//        mail.setSSLOnConnect(true);//设置ssl连接
//        mail.setSubject("标题");
//        mail.setMsg("内容");
//        mail.addTo("15818961209@163.com");
//        mail.send();
//        MailUtil mail = new MailUtil();
//        mail.reg_mail("15818961209@163.com","123456");
//        char[] num_letter = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
//        Random r = new Random();
//
//        for (int i = 0; i < 10; i++) {
//            String a= "";
//            for (int j = 0; j < 6; j++) {
//                a = a + num_letter[r.nextInt(num_letter.length-1)];
//            }
//            System.out.println(a);
//        }
//        System.out.println("a");
//        Thread.sleep(1000);
//        System.out.println("a");
    }
}
