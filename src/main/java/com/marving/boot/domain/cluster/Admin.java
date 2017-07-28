package com.marving.boot.domain.cluster;


/**
 * @author chenssy
 * @date 2017/6/3
 * @since v1.0.0
 */
public class Admin {

    private String id;

    private String userName;

    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
