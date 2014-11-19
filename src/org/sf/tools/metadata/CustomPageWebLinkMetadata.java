package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class CustomPageWebLinkMetadata extends BaseMetadata{
	private Set<String> links=new HashSet<String>();
	
	public CustomPageWebLinkMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		links.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), links);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.CustomPageWebLink.toString();
	}
	
	@Override
	public void addMember(String member){
		links.add(member);
	}
}
