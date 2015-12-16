package org.prokarma.web.demo.test;

import com.vzt.framework.core.annotations.Data;

/**
 * service plan test data
 * @author prokarma
 * @verion 1.0
 */

public class ServicePlanInfo {
	
	@Data(name="ServicePlanInfo_trailservices") 
	private String servicePlanInfo_trailservices;
	
	@Data(name="ServicePlanInfo_paidservices")
	 private String servicePlanInfo_paidservices;
	
	 @Data(name="ServicePlanInfo_duration")
	 private String servicePlanInfo_duration;
	 
	 @Data(name="ServicePlanInfo_promocode")
	 private String servicePlanInfo_promocode;

	public String getServicePlanInfo_trailservices() {
		return servicePlanInfo_trailservices;
	}

	public void setServicePlanInfo_trailservices(
			String servicePlanInfo_trailservices) {
		this.servicePlanInfo_trailservices = servicePlanInfo_trailservices;
	}

	public String getServicePlanInfo_paidservices() {
		return servicePlanInfo_paidservices;
	}

	public void setServicePlanInfo_paidservices(String servicePlanInfo_paidservices) {
		this.servicePlanInfo_paidservices = servicePlanInfo_paidservices;
	}

	public String getServicePlanInfo_duration() {
		return servicePlanInfo_duration;
	}

	public void setServicePlanInfo_duration(String servicePlanInfo_duration) {
		this.servicePlanInfo_duration = servicePlanInfo_duration;
	}

	public String getServicePlanInfo_promocode() {
		return servicePlanInfo_promocode;
	}

	public void setServicePlanInfo_promocode(String servicePlanInfo_promocode) {
		this.servicePlanInfo_promocode = servicePlanInfo_promocode;
	}
	 
}
