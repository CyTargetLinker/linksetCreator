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
package org.linkset.creator.utils;

import java.io.File;
import java.io.PrintWriter;
import java.util.logging.Logger;

import org.linkset.creator.graph.Graph;
import org.linkset.creator.graph.XGMMLWriter;

import uk.co.flamingpenguin.jewel.cli.ArgumentValidationException;
import uk.co.flamingpenguin.jewel.cli.CliFactory;
import uk.co.flamingpenguin.jewel.cli.Option;

/**
 * Commonly used command line arguments to be parsed.
 * @author Thomas
 */
public class ArgsParser {
	private final static Logger log = Logger.getLogger(ArgsParser.class.getName());
	
	public static <A> A parse(String[] args, Class<? extends A> c) throws ArgumentValidationException {
		return CliFactory.parseArguments(c, args);
	}

	public interface AHelp {
		@Option(helpRequest = true, shortName = "h")
		boolean getHelp();
	}
	
	public interface AFilesIn {
		@Option(shortName = "i", description = "The path to the input file.")
		public File getInput();
		public boolean isInput();
		
		@Option(shortName = "c", description = "The path to the config file.")
		public File getConfig();
		public boolean isConfig();
	}
	
	public interface AFilesOut {
		@Option(shortName = "o", description = "The output GML file or directory to write the network(s) to.")
		public File getOutput();
		public boolean isOutput();
	}
	
	public interface GraphBuilder {
		public Graph buildGraph(File in) throws Exception;
	}
	
	private interface GraphWriter {
		public void write(Graph g, PrintWriter out) throws Exception;
	}
	
	private static class XGMML implements GraphWriter {
		public void write(Graph g, PrintWriter out) throws Exception { XGMMLWriter.write(g, out); }
	}
	
	/**
	 * writes xgmml file
	 * @param fi
	 * @param fo
	 * @param gb
	 * @throws Exception
	 */
	public static void convertAndWrite(AFilesIn fi, AFilesOut fo, GraphBuilder gb) throws Exception {
		GraphWriter writer = new XGMML();
		File input = fi == null ? null : fi.getInput();
		File output = fo.isOutput() ? fo.getOutput() : new File(fi.getInput().getAbsolutePath() + ".xgmml");
		log.info("Converting " + input + " to " + output + "\n");
		Graph g = gb.buildGraph(input);
		PrintWriter po = new PrintWriter(output);
		writer.write(g, po);
		po.close();
	}
}