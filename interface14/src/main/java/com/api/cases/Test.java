package com.api.cases;

public class Test {
    public static void main(String[] args) {
        System.out.println(Math.abs("161135681".hashCode()% 10));
        System.out.println(Math.abs("161135681".hashCode()% 39));
    }
}
