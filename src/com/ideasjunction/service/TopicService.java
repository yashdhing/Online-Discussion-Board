package com.ideasjunction.service;

import com.ideasjunction.model.*;
import java.util.List;

public interface TopicService {
	List<Topic> getAllTopics();
	List<Topic> getTopicsForEmp(long empId);
	void addTopic(String topicTitle);
	void deleteEmployeeByTopic(long topicId, long empId);
	List<Topic> loadAntiTopicsByEmpId(long empId);
	void assignEmployeeByTopic(long topicId, long empId);
}
