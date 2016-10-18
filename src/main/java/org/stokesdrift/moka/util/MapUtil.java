package org.stokesdrift.moka.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MapUtil {

    @SuppressWarnings("unchecked")
	public static <T> Set<T> toSet(Set<?> set) {
        Set<T> setOfType = new HashSet<T>(set.size());
        Iterator<?> iter = set.iterator();
        while(iter.hasNext()) {
        	T element = (T)iter.next();
        	setOfType.add(element);
        }
        return setOfType;
     }
	
}
