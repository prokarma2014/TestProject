/**
 * 
 */
package org.prokarma.web.demo.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.prokarma.web.demo.test.AMPTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vzt.framework.core.web.WebPage;
import com.vzt.framework.core.web.components.SelectWrapper;
import com.vzt.framework.core.web.components.TextBox;




/**
 * Login page where credentials can be entered for user login.
 * @author prokarma
 * @verion 1.0
 */
public class CustomerInfoPage extends WebPage {

	  private static final Logger logger = LoggerFactory.getLogger(CustomerInfoPage.class);

	@FindBy(xpath = "html/body/table/tbody/tr[3]/td/div[3]/form/table/tbody/tr[1]/td/h2[1]")
	private WebElement step2CustInfo;
	
	
	@FindBy(id="esaData.orderType1")
	private WebElement newAgreement;
	
	@FindBy(id="esaData.orderType2")
	private WebElement updateAgreement;
	
	@FindBy(id="pcFirstName")
	private TextBox firstName;
	
	@FindBy(id="pcLastName")
	private TextBox lastName;
	
	@FindBy(id="emailAddress")
	private TextBox emailAddr;
	
	@FindBy(id="emailNotProvided")
	private WebElement emailCheckBox;
	
	@FindBy(id="pcAddress1")
	private TextBox addressLine1;
	
	@FindBy(id="pcAddress2")
	private TextBox addressLine2;
	
	@FindBy(id="pcState")
	private SelectWrapper state;
	
	@FindBy(id="pcZip")
	private TextBox zipCode;
	
	@FindBy(id="pcCity")
	private SelectWrapper city;
	
	@FindBy(id="pcCounty")
	private SelectWrapper county;
	
	@FindBy(id="pcCountry")
	private SelectWrapper country;
	
	@FindBy(id="mobilePhone")
	private TextBox mobilePhone;
	
	@FindBy(id="homePhone")
	private TextBox homePhone;
	
	@FindBy(id="primaryPhoneType")
	private TextBox primaryPhoneType;
	
	@FindBy(id="primaryContact.PIN")
	private TextBox pin;
	
	@FindBy(id="paymentMethod")
	private SelectWrapper paymentMethod;
	
	



	/**
	 * Initialized with web driver.
	 * @param webDriver
	 */
	public CustomerInfoPage(WebDriver webDriver) {
		super(webDriver);
	}

	/**
	 * Validate the title
	 */
	@Override
	protected void validate() {

		waitFor(ExpectedConditions.visibilityOf(step2CustInfo),50);

	}

	 /**
     * select order type 
     * @param 
     */
    public void selectOrderType(AMPTestData ampTestData){
    	
    	String address = ampTestData.getCustomerInfo_address1() + ";" +  ampTestData.getCustomerInfo_address2()
    			+ ";" +  ampTestData.getCustomerInfo_state().trim() + ";" +  ampTestData.getCustomerInfo_zipcode() 
    			+ ";" +  ampTestData.getCustomerInfo_city().trim()  + ";" +  ampTestData.getCustomerInfo_county().trim()
    			 + ";" +  ampTestData.getCustomerInfo_country();
    	
    	if(ampTestData.getCustomerInfo_ordertype().equalsIgnoreCase("new")){
    		newAgreement.click(); 
    		setFirstName(ampTestData.getCustomerInfo_firstname());
        	setLastName(ampTestData.getCustomerInfo_lastname());
        	setEmail(ampTestData.getCustomerInfo_emailonoff(),ampTestData.getCustomerInfo_email());        	
        	logger.info(address);
        	setAddress(address);
        	setMobileNo(ampTestData.getCustomerInfo_mobileno());
        	setPaymentType(ampTestData.getCustomerInfo_paymenttype());
    		
    	}
    	else if(ampTestData.getCustomerInfo_ordertype().equalsIgnoreCase("update")){
    		updateAgreement.click();
    		setAddress(address);
    	}
    			
    }
    
    /**
     * set first name  
     * @param 
     */
    public void setFirstName(String sfirstName){
    	
    	firstName.clear().enterText(sfirstName);  
		
    }
    
    /**
     * set address details  
     * @param 
     */
    public void setAddress(String sAddress){
    	
    	String addrData[]  = sAddress.split(";");
    	addressLine1.clear().enterText(addrData[0]);
    	addressLine2.clear().enterText(addrData[1]);    	
    	state.get().selectByValue(addrData[2]);
    	zipCode.clear().enterText(addrData[3]);    	
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	city.get().selectByValue(addrData[4]);   
    
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	county.get().selectByValue(addrData[5]);
    	country.get().selectByValue(addrData[6]);
    }
    
    
    
    /**
     * set mobile phone details  
     * @param 
     */
    public void setMobileNo(String sMobileNo){
    	
    	mobilePhone.clear().enterText(sMobileNo);
    	
    	
    }
    
    /**
     * set payment method details  
     * @param 
     */
    public void setPaymentType(String sPaymentmethod){
    	
    	paymentMethod.get().selectByValue(sPaymentmethod);
    	
    	
    }
    
    /**
     * set email  
     * @param 
     */
    public void setEmail(String sEmailOnOff,String email){
    	if(sEmailOnOff.equalsIgnoreCase("On")){
    		if(!emailCheckBox.isSelected()){
    			emailCheckBox.click();
    			}
    	}
    	else
    	{
    		emailAddr.clear().enterText(email);
    	}
    	
    		
    	
		
    }
	
    /**
     * set last name  
     * @param 
     */
    public void setLastName(String sLastName){
    	
    	lastName.clear().enterText(sLastName);  
		
    }
    
    
    public void setCustomerInfo(AMPTestData ampTestData){
    	
    	
    	selectOrderType(ampTestData);
    	
    	
    	
    }
    /**
     * verify if customer page is displayed
     * 
     */
    public String verifyCustPage() throws Exception{
    	String strCust = null;
    	try {
    		strCust = step2CustInfo.getText();;
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
		return strCust;

	}



}