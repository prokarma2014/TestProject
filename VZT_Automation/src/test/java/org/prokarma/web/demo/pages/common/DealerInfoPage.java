/**
 * 
 */
package org.prokarma.web.demo.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.prokarma.web.demo.test.AMPTestData;

import com.vzt.framework.core.web.WebPage;
import com.vzt.framework.core.web.components.SelectWrapper;



/**
 * Login page where credentials can be entered for user login.
 * @author prokarma
 * @verion 1.0
 */
public class DealerInfoPage extends WebPage {

						
	@FindBy(xpath = "html/body/table/tbody/tr[3]/td/div[3]/form/table/tbody/tr[1]/td/h2[1]")
	private WebElement step1DealerInfo;

	@FindBy(id = "futureFieldsData.vehicleType") 
	private SelectWrapper vehicleType;

	@FindBy(id = "futureFieldsData.stateOfRegistration")
	private WebElement vehicleRegState;

	@FindBy(id = "esaData.account.salesDealerCode")
	private WebElement dealersCode;

    
    /**
     * Initialized with web driver.
     * @param webDriver
     */
    public DealerInfoPage(WebDriver webDriver) {
        super(webDriver);
    }
    
    /**
     * Validate the title
     */
    @Override
    protected void validate() {
      
        waitFor(ExpectedConditions.visibilityOf(step1DealerInfo),50);
        
    }
    
    /**
     * select vehicle type  
     * @param userCredentials
     */
    public void selectVehicle(AMPTestData dealerInfo){
    
    	vehicleType.get().selectByValue(dealerInfo.getDealerInfo_vehicletype());   	
		
    }
    
    /**
     * verify if dealer page is displayed
     * 
     */
    public String verifyDealerPage() throws Exception{
    	  
    	String strDealer = null;
    	try {
    		strDealer = step1DealerInfo.getText();;
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
		return strDealer;

	}
  
}