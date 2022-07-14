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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Thomas
 *
 */
public class Graph extends AttributeHolder {
	String title = "";
	
	Map<String, Node> nodes = new HashMap<String, Node>();
	Map<String, Edge> edges = new HashMap<String, Edge>();

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Node addNode(String id) {
		Node n = nodes.get(id);
		if(n == null) { 
			n = new Node(id);
			nodes.put(id, n);
		}
		return n;
	}
	
	public Edge addEdge(String id, Node src, Node tgt) {
		Edge e = edges.get(id);
		if(e == null) {
			e = new Edge(id, src, tgt);
			edges.put(id, e);
		}
		return e;
	}
	
	public Node getNode(String id) { return nodes.get(id); }
	
	public Collection<Node> getNodes() { return nodes.values(); }
	public Collection<Edge> getEdges() { return edges.values(); }
	
	public class Node extends AttributeHolder {
		String id;
		
		public Node(String id) {
			this.id = id;
		}
		
		public String getId() {
			return id;
		}
		
		public int hashCode() {
			return id.hashCode();
		}
	}
	
	public class Edge extends AttributeHolder {
		String id;
		Node src;
		Node tgt;
		
		public Edge(String id, Node src, Node tgt) {
			this.id = id;
			this.src = src;
			this.tgt = tgt;
		}
		
		public Node getSrc() {
			return src;
		}
		
		public Node getTgt() {
			return tgt;
		}
		
		public String getId() {
			return id;
		}
	}
}
