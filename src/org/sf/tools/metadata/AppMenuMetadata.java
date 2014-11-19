package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class AppMenuMetadata extends BaseMetadata{
	private Set<String> menus=new HashSet<>();
	
	public AppMenuMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		menus.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), menus);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.AppMenu.toString();
	}
	
	@Override
	public void addMember(String member){
		menus.add(member);
	}
}
