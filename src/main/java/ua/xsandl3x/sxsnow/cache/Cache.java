package ua.xsandl3x.sxsnow.cache;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import java.util.*;

@Getter
@FieldDefaults(makeFinal = true)
public class Cache {

    @Getter
    private static final Cache cachedData = new Cache();

    private Map<String, Object> cacheMap = new HashMap<>();

    public void addCache(String key, Object value) {
        this.cacheMap.put(key, value);
    }

    public void changeCache(String key, Object value) {
        this.cacheMap.computeIfPresent(key, (finalKey, oldValue) -> oldValue = value);
    }

    public Object getCachedObject(String key) {
        return this.cacheMap.get(key);
    }

    public boolean isCached(String key) {
        return this.cacheMap.containsKey(key);
    }

    public void clearCacheData() {
        this.cacheMap.clear();
    }
}
