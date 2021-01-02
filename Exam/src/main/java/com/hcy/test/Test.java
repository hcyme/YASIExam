package com.hcy.test;

import com.hcy.jdbc.JdbcUtils;
import com.hcy.utils.DBUtils;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        Connection con = DBUtils.getInstance().openConnection();
        System.out.println(con);
    }
}
