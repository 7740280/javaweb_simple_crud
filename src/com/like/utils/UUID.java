package com.like.utils;

public class UUID
{
    public static String getUUID()
    {
        return java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
