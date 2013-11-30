package net.darkhax.cserver.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;

public class ClientProxy extends ServerProxy {
	
	public static void setServer(String name, String address) {
		
		boolean serverExists = false;
		
		ServerList list = new ServerList(Minecraft.getMinecraft());
        list.loadServerList();
         
        for (int i = 0; i < list.countServers(); i++) {
        	
        	ServerData servers = list.getServerData(i);
        	
        	if (servers.serverName.equals(name) && servers.serverIP.equals(address)) {
        		
        		serverExists = true;
        		break;
        	}
        }
         
        if (!serverExists) {
        	
                 list.addServerData(new ServerData(name, address));
        }
         
        list.saveServerList(); 
	}
}
