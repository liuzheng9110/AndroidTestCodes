package com.example.androidtest.jsonparse;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;


public class tiripPackage {
	@JSONField(name = "service")
	service service;
	busiContent busiContent;
	returnState returnState;
	
	public service getService() {
		return service;
	}
	
	public busiContent getBusiContent() {
		return busiContent;
	}
	
	public returnState getReturnState() {
		return returnState;
	}
}

class service {
	@JSONField(name = "serviceId")
	String serviceId;
	@JSONField(name = "tranSeq")
	String tranSeq;
	@JSONField(name = "tranReqDate")
	String tranReqDate;
}

class busiContent{
	response response;
	
//	public Response getResponse() {
//		return response;
//	}
}

class response{
	String rtnCode;
	String rtnMsg;
}

class returnState{
	String returnCode;
	String returnMessage;
}
