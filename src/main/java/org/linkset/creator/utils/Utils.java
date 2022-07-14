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
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.bridgedb.BridgeDb;
import org.bridgedb.IDMapper;
import org.bridgedb.IDMapperException;

/**
 * 
 * @author mkutmon
 *
 */
public class Utils {

	public static void setUpLogger(Logger log, File logFile, boolean append) throws SecurityException, IOException {
		FileHandler fh = new FileHandler(logFile.getAbsolutePath(), append);
		log.addHandler(fh);
		log.setLevel(Level.ALL);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
	}

	public static IDMapper initIDMapper(File file, boolean transitive) {
		try {
			Class.forName("org.bridgedb.rdb.IDMapperRdb");
		} catch (ClassNotFoundException ex) {
			return null;
		}

		try {
			IDMapper mapper = BridgeDb.connect("idmapper-pgdb:" + file.getAbsolutePath());
			return mapper;
		} catch (IDMapperException e) {
			return null;
		}
	}

}
