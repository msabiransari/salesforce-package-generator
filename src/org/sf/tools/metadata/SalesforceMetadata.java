package org.sf.tools.metadata;

import java.util.Set;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

/**
 * <p>This is the abstraction for metadata with methods which collaborate with
 * other metadata instances, provide useful information about the type, dependencies, 
 * fetch information from salesforce and serialize them in XML.</p>
 * 
 * @author Muhammad Sabir. msabiransari@gmail.com. 11/07/2014.
 * @see MetadataManager
 * @see MetadataType
 * @see BaseMetadata
 * @version 1.0
 * @since 1.0 
 *
 */
public interface SalesforceMetadata {
	/**
	 * Loads data from salesforce using MetadataConnection provided by MetadataManager.
	 */
	void load();
	
	/**
	 * It converts metadata information to XML string
	 * @return XML as String
	 */
	String toXMLString();
	
	/**
	 * Returns Metadata type of the instance
	 * @return enum MetadataType as String
	 */
	String getMetadataType();
	
	/**
	 * Adds an artifact from salesforce into the collection
	 * @param member as artifact name
	 */
	void addMember(String member);
	
	/**
	 * Adds folder to the collection of folders. Its used only for the metadata which are basically 
	 * folder dependent like email templates and other documents
	 * @param folder
	 */
	void addFolder(String folder);
	
	/**
	 * Returns list of folders used in all metadata artifacts
	 * @return list of folder names
	 */
	Set<String> getFolders();
	
	/**
	 * If metadata is folder based.
	 * @return True if metadata is folder based False otherwise.
	 */
	Boolean isFolderBased();
	
	/**
	 * If metadata itself is folder
	 * @return True if metada keep only folders, False otherwise.
	 */
	Boolean isFolderType();
	
	/**
	 * Sequence id is used to make sure that all the dependencies are loaded first.
	 * For example email templates are dependent on folders and all folders have to be loaded
	 * first before templates otherwise there is no way to pull metadata for email templates. Dependent metadata 
	 * will have greater number assigned then the metadata it is depending upon. Folder comes first and than Templates.    
	 * @return sequence number for the metadata.
	 */
	Integer getSequenceId();	
}
