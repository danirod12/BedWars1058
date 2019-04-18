package com.andrei1058.bedwars.lobbysocket;

import com.andrei1058.bedwars.Main;
import com.andrei1058.bedwars.api.events.ArenaEnableEvent;
import com.andrei1058.bedwars.api.events.GameStateChangeEvent;
import com.andrei1058.bedwars.api.events.PlayerJoinArenaEvent;
import com.andrei1058.bedwars.api.events.PlayerLeaveArenaEvent;
import com.andrei1058.bedwars.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArenaListeners implements Listener {

    @EventHandler
    public void onPlayerJoinArena(PlayerJoinArenaEvent e) {
        Arena a = Arena.getArenaByPlayer(e.getPlayer());
        Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, ()-> ArenaSocket.sendMessage(a));
    }

    @EventHandler
    public void onPlayerLeaveArena(PlayerLeaveArenaEvent e){
        Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, ()-> ArenaSocket.sendMessage(e.getArena()));
    }

    @EventHandler
    public void onArenaStatusChange(GameStateChangeEvent e){
        Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, ()-> ArenaSocket.sendMessage(e.getArena()));
    }

    @EventHandler
    public void onArenaLoad(ArenaEnableEvent e){
        Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, ()-> ArenaSocket.sendMessage(e.getArena()));
    }
}