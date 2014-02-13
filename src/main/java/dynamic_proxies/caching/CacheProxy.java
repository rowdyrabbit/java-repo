package dynamic_proxies.caching;

import dynamic_proxies.domain.Purchase;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CacheProxy implements InvocationHandler {

    private Object realObject;

    public CacheProxy(Object obj) {
        realObject = obj;
    }

    public static Object newInstance(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new CacheProxy(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try {
            //Not sure if this is ok to mash casting logic into what is essentially an implementation agnostic class?
            Purchase purchase = (Purchase) args[0];

            //This is a sysout of what we might write to redis. It means we don't need to tie this caching logic into our main class logic.
            System.out.println("Someone from "+purchase.getCustomerSuburb() + " just bought a "+purchase.getItemDescription()+ "!");
            result = method.invoke(realObject, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        } finally {
            System.out.println("after method " + method.getName());
        }
        return result;
    }
}
