package com.athl.gulimall.order.code;


//双检锁实现单例模式
public class DemoClazz {
    private  static  volatile  DemoClazz instance;
    private  DemoClazz(){}
    public  static  DemoClazz getInstance(){
        if (instance==null){
            //第一层判断 判断是否实例化，有实例对象则直接返回，不需要其他操作。
            //如果没有实例化，则说明是程序第一次去获取实例对像，会进行一次加锁操作，只允许一个线程进入方法，
            //进入方法之后的判空操作是为了仅允许第一个进入的线程进行实例化，其他线程不允许实例化。
            synchronized (DemoClazz.class){
                //因为实例化操作仅需要进行一次同步，所以可以用第一次判空操作来进行避免。至于第二次判空操作，则是为了保证单例。
                if (instance==null){
                   instance= new DemoClazz();
                }
            }
        }
        return instance;
        
    }
}
