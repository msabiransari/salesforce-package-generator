package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class WorkflowAlertMetadata extends BaseMetadata{
	private Set<String> workflowAlerts=new HashSet<>();
	
	public WorkflowAlertMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		workflowAlerts.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), workflowAlerts);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.WorkflowAlert.toString();
	}
	
	@Override
	public void addMember(String member){
		workflowAlerts.add(member);
	}
}
