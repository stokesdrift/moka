package org.stokesdrift.moka.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.stokesdrift.moka.util.MapUtil;

public class PropertyDelegateMap implements Map<String, Object> {

		private Properties delegateMap;
		
		public void setDelegateMap(Properties replacementMap) {
			this.delegateMap = replacementMap;
		}
	
	/// Implementing Map methods
	
		@Override
		public int size() {
			if (delegateMap != null) {
				return delegateMap.size();
			}
			return 0;
		}

		@Override
		public boolean isEmpty() {
			if (delegateMap != null) {
			  return delegateMap.isEmpty();
			}
			return true;
		}

		@Override
		public boolean containsKey(Object key) {
			if (delegateMap != null) {
				return delegateMap.containsKey(key);
			}
			return false;
		}

		@Override
		public boolean containsValue(Object value) {
			if (delegateMap != null) {
				return delegateMap.containsValue(value);
			}
			return false;
		}

		@Override
		public Object get(Object key) {
			if (delegateMap != null) {
				return delegateMap.get(key);
			}		
			return null;
		}

		@Override
		public Object put(String key, Object value) {
			if (delegateMap != null) {
				return delegateMap.put(key, value);
			}
			return null;
		}

		@Override
		public Object remove(Object key) {
			if (delegateMap != null) {
				return delegateMap.remove(key);
			}
			return null;
		}

		@Override
		public void putAll(Map<? extends String, ? extends Object> m) {
			if (delegateMap != null) {
				delegateMap.putAll(m);
			}		
		}

		@Override
		public void clear() {
			if (delegateMap != null) {
				delegateMap.clear();
			}		
		}

		@Override
		public Set<String> keySet() {
			if (delegateMap != null) {
				return delegateMap.stringPropertyNames();
			}
			return null;
		}

		@Override
		public Collection<Object> values() {
			if (delegateMap != null) {
				return delegateMap.values();
			}
			return null;
		}

		@Override
		public Set<java.util.Map.Entry<String, Object>> entrySet() {
			if (delegateMap != null) {
				return MapUtil.<Map.Entry<String,Object>>toSet(delegateMap.entrySet());			
			}
			return null;
		}
}
