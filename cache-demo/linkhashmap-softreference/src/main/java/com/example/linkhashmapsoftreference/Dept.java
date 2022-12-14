package com.example.linkhashmapsoftreference;

public class Dept {

    private Long id;

    private byte[] bytes = new byte[1024*1024];

    public Dept(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(id+"将要被回收，赶紧想法自救吧....");
    }
}
