package com.prokarma.web.demo.jaxws;

import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.vzt.framework.core.BaseTest;
import com.vzt.framework.core.api.JAXWSRequestTestData;
import com.vzt.framework.core.api.factory.JAXWSTestFactory;
import com.vzt.framework.core.testdrivers.DriverFactory;
import com.vzt.framework.hpqc.entity.services.impl.TestEntityService;
import com.vzt.framework.hpqc.entity.services.impl.TestFolderEntityService;
import com.vzt.framework.hpqc.entity.services.impl.TestInstanceEntityService;
import com.vzt.framework.hpqc.entity.services.impl.TestSetEntityService;
import com.vzt.framework.hpqc.entity.services.impl.TestSetFolderEntityService;
import com.vzt.framework.hpqc.model.alm.EntityBean;
import com.vzt.framework.hpqc.model.alm.ListOfEntitiesBean;
import com.vzt.framework.hpqc.utils.Constants;
import com.vzt.framework.hpqc.utils.EntityBeanUtils;

public class JaxWSTest extends BaseTest<Object> {

	// @BeforeMethod
	// public void addTestToALM() {
	//
	// createTestFolderInALM(testCaseName);
	// }

	@Test(dataProvider = "default")
	public void testJAXWSService(JAXWSRequestTestData jaxRestTestData)
			throws Exception {

		// System.out.println(jaxRestTestData.toString());

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

	@AfterMethod
	public void updateResultsInALM(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		//updateALM(testCaseName);

	}

	private void updateALM(String testCaseName) {

		String TEST_SET_FOLDER_NAME = "DemoTestSet";
		String TEST_SET_NAME = "JaxWSTest";

		TestFolderEntityService testFolderService;
		TestEntityService testService;
		TestSetFolderEntityService testSetFolderService;
		TestSetEntityService testSetService;
		TestInstanceEntityService testInstanceService;
		String PARENT_ALM_TEST_FOLDER_ID = null;
		String PARENT_ALM_TEST_FOLDER_NAME = null;
		String PARENT_ALM_TESTSET_FOLDER_ID = null;
		String PARENT_ALM_TESTSET_FOLDER_NAME = null;

		String TEST_ID = null;
		String TEST_SET_ID = null;
		String TEST_SET_FOLDER_ID = null;

		ListOfEntitiesBean allEntities = null;
		EntityBean entity = null;
		List<String> allDefaultTestFolders = new ArrayList<String>();
		String[] defaultTestFolderArr = null;
		String testFolderPath = null;

		testFolderService = new TestFolderEntityService(Constants.BASE_URL,
				Constants.DOMAIN, Constants.PROJECT, Constants.USERNAME,
				Constants.PASSWORD);
		testService = new TestEntityService(Constants.BASE_URL,
				Constants.DOMAIN, Constants.PROJECT, Constants.USERNAME,
				Constants.PASSWORD);
		testSetFolderService = new TestSetFolderEntityService(
				Constants.BASE_URL, Constants.DOMAIN, Constants.PROJECT,
				Constants.USERNAME, Constants.PASSWORD);
		testSetService = new TestSetEntityService(Constants.BASE_URL,
				Constants.DOMAIN, Constants.PROJECT, Constants.USERNAME,
				Constants.PASSWORD);
		testInstanceService = new TestInstanceEntityService(Constants.BASE_URL,
				Constants.DOMAIN, Constants.PROJECT, Constants.USERNAME,
				Constants.PASSWORD);

		testFolderPath = Constants.TEST_FOLDER_PATH;
		defaultTestFolderArr = testFolderPath.split("\\.");

		for (String defaultTestFolder : defaultTestFolderArr) {
			allDefaultTestFolders.add(defaultTestFolder);
		}

		PARENT_ALM_TEST_FOLDER_NAME = Constants.ROOT_ALM_TEST_FOLDER_NAME;
		allEntities = testFolderService.getListOfEntities("test-folders",
				PARENT_ALM_TEST_FOLDER_ID);
		PARENT_ALM_TEST_FOLDER_ID = EntityBeanUtils.getEntityIdWithName(
				allEntities, PARENT_ALM_TEST_FOLDER_NAME);
		for (String defaultFolder : allDefaultTestFolders) {
			allEntities = testFolderService.getListOfEntities("test-folders",
					PARENT_ALM_TEST_FOLDER_ID);
			String defaultTestFolderId = null;
			if (!(null == allEntities)) {
				defaultTestFolderId = EntityBeanUtils.getEntityIdWithName(
						allEntities, defaultFolder);
			}
			if ((null == defaultTestFolderId)) {
				entity = testFolderService.createEntity("test-folders",
						defaultFolder, PARENT_ALM_TEST_FOLDER_ID, "", "");
				if (!(null == entity)) {
					PARENT_ALM_TEST_FOLDER_ID = EntityBeanUtils
							.getEntityId(entity);
				}
			} else {
				PARENT_ALM_TEST_FOLDER_ID = defaultTestFolderId;
			}
		}

		entity = testService.createEntity("tests", testCaseName,
				PARENT_ALM_TEST_FOLDER_ID, null, null);

		TEST_ID = EntityBeanUtils.getEntityId(entity);
		// ***********************************************************************************//

		entity = testSetFolderService.createEntity("test-set-folders",
				TEST_SET_FOLDER_NAME, "", "", "");

		PARENT_ALM_TESTSET_FOLDER_ID = EntityBeanUtils.getEntityId(entity);

		entity = testSetService.createEntity("test-sets", TEST_SET_NAME,
				PARENT_ALM_TESTSET_FOLDER_ID, null, null);

		TEST_SET_ID = EntityBeanUtils.getEntityId(entity);

		entity = testInstanceService.createTestInstance("TestInstance1", TEST_SET_ID,
				TEST_ID, null);
		
		// allEntities = testService
		// .getTestsByFolderId(PARENT_ALM_TEST_FOLDER_ID);
		// List<EntityBean> allTestcases = allEntities
		// .getListOfEntity();
		// allEntities = testSetService.getTestSetByName(TEST_SET_NAME);
		// for (EntityBean testCase : allTestcases) {
		// TEST_CASE_ID = EntityBeanUtils
		// .getEntityId(testCase);

	}

	@Override
	protected DriverFactory<Object> initPrimaryDriverFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}