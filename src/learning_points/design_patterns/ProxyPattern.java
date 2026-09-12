package learning_points.design_patterns;

import java.lang.annotation.Target;
import java.lang.reflect.Proxy;

public class ProxyPattern {
    public static void main() {

        UserService taget = new UserServiceImpl();
        UserService proxy = (UserService) Proxy.newProxyInstance(
            Target.class.getClassLoader(), 
            Target.class.getInterfaces(),
            ( proxyObj, method, args )->{
                System.out.println("Before");
                method.invoke(proxyObj, args);
                System.out.println("After");
                return null;
            } 
        );

    }
}

interface UserService {
    void save();
}

class UserServiceImpl implements UserService {

    public void save(){
        System.out.println("User Save");
    }
    
}