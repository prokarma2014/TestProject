package org.prokarma.web.demo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.prokarma.web.demo.pages.common.AMPInputHTMLPage;
import org.prokarma.web.demo.pages.common.CustomerInfoPage;
import org.prokarma.web.demo.pages.common.DealerInfoPage;
import org.prokarma.web.demo.pages.common.LoginInPage;
import org.prokarma.web.demo.pages.common.NavigationPage;

import org.prokarma.web.demo.pages.common.PaymentInfoPage;
import org.prokarma.web.demo.pages.common.ServicePlanPage;
import org.testng.annotations.Test;

import com.vzt.framework.core.web.WebTest;

/**
 * This class validates that amzaon search capability.
 * 
 * @author prokarma
 * @version 1.0
 */
public class AMPOrderProcess extends WebTest {



	/**
	 * select new agreement and process order
	 * @param credentials
	 * @throws Exception 
	 */
	@Test(priority=1,dataProvider = "default")
	public void ampNewAgreementOrder(AMPTestData ampTestData) throws Exception {

		getDriver().get(getTestConfig().getConfig("webapp.url"));
		getDriver().manage().window().maximize();
		getDriver().get(getTestConfig().getConfig("webapp.url"));
		new AMPInputHTMLPage(getDriver()).launchAppl();

		new LoginInPage(getDriver()).login(ampTestData);
		assertEquals("Step 1: Required Dealer Information",new DealerInfoPage(getDriver()).verifyDealerPage());

		new DealerInfoPage(getDriver()).selectVehicle(ampTestData);         
		new NavigationPage(getDriver()).clickNext();
		assertEquals("Step 2: Required Customer Information",new CustomerInfoPage(getDriver()).verifyCustPage());

		new CustomerInfoPage(getDriver()).setCustomerInfo(ampTestData);
		new NavigationPage(getDriver()).clickNext();
		assertEquals("Step 3: Required Service Plan Information",new ServicePlanPage(getDriver()).verifySPPage());


		assertTrue(new ServicePlanPage(getDriver()).verifySelectedTrialService());
		new ServicePlanPage(getDriver()).selectPaidServices(ampTestData);
		new NavigationPage(getDriver()).clickNext();

		assertTrue(new PaymentInfoPage(getDriver()).verifyPageTitle().contains("Step 4: Required Payment Information"));

		new NavigationPage(getDriver()).clickLogout();
	}

	/**
	 * select update agreement and process order
	 * @param credentials
	 * @throws Exception 
	 */

/*	@Test(priority=2,dataProvider = "default")
	public void ampUpdateAgreementOrder(AMPTestData ampTestData) throws Exception {

		getDriver().get(getTestConfig().getConfig("webapp.url"));
		getDriver().manage().window().maximize();
		new AMPInputHTMLPage(getDriver()).launchAppl();

		new LoginInPage(getDriver()).login(ampTestData);
		assertEquals("Step 1: Required Dealer Information",new DealerInfoPage(getDriver()).verifyDealerPage());

		new DealerInfoPage(getDriver()).selectVehicle(ampTestData);         
		new NavigationPage(getDriver()).clickNext();
		assertEquals("Step 2: Required Customer Information",new CustomerInfoPage(getDriver()).verifyCustPage());

		new CustomerInfoPage(getDriver()).setCustomerInfo(ampTestData);
		new NavigationPage(getDriver()).clickNext();
		assertEquals("Step 3: Required Service Plan Information",new ServicePlanPage(getDriver()).verifySPPage());

		assertTrue(new ServicePlanPage(getDriver()).verifySelectedTrialService());
		new ServicePlanPage(getDriver()).selectPaidServices(ampTestData);
		new NavigationPage(getDriver()).clickNext();

		assertTrue(new PaymentInfoPage(getDriver()).verifyPageTitle().contains("Step 4: Required Payment Information"));
		assertEquals(new PaymentInfoPage(getDriver()).verifyAuthorizedPymt(),ampTestData.getPaymentInfo_auth_msg());

		new NavigationPage(getDriver()).clickLogout();
	}*/


}