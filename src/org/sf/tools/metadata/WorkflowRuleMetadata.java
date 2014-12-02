package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class WorkflowRuleMetadata extends BaseMetadata{
	private Set<String> workflowRules=new HashSet<>();
	
	public WorkflowRuleMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		SalesforceMetadata workflowMetadata=manager.getMetadata(MetadataType.Workflow.toString());
		data.stream().map(fp->fp.getFullName()).forEach(s->{
			workflowRules.add(s);
			workflowMetadata.addMember(s.substring(0, s.indexOf('.')));
		});
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), workflowRules);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.WorkflowRule.toString();
	}
	
	@Override
	public void addMember(String member){
		workflowRules.add(member);
	}
	
	@Override
	public Integer getSequenceId(){
		return new Integer(501);
	}
}
