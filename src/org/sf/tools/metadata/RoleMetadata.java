package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class RoleMetadata extends BaseMetadata{
	private Set<String> roles=new HashSet<>();
	
	public RoleMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		roles.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), roles);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.Role.toString();
	}
	
	@Override
	public void addMember(String member){
		roles.add(member);
	}
}
