package org.sf.tools.metadata;

import java.util.List;

import org.sf.tools.MetadataManager;
import org.sf.tools.MetadataType;

import com.sforce.soap.metadata.FileProperties;

public class ReportFolderMetadata extends BaseMetadata{
	
	public ReportFolderMetadata(MetadataManager manager){
		super(manager);
	}
	
	@Override
	public void process(List<FileProperties> data){
		SalesforceMetadata reportMetadata=manager.getMetadata(MetadataType.Report.toString());
		data.stream().map(fp->fp.getFullName()).forEach(s->{
			reportMetadata.addFolder(s);
		});
	}
	
	@Override
	public String toXMLString(){
		return "";
	}
	
	@Override
	public String getMetadataType(){
		return MetadataType.ReportFolder.toString();
	}

	@Override
	public void addMember(String member) {
		throw new RuntimeException("ReportFolder metadata is special and keep its members in Report.");
	}
	
	@Override
	public Integer getSequenceId(){
		return new Integer(300);
	}
	
	@Override
	public Boolean isFolderType(){
		return Boolean.TRUE;
	}
}
