package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class PermissionSetMetadata extends BaseMetadata{
	private Set<String> permissions=new HashSet<>();
	
	public PermissionSetMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		permissions.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), permissions);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.PermissionSet.toString();
	}
	
	@Override
	public void addMember(String member){
		permissions.add(member);
	}
}
