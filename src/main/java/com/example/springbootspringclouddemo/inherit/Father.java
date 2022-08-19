package com.example.springbootspringclouddemo.inherit;

public class Father {

    private String a = "a";

    String b = "b";

    protected String c = "c";

    public String d = "d";

    private void a() {
        System.out.println("fu a。。。");
    }

    void b() {
        System.out.println("fu b。。。");
    }

    protected void c() {
        System.out.println("fu c。。。");
    }

    public void d() {
        System.out.println("fu d。。。");
    }

    public void call(String method) {
        switch (method) {
            case "a":
                this.a();
                System.out.println(this.a);
                break;
            case "b":
                this.b();
                System.out.println(this.b);
                break;
            case "c":
                this.c();
                System.out.println(this.c);
                break;
            case "d":
                this.d();
                System.out.println(this.d);
                break;
            default:
                break;
        }
    }
}
