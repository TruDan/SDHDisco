package com.sirdrakeheart.disco;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class discoBlockListener implements Listener {
        @SuppressWarnings("unused")
        private discoMain plugin;

        public discoBlockListener(discoMain plugin) {
                this.plugin = plugin;
        }
        
        @EventHandler
        public void onBlockPlace(BlockPlaceEvent event) {
                // on block place
                Player player = event.getPlayer();
                Block block = event.getBlockPlaced();
                String world = event.getBlock().getWorld().getName(); //make this a string with the world name not the world
                
                
                if (block.getType() == Material.WOOL) {
                        if (discoMain.players.contains(player)) {
                                player.sendMessage(ChatColor.YELLOW
                                                + "You placed a WOOL at " + block.getX() + ", "
                                                + block.getY() + ", " + block.getZ() + " in the world " + world);
                                
                                
                                coordinateWriter.coordinateWrite(world + "," + block.getX() + ","       + block.getY() + "," + block.getZ()+";");
                                //discoMain.blocks.put(world, block);
                                discoMain.blocks.add(block);
                        }
                }
        }
        
        @EventHandler
        public void onBlockBreak(BlockBreakEvent event){
                Player player = event.getPlayer();
                Block block = event.getBlock();
                String world = event.getBlock().getWorld().getName();
                
                if (discoMain.players.contains(player)) {
                        if (block.getType() == Material.WOOL) {
                        player.sendMessage(ChatColor.YELLOW + "You removed a WOOL at "
                                        + block.getX() + ", " + block.getY() + ", " + block.getZ()
                                        + " in the world " + world);
                        coordinateWriter.coordinateRemove(world + "," +block.getX() + ", " + block.getY() + ", " + block.getZ());
                        }
                }
        }
}