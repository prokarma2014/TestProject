package org.prokarma.web.demo.api;

import static org.testng.AssertJUnit.assertEquals;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.vzt.framework.core.BaseTest;
import com.vzt.framework.core.api.RestRequestTestData;
import com.vzt.framework.core.api.factory.APITestFactory;
import com.vzt.framework.core.testdrivers.DriverFactory;

public class APITest extends BaseTest<Object>{
	
	@Test(dataProvider = "default")
	public void testRestServices(RestRequestTestData request) throws Exception {		
		 SSLSocketFactory   sslSocketFactory = CertificateConfig.setCertificate();
		APITestFactory object = new APITestFactory();
		//verifying that it is special service or not
		if(request.getAuth_Service() != null && !request.getAuth_Service().equals("Yes")) {
			
			if(request.getRun_Mode() != null && request.getRun_Mode().equalsIgnoreCase("YES")) {
				
				System.out.println(" Going to execute " + request.getApi());				
				object.initialize(request); 
				Response response = object.getResponse(sslSocketFactory);
				if(response!=null)	{
				String resStr = object.validateResponse(response);							
				//assert response code
				assertEquals(request.getExpected_Response_Code(),Integer.toString(response.getStatusCode()));
				System.out.println("Response--------"+resStr);
				}
			}
		}
	}
	
	
	@Override
	protected DriverFactory<Object> initPrimaryDriverFactory() {		
		return null;
	}

}
