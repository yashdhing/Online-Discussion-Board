package com.ideasjunction.service;

import com.ideasjunction.model.*;


import java.util.ArrayList;
import java.util.List;
/*import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Repository
public class TopicServiceImplementation implements TopicService{
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public TopicServiceImplementation(DataSource dataSource){
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Topic> getAllTopics(){
		try{
			String query = "SELECT * FROM topic";
			List<Topic> topics = jdbcTemplate.query(query, new BeanPropertyRowMapper(Topic.class));
			return topics;
		}
		catch(EmptyResultDataAccessException e){
			List<Topic> topics = null;
			return topics;
		}
	}
	
	public List<Topic> getTopicsForEmp(long empId){
		try{
			String query = "SELECT topic.topicId, topic.topicTitle FROM topic INNER JOIN assignments ON topic.topicid = assignments.topicid WHERE assignments.empid=?";
			List<Topic> topics = jdbcTemplate.query(query, new Object[] { empId }, new RowMapper<Topic>(){
				public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
					Topic t = new Topic();
		            t.setTopicId(rs.getInt(1));
		            t.setTopicTitle(rs.getString(2));
		            return t;
		        }
			});
			
			return topics;
		}
		catch(EmptyResultDataAccessException e){
			List<Topic> topics = null;
			return topics;
		}
	}
	
	
	
	public void addTopic(String topicTitle) {
		try{
			String query = "INSERT INTO topic (topictitle) VALUES('" +topicTitle+ "')";
			jdbcTemplate.update(query);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteEmployeeByTopic(long topicId, long empId) {
		try	{
			String query= "DELETE FROM assignments WHERE empid="+ empId + " and topicId = " + topicId;
			jdbcTemplate.update(query);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void assignEmployeeByTopic(long topicId, long empId) {
		try	{
			String query= "INSERT INTO assignments (topicId, empId) VALUES ('" + topicId + "','" + empId + "')";
			jdbcTemplate.update(query);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Topic> loadAntiTopicsByEmpId(long empId) {
		try{			
			String query = "select * from topic";
			List<Topic> topics = jdbcTemplate.query(query, new RowMapper<Topic>(){
				public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
					Topic t = new Topic();
		            t.setTopicId(rs.getInt(1));
		            t.setTopicTitle(rs.getString(2));
		            return t;
		        }
			});
	   
			String query1 = "select * from topic inner join assignments on topic.topicid = assignments.topicid where assignments.empid=" + empId;			
			List<Topic> antitopics = jdbcTemplate.query(query1, new RowMapper<Topic>(){
				public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
					Topic t = new Topic();
		            t.setTopicId(rs.getInt(1));
		            t.setTopicTitle(rs.getString(2));
		            return t;
		        }
			});
			
			ArrayList<Topic> antitopics1 = new ArrayList<Topic>();
			for(int i = 0; i < topics.size(); i++) {
				int flag = 0;
				for(int j = 0; j < antitopics.size(); j++) {
					 if(topics.get(i).getTopicId() == antitopics.get(j).getTopicId()) {
						 flag = 1;
						 break;
					 }
				}
				if(flag == 0) {
					antitopics1.add(topics.get(i));
				}
			}
			return antitopics1;
		}catch(Exception e)	{
			e.printStackTrace();
			ArrayList<Topic> t = null;
			return t;
		}
		
		
	}
	
}

class TopicRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Topic topic = new Topic();
		topic.setTopicId(rs.getInt("TopicId"));
		topic.setTopicTitle(rs.getString("TopicTitle"));
        return topic;
    }
}
