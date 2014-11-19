package org.sf.tools;

/**
 * <p>This is enum declaration for all possible Metadata types in salesforce. 
 * It shows all supported metadata types in this tools.</p>
 * 
 * @author Muhammad Sabir. msabiransari@gmail.com. 11/07/2014.
 * @see PackageGeneratorTask
 * @see MetadataManager
 * @version 1.0
 * @since 1.0 
 *
 */

public enum MetadataType {

	ApexClass ("ApexClass"),
	ApexComponent ("ApexComponent"),
	ApexPage ("ApexPage"),
	ApexTrigger ("ApexTrigger"),
	AppMenu ("AppMenu"),
	ApprovalProcess ("ApprovalProcess"),
	CustomApplication ("CustomApplication"),
	CustomField ("CustomField"),
	CustomObject ("CustomObject"),
	CustomPageWebLink ("CustomPageWebLink"),
	CustomTab ("CustomTab"),
	DocumentFolder ("DocumentFolder"),
	Document ("Document"),
	EmailFolder ("EmailFolder"),
	EmailTemplate ("EmailTemplate"),
	Group ("Group"),
	HomePageComponent ("HomePageComponent"),
	Layout ("Layout"),
	PermissionSet ("PermissionSet"),
	Profile ("Profile"),
	Queue ("Queue"),
	RecordType ("RecordType"),
	ReportFolder ("ReportFolder"),
	Report ("Report"),
	ReportType ("ReportType"),
	Role ("Role"),
	StaticResource ("StaticResource"),
	ValidationRule ("ValidationRule"),
	WebLink ("WebLink"),
	Workflow ("Workflow");
	
	private final String metadataName;
	
	MetadataType(String metadataName){
		this.metadataName=metadataName;
	}
	
	public String toString() {
		return metadataName;
	}
	
}
