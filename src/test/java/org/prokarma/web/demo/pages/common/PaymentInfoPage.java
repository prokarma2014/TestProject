package org.prokarma.web.demo.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.prokarma.web.demo.test.AMPTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vzt.framework.core.api.factory.APITestFactory;
import com.vzt.framework.core.web.WebPage;
import com.vzt.framework.core.web.components.SelectWrapper;
import com.vzt.framework.core.web.components.TextBox;



/**
 * payment info page
 * @author prokarma
 * @verion 1.0
*/
public class PaymentInfoPage extends WebPage {
	
	private static Logger logger = LoggerFactory.getLogger(PaymentInfoPage.class);
 		//Billing address Details
		@FindBy(xpath="html/body/table/tbody/tr[3]/td/div[3]/form/table/tbody/tr[1]/td/h2")
		private WebElement pagetitle;
		@FindBy(id="ccMethod")
		private WebElement Radio_CrediCard;
		@FindBy(name="billingAddressAsPrimary")
		private WebElement CheckBox_BillingAsPrimary;
		@FindBy(id="ccPrefix")
		private SelectWrapper DropDown_CCPrefix;
		@FindBy(id="ccFirstName")
		private TextBox Input_CCFirstName;
		@FindBy(id="ccLastName")
		private TextBox Input_CCLastName;
		@FindBy(id="ccCountry")
		private SelectWrapper Dropdown_CCCountry;
		@FindBy(id="ccAddress1")
		private TextBox Input_CCAddress1;
		@FindBy(id="ccAddress2")
		private TextBox Input_CCAddress2;
		@FindBy(id="ccCity")
		private TextBox Input_CCCity;
		@FindBy(id="ccState")
		private SelectWrapper DropDown_CCState;
		@FindBy(id="ccZip")
		private TextBox Input_CCZip;

		//credicard popup Details
		@FindBy(id="card_type")
		private SelectWrapper Dropdown_CrediCardType;
		@FindBy(id="PAN")
		private TextBox Input_CreditcardNo;
		@FindBy(id="cresecure_cc_expires_month")
		private SelectWrapper DropDown_CCMonth;
		@FindBy(id="cresecure_cc_expires_year")
		private SelectWrapper DropDown_CCYear;
		@FindBy(id="cv_data")
		private TextBox Input_CVC;
		@FindBy(id="submitButton")
		private WebElement submit_btn;
		@FindBy(xpath="html/body/table/tbody/tr[3]/td/div[3]/form/table/tbody/tr[5]/td/div[2]/b")
		private WebElement authorized_pymt;
		
/**
 * Initialized with web driver.
 * @param webDriver
 */
public PaymentInfoPage(WebDriver webDriver) {
	super(webDriver);
	// TODO Auto-generated constructor stub
}
public String verifyPageTitle(){
	String strTitlte=null;
	try {
		strTitlte= pagetitle.getText();
	} catch (Exception exception) {
		// TODO Auto-generated catch block
		logger.info(exception.getMessage(), exception);
	}
	return strTitlte;
	
}

public String verifyAuthorizedPymt(){
	String strPymt=null;
	try {
		
		strPymt= authorized_pymt.getText();
	} catch (Exception exception) {
		// TODO Auto-generated catch block
		logger.info(exception.getMessage(), exception);
	}
	return strPymt;
	
}

//set billing address
public void setBillingAddress(AMPTestData billingaddress){
    
	//vehicleType.get().selectByValue(dealerInfo.getDealerInfo_vehicletype());
	String Checkboxonoff=billingaddress.getPaymentInfo_billinsameasprimaryonoff().toLowerCase();
	if(Checkboxonoff=="on"){
		if (!CheckBox_BillingAsPrimary.isSelected()){
			//Checking the checkbox	
			CheckBox_BillingAsPrimary.click();
		}		
	}
	else if (Checkboxonoff=="off") {
		if(CheckBox_BillingAsPrimary.isSelected()){
			//Unchecking the checkbox
			CheckBox_BillingAsPrimary.click();
			//Entering the Address details
			DropDown_CCPrefix.get().selectByValue(billingaddress.getPaymentInfo_prefix());
			Input_CCFirstName.enterText(billingaddress.getPaymentInfo_firstname());
			Input_CCLastName.enterText(billingaddress.getPaymentInfo_lastname());
			Dropdown_CCCountry.get().selectByValue(billingaddress.getPaymentInfo_country());
			Input_CCAddress1.enterText(billingaddress.getPaymentInfo_address1());
			Input_CCAddress2.enterText(billingaddress.getPaymentInfo_address2());
			Input_CCCity.enterText(billingaddress.getPaymentInfo_city());
			DropDown_CCState.get().selectByValue(billingaddress.getPaymentInfo_state());
			Input_CCZip.enterText(billingaddress.getPaymentInfo_zipcode());
			
		}
	}
		
}	

//Enter creditcard information
public void setCreditcardInfo(AMPTestData billingaddress){
	Dropdown_CrediCardType.get().selectByValue(billingaddress.getPaymentInfo_creditcardtype());
	Input_CreditcardNo.enterText(billingaddress.getPaymentInfo_creditcardno());
	DropDown_CCMonth.get().selectByValue(billingaddress.getPaymentInfo_expirymonth());
	DropDown_CCYear.get().selectByValue(billingaddress.getPaymentInfo_expiryyear());
	Input_CVC.enterText(billingaddress.getPaymentInfo_cvcno());
	submit_btn.click();
	
}

}