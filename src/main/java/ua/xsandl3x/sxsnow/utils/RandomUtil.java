package ua.xsandl3x.sxsnow.utils;

import lombok.experimental.UtilityClass;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class RandomUtil {

    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public int randInt(int bound, int origin) {
        return random.nextInt(bound, origin);
    }

    public int randInt(int origin) {
        return randInt(0, origin);
    }

    public double randDouble(double bound, double origin) {
        return random.nextDouble(bound, origin);
    }
}
