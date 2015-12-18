package com.prokarma.web.demo.jaxws;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.vzt.framework.core.BaseTest;
import com.vzt.framework.core.api.JAXWSRequestTestData;
import com.vzt.framework.core.api.factory.JAXWSTestFactory;
import com.vzt.framework.core.testdrivers.DriverFactory;

public class JaxWSTest extends BaseTest<Object> {
	

	@Test(dataProvider = "default")
	public void testJAXWSService(JAXWSRequestTestData jaxRestTestData)
			throws Exception {

		JAXWSTestFactory object = new JAXWSTestFactory();
		if (jaxRestTestData.getRun_Mode() != null
				&& jaxRestTestData.getRun_Mode().equalsIgnoreCase("YES")) {
			object.initialize(jaxRestTestData);
			Response response = object.getResponse();
			if (response != null) {
				// can do the assertion based on the result.
				assertEquals(jaxRestTestData.getExpected_Response_Code(),
						Integer.toString(response.getStatusCode()));
				String resStr = object.validateResponse(response);
				System.out.println("------------" + resStr
						+ "===========================");
				}
		}
	}
	
	@Test(dataProvider = "default")
	public void getActiveVHRAlerts(JAXWSRequestTestData jaxRestTestData)
			throws Exception {

		JAXWSTestFactory object = new JAXWSTestFactory();
		if (jaxRestTestData.getRun_Mode() != null
				&& jaxRestTestData.getRun_Mode().equalsIgnoreCase("YES")) {
			object.initialize(jaxRestTestData);
			Response response = object.getResponse();
			if (response != null) {
				// can do the assertion based on the result.
				assertEquals(jaxRestTestData.getExpected_Response_Code(),
						Integer.toString(response.getStatusCode()));
				String resStr = object.validateResponse(response);
				System.out.println("------------" + resStr
						+ "===========================");
				}
		}
	}
	
	@Test(dataProvider = "default")
	public void vehicleHealthReport(JAXWSRequestTestData jaxRestTestData)
			throws Exception {

		JAXWSTestFactory object = new JAXWSTestFactory();
		if (jaxRestTestData.getRun_Mode() != null
				&& jaxRestTestData.getRun_Mode().equalsIgnoreCase("YES")) {
			object.initialize(jaxRestTestData);
			Response response = object.getResponse();
			if (response != null) {
				// can do the assertion based on the result.
				assertEquals(jaxRestTestData.getExpected_Response_Code(),
						Integer.toString(response.getStatusCode()));
				String resStr = object.validateResponse(response);
				System.out.println("------------" + resStr
						+ "===========================");
				}
		}
	}
	
	@Test(dataProvider = "default")
	public void getGeoFencing(JAXWSRequestTestData jaxRestTestData)
			throws Exception {

		JAXWSTestFactory object = new JAXWSTestFactory();
		if (jaxRestTestData.getRun_Mode() != null
				&& jaxRestTestData.getRun_Mode().equalsIgnoreCase("YES")) {
			object.initialize(jaxRestTestData);
			Response response = object.getResponse();
			if (response != null) {
				// can do the assertion based on the result.
				assertEquals(jaxRestTestData.getExpected_Response_Code(),
						Integer.toString(response.getStatusCode()));
				String resStr = object.validateResponse(response);
				System.out.println("------------" + resStr
						+ "===========================");
				}
		}
	}
	
	@Test(dataProvider = "default")
	public void getGeoFenceRecords(JAXWSRequestTestData jaxRestTestData)
			throws Exception {

		JAXWSTestFactory object = new JAXWSTestFactory();
		if (jaxRestTestData.getRun_Mode() != null
				&& jaxRestTestData.getRun_Mode().equalsIgnoreCase("YES")) {
			object.initialize(jaxRestTestData);
			Response response = object.getResponse();
			if (response != null) {
				// can do the assertion based on the result.
				assertEquals(jaxRestTestData.getExpected_Response_Code(),
						Integer.toString(response.getStatusCode()));
				String resStr = object.validateResponse(response);
				System.out.println("------------" + resStr
						+ "===========================");
				}
		}
	}
	

	

	@Override
	protected DriverFactory<Object> initPrimaryDriverFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
