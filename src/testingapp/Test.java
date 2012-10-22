package testingapp;

public class Test {
	private String name;
	private String outcome;
	private String report;

	public Test(String name, String outcome, String report) 
	{
		this.name = name;
		this.outcome = outcome;
		this.report = report;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
}
