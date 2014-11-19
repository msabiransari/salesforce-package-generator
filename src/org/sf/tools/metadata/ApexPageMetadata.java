package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class ApexPageMetadata extends BaseMetadata{
	private Set<String> pages=new HashSet<String>();
	
	public ApexPageMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		pages.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), pages);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.ApexPage.toString();
	}
	
	@Override
	public void addMember(String member){
		pages.add(member);
	}
}
