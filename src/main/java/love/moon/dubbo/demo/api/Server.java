package love.moon.dubbo.demo.api;

import com.alibaba.dubbo.config.*;
import love.moon.dubbo.demo.service.DemoService;
import love.moon.dubbo.demo.service.DemoServiceImpl;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2017/5/4 13:18
 */
public class Server {

    public static final String REGISTRY_ADDRESS="zookeeper://slc11fsp.us.oracle.com:2181";
    public final static String APP_NAME="dubbo-demo";
    public static final String VERSION="1.0.1";

    public  static void start() {
        DemoService demoService = new DemoServiceImpl();
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName(APP_NAME);

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(REGISTRY_ADDRESS);
//        registry.setUsername("aaa");
//        registry.setPassword("bbb");

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(12345);
        protocol.setThreads(200);

// 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

// 服务提供者暴露服务配置
        ServiceConfig<DemoService> service = new ServiceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setInterface(DemoService.class);
        service.setRef(demoService);
        service.setVersion(VERSION);

    // 暴露及注册服务
        service.export();
    }
    public static void main(String[]args) throws IOException {
        Server.start();
        System.in.read();
    }

}
