package org.geekbang.dependency.injection;

import org.geekbang.dependency.injection.annotation.UserGroup;
import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link Qualifier} 注解依赖注入
 * Created by eru on 2020/7/22.
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // superUser -> primary = true

    @Autowired
    @Qualifier("user")
    private User namedUser; // 指定 Bean id 或者名称

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers;

    @Bean
    @Qualifier
    public User user7(){
        return createUser(7L);
    }

    @Bean
    @Qualifier
    public User user8(){
        return createUser(8L);
    }

    @Bean
    @UserGroup
    public User user9(){
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public User user10(){
        return createUser(10L);
    }

    public static User createUser(Long id){
        User user = new User();
        user.setId(id);
        return user;
    }


    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        // 期待输出 superUser Bean
        System.out.println("demo.user = " + demo.user);
        System.out.println();
        // 期待输出 user Bean
        System.out.println("demo.namedUser = " + demo.namedUser);
        System.out.println();
        // 期待输出 superUser user user1 user2
        System.out.printf("demo.allUsers = %s, size=%d" , demo.allUsers, demo.allUsers.size());
        System.out.println();
        //// 期待输出 user1 user2
        System.out.println("demo.qualifiedUsers = " + demo.qualifiedUsers);
        System.out.println();
        //// 期待输出 user3 user4
        System.out.println("demo.groupedUsers = " + demo.groupedUsers);


        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }
}
