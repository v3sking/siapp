package com.lhy.si.sec.user;

/**
* @ClassName: SecUserEnum
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月26日 上午9:39:05
* @Copyright: Copyright (c) 2017 wisedu
*/
public enum SecUserEnum {
	
	/**
	 * 0正常，1审核中，2审核驳回，-1注销 -2黑名单
	 */
	normal(0),auditing(1),reject(2),cancel(-1),blacklist(-2);
	
	private int status;
	
	private SecUserEnum(int status){
		this.setStatus(status);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public static String getStatusName(Integer status){
		if (status == null) {
			return "";
		}
		String statusName = "";
		for (SecUserEnum secUserEnum : SecUserEnum.values()) {
			if (secUserEnum.getStatus() == status) {
				statusName = secUserEnum.name();
				break;
			}
		}
		return statusName;
	}
}
