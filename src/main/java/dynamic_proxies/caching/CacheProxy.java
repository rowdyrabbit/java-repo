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
            if (args.length > 0) {
                if (args[0] instanceof Purchase) {
                    Purchase purchase = (Purchase) args[0];
                    //This is a sysout of what we might write to a redis store. It means we don't need to tie this caching logic into our main class logic.
                    System.out.println("Someone from "+purchase.getCustomerSuburb() + " just bought a "+purchase.getItemDescription()+ "!");
                } else {
                    System.out.println("Proxy for a method which does not have a purchase as the first argument: " + args[0]);
                }
            } else {
                System.out.println("Proxy for a method that is being passed with no arguments.");
            }
            result = method.invoke(realObject, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("Unexpected invocation exception: " + e.getMessage(), e);
        } finally {
            System.out.println("After method invocation with method name: " + method.getName());
        }
        return result;
    }
}
