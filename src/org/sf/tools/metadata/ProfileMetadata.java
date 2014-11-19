package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class ProfileMetadata extends BaseMetadata{
	private Set<String> profiles=new HashSet<>();
	
	public ProfileMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		profiles.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), profiles);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.Profile.toString();
	}
	
	@Override
	public void addMember(String member){
		profiles.add(member);
	}
}
