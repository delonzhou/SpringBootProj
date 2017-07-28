package com.marving.boot.domain.master;

/**
 * @author chenssy
 * @date 2017/6/3
 * @since v1.0.0
 */
public class UserInfo {
    private String id;

    private String name;

    private int status;

    private int db;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }
}
