package com.example.androidtest.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

// 动态构造调用串，灵活性更大
/**
 * http://www.cnblogs.com/lanxuezaipiao/archive/2013/05/10/3072216.html
 * @author Administrator
 *
 */
public class DynamicHttpclientCall {
    
    private static String NAME_SPACE = "http://iitaxws.webservice.taxoffice.hn.foresee.com/";
    private static String METHOD_NAME = "entrance";
    private static String METHOD_PARAM = "requestJson";
    private static String WSDL_LOCATION = "http://175.6.1.176:9001/admin/webservice/IItaxAppImpl?wsdl";
    
    private static String soapResponseData;
	private static int statusCode;

    public DynamicHttpclientCall() {}

    /**
     * 
     * @param reqData
     * @return
     * @throws Exception
     */
    public static String invoke(String reqData) throws Exception {
        PostMethod postMethod = new PostMethod(WSDL_LOCATION);
        String soapRequestData = buildRequestData(reqData);
        
        byte[] bytes = soapRequestData.getBytes("utf-8");
        InputStream inputStream = new ByteArrayInputStream(bytes, 0,  bytes.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,  bytes.length, "application/soap+xml; charset=utf-8");
        postMethod.setRequestEntity(requestEntity);
        
        HttpClient httpClient = new HttpClient();
        statusCode = httpClient.executeMethod(postMethod);
        soapResponseData = getReturnJsonString(postMethod.getResponseBodyAsStream());
        
        return soapResponseData;
    }

	private static String buildRequestData(String reqData) {
        StringBuffer soapRequestData = new StringBuffer();
        soapRequestData.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:iit=\"" + NAME_SPACE + "\">");
        soapRequestData.append("<soap:Body>");
        soapRequestData.append("<iit:"+METHOD_NAME+">");

        soapRequestData.append("<"+METHOD_PARAM+">" + reqData + "</"+METHOD_PARAM+">");
        
        soapRequestData.append("</iit:"+METHOD_NAME+">");
        soapRequestData.append("</soap:Body>");
        soapRequestData.append("</soap:Envelope>");

        return soapRequestData.toString();
    }

	/**
	 * 获取请求响应returnJson
	 * @param inputStream 
	 */
	private static String getReturnJsonString(InputStream inputStream) {
		try {
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
			parser.setInput(inputStream, "utf-8");
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					String tag = parser.getName();
					if (tag.equals("return")) {
						Log.i("liuz", "return..." + parser.nextText());
					}
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
    
    
    /**
     * @param args
     * @throws Exception
     */
//    public static void main(String[] args) throws Exception {
////        DynamicHttpclientCall dynamicHttpclientCall = new DynamicHttpclientCall(
////                "http://iitaxws.webservice.taxoffice.hn.foresee.com/", "entrance",
////                "http://175.6.1.176:9001/admin/webservice/IItaxAppImpl?wsdl");
//    	
//        String methodString = "{\"tiripPackage\":{\"service\":{\"serviceId\":\"ITAX.APP.FLK.CX.LB\",\"tranSeq\":\"1390000000020140805092628092285578\",\"tranReqDate\":\"2014-08-05 09:26:28\"},\"businessContent\":{\"request\":{\"pageSize\":\"10\",\"fgbt\":\"\",\"serviceId\":\"ITAX.APP.FLK.CX.LB\",\"fglb\":[],\"fbnd\":[0],\"pageIndex\":\"1\",\"fglx\":[]}},\"identity\":{\"user\":{\"password\":\"\",\"cert\":\"\",\"authenticateType\":\"2\",\"userId\":\"134223\"},\"application\":{\"applicationId\":\"taxoffice\",\"cert\":\"\",\"authenticateType\":\"2\",\"phoneType\":\"HUAWEI MT2-L05/android4.2/720x1208/320\",\"password\":\"123456\",\"clientNo\":\"tf0000000000006\",\"version\":\"V1.0.20140527.1\"}}}}";
//        
//        String soapRequestData = buildRequestData(methodString);
//        System.out.println(soapRequestData);
//
//        invoke(methodString);
//        
//        if(statusCode == 200) {
//            System.out.println("调用成功！");
//            System.out.println(soapResponseData);
//        }
//        else {
//            System.out.println("调用失败！错误码：" + statusCode);
//        }
//    }
}