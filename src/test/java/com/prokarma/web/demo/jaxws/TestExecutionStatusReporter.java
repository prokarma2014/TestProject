package com.prokarma.web.demo.jaxws;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.vzt.framework.hpqc.entity.services.impl.TestEntityService;
import com.vzt.framework.hpqc.entity.services.impl.TestFolderEntityService;
import com.vzt.framework.hpqc.entity.services.impl.TestInstanceEntityService;
import com.vzt.framework.hpqc.entity.services.impl.TestSetEntityService;
import com.vzt.framework.hpqc.entity.services.impl.TestSetFolderEntityService;
import com.vzt.framework.hpqc.model.alm.EntityBean;
import com.vzt.framework.hpqc.model.alm.ListOfEntitiesBean;
import com.vzt.framework.hpqc.utils.Constants;
import com.vzt.framework.hpqc.utils.EntityBeanUtils;

/**
 * This is the custom reporter class which will be acting as a listener to
 * TestNG.
 * 
 * 
 * @author ProKarma
 *
 */
public class TestExecutionStatusReporter implements IReporter {

	private ExtentReports extent;
	// private static Logger logger =
	// LoggerFactory.getLogger(TestExecutionStatusReporter.class);

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date dateObject = new Date();

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		System.out.println(" Inside generateReport()");

		try {

			// Iterating over each suite included in the test
			for (ISuite suite : suites) {
				// Following code gets the suite name
				String suiteName = suite.getName();
				System.out.println(" Suite Name " + suiteName);
				// Getting the results for the said suite
				Map<String, ISuiteResult> suiteResults = suite.getResults();
				for (ISuiteResult sr : suiteResults.values()) {
					ITestContext iTestContext = sr.getTestContext();
					ValidateHPAlmUpdationDetails(iTestContext.getPassedTests(), LogStatus.PASS);
					ValidateHPAlmUpdationDetails(iTestContext.getFailedTests(), LogStatus.FAIL);
					ValidateHPAlmUpdationDetails(iTestContext.getSkippedTests(), LogStatus.SKIP);

				} // End of inner for
			} // End of outside for
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.println(" Done------------");
	}// End of generateReport Method.

	/**
	 * This method take the Passed/Failed/Skipped Tests and update their status
	 * in HPAlm.
	 * 
	 * @param tests
	 * @param status
	 * @throws Exception
	 */
	public void ValidateHPAlmUpdationDetails(IResultMap tests, LogStatus status) throws Exception {
		
		String testCases = null;
		String methodName = null;
		String testStat = verifyLogStatus(status);

		for (ITestResult result : tests.getAllResults()) {

			//System.out.println(result.toString());
			
			if (testStat.equalsIgnoreCase("FAIL")) {
				//System.out.println(result.toString());
				
				updateHPAlmWithSuccessStat(result.getMethod().getMethodName(), String.valueOf(result.getStatus()));
				
				Throwable t = result.getThrowable();
				if (t != null) {
					System.out.println("Exception STACKTRACE, message:" + t.getMessage());
					System.out.println("Exception Class:" + t.getClass().getName());
					StackTraceElement[] elems = t.getStackTrace();
					for (StackTraceElement elem : elems) {

						System.out.println(elem.toString());
					}
					System.out.println("END STACKTRACE");
				} else {

					System.out.println("No Exception Stacktrace:");
				}
			}
			
			else if (testStat.equalsIgnoreCase("pass")) {
				
				//System.out.println(result.getTestName());
				updateHPAlmWithSuccessStat(result.getMethod().getMethodName(), String.valueOf(result.getStatus()) );
			/**/
			}
			else {			
				
				//System.out.println(result.getTestName());
				updateHPAlmWithSuccessStat(result.getMethod().getMethodName(), String.valueOf(result.getStatus()) );
			}
			
		
		}
	}

	/**
	 * This method will update the status in HP ALM.
	 * 
	 * @param testCaseID
	 * @param testStat
	 * @throws Exception
	 */
	public void updateHPAlmWithSuccessStat(String testCaseName, String testCaseStatus) throws Exception {
		System.out.println("HPAlm Update for " + testCaseName + " --->  " + testCaseStatus);

		int randomPIN = (int)(Math.random()*9000)+1000;
		String pin = String.valueOf(randomPIN);

		String TEST_SET_FOLDER_NAME = "FAL-Sprint12";
		String TEST_SET_NAME = "FAL-Sprint12";
		String TEST_CASE_NAME = "TestCase"+ pin + "-" + testCaseName;
		
		updateALM(testCaseName, testCaseStatus, TEST_SET_FOLDER_NAME, TEST_SET_NAME,  TEST_CASE_NAME);
	}

	/**
	 * This will return the String corresponding to various verdicts depending
	 * on the Status of Test execution.
	 * 
	 * //Fail,Inconclusive,Pass,Blocked,Error
	 * 
	 * @param status
	 * @return
	 */
	public String verifyLogStatus(LogStatus status) {
		String strStat = status.toString();
		if (strStat.equalsIgnoreCase("Pass")) {
			return "Pass";
		} else if (strStat.equalsIgnoreCase("Fail")) {
			return "Fail";
		} else if (strStat.equalsIgnoreCase("Blocked")) {
			return "Blocked";
		} else if (strStat.equalsIgnoreCase("Error")) {
			return "Error";
		} else {
			return "Inconclusive";
		}
	}
	
	
	private void updateALM(String testCaseName,String testCaseStatus,String TEST_SET_FOLDER_NAME,String TEST_SET_NAME,String  TEST_CASE_NAME) {


		String TEST_STATUS = "Failed";
		System.out.println("Test Case Status: " + testCaseStatus);
		if (testCaseStatus.equals("1")) {
			TEST_STATUS = "Passed";
		}

		TestFolderEntityService testFolderService;
		TestEntityService testService;
		TestSetFolderEntityService testSetFolderService;
		TestSetEntityService testSetService;
		TestInstanceEntityService testInstanceService;
		
		String PARENT_ALM_TEST_FOLDER_ID = null;
		String PARENT_ALM_TEST_FOLDER_NAME = null;
		String PARENT_ALM_TESTSET_FOLDER_ID = null;
		

		String TEST_ID = null;
		String TEST_SET_ID = null;
		String TEST_INSTANCE_ID = null;

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
			if (!(allEntities.getTotalResults() == 0)) {
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

		allEntities = testService.getEntityByName("tests", TEST_CASE_NAME);
		if (!(allEntities.getTotalResults() == 0)) {
			TEST_ID = EntityBeanUtils.getEntityIdWithName(allEntities,
					testCaseName);
		} else {
			entity = testService.createEntity("tests", TEST_CASE_NAME,
					PARENT_ALM_TEST_FOLDER_ID, null, null);
			TEST_ID = EntityBeanUtils.getEntityId(entity);
		}

		allEntities = testSetFolderService.getEntityByName("test-set-folders",
				TEST_SET_FOLDER_NAME);
		if (!(allEntities.getTotalResults() == 0)) {
			PARENT_ALM_TESTSET_FOLDER_ID = EntityBeanUtils.getEntityIdWithName(
					allEntities, TEST_SET_FOLDER_NAME);
		} else {
			entity = testSetFolderService.createEntity("test-set-folders",
					TEST_SET_FOLDER_NAME, "", "", "");
			PARENT_ALM_TESTSET_FOLDER_ID = EntityBeanUtils.getEntityId(entity);

		}

		allEntities = testSetService.getListOfEntities("test-sets",
				PARENT_ALM_TESTSET_FOLDER_ID);
		if (!(allEntities.getTotalResults() == 0)) {
			TEST_SET_ID = EntityBeanUtils.getEntityIdWithName(allEntities,
					TEST_SET_NAME);
		} else {
			entity = testSetService.createEntity("test-sets", TEST_SET_NAME,
					PARENT_ALM_TESTSET_FOLDER_ID, null, null);
			TEST_SET_ID = EntityBeanUtils.getEntityId(entity);
		}

		// TEST_SET_ID = EntityBeanUtils.getEntityId(entity);
		// entity = testInstanceService.createTestInstance(null, "29593",
		// "180042", null);

		Properties myConfigProperties = new Properties();
		InputStream systemResource = ClassLoader
				.getSystemResourceAsStream("test-instance.properties");
		try {
			myConfigProperties.load(systemResource);
		} catch (IOException e) {
			e.printStackTrace();
		}

		allEntities = testInstanceService
				.getTestInstancesByTestSetId(TEST_SET_ID);
		if (!(allEntities.getTotalResults() == 0)) {
			entity = testInstanceService.createTestInstance(null, TEST_SET_ID,
					TEST_ID, null);
			TEST_INSTANCE_ID = EntityBeanUtils.getEntityId(entity);
			myConfigProperties.setProperty("0", "status," + TEST_STATUS);
			testInstanceService.updateTestInstance(TEST_INSTANCE_ID,
					TEST_STATUS);
			/*List<String> entityIds = EntityBeanUtils.getEntityIds(allEntities);
			for (String testInstanceId : entityIds) {
				myConfigProperties.setProperty("0", "status," + TEST_STATUS);
				testInstanceService.updateTestInstance(testInstanceId,
						TEST_STATUS);
			}*/
		} else {
			entity = testInstanceService.createTestInstance(null, TEST_SET_ID,
					TEST_ID, null);
			TEST_INSTANCE_ID = EntityBeanUtils.getEntityId(entity);
			myConfigProperties.setProperty("0", "status," + TEST_STATUS);
			testInstanceService.updateTestInstance(TEST_INSTANCE_ID,
					TEST_STATUS);
		}

		myConfigProperties.setProperty("0", "status," + "No Run");
	}

}
