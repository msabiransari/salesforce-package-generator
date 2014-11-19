package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class ApprovalProcessMetadata extends BaseMetadata{
	private Set<String> approvalProcesses=new HashSet<>();
	
	public ApprovalProcessMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		approvalProcesses.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), approvalProcesses);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.ApprovalProcess.toString();
	}
	
	@Override
	public void addMember(String member){
		approvalProcesses.add(member);
	}
}
