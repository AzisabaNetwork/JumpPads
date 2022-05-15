package net.azisaba.jumppads;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JumpPads extends JavaPlugin {
    private final List<String> enabledWorlds = new ArrayList<>();
    private double jumpPowerY = 1;
    private double jumpPower = 4;
    private boolean accumulateSpeed = false;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        enabledWorlds.addAll(getConfig().getStringList("enabled-worlds").stream().map(String::toLowerCase).collect(Collectors.toList()));
        jumpPowerY = getConfig().getDouble("jump-power-y", 1);
        jumpPower = getConfig().getDouble("jump-power", 2);
        accumulateSpeed = getConfig().getBoolean("accumulate-speed", false);
        if (accumulateSpeed && jumpPower > 10) {
            getLogger().warning("accumulate-speed is true and jump-power is too high (" + jumpPower + ")! Setting jump-power to 10 because it is very likely to crash the server because of excessive velocity.");
            jumpPower = 10;
        }
        getServer().getPluginManager().registerEvents(new JumpPadListener(this), this);
    }

    public boolean isEnabledInWorld(String world) {
        return enabledWorlds.contains(world.toLowerCase());
    }

    public double getJumpPowerY() {
        return jumpPowerY;
    }

    public double getJumpPower() {
        return jumpPower;
    }

    public boolean isAccumulateSpeed() {
        return accumulateSpeed;
    }
}
