package com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("datasource.db_config");

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }

    private DBResourceManager() {
    }
}
