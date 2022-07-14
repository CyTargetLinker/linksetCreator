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

import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.linkset.creator.graph.Graph.Edge;
import org.linkset.creator.graph.Graph.Node;


/**
 * 
 * @author Thomas
 *
 */
public class XGMMLWriter {
	final static String NS = "http://www.cs.rpi.edu/XGMML";
	
	public static <N, E> void write(Graph graph, PrintWriter out) throws IOException, XMLStreamException, FactoryConfigurationError {
		
		XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(out);
		writer.writeStartDocument();
		writer.writeCharacters("\n");
		writer.writeStartElement("graph");
		writer.writeDefaultNamespace(NS);
		writer.writeAttribute("id", "" + System.currentTimeMillis());
		writer.writeAttribute("label", graph.getTitle());

		printAttributes(graph, writer, 1);
		
		//Create the nodes
		for(Node n : graph.getNodes()) {
			writer.writeCharacters("\n");
			writer.writeCharacters("   ");
			writer.writeStartElement("node");
			writer.writeAttribute("id", n.getId());
			writer.writeAttribute("label", n.getId());

			printAttributes(n, writer, 2);
			
			writer.writeCharacters("\n");
			writer.writeCharacters("   ");
			writer.writeEndElement();
		}
		
		//Create the edges
		for(Edge edge : graph.getEdges()) {
			Node src = edge.getSrc();
			Node tgt = edge.getTgt();
			writer.writeCharacters("\n");
			writer.writeCharacters("   ");
			writer.writeStartElement("edge");
			writer.writeAttribute("id", edge.getId());
			writer.writeAttribute("label", edge.getId());
			writer.writeAttribute("source", src.getId());
			writer.writeAttribute("target", tgt.getId());
			
			Object interaction = edge.getAttribute("interaction");
			writer.writeCharacters("\n");
			writer.writeCharacters("      ");
			writer.writeStartElement("att");
			writer.writeAttribute("label", "interaction");
			writer.writeAttribute("name", "interaction");
			writer.writeAttribute("value", interaction == null ? "" : interaction.toString());	
			writer.writeAttribute("type", "string");

			writer.writeCharacters("\n");
			writer.writeCharacters("      ");
			writer.writeEndElement();
			
			printAttributes(edge, writer, 2);

			writer.writeCharacters("\n");
			writer.writeCharacters("   ");
			writer.writeEndElement();
		}
		writer.writeCharacters("\n");
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.close();
	}
	
	private static void printAttributes(AttributeHolder attr, XMLStreamWriter writer, int depth) throws XMLStreamException {
		for(String a : attr.getAttributeNames()) {
			Object o = attr.getAttribute(a);
			if(o == null) continue;
			
			String type =  o instanceof Number ? "real" : "string";
			writer.writeCharacters("\n");
			for(int i = 0; i < depth; i++) {
				writer.writeCharacters("   ");
			}
			writer.writeStartElement("att");
			boolean list = false;
			if(o.toString().contains("[") && o.toString().contains("]")) {
				type = "list";
				list = true;
				writer.writeAttribute("type", "list");
				writer.writeAttribute("name", "identifiers");

				String [] str = ((String)o).replace("[", "").replace("]", "").split(",");
				for(int i = 0; i < str.length; i++) {
					writer.writeCharacters("\n");
					for(int j = 0; j < depth+1; j++) {
						writer.writeCharacters("   ");
					}
					writer.writeStartElement("att");
					writer.writeAttribute("type", "string");
					writer.writeAttribute("name", "identifiers");
					writer.writeAttribute("value", str[i]);

					writer.writeEndElement();
				}
			} else {
				writer.writeAttribute("label", a);
				writer.writeAttribute("name", a);
				writer.writeAttribute("value", "" + o);
				if(a.equals("context+ score") || a.equals("score") || a.equals("pvalue")) {
					writer.writeAttribute("type", "real");
				} else {
					writer.writeAttribute("type", type);
				}
			}
			if(list) {
				writer.writeCharacters("\n");
				for(int i = 0; i < depth; i++) {
					writer.writeCharacters("   ");
				}
			}
			writer.writeEndElement();
		}
	}
}