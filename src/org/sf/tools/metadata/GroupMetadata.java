package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class GroupMetadata extends BaseMetadata{
	private Set<String> groups=new HashSet<>();
	
	public GroupMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		groups.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), groups);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.Group.toString();
	}
	
	@Override
	public void addMember(String member){
		groups.add(member);
	}
}
