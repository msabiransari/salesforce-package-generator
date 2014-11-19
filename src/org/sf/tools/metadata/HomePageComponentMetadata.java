package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class HomePageComponentMetadata extends BaseMetadata{
	private Set<String> components=new HashSet<String>();
	
	public HomePageComponentMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		components.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), components);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.HomePageComponent.toString();
	}
	
	@Override
	public void addMember(String member){
		components.add(member);
	}
}
