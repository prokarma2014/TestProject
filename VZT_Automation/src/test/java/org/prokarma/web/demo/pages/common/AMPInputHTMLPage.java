/**
 * 
 */
package org.prokarma.web.demo.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.prokarma.web.demo.test.UserCredentials;

import com.vzt.framework.core.web.WebPage;
import com.vzt.framework.core.web.components.TextBox;



/**
 * Input HTML page with
 * @author prokarma
 * @verion 1.0
 */
public class AMPInputHTMLPage extends WebPage {

	@FindBy(xpath="html/body/table/tbody/tr[6]/td[2]/form[4]/input[3]")
	private WebElement BillingOps;
	
	  
    /**
     * Initialized with web driver.
     * @param webDriver
     */
    public AMPInputHTMLPage(WebDriver webDriver) {
        super(webDriver);
    }
    
    /**
     * Validate the title
     */
    @Override
    protected void validate() {
    	
    	
    	   waitFor(ExpectedConditions.visibilityOf(BillingOps),15);
    }
    
    /**
     * click on Billing Ops
     * 
     */
    public void launchAppl() {
    	
    	if(BillingOps.isDisplayed()){
    		BillingOps.click();
    	}
    }
}