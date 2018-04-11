//	Copyright 2015-2018 CyTargetLinker LinkSetCreator
//	Department of Bioinformatics - BiGCaT (Maastricht University)
//
//	Licensed under the Apache License, Version 2.0 (the "License");
//	you may not use this file except in compliance with the License.
//	You may obtain a copy of the License at
//
//		http://www.apache.org/licenses/LICENSE-2.0
//
//	Unless required by applicable law or agreed to in writing, software
//	distributed under the License is distributed on an "AS IS" BASIS,
//	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//	See the License for the specific language governing permissions and
//	limitations under the License.
package org.linkset.creator.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Thomas
 *
 */
public class AttributeHolder {
	Map<String, Object> attributes = new HashMap<String, Object>();

	public void setAttribute(String name, String value) {
		attributes.put(name, value);
	}

	public Object getAttribute(String name) {
		return attributes.get(name); 
	}

	public Set<String> getAttributeNames() {
		return attributes.keySet();
	}

	public void appendAttribute(String name, String value) {
		appendAttribute(name, value, "; ");
	}
	
	public void appendAttribute(String name, String value, String sep) {
		String curr = attributes.get(name) == null ? "" : attributes.get(name).toString();
		if("".equals(curr)) curr = value;
		else if(!curr.startsWith(value) && !curr.contains(sep + value)) {
			curr += sep + value;
		}
		attributes.put(name, curr);
	}
}
