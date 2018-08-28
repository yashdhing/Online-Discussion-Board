package com.ideasjunction.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ideasjunction.model.Employee;


import java.util.List;

import com.ideasjunction.model.*;
import com.ideasjunction.service.*;

/*import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.transaction.annotation.Transactional;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;

import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import com.ideasjunction.helper.*;

@RestController
public class MessageController {
	@Autowired
	MessageService messageService;
	 
	@RequestMapping(value = "/messages/{empId}", method = RequestMethod.GET)
	public ResponseEntity<List<Message>> getMessagesForEmp(@PathVariable("empId") int empId) {
		List<Message> messages = messageService.getMessagesForEmp(empId);
		if(messages != null){
			return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
		}
		return new ResponseEntity<List<Message>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value= "/messagesByTopic/{topicId}" , method=RequestMethod.GET)
	public ResponseEntity<List<Message>> getMessages(@PathVariable("topicId") int topicId) {
		List<Message> messages = messageService.getMessages(topicId );
		if(messages != null){
			return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
		}
		return new ResponseEntity<List<Message>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value= "/MessageToDisplay/{topicId}" , method=RequestMethod.GET)
	public ResponseEntity<List<MessageToDisplay>> getDiscussion(@PathVariable("topicId") int topicId) {
		List<MessageToDisplay> messages = messageService.getDiscussions(topicId);
		if(messages != null){
			return new ResponseEntity<List<MessageToDisplay>>(messages, HttpStatus.OK);
		}
		return new ResponseEntity<List<MessageToDisplay>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/post/", method = RequestMethod.POST)
	public ResponseEntity<Boolean> getEmployee(@RequestBody Message message, HttpServletRequest request) {
		Boolean flag = messageService.postMessage(message);
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}
}