package POST_JSON_using_HTTPClient;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class POST_JSON_using_HTTPClient1 {
	
	//instantiates httpclient to make request
	HttpClient client;
	HttpPost httpPost;
	HttpPut httpPut;
	HttpResponse response;
	HttpGet httpGet;
	HttpEntity httpEntity;
	JSONParser parser;
	InputStream inputStream;
	Object obj;
	JSONObject jsonObject;
	String actualStatus;
	String actualErrorCode;
	String errorMessage;
	String success;
	String failure;
	String filePath = "E:/LogFile.txt";
	String thisMethodName;
	@Before
	public void beforeEachIdividualTest() throws ClientProtocolException, IOException {
		thisMethodName = "beforeEachIdividualTest";
		//instantiates httpclient to make request
		client = HttpClients.createDefault();

		httpPost = new HttpPost("http://10.10.8.32:8080/AppCenter/services/rest/login");
		String json = "{\"username\":\"admin\",\"password\":\"admin\"}";
		StringEntity entity = new StringEntity(json);
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-type", "application/json");
		response = client.execute(httpPost);
	}

	@After
	public void afterEachIdividualTest() throws ClientProtocolException, IOException, org.json.simple.parser.ParseException {
		httpEntity = response.getEntity();
		InputStream is = httpEntity.getContent();
		String logFilePath = "E:/LogFile.txt";
		FileOutputStream fos = new FileOutputStream(new File(logFilePath));
		int inByte;
		while ((inByte = is.read()) != -1)
			fos.write(inByte);
	 

		parser = new JSONParser();

		obj = parser.parse(new FileReader(logFilePath));

		jsonObject = (JSONObject) obj;

		actualStatus = (String) jsonObject.get("status");
		actualErrorCode = (String) jsonObject.get("errorCode");
		errorMessage = (String) jsonObject.get("errorMessage");
		// String data = (String) jsonObject.get("data");
		success = "success";
		failure = "failure";
		System.out.println("\t\t\t" + thisMethodName + "");
		System.out.println("Status:		" + actualStatus);
		System.out.println("Error Code:	" + actualErrorCode);

		assertEquals(success, actualStatus);
		// Assert.assertTrue("status".equals("success"));
		// Assert.assertTrue(success.equals(actualStatus));
	}
	@Test
	public void LoginAndEstablishSession1() throws ClientProtocolException, IOException, ParseException {
	
		thisMethodName = "SetUpPermissionOnResourceInstances";
		JSONParser parser = new JSONParser();
		FileReader FR = new FileReader("E:/newfile3UBUNTU.json");
		Object obj = parser.parse(FR);
	 	System.out.println(obj.toString());
		JSONArray array = (JSONArray) obj;
		System.out.println(array.toString());
		httpPost = new HttpPost("http://10.10.8.32:8080/AppCenter/services/rest/standards?name=OS+Profile");
		StringEntity entity = new StringEntity(obj.toString());
  		httpPost.setEntity(entity);
 	    httpPost.setHeader("Content-type", "application/json");
		response = client.execute(httpPost);
        ResponseHandler responseHandler = new BasicResponseHandler();
 	    System.out.println(responseHandler.toString());
 	    System.out.println(response);
 	    System.out.println(response.toString());
 	    
 	   assertEquals(200, response.getStatusLine().toString());
 	  //assertEquals(200, response.getStatusLine().getStatusCode());
 	    //assertEquals(success, actualStatus);
 	  // Assert.assertTrue("status".equals("success"));
		// Assert.assertTrue(success.equals(actualStatus));
	}
}

