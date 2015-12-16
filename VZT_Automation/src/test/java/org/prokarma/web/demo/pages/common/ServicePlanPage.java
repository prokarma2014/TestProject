/**
 * 
 */
package org.prokarma.web.demo.pages.common;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.prokarma.web.demo.test.AMPTestData;
import org.prokarma.web.demo.test.ServicePlanInfo;
import org.prokarma.web.demo.test.UserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vzt.framework.core.api.factory.APITestFactory;
import com.vzt.framework.core.web.WebPage;
import com.vzt.framework.core.web.components.SelectWrapper;
import com.vzt.framework.core.web.components.TextBox;



/**
 * 
 * @author prokarma
 * @verion 1.0
 */
public class ServicePlanPage extends WebPage {

	private static Logger logger = LoggerFactory.getLogger(ServicePlanPage.class);
	@FindBy(xpath = "html/body/table/tbody/tr[3]/td/div[3]/form/table/tbody/tr[1]/td/h2[1]")
    private WebElement servicePlanTitle;
	
	@FindBy(id="Group0trialPartNumber0")
	private WebElement servicePlan_VWCarNetNewVehicleTrial;
	
	@FindBy(id="Group0yearlyPaidPartNumber0")
	private WebElement servicePlan_VWCarNetMonthlyService;
	
	@FindBy(id="Group0yearlyPaidPartNumber1")
	private WebElement servicePlan_VWCarNetAnnualService;
	
	@FindBy(id="Group0monthlyPartNumber0")
	private WebElement servicePlan_VWCarNetMultiYrService;
	
	@FindBy(id="Group0monthlyPartNumber1")
	private WebElement servicePlan_VWCarNetVWGoodwill;
	
	@FindBy(id="Group0monthlyPartNumber0monthlySubscriptionDuration")
	private TextBox servicePlan_VWCarNetMultiYrServiceDuration;
	
	@FindBy(id="Group0monthlyPartNumber1monthlySubscriptionDuration")
	private TextBox servicePlan_VWCarNetVWGoodwillDuration;
	
	@FindBy(id="selectedPromoCode")
	private WebElement servicePlan_PromoCode;
	
	@FindBy(id="addPromoCode")
	private WebElement servicePlan_AddPromoCode;
	
	
    
    /**
     * Initialized with web driver.
     * @param webDriver
     */
    public ServicePlanPage(WebDriver webDriver) {
        super(webDriver);
    }
    
    /**
     * Validate the title
     */
    @Override
    protected void validate() {
        waitFor(ExpectedConditions.visibilityOf(servicePlanTitle),50);
    }
    
    
    public boolean verifySelectedTrialService(){
    	
    		return servicePlan_VWCarNetNewVehicleTrial.isSelected();
    }
    
    
    
    public void selectPaidServices(AMPTestData ampTestData){
    	
    	try{
    	if(ampTestData.getServicePlanInfo_paidservices().equalsIgnoreCase("VW Car-Net Monthly Service")){
    		servicePlan_VWCarNetMonthlyService.click();
    	}
    	else if(ampTestData.getServicePlanInfo_paidservices().equalsIgnoreCase("VW Car-Net Annual Service")){
    		servicePlan_VWCarNetAnnualService.click();
    		
    	}
    	else if(ampTestData.getServicePlanInfo_paidservices().equalsIgnoreCase("VW Car-Net Multi-Yr Service")){
    		servicePlan_VWCarNetMultiYrService.click();
    		servicePlan_VWCarNetMultiYrServiceDuration.clear().enterText(ampTestData.getServicePlanInfo_duration());
    	}
    	else if(ampTestData.getServicePlanInfo_paidservices().equalsIgnoreCase("VW Car-Net Goodwill Service")){
    		servicePlan_VWCarNetVWGoodwill.click();
    		servicePlan_VWCarNetVWGoodwillDuration.clear().enterText(ampTestData.getServicePlanInfo_duration());
    	}
    	}
    	catch(Exception exception){
    		logger.info(exception.getMessage(), exception);
    	}
    }
    
    /**
     * verify if service plan page is displayed
     * 
     */
    public String verifySPPage() throws Exception{
    	String strsp = null;
    	try {
    		strsp = servicePlanTitle.getText();
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
		return strsp;

	}
   
}