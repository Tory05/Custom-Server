package net.darkhax.cserver.util;

import java.io.File;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config{

	public static String serverName;
	public static String serverAddress;
	
	public static void createConfig(File configFile){
		Configuration config = new Configuration(configFile);
		config.load();
		
		serverName = config.get("Settings", "Custom Server Name", "Your server name here").getString();
		serverAddress = config.get("Settings", "Custom Server Address", "Your server Address here").getString();
		
		config.save();
	}
}
