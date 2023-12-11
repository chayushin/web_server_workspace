package com.sh.mybatis;

public class App {
    public int sum(int a, int b) {
        return a + b;
    }

    public int random() {
        int n = (int) (Math.random() * 100) + 1;
        return n;
    }
}
