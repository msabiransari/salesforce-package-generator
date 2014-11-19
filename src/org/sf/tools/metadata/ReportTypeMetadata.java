package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class ReportTypeMetadata extends BaseMetadata{
	private Set<String> reportTypes=new HashSet<>();
	
	public ReportTypeMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		reportTypes.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), reportTypes);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.ReportType.toString();
	}
	
	@Override
	public void addMember(String member){
		reportTypes.add(member);
	}
}
