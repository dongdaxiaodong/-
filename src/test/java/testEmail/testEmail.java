package testEmail;

import io.github.biezhi.ome.OhMyEmail;
import io.github.biezhi.ome.SendMailException;
import io.netty.handler.codec.MessageAggregationException;

public class testEmail {
    public static void main(String[] args){
        try {
            testSendText();
        }
        catch (Exception e){
            System.out.println(e.toString());
                System.out.println("ok");
        }

    }
    public static void testSendText() throws MessageAggregationException,SendMailException{
        OhMyEmail.config(OhMyEmail.SMTP_QQ(true),"1346536639@qq.com","vdhhqbliszueihfi");
        OhMyEmail.subject("这是一封测试TEXT邮件")
                .text("hello")
                .from("colin's email")
                .to("20175055@stu.neu.edu.cn")
                .send();
    }
}
