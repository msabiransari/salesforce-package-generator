package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class WorkflowMetadata extends BaseMetadata{
	private Set<String> workflows=new HashSet<>();
	
	public WorkflowMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		workflows.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), workflows);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.Workflow.toString();
	}
	
	@Override
	public void addMember(String member){
		workflows.add(member);
	}
}
