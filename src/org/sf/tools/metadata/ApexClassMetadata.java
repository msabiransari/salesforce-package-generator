package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class ApexClassMetadata extends BaseMetadata{
	private Set<String> classes=new HashSet<String>();
	
	public ApexClassMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		classes.addAll(data.stream().map(fp->fp.getFullName()).collect(Collectors.toSet()));
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), classes);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.ApexClass.toString();
	}
	
	@Override
	public void addMember(String member){
		classes.add(member);
	}
}
