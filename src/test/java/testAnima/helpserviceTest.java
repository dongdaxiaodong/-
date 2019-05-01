package testAnima;

import com.alibaba.druid.pool.DruidDataSource;
import com.bladejava.models.helpservice;
import io.github.biezhi.anima.Anima;

import java.util.List;

public class helpserviceTest {
    public static void main(String[] args){
        Anima.open("jdbc:mysql://127.0.0.1:3306/dongdafuxue?useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true&serverTimezone=UTC","root","1346536639");
//        String course="4,1";
//        helpservice helpservice=Anima.select().from(helpservice.class)
//                .notNull("givestuid")
//                .where("getstuid",null)
//                .notNull("launchtime")
//                .where("matchtime",null)
//                .where("course",course)
//                .where("complete",null).one();
//        if(helpservice==null){
//            System.out.println("it's null");
//        }
//        else {
//            System.out.println(helpservice.getId()+" and "+helpservice.getCourse());
//        }
        new helpservice().set("getstuid","20175055").updateById(34);
//        Anima.update().from(com.bladejava.models.helpservice.class).where("id",34)
//                .set("getstuid","20175555")
//                .set("matchtime",System.currentTimeMillis()+"");
//        helpservice helpservice=Anima.select().from(com.bladejava.models.helpservice.class).where("id",34).one();
//        System.out.println(helpservice.getId());
        Anima.update().from(com.bladejava.models.helpservice.class).where("id",35)
                .set("getstuid","20883").execute();
    }
}
