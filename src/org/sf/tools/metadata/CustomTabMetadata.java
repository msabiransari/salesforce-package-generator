package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class CustomTabMetadata extends BaseMetadata{
	private Set<String> customTabs=new HashSet<>();
	
	public CustomTabMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		customTabs.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), customTabs);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.CustomTab.toString();
	}
	
	@Override
	public void addMember(String member){
		customTabs.add(member);
	}
}
