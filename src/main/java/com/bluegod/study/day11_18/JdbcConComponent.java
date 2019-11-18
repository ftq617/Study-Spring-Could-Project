package com.bluegod.study.day11_18;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Component
public class JdbcConComponent implements CommandLineRunner {

    //CommandLineRunner springboot构建项目时，预先执行的代码处  可以多个  使用@order(value=1)区别顺序


    @Autowired
    private DataSource dataSource;


    @Override
    public void run(String... args) throws Exception {
        showConnection();
    }

    private void showConnection() throws SQLException {
        log.info(dataSource.toString());
        Connection con=dataSource.getConnection();
        log.info(con.toString());
        con.close();
    }
}
