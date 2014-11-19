package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class LayoutMetadata extends BaseMetadata{
	private Set<String> layouts=new HashSet<>();
	
	public LayoutMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		SalesforceMetadata customObjectMetadata=manager.getMetadata(MetadataType.CustomObject.toString());
		data.stream().map(fp->fp.getFullName()).forEach(s->{
			layouts.add(s);
			customObjectMetadata.addMember(s.substring(0, s.indexOf('-')));
		});
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), layouts);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.Layout.toString();
	}
	
	@Override
	public void addMember(String member){
		layouts.add(member);
	}
}
