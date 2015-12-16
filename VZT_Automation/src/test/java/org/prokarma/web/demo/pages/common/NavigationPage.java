package org.prokarma.web.demo.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vzt.framework.core.web.WebPage;

/**
 * .
 * @author prokarma
 * @verion 1.0
 */
public class NavigationPage extends WebPage {

	
	@FindBy(name = "next")
	public WebElement nextBtn;
	
	@FindBy(xpath="html/body/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a")
	private WebElement logout;
	
	public NavigationPage(WebDriver webDriver) {
		super(webDriver);
		
		
		// TODO Auto-generated constructor stub
	}


    /**
     * click on Next
     */
    public void clickNext() {   
    	if(nextBtn.isDisplayed() && nextBtn.isEnabled()){
    	nextBtn.click();
    	}
    	
    }
    
    /**
     * click on logout
     */
    public void clickLogout() {   
    	if(logout.isDisplayed() && logout.isEnabled()){
    		logout.click();
    	}
    	
    }
}
