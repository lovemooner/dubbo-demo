package love.moon.dubbo.demo;

import love.moon.dubbo.demo.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lovemooner on 2017/5/2.
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getId());
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();

        DemoService demoService = (DemoService)context.getBean("demoServiceImpl"); // 获取远程服务代理
        String result = demoService.sayHello("world"); // 执行远程方法

        System.out.println( result +"==============================="); // 显示调用结果
    }
}
