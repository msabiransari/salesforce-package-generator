package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class DocumentMetadata extends BaseMetadata{
	private Set<String> folders=new HashSet<>();
	private Set<String> documents=new HashSet<>();
	
	public DocumentMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		documents.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
		Set<String> usedFolders=new HashSet<>();
		documents.stream().forEach(s->{
			if(s.indexOf("/")>-1){
				String folderName=s.substring(0, s.indexOf("/"));
				usedFolders.add(folderName);
			}
		});
		this.folders=usedFolders;
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), documents, folders);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.Document.toString();
	}
	
	@Override
	public void addMember(String member){
		documents.add(member);
	}
	
	@Override
	public void addFolder(String folder){
		folders.add(folder);
	}
	
	@Override 
	public Boolean isFolderBased(){
		return Boolean.TRUE;
	}
	
	@Override
	public Set<String> getFolders(){
		return this.folders;
	}
	
	@Override
	public Integer getSequenceId(){
		return new Integer(201);
	}
}
