package org.sf.tools;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.sf.tools.metadata.SalesforceMetadata;

import com.sforce.soap.metadata.MetadataConnection;

/**
 * <p>This class instantiate all metadata classes, sort them, loads information from 
 * salesforce by executing build method on each metadata instance and collects XML</p>
 * 
 * @author Muhammad Sabir. msabiransari@gmail.com. 11/07/2014.
 * @see PackageGeneratorTask
 * @see MetadataType
 * @version 1.0
 * @since 1.0 
 *
 */

public class MetadataManager {
	private Map<String, SalesforceMetadata> metadata=new HashMap<>();
	private Calendar changeDate;
	private double apiVersion;
	private List<String> metadataList;
	
	private MetadataConnection connection;
	
	public MetadataManager(MetadataConnection connection, double apiVersion, Calendar date, List<String> metadataList){
		this.connection=connection;
		this.apiVersion=apiVersion;
		this.changeDate=date;
		this.metadataList=metadataList.stream().map(s->s.trim()).collect(Collectors.toList());
	}
	
	public String generateMetadataXML(){
		createMetadata();
		StringBuffer buffer=new StringBuffer();
		try{
			//load all metadata types before serializing since there are dependencies
			this.metadata.values().stream().sorted((sfm1, sfm2)->sfm1.getSequenceId().compareTo(sfm2.getSequenceId())).forEach(sfm->{
				sfm.load();
			});
			//serialize as XML
			this.metadata.values().stream().forEach(sfm->{
				buffer.append(sfm.toXMLString());
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	public double getApiVersion() {
		return apiVersion;
	}

	public MetadataConnection getConnection() {
		return connection;
	}

	public Calendar getChangeDate() {
		return changeDate;
	}
	
	public SalesforceMetadata getMetadata(String metadataName) {
		return metadata.get(metadataName);
	}

	private void createMetadata() {
		Arrays.asList(MetadataType.values()).stream().forEach(mdc->{
			try{
				SalesforceMetadata smd=(SalesforceMetadata)Class.forName("org.sf.tools.metadata."+mdc+"Metadata").getDeclaredConstructor(MetadataManager.class).newInstance(this);
				if(metadataList.size()==0 || metadataList.contains(smd.getMetadataType())){
					metadata.put(mdc.toString(), smd);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		});
	}
}
