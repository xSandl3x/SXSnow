package ua.xsandl3x.sxsnow.snow.impl;

import lombok.experimental.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.snow.AbstractSnow;
import ua.xsandl3x.sxsnow.utils.RandomUtil;
import java.util.*;

@FieldDefaults(makeFinal = true)
public class Snow extends AbstractSnow {

    private int amount;
    private double radius;

    public Snow(Main instance, List<String> enabledWorlds, long interval, double radius, int amount) {
        super(instance, interval, enabledWorlds);

        this.radius = radius;
        this.amount = amount;
    }

    @Override
    protected void spawn(Player player) {
        for (int amount = 0; amount < this.amount; amount++) {
            double x = RandomUtil.randDouble(-this.radius, this.radius);
            double y = RandomUtil.randDouble(0.0, this.radius * 2);
            double z = RandomUtil.randDouble(-this.radius, this.radius);

            List<Particle> particles = Arrays.asList(Particle.FIREWORKS_SPARK, Particle.END_ROD);

            Location playerLocation = player.getLocation();
            Location location = playerLocation.clone().add(x, y, z);
            Particle particle = particles.get(RandomUtil.randInt(particles.size()));

            player.spawnParticle(particle, location, 0);
        }
    }
}
