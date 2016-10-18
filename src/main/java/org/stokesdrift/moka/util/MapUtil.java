package org.stokesdrift.moka.util;

import java.util.HashSet;
import java.util.Set;

public class MapUtil {

    public static <T> Set<T> toSet(Set<?> set) {
        Set<T> setOfType = new HashSet<T>(set.size());
        set.forEach(ele -> {
            setOfType.add((T) ele);
        });
        return setOfType;
     }
	
}
