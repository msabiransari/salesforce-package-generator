package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class StaticResourceMetadata extends BaseMetadata{
	private Set<String> resources=new HashSet<String>();
	
	public StaticResourceMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		resources.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), resources);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.StaticResource.toString();
	}
	
	@Override
	public void addMember(String member){
		resources.add(member);
	}
}
