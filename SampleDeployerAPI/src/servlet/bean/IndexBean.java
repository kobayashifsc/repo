package servlet.bean;

import java.util.Map;

public class IndexBean {
	private String message;
	private Map<String, String> AttributeMap;
	private Map<String, String> RuleMap;
	public Map<String, String> getAttributeMap() {
		return AttributeMap;
	}

	public void setAttributeMap(Map<String, String> attributeMap) {
		AttributeMap = attributeMap;
	}

	public Map<String, String> getRuleMap() {
		return RuleMap;
	}

	public void setRuleMap(Map<String, String> ruleMap) {
		RuleMap = ruleMap;
	}

	private Map<String, String> ContentsMap;

	public Map<String, String> getContentsMap() {
		return ContentsMap;
	}

	public void setContentsMap(Map<String, String> contentsMap) {
		ContentsMap = contentsMap;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
