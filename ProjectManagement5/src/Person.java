
public class Person {

	String name;
	String telephoneNo;
	String emailAddress;
	String physicalAddress;

	// constructor
	public Person(String name, String telephoneNo, String emailAddress, String physicalAddress) {
		this.name = name;
		this.telephoneNo = telephoneNo;
		this.emailAddress = emailAddress;
		this.physicalAddress = physicalAddress;
	}

	// tostring method which prints data in format specified
	@Override
	public String toString() {
		return "Name: " + name + "\n" + "Telephone Number: " + telephoneNo + "\n" + "Email Address: " + emailAddress
				+ "\n" + "Physical Address: " + physicalAddress + "\n";
	}

	// creating getters and setters
	public String getName() {
		return name;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

}
