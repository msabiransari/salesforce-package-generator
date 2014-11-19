package org.sf.tools.metadata;

import java.util.List;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class DocumentFolderMetadata extends BaseMetadata{
	
	public DocumentFolderMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		SalesforceMetadata documentMetadata=manager.getMetadata(MetadataType.Document.toString());
		data.stream().map(fp->fp.getFullName()).forEach(s->{
			documentMetadata.addFolder(s);
		});
	}
	
	@Override
	public String toXMLString(){
		return "";
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.DocumentFolder.toString();
	}

	@Override
	public void addMember(String member) {
		throw new RuntimeException("DocumentFolder metadata is special and keep its members in Document.");
	}
	
	@Override
	public Integer getSequenceId(){
		return new Integer(200);
	}
	
	@Override
	public Boolean isFolderType(){
		return Boolean.TRUE;
	}
}
