package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class CustomFieldMetadata extends BaseMetadata{
	private Set<String> customFields=new HashSet<String>();
	
	public CustomFieldMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		SalesforceMetadata customObjectMetadata=manager.getMetadata(MetadataType.CustomObject.toString());
		data.stream().map(fp->fp.getFullName()).forEach(s->{
			customFields.add(s);
			customObjectMetadata.addMember(s.substring(0, s.indexOf('.')));
		});
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), customFields);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.CustomField.toString();
	}
	
	@Override
	public void addMember(String member){
		customFields.add(member);
	}
}
