package net.azisaba.jumppads;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class JumpPadListener implements Listener {
    private final JumpPads plugin;

    public JumpPadListener(JumpPads plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.PHYSICAL) {
            return;
        }
        if (!plugin.isEnabledInWorld(e.getPlayer().getWorld().getName())) {
            return;
        }
        if (e.getClickedBlock() == null || e.getClickedBlock().getType() != Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
            return;
        }
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_PISTON_EXTEND, 2.0f, 1.0f);
        Vector newVelocity = e.getPlayer().getLocation().getDirection();
        if (newVelocity.getY() < 0) {
            newVelocity.setY(-newVelocity.getY());
        }
        newVelocity.setY(Math.max(1, newVelocity.getY()));
        if (plugin.isAccumulateSpeed()) {
            Vector oldVelocity = e.getPlayer().getVelocity();
            if (oldVelocity.getX() > 0) {
                newVelocity.setX(newVelocity.getX() + oldVelocity.getX());
            }
            if (oldVelocity.getY() > 0) {
                newVelocity.setY(newVelocity.getY() + oldVelocity.getY());
            }
            if (oldVelocity.getZ() > 0) {
                newVelocity.setZ(newVelocity.getZ() + oldVelocity.getZ());
            }
        }
        newVelocity.setX(newVelocity.getX() * plugin.getJumpPower());
        newVelocity.setZ(newVelocity.getZ() * plugin.getJumpPower());
        newVelocity.setY(newVelocity.getY() * plugin.getJumpPowerY());
        e.getPlayer().setVelocity(newVelocity);
    }
}
