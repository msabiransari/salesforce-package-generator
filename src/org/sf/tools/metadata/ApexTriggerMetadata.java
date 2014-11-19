package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class ApexTriggerMetadata extends BaseMetadata{
	private Set<String> triggers=new HashSet<String>();
	
	public ApexTriggerMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		triggers.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), triggers);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.ApexTrigger.toString();
	}
	
	@Override
	public void addMember(String member){
		triggers.add(member);
	}
}
