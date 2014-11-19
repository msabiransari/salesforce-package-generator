package org.sf.tools;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.tools.ant.BuildException;

import com.salesforce.ant.SFDCAntTask;
import com.sforce.soap.metadata.MetadataConnection;

/**
 * <p>This is packagegenerator ANT task which takes parameters from ANT build, 
 * performs validation, authenticates with salesforce, 
 * instantiate the MetadataManager and calls the generate method on it. 
 * When manager is done generating all the metadata,
 * it than creates the XML file.</p>
 * 
 * @author Muhammad Sabir. msabiransari@gmail.com. 11/07/2014.
 * @see MetadataManager
 * @see MetadataType
 * @version 1.0
 * @since 1.0 
 *
 */
public class PackageGeneratorTask extends SFDCAntTask {
	private String changeDate;
	private String outputFile;
	private String metadataTypes;
	private String[] mTypes;
	private Calendar changeCalendar;
	
	public void execute() throws BuildException {
		validateParameters();
		
		MetadataConnection connection=getMetadataConnection();
		MetadataManager manager=new MetadataManager(connection, getApiVersion(), changeCalendar, Arrays.asList(mTypes));
		
		String xmlStr=manager.generateMetadataXML();
		try{
			FileWriter writer=new FileWriter(outputFile);
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			writer.write("<Package xmlns=\"http://soap.sforce.com/2006/04/metadata\">\n");
			writer.write(xmlStr);
			writer.write("\t<version>"+getApiVersion()+"</version>\n");
			writer.write("</Package>");
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
			log("Error: " + e.getMessage());
		}
		System.out.printf("Completed.\n");
	}
	
	private void validateParameters() throws BuildException{
		if(changeDate==null || "".equals(changeDate.trim())){
			throw new BuildException("Change Date cannot be empty. Please provide the changeDate attribute with the value in format MM/dd/yyyy");
		}
		changeDate=changeDate.trim();
		try{
			if(changeDate.indexOf(":")<0){
				changeDate=changeDate + " 23:59";
			}
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
			LocalDateTime date = LocalDateTime.parse(changeDate, formatter);
			changeCalendar=new GregorianCalendar(date.getYear(), date.getMonthValue()-1, date.getDayOfMonth(), date.getHour(), date.getMinute());
		}catch(Exception e){
			throw new BuildException("Change Date is not valid. Please provide the changeDate value as MM/dd/yyyy or MM/dd/yyyy HH:mm");
		}
		
		if(super.getServerURL()==null || "".equals(super.getServerURL())){
			throw new BuildException("Server URL cannot be empty. Please provide the serverurl attribute with the values like https://login.salesforce.com");
		}
		
		if(super.getUsername()==null || "".equals(super.getUsername())){
			throw new BuildException("User Name cannot be empty. Please provide the username attribute as a valid Salesforce user");
		}
		
		if(super.getPassword()==null || "".equals(super.getPassword())){
			throw new BuildException("Password cannot be empty. Please provide the password attribute as a valid Salesforce password");
		}
		
		if(outputFile==null || "".equals(outputFile)){
			throw new BuildException("Output File cannot be empty. Please provide the outputFile attribute as a valid File location");
		}
		if(metadataTypes==null || "".equals(metadataTypes.trim())){
			mTypes=new String[0];
		}else{
			mTypes=metadataTypes.split(",");
		}
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	
	public void setMetadataTypes(String metadataTypes) {
		this.metadataTypes = metadataTypes;
	}
	
}
