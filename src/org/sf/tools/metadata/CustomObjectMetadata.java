package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class CustomObjectMetadata extends BaseMetadata{
	private Set<String> customObjects=new HashSet<>();
	
	public CustomObjectMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		customObjects.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), customObjects);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.CustomObject.toString();
	}
	
	@Override
	public void addMember(String member){
		customObjects.add(member);
	}
}
