package org.sf.tools.metadata;

import java.util.List;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class EmailFolderMetadata extends BaseMetadata{
	
	public EmailFolderMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		SalesforceMetadata emailTemplateMetadata=manager.getMetadata(MetadataType.EmailTemplate.toString());
		data.stream().map(fp->fp.getFullName()).forEach(s->{
			emailTemplateMetadata.addFolder(s);
		});
	}
	
	@Override
	public String toXMLString(){
		return "";
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.EmailFolder.toString();
	}

	@Override
	public void addMember(String member) {
		throw new RuntimeException("EmailFolder metadata is special and keep its members in EmailTemplate.");
	}
	
	@Override
	public Integer getSequenceId(){
		return new Integer(100);
	}
	
	@Override
	public Boolean isFolderType(){
		return Boolean.TRUE;
	}
}
