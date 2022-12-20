package get.references.demo;

// import java.net.URL;
// import java.net.HttpURLConnection;
// import java.net.MalformedURLException;
// import java.util.Base64;
// import java.util.logging.Level;
// import java.util.logging.Logger;
// import java.io.InputStreamReader;
// import java.io.InputStream;
// import java.io.BufferedReader;
// import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.json.JSONArray;
// import org.json.JSONException;
import org.json.JSONObject;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		// SpringApplication.run(DemoApplication.class, args);
		String username = "pspbgmu";
		String password = "3387007";
		int timeout = 1000;
		String rosridDomain = "https://rosrid.ru";
		String[] rosridReferenceData = {
			"/api/base/oecd-tree?format=json",
			"/api/base/rubric-tree?all=true%3Fformat%3Djson&format=json",
			"/api/base/scientific-activity-type-tree?format=json",
			"/api/base/dictionaries/scientific-degree?format=json",
			"/api/base/dictionaries/scientific-rank?format=json",
			"/api/base/dictionaries/rid-type?format=json",
			"/api/base/dictionaries/territory?format=json",
			"/api/base/dictionaries/budget-type?format=json",
			"/api/base/dictionaries/scientific-technology-priority?format=json",
			"/api/base/dictionaries/scientific-technical-result?format=json",
			"/api/base/dictionaries/identificator-type?format=json",
			"/api/base/dictionaries/conference-status?format=json",
			"/api/base/dictionaries/thematic-report-status?format=json",
			"/api/base/dictionaries/edition-type?format=json",
			"/api/base/dictionaries/scientific-educational-center?format=json",
			"/api/base/dictionaries/scientific-center?format=json",
			"/api/base/dictionaries/national-technology-initiative?format=json",
			"/api/iksi/dictionaries/contract-type?format=json",
			"/api/ikspo/dictionaries/registration-authority?format=json",
			"/api/ikspo/dictionaries/protection-way?format=json",
			"/api/nioktr/dictionaries/nioktr-bases?format=json",
			"/api/nioktr/dictionaries/priority-direction?format=json",
			"/api/nioktr/dictionaries/critical-technology?format=json",
			"/api/nioktr/federal-scientific-technical-program-tree?format=json",
			// "/api/nioktr/dictionaries/full-innovation-cycle-scientific-technical-program?format=json",
			"/api/nioktr/dictionaries/identify-executor-method?format=json",
			"/api/organizations/dictionaries/executor-category?format=json",
			"/api/organizations/dictionaries/federal-district?format=json",
			"/api/organizations/dictionaries/region?format=json",
			"/api/organizations/dictionaries/organization-type?format=json",
			"/api/organizations/dictionaries/okogu?format=json",
			"/api/organizations/dictionaries/okopf?format=json",
			"/api/organizations/dictionaries/okfs?format=json",
			"/api/rid/dictionaries/expected-securing-rights?format=json",
			"/api/rid/dictionaries/rid-type?format=json",
			"/api/rid/dictionaries/end-to-end-initiative-technology?format=json",
			"/api/rid/dictionaries/national-technology-initiative-market?format=json",
			"/api/rid/dictionaries/development-priority?format=json",
			"/api/nioktr/target-program-tree/state?format=json",
			"/api/nioktr/target-program-tree/federal?format=json",
			"/api/ikrbs/dictionaries/report-type?format=json",
			"/api/nioktr/national-federal-project-tree?format=json",
			"/api/ikrbs/dictionaries/preparation-status?format=json",
			// "/api/rid/international-patent-classifier-tree-search?format=json",
			"/api/ikrbs/dictionaries/report-license-type?format=json"
		};
		for (String rosridReferenceDataUnit: rosridReferenceData) {
			String url = rosridDomain.concat(rosridReferenceDataUnit);
			String jsonString = getJSON(url, timeout, username, password);
			// System.out.println(jsonString);
			if (jsonString.startsWith("[")) {
				JSONArray jsonObj = new JSONArray(jsonString.toString());
				System.out.println(jsonObj);
			} else if (jsonString.startsWith("{")) {
				JSONObject jsonObj = new JSONObject(jsonString.toString());
				System.out.println(jsonObj);
			} else {
				throw new java.lang.Error("Invalid JSON object");
			}
		}
	}

	public static String getJSON(String url, int timeout, String username, String password) {
		try {
			// create headers
			HttpHeaders headers = new HttpHeaders();
			headers.setBasicAuth(username, password);
		
			// create request
			HttpEntity<String> request = new HttpEntity<>(headers);
		
			// make a request
			ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);
		
			// get JSON response
			String json = response.getBody();
			
			return json;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
