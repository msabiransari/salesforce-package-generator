package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		SalesforceMetadata workflowMetadata=manager.getMetadata(MetadataType.Workflow.toString());
		data.stream().map(fp->fp.getFullName()).forEach(s->{
			workflowAlerts.add(s);
			workflowMetadata.addMember(s.substring(0, s.indexOf('.')));
		});
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
	
	@Override
	public Integer getSequenceId(){
		return new Integer(502);
	}
}
