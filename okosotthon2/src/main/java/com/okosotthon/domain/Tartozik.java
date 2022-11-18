package com.okosotthon.domain;

public class Tartozik {
    private int felhid;
    private int lakasid;

    public Tartozik(int felhid, int lakasid) {
        this.felhid = felhid;
        this.lakasid = lakasid;
    }

    public int getFelhid() {
        return felhid;
    }

    public void setFelhid(int felhid) {
        this.felhid = felhid;
    }

    public int getLakasid() {
        return lakasid;
    }

    public void setLakasid(int lakasid) {
        this.lakasid = lakasid;
    }
}
