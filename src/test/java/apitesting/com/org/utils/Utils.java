package apitesting.com.org.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Utils {

	String p_attribute;
	List<List<String>> csvData;
	BufferedReader stream;

    String protocole;
	String server;
	String port;
	String db;
	String urlServer;
	String invalidUrlServer;
	
	public String readPropertie(String p_path, String properties) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(p_path);
			prop.load(input);
			p_attribute = prop.getProperty(properties);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return p_attribute;
	}

	public List<List<String>> readData(String propertiesPath, String propertie, String cvsSplitBy) {
		String csvPath = readPropertie(propertiesPath, propertie);
		stream = null;
		csvData = new ArrayList<List<String>>();
		String line = null;

		try {
			stream = new BufferedReader(new FileReader(new File(csvPath)));
			while ((line = stream.readLine()) != null) {
				String[] splitted = line.split(",",-1);
				List<String> dataLine = new ArrayList<String>(splitted.length);
				for (String data : splitted)
					dataLine.add(data);
				csvData.add(dataLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stream != null)
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return csvData;
	}
	
	public String URLComposition(){ 
   		protocole = readPropertie("config\\server.properties","api.protocole");
   	    server = readPropertie("config\\server.properties","api.server");
   		port = readPropertie("config\\server.properties","api.port");
   		db = readPropertie("config\\server.properties","api.db");
        urlServer = protocole + "://" + server + ":" + port + "/" + db;
        return urlServer;
        }
	
	public String InvalidURLComposition(){
		String wrong_db = "user";
   		protocole = readPropertie("config\\server.properties","api.protocole");
   	    server = readPropertie("config\\server.properties","api.server");
   		port = readPropertie("config\\server.properties","api.port");
   		invalidUrlServer = protocole + "://" + server + ":" + port + "/" + wrong_db;
        return invalidUrlServer;
        }
}
