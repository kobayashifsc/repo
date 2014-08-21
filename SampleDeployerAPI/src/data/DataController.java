package data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataController {

	public final String FILE_ATTRIBUTE = "attributelist.json";
	public final String FILE_RULE = "rulelist.json";
	public final String FILE_CONTENTS = "contentslist.json";
	private final ObjectMapper mapper = new ObjectMapper();

	@Path("/getAttribute.json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAttribute() {
		String json = "";
		Map<String, List<String>> dataList = readJsonAttribute();
		try {
			json = mapper.writeValueAsString(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@Path("/getRule.json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRule() {
		String json = "";
		List<Map<String, List<String>>> dataList = readJsonRule();
		try {
			json = mapper.writeValueAsString(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@Path("/getContents.json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getContents() {
		String json = "";
		Map<String, String> dataList = readJsonContents();
		try {
			json = mapper.writeValueAsString(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public Map<String, List<String>> readJsonAttribute() {
		Map<String, List<String>> dataList;
		try {
			// Json形式のファイルをMapにマッピング
			dataList = mapper.readValue(new File(getFilePath(FILE_ATTRIBUTE)),
					Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			// 読めない場合は、空リスト作成
			dataList = new HashMap<String, List<String>>();
		}
		System.out.println("【読み込んだ属性情報リスト】");
		for (Map.Entry<String, List<String>> entry : dataList.entrySet()) {
			System.out.println(entry.getKey() + ":"
					+ entry.getValue().toString());
		}
		System.out.println("【読み込んだ属性情報リスト】");
		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, List<String>>> readJsonRule() {
		List<Map<String, List<String>>> dataList;
		try {
			// Json形式のファイルをMapにマッピング
			dataList = mapper.readValue(new File(getFilePath(FILE_RULE)),List.class);
		} catch (Exception e) {
			// 読めない場合は、空リスト作成
			dataList = new ArrayList<Map<String, List<String>>>();

		}
		System.out.println("【読み込んだ配信ルールリスト】");
		for (Map<String, List<String>> rule : dataList) {
			for (Map.Entry<String, List<String>> entry : rule.entrySet()) {
				System.out.println(entry.getKey() + ":"
						+ entry.getValue().toString());
			}
		}
		return dataList;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> readJsonContents() {
		Map<String, String> dataList;
		try {
			// Json形式のファイルをMapにマッピング
			dataList = mapper.readValue(new File(getFilePath(FILE_CONTENTS)),
					Map.class);
		} catch (Exception e) {
			// 読めない場合は、空リスト作成
			dataList = new HashMap<String, String>();
		}
		System.out.println("【読み込んだコンテンツリスト】");
		for(Map.Entry<String, String> entry : dataList.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		System.out.println("【読み込んだコンテンツリスト】");
		return dataList;
	}

	public String getFilePath(String fileName) {
		return DataController.class.getResource("").getPath() + fileName;
	}
}
