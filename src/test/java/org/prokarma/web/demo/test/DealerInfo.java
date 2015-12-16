package org.prokarma.web.demo.test;

import com.vzt.framework.core.annotations.Data;

/**
 * amp test data
 * @author prokarma
 * @verion 1.0
 */
public class DealerInfo {
	
	@Data(name="DealerInfo_vehicletype")
	private String dealerInfo_vehicletype;
	
	@Data(name="DealerInfo_platenumber")
	private String dealerInfo_platenumber;
	
	@Data(name="DealerInfo_regstate")
	private String dealerInfo_regstate;
	
	@Data(name="DealerInfo_dealercode")
	private String dealerInfo_dealercode;

	public String getDealerInfo_vehicletype() {
		return dealerInfo_vehicletype;
	}

	public void setDealerInfo_vehicletype(String dealerInfo_vehicletype) {
		this.dealerInfo_vehicletype = dealerInfo_vehicletype;
	}

	public String getDealerInfo_platenumber() {
		return dealerInfo_platenumber;
	}

	public void setDealerInfo_platenumber(String dealerInfo_platenumber) {
		this.dealerInfo_platenumber = dealerInfo_platenumber;
	}

	public String getDealerInfo_regstate() {
		return dealerInfo_regstate;
	}

	public void setDealerInfo_regstate(String dealerInfo_regstate) {
		this.dealerInfo_regstate = dealerInfo_regstate;
	}

	public String getDealerInfo_dealercode() {
		return dealerInfo_dealercode;
	}

	public void setDealerInfo_dealercode(String dealerInfo_dealercode) {
		this.dealerInfo_dealercode = dealerInfo_dealercode;
	}
	
	
}
