package com.lhy.si.sec.role.model;

import java.io.Serializable;

import com.lhy.si.common.util.UrlUtil;

/**
 * @ClassName: UrlRole
 * @Description: 角色拥有的url权限
 * @author luanhy
 * @date 2017年8月5日 上午10:29:16
 * @Copyright: Copyright (c) 2017 wisedu
 */
public class UrlRole implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 路径 也就是uri
	 */
	private String path;

	/**
	 * 角色名称
	 */
	private String role;

	public UrlRole() {
		super();
	}

	public UrlRole(String path, String role) {
		super();
		this.path = path;
		this.role = role;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @Description:重写equals方法<br>
	 * 要求role相同，并且请求requestPath 即obj.path是以
	 * 配置configPath 即 this.path开头 即认为相同<br>
	 * 这里判断path相同只使用startwith
	 * 只要请求的url是以配置的menupath开头 情况下 即认为相同<br>
	 * 配置的 configPath ：/admin/rzcx/zzfwXmrz.do?zzfwMode=1<br>
	 * 实际请求的 requestPath ：/admin/rzcx/zzfwXmrz.do?zzfwMode=1&id=123 相同 <br>
	 * 配置的 configPath ：/admin/rzcx/zzfwXmrz.do?zzfwMode=2<br>
	 * 实际请求的 requestPath ：/admin/rzcx/zzfwXmrz.do?zzfwMode=1&id=123	 不相同<br>
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UrlRole other = (UrlRole) obj;

		String configPath = UrlUtil.getRequestPathWithSortedParams(other.getPath());
		String requestPath = UrlUtil.getRequestPathWithSortedParams(this.getPath());
		if (requestPath == null) {
			if (configPath != null)
				return false;
		} else if (!requestPath.startsWith(configPath))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

}
