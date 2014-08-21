package common;

import java.util.HashMap;
import java.util.Map;

public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8286359740663736660L;

	private Map<String, String> messageMap;
	private String code;

	public AppException() {
		setMessageMap();
	}

	public AppException(String code) {
		this.code = code;
		setMessageMap();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		String massage = messageMap.get(code);
		if(null == massage) {
			massage = messageMap.get("999");
		}
		return massage;
	}
	private void setMessageMap() {
		messageMap = new HashMap<String, String>();
		messageMap.put("001", "no exist hems-id");
		messageMap.put("002", "unmatch depoy-rule");
		messageMap.put("003", "unmatch contents-id");
		messageMap.put("998", "invalid argument");
		messageMap.put("999", "System error");
	}
}
