package com.forum.projectlzy.entity;

/**
 * 存储当前线程下的user信息
 * @author 扬
 */
public class CurrentUserInfo {

    public static final ThreadLocal<User> USER_THREAD_LOCAL=new InheritableThreadLocal<>();

    public static void set(User user){
        USER_THREAD_LOCAL.set(user);
    }

    public static User get(){
        return USER_THREAD_LOCAL.get();
    }

    public static void remove(){
        USER_THREAD_LOCAL.remove();
    }

}
