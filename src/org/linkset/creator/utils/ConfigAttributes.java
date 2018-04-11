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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author mkutmon
 *
 */
public class ConfigAttributes {
	
	public final static String NAME = "name";
	public final static String ORGANISM = "organism";
	public final static String VERSION = "version";
	public final static String LICENSE = "license";
	
	public final static String SOURCE_COLUMNS = "source_columns";
	public final static String TARGET_COLUMNS = "target_columns";
	public final static String EDGE_COLUMNS = "edge_columns";

	public final static String SOURCE_ID_COLUMN = "source_id_column";
	public final static String SOURCE_TYPE = "source_type";
	public final static String SOURCE_LABEL_COLUMN = "source_label_column";
	public final static String SOURCE_BRIDGEDB = "source_bridgedb";
	public final static String SOURCE_SYSCODE_IN = "source_syscode_in";
	public final static String SOURCE_SYSCODES_OUT = "source_syscodes_out";
	
	public final static String TARGET_ID_COLUMN = "target_id_column";
	public final static String TARGET_TYPE = "target_type";
	public final static String TARGET_LABEL_COLUMN = "target_label_column";
	public final static String TARGET_BRIDGEDB = "target_bridgedb";
	public final static String TARGET_SYSCODE_IN = "target_syscode_in";
	public final static String TARGET_SYSCODES_OUT = "target_syscodes_out";
	
	public final static String INTERACTION_TYPE = "interaction_type";

	
	private String name;
	private String organism;
	private String version;
	private String license;
	
	private Set<Integer> sourceColumns;
	private Set<Integer> targetColumns;
	private Set<Integer> edgeColumns;
	private String interactionType;
	
	private Integer sourceIdColumn;
	private Integer sourceLabelColumn;
	private String sourceType;
	private File sourceBridgeDb;
	private String sourceSyscodeIn;
	private List<String> sourceSyscodeOut;
	
	private Integer targetIdColumn;
	private Integer targetLabelColumn;
	private String targetType;
	private File targetBridgeDb;
	private String targetSyscodeIn;
	private List<String> targetSyscodeOut;
	
	public ConfigAttributes() {
		sourceColumns = new HashSet<Integer>();
		targetColumns = new HashSet<Integer>();
		edgeColumns = new HashSet<Integer>();
		sourceSyscodeOut = new ArrayList<String>();
		targetSyscodeOut = new ArrayList<String>();
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganism() {
		return organism;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	public String getLicense() {
		return license;
	}


	public void setLicense(String license) {
		this.license = license;
	}


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceSyscodeIn() {
		return sourceSyscodeIn;
	}

	public void setSourceSyscodeIn(String sourceSyscodeIn) {
		this.sourceSyscodeIn = sourceSyscodeIn;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetSyscodeIn() {
		return targetSyscodeIn;
	}

	public void setTargetSyscodeIn(String targetSyscodeIn) {
		this.targetSyscodeIn = targetSyscodeIn;
	}

	public Set<Integer> getSourceColumns() {
		return sourceColumns;
	}
	
	public void setSourceColumns(String columns) {
		String [] split = columns.split(",");
		for(String str : split) {
			Integer column = Integer.parseInt(str);
			sourceColumns.add(column);
		}
	}

	public Set<Integer> getTargetColumns() {
		return targetColumns;
	}
	
	public void setTargetColumns(String columns) {
		String [] split = columns.split(",");
		for(String str : split) {
			Integer column = Integer.parseInt(str);
			targetColumns.add(column);
		}
	}

	public Set<Integer> getEdgeColumns() {
		return edgeColumns;
	}
	
	public void setEdgeColumns(String columns) {
		String [] split = columns.split(",");
		for(String str : split) {
			Integer column = Integer.parseInt(str);
			edgeColumns.add(column);
		}
	}

	public Integer getSourceIdColumn() {
		return sourceIdColumn;
	}
	
	public void setSourceIdColumn(String column) {
		sourceIdColumn = Integer.parseInt(column);
	}

	public Integer getSourceLabelColumn() {
		return sourceLabelColumn;
	}
	
	public void setSourceLabelColumn(String column) {
		sourceLabelColumn = Integer.parseInt(column);
	}

	public File getSourceBridgeDb() {
		return sourceBridgeDb;
	}
	
	public void setSourceBridgeDb(String bridgedb) {
		sourceBridgeDb = new File(bridgedb);
	}

	public List<String> getSourceSyscodeOut() {
		return sourceSyscodeOut;
	}
	
	public void setSourceSyscodeOut(String syscodes) {
		String [] split = syscodes.split(",");
		for(String str : split) {
			sourceSyscodeOut.add(str);
		}
	}

	public Integer getTargetIdColumn() {
		return targetIdColumn;
	}
	
	public void setTargetIdColumn(String column) {
		targetIdColumn = Integer.parseInt(column);
	}

	public Integer getTargetLabelColumn() {
		return targetLabelColumn;
	}
	
	public void setTargetLabelColumn(String column) {
		targetLabelColumn = Integer.parseInt(column);
	}

	public File getTargetBridgeDb() {
		return targetBridgeDb;
	}
	
	public void setTargetBridgeDb(String bridgedb) {
		targetBridgeDb = new File(bridgedb);
	}

	public List<String> getTargetSyscodeOut() {
		return targetSyscodeOut;
	}
	
	public void setTargetSyscodeOut(String syscodes) {
		String [] split = syscodes.split(",");
		for(String str : split) {
			targetSyscodeOut.add(str);
		}
	}


	public String getInteractionType() {
		return interactionType;
	}


	public void setInteractionType(String interactionType) {
		this.interactionType = interactionType;
	}
}
