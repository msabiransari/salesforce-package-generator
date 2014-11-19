package org.sf.tools.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class WebLinkMetadata extends BaseMetadata{
	private Set<String> weblinks=new HashSet<>();
	
	public WebLinkMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		SalesforceMetadata customObjectMetadata=manager.getMetadata(MetadataType.CustomObject.toString());
		data.stream().map(fp->fp.getFullName()).forEach(s->{
			weblinks.add(s);
			customObjectMetadata.addMember(s.substring(0, s.indexOf('.')));
		});
	}
	
	@Override
	public String toXMLString(){
		return super.toXMLString(getMetadataType(), weblinks);
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.WebLink.toString();
	}
	
	@Override
	public void addMember(String member){
		weblinks.add(member);
	}
}
