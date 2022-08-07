import java.time.LocalDate;

public class ProjectManagement {

	private String projectNo;
	private String projectName;
	private String customerName;
	private String typeBuilding;
	private String physicalAddress;
	private String erfNo;
	private Double projectFee;
	private Double totalFeePaid;
	private String deadline;
	private String contractorName;
	private String projectStatus;

	// constructor
	public ProjectManagement(String projectNo, String customerName, String projectName, String typeBuilding,
			String physicalAddress, String erfNo, Double projectFee, Double totalFee, String deadline,
			String contractorName, String projectStatus) {

		this.projectNo = projectNo;
		this.customerName = customerName;
		this.projectName = projectName;
		this.typeBuilding = typeBuilding;
		this.physicalAddress = physicalAddress;
		this.erfNo = erfNo;
		this.projectFee = projectFee;
		this.totalFeePaid = totalFee;
		this.deadline = deadline;
		this.contractorName = contractorName;
		this.projectStatus = projectStatus;

	}

	// tostring method which prints data in format specified
	@Override
	public String toString() {
		return "Project Number: " + projectNo + "\n" + "Customer Name: " + customerName + "\n" + "Project Name: "
				+ projectName + "\n" + "Type Building: " + typeBuilding + "\n" + "Physical Address: " + physicalAddress
				+ "\n" + "ERF Number: " + erfNo + "\n" + "Project Fee: " + projectFee + "\n" + "Total Fee Paid: "
				+ totalFeePaid + "\n" + "Deadline: " + deadline + "\n" + "Contractor Name: " + contractorName + "\n"
				+ "Project Status: " + projectStatus + "\n";
	}

	// creating getters and setters
	public String getProjectNo() {
		return projectNo;
	}

	public Double getTotalFeePaid() {
		return totalFeePaid;
	}

	public void setTotalFeePaid(Double totalFeePaid) {
		this.totalFeePaid = totalFeePaid;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setTypeBuilding(String typeBuilding) {
		this.typeBuilding = typeBuilding;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public void setErfNo(String erfNo) {
		this.erfNo = erfNo;
	}

	public void setProjectFee(Double projectFee) {
		this.projectFee = projectFee;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getTypeBuilding() {
		return typeBuilding;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public String getErfNo() {
		return erfNo;
	}

	public Double getProjectFee() {
		return projectFee;
	}

	public Double getTotalFee() {
		return totalFeePaid;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFeePaid = totalFee;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	// method to finalise a project
	public int finaliseProject(String name, String phone, String email) {
		// Pass though project name so details about contractor etc can be found in
		// future
		double leftOver;

		if (getProjectFee().equals(getTotalFee()) || getTotalFee() > getProjectFee()) {
			LocalDate todaysDate = LocalDate.now();
			System.out.println("Project Fee fully paid. Project marked as completed. \nDate: " + todaysDate + "\n");
			return 1;
		} else if (getProjectFee() > getTotalFee()) {
			leftOver = getProjectFee() - getTotalFee();
			System.out.println("------Invoice-------");
			System.out.println("Name: " + name + "\n" + "Phone: " + phone + "\n" + "Email Address: " + email + "\n"
					+ "Fee still to be paid: " + leftOver);
			System.out.println("------Invoice-------" + "\n");
			return 0;
		} else {
			System.out.print("Error!");
			return -1;
		}
	}

}
