package net.darkhax.cserver.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;

import net.darkhax.cserver.CustomServer;
import argo.format.JsonFormatter;
import argo.format.PrettyJsonFormatter;
import argo.jdom.JsonObjectNodeBuilder;
import argo.jdom.JsonRootNode;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class Config {

	public static void configSetup() throws IOException {
		
		File configFile = new File("config/CustomServer.json");
		if (!configFile.exists()) {
			
			createDefaultFile();
		}
		
		BufferedReader br = new BufferedReader(new FileReader(configFile));
		JsonReader jsonReader = new JsonReader(br);
		
		readConfigFile(jsonReader);
	}
	
	public static void readConfigFile(JsonReader jsonReader) throws IOException {

		jsonReader.beginObject();
		while (jsonReader.hasNext()) {

			String name = jsonReader.nextName();

			if (name.contains("server")) {

				attemptServerCreation(jsonReader);
			}
		}

		jsonReader.endObject();
	}
	
	public static void attemptServerCreation(JsonReader jsonReader) throws IOException {
		
		String serverName = null;
		String serverIP = null;
		boolean hidden = false;
		
		jsonReader.beginObject();
		while (jsonReader.hasNext()) {
			
			String n = jsonReader.nextName();
			
			if (n.equals("serverName")) {
				
				serverName = jsonReader.nextString();
			}
			
			if (n.equals("serverIP")) {
				
				serverIP = jsonReader.nextString();
			}
			
			if (n.equals("hidden")) {
				
				hidden = jsonReader.nextBoolean();
			}
		}
		
		CustomServer.proxy.addServer(serverName, serverIP, hidden);
		jsonReader.endObject();
	}
	
	public static void createDefaultFile() throws IOException {
		
		JsonWriter writer = new JsonWriter(new FileWriter("config/CustomServer.json"));
		writer.beginObject();
		writer.name("server-1");
		writer.beginObject();
		writer.name("serverName").value("Example Server Name");
		writer.name("serverIP").value("example.server.address"); 
		writer.endObject();
		writer.endObject();
		writer.close();
	}
}