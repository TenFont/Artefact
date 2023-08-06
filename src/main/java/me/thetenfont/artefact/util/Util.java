package me.thetenfont.artefact.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Util {
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
