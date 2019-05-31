import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {

	public static JSONObject urlToJSON(String i) throws Exception {
		
		URL url = new URL("https://nlotto.co.kr/common.do?method=getLottoNumber&drwNo="+i);
		HttpURLConnection con = null;
		
		try {
			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			
			con = (HttpsURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine= null; 
			StringBuilder response = new StringBuilder(); 
			while ((inputLine = br.readLine()) != null) 
			{ response.append(inputLine); } 
			
			JSONParser parser = new JSONParser(); 
			Object obj = parser.parse( response.toString() ); 
			
			JSONObject jsonObj = (JSONObject) obj;
			
			if(jsonObj.get("returnValue").equals("fail")) {
				return null;
				
			} else {
				return jsonObj;	
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			con.disconnect();
		}
	}
}