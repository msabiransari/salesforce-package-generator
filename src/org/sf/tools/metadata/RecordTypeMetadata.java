package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class RecordTypeMetadata extends BaseMetadata{
	private Set<String> recordTypes=new HashSet<>();
	
	public RecordTypeMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		recordTypes.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), recordTypes);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.RecordType.toString();
	}
	
	@Override
	public void addMember(String member){
		recordTypes.add(member);
	}
}
