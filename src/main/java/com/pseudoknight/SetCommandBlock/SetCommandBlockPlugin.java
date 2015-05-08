package com.pseudoknight.SetCommandBlock;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SetCommandBlockPlugin extends JavaPlugin implements Listener {

	public void onEnable() {
		PluginDescriptionFile pdf = this.getDescription();
		getLogger().info( pdf.getName() + " version " + pdf.getVersion() + " is enabled!" );
		getServer().getPluginManager().registerEvents(this, this);
	}

	public void onDisable() {
		PluginDescriptionFile pdf = this.getDescription();
		getLogger().info( pdf.getName() + " version " + pdf.getVersion() + " is disabled!" );
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("scb"))
		{
			if (args.length > 4)
			{
				String world = args[0];
				int x;
				int y;
				int z;

				try {
					x = Integer.parseInt(args[1]);
					y = Integer.parseInt(args[2]);
					z = Integer.parseInt(args[3]);
				} catch (NumberFormatException e) {
					return false;
				}

				Block b = Bukkit.getWorld(world).getBlockAt(x, y, z);

				if(b.getType() != Material.COMMAND) {
					return false;
				}

				CommandBlock cb = (CommandBlock) b.getState();

				StringBuilder sb = new StringBuilder();
				for(int i = 4; i < args.length; i++) {
					sb.append(args[i]);
					sb.append(" ");
				}

				cb.setCommand(sb.toString());
				cb.update();

				return true;
			}
			return false;
		}
		return false;
	}

}
