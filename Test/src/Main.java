import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {
		Gson gson = new Gson();
		
		try {
			
			URL url = new URL("http://localhost:3000/colleges/district");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("District", "Sangli");
			con.setRequestProperty("Offset", "10");
			
			String line;
			if(con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuilder sb = new StringBuilder();
				while((line = in.readLine()) != null) {
					sb.append(line);
				}
				in.close();
				line = sb.toString();
				System.out.println(line + "\n\n");
				College[] clgs = gson.fromJson(line, College[].class);
				for(College clg : clgs) {
					  System.out.println(clg.toString() + "\n");
				}
			}
			
		}catch(Exception e) {
			
		}
	}
}
