package org.sf.tools.metadata;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;

import com.sforce.soap.metadata.FileProperties;
import com.sforce.soap.metadata.ListMetadataQuery;

/**
 * This is base implementation of metadata and all common implementations are here
 * including loading information from salesforce and serializing the data to XML. It has one
 * abstract method process() which is implemented by all concrete classes and has implementations related to 
 * that metadata. 
 * @author Muhammad Sabir. msabiransari@gmail.com. 11/07/2014.
 * @see SalesforceMetada
 * @see MetadataType
 * @version 1.0
 * @since 1.0 
 */

public abstract class BaseMetadata implements SalesforceMetadata{
	protected MetadataManager manager;
	public BaseMetadata(MetadataManager manager){
		this.manager=manager;
	}
	
	@Override
	public void load(){
		if(this.isFolderBased()){
			this.getFolders().stream().forEach(f->load(f));
		}else{
			load(null);
		}
	}
	
	/**
	 * Loads data from salesforce using MetadataConnection provided by MetadataManager.
	 * This functionality is same for all metadata types that why implementation has been 
	 * provided in base class. This method detects the metadata type if its folder or not and then
	 * invokes the listMetadata Api's.
	 * @param folder Folder name if metadata is dependent on folder, Null otherwise.
	 */
	private void load(String folder) {
		ListMetadataQuery query = new ListMetadataQuery();
		query.setType(getMetadataType());
		if(folder!=null){
			query.setFolder(folder);
		}
		try{
			FileProperties[] lmr = manager.getConnection().listMetadata(new ListMetadataQuery[] {query}, manager.getApiVersion());
			if (lmr != null) {
				if(this.isFolderType()){
					process(Arrays.asList(lmr));
				}else{
					process(
						Arrays.asList(lmr).stream().filter(fp->
							fp.getCreatedDate().after(manager.getChangeDate()) ||
							fp.getLastModifiedDate().after(manager.getChangeDate())
						).collect(Collectors.toList())
					);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Its an internal implementation for each metadata type. Since loading data from salesforce is
	 * same API call with metadata type/folder information passed, this method give chance to build the dependencies
	 * among metadata.
	 * @param data Information regarding the metadata including full name and timestamps.
	 */
	protected abstract void process(List<FileProperties> data);
	
	/**
	 * It converts metada information to XML string for all metadata which are not folder based.
	 * @param metadata Name Name of metadata
	 * @param members List of all members of the metadata
	 * @return XML as 
	 */
	protected String toXMLString(String metadataName, Set<String> members){
		if(members.size()==0){
			return "";
		}
		return toXMLString(metadataName, members, null);
	}

	/**
	 * It converts metadata information to XML string.
	 * @param metadataName Name of the metadata
	 * @param members List of all members of the metadata
	 * @param folders List of folders if its folder based.
	 * @return XML as String.
	 */
	protected String toXMLString(String metadataName, Set<String> members, Set<String> folders){
		if(members.size()==0){
			return "";
		}
		StringBuilder builder=new StringBuilder();
		builder.append("\t<types>\n");
		builder.append("\t\t<name>"+metadataName+"</name>\n");
		if(folders!=null){
			folders.stream().sorted().forEach(s->{
				builder.append("\t\t<members>" + s + "</members>\n");
			});
		}
		members.stream().sorted().forEach(s->{
			builder.append("\t\t<members>" + s + "</members>\n");
		});
		builder.append("\t</types>\n");
		return builder.toString();
	}

	/**
	 * Sequence Id. Returns zero as default. Concrete implementations return relevant values if needed.
	 */
	@Override
	public Integer getSequenceId(){
		return new Integer(0);
	}
	
	@Override
	public Boolean isFolderBased(){
		return Boolean.FALSE;
	}

	@Override
	public Boolean isFolderType(){
		return Boolean.FALSE;
	}
	
	@Override
	public void addFolder(String folder){
		throw new RuntimeException("Method has to be implemented in concrete class");
	}
	
	@Override
	public Set<String> getFolders(){
		throw new RuntimeException("Method has to be implemented in concrete class");
	}
}
