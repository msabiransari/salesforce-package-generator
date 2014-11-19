package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class QueueMetadata extends BaseMetadata{
	private Set<String> queues=new HashSet<String>();
	
	public QueueMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		queues.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), queues);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.Queue.toString();
	}
	
	@Override
	public void addMember(String member){
		queues.add(member);
	}
}
