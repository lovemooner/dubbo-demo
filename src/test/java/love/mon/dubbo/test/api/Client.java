package love.mon.dubbo.test.api;


import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import love.moon.dubbo.demo.service.DemoService;

import static love.mon.dubbo.test.api.Server.REGISTRY_ADDRESS;
import static love.mon.dubbo.test.api.Server.VERSION;

/**
 * Author: lovemooner
 * Date: 2017/5/4 15:32
 */
public class Client {
    public static void main(String[] args) {
// 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubbo-demo");

// 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(REGISTRY_ADDRESS);

// 注意：ReferenceConfig 为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
// 引用远程服务
        ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(DemoService.class);
        reference.setVersion(VERSION);

// 和本地bean一样使用xxxService
        DemoService demoService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        System.out.println(demoService.getClass().getDeclaredMethods());
        String result= demoService.sayHello("Moon");
        System.out.println(result);
    }
}

