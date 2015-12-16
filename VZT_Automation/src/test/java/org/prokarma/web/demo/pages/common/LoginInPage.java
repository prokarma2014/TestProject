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
import org.prokarma.web.demo.test.UserCredentials;

import com.vzt.framework.core.web.WebPage;
import com.vzt.framework.core.web.components.SelectWrapper;
import com.vzt.framework.core.web.components.TextBox;



/**
 * Login page where credentials can be entered for user login.
 * @author prokarma
 * @verion 1.0
 */
public class LoginInPage extends WebPage {



	@FindBy(id = "vinNumber")
	private TextBox vinNumber;

	@FindBy(id = "sourceName")
	private SelectWrapper sourceName;

	@FindBy(id = "startActivation")
	private WebElement startActivation;

	@FindBy(xpath = "html/body/table/tbody/tr[3]/td/div[3]/form/table/tbody/tr[5]/td/div/table/tbody/tr[2]/td/table[2]/tbody/tr[3]/td/input[1]")
	private List<WebElement> prevDataYes;

	@FindBy(xpath = "html/body/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a")
	private WebElement logOut;    


    
    /**
     * Initialized with web driver.
     * @param webDriver
     */
    public LoginInPage(WebDriver webDriver) {
        super(webDriver);
    }
    
    /**
     * Validate the title
     */
    @Override
    protected void validate() {
        waitFor(ExpectedConditions.titleContains("Account Management Portal"),50);
    }
    
    /**
     * login to amp 
     * @param userCredentials
     */
    public void login(AMPTestData ampTestData) {
    	
    	
    	vinNumber.clear().enterText(ampTestData.getAmpLoginTestData_vinno());    	
    	sourceName.get().selectByValue(ampTestData.getAmpLoginTestData_activationtype());  	
		startActivation.click();
		
		 
	       if( !prevDataYes.isEmpty())
	       {
	    	   WebElement eYes = prevDataYes.get(0);
	    	   eYes.click();
	       }
	       else{
	    	  // System.out.println("element not present");
	       }
	    
    }
}