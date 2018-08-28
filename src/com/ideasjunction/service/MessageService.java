package com.ideasjunction.service;

import com.ideasjunction.helper.*;
import com.ideasjunction.model.*;


import java.util.List;

public interface MessageService {
	List<Message> getMessages(int topicId);
	List<Message> getMessagesForEmp(int empId);
	boolean postMessage(Message message);
	List<MessageToDisplay> getDiscussions(int topicId);
}
