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

@RestController
public class TopicController {
	@Autowired
	TopicService topicService;
	 
	@RequestMapping(value = "/topics/{empId}", method = RequestMethod.GET)
	public ResponseEntity<List<Topic>> getTopicsForEmp(@PathVariable("empId") long empId) {
		List<Topic> topics = topicService.getTopicsForEmp(empId);
		if(topics != null){
			return new ResponseEntity<List<Topic>>(topics, HttpStatus.OK);
		}
		List<Topic> t = null;
		return new ResponseEntity<List<Topic>>(t, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/allTopics" , method=RequestMethod.GET)
	public ResponseEntity<List<Topic>> getTopics() {
		List<Topic> topics = topicService.getAllTopics();
		if(topics != null){
			return new ResponseEntity<List<Topic>>(topics, HttpStatus.OK);
		}
		return new ResponseEntity<List<Topic>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/addtopic/{topicTitle}", method = RequestMethod.GET)
    public ResponseEntity<Void> addTopic(@PathVariable("topicTitle") String topicTitle) {
		topicService.addTopic(topicTitle);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "deleteemployeefortopic/topicTitle", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteEmployeeByTopic(@RequestBody TopicEmployee topicemployee) {
        topicService.deleteEmployeeByTopic(topicemployee.getTopicId(),topicemployee.getEmpId());
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
	
	@RequestMapping(value = "/addemployeefortopic", method = RequestMethod.POST)
    public ResponseEntity<Void> assignEmployeeByTopic(@RequestBody TopicEmployee topicEmployee) { 
		topicService.assignEmployeeByTopic(topicEmployee.getTopicId(), topicEmployee.getEmpId());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    } 
	
	@RequestMapping(value = "/employeeAntiTopics/{empid}", method = RequestMethod.GET)
    public ResponseEntity<List<Topic>> loadAntiTopicsByEmpId(@PathVariable("empid") long empId) {
        List<Topic> topics = topicService.loadAntiTopicsByEmpId(empId);
        if(topics.isEmpty()){
            return new ResponseEntity<List<Topic>> (HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Topic>> (topics, HttpStatus.OK);
    }
}
