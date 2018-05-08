package com.gaussic.dao;

import com.gaussic.model.BlogEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userSeBlog {
    private JdbcTemplate jdbcTemplate;
    public void  setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<BlogEntity> queryById(Integer id){
        String sql  = "select title,pub_date from blog where id = '" +id+ " ' ";
        List<BlogEntity> list = new ArrayList<BlogEntity>();
        list = jdbcTemplate.execute(sql, new PreparedStatementCallback<List<BlogEntity>>() {
                    public List<BlogEntity> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                        ResultSet rs = ps.executeQuery();
                        BlogEntity blogEntity= new BlogEntity();
                        List<BlogEntity> list = new ArrayList<BlogEntity>();
                        while(rs.next()){
                         blogEntity.setTitle(rs.getString(2));
                         blogEntity.setPubDate(rs.getDate("pub_date"));
                         list.add(blogEntity);
                        }
                        return list;
                    }
                }
        );


        return list;
    }
}
