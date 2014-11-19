package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class CustomApplicationMetadata extends BaseMetadata{
	private Set<String> applications=new HashSet<>();
	
	public CustomApplicationMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		applications.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), applications);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.CustomApplication.toString();
	}
	
	@Override
	public void addMember(String member){
		applications.add(member);
	}
}
