package de.kirito.afkattack;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "afkattack")
public class ModConfig implements ConfigData {

    public boolean EnableDing = true;

    public boolean EnableActionbar = true;

    public boolean EnableOnScreenState = true;

    public ModConfig(){ }

}
