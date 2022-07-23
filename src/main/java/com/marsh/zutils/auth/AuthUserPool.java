package com.marsh.zutils.auth;


public class AuthUserPool {

    public static final ThreadLocal<UserIdentity> POOL = new ThreadLocal<>();
}
