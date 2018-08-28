package com.ideasjunction.service;

import com.ideasjunction.helper.MessageToDisplay;
import com.ideasjunction.model.*;
import com.ideasjunction.helper.*;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MessageServiceImplementation implements MessageService{
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MessageServiceImplementation(DataSource dataSource){
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Message> getMessages(int topicId) {
		try{
			String query = "SELECT * FROM message WHERE topicId=?";
			List<Message> messages = jdbcTemplate.query(query, new Object[] { topicId }, new RowMapper<Message>(){
				public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
					Message m = new Message();
		            m.setMessageId(rs.getInt(1));
		            m.setMessageContent(rs.getString(2));
		            m.setTopicId(rs.getInt(3));
		            m.setUserId(rs.getInt(4));
		            return m;
		        }
			});return messages;
		}
		catch(EmptyResultDataAccessException e){
			List<Message> messages = null;
			return messages;
		}
	}
	
	public List<MessageToDisplay> getDiscussions(int topicId){
		try{
			String query = "SELECT * FROM message WHERE topicId=?";
			List<Message> messages = jdbcTemplate.query(query, new Object[] { topicId }, new RowMapper<Message>(){
				public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
					Message message = new Message();
					message.setMessageId(rs.getInt(1));
					message.setMessageContent(rs.getString(2));
					message.setTopicId(rs.getInt(3));
					message.setUserId(rs.getInt(4));
		            return message;
		        }
			});
			ArrayList<MessageToDisplay> messagesToDisplay = new ArrayList<MessageToDisplay>();
			for(Message m: messages) {
				String query2 = "SELECT * FROM employee WHERE empId = ?";
				Employee employee = (Employee)jdbcTemplate.queryForObject(query2, new Object[] { m.getUserId() }, new EmployeeRowMapper());
				MessageToDisplay messageToDisplay = new MessageToDisplay();
				messageToDisplay.setMessageContent(m.getMessageContent());
				messageToDisplay.setEmpName(employee.getEmployeeFirstName() + " " + employee.getEmployeeLastName());
				messagesToDisplay.add(messageToDisplay);
			}
			return messagesToDisplay;
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	public List<Message> getMessagesForEmp(int empId) {
		try{
			String query = "SELECT * FROM message WHERE empid=?";
			List<Message> messages = jdbcTemplate.query(query, new Object[] { empId }, new RowMapper<Message>(){
				public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
					Message m = new Message();
		            m.setMessageId(rs.getInt(1));
		            m.setMessageContent(rs.getString(2));
		            m.setTopicId(rs.getInt(3));
		            m.setUserId(rs.getInt(4));
		            return m;
		        }
			});
			return messages;
		}
		catch(EmptyResultDataAccessException e){
			List<Message> messages = null;
			return messages;
		}
	}
	
	public boolean postMessage(Message message) {
		try{
			String query = "INSERT INTO message(content, topicId, empId) VALUES('" + message.getMessageContent() + "', '" +  message.getTopicId() + "', '" + message.getUserId() +"')";
			jdbcTemplate.update(query);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}

class MessageRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Message message = new Message();
        message.setMessageId(rs.getInt(1));
        message.setMessageContent("voila");
        message.setTopicId(rs.getInt("TopicId"));
        message.setUserId(rs.getInt("empId"));
        return message;
    }
}