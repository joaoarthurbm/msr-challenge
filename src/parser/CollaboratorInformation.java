package parser;

public class CollaboratorInformation implements LineExtractedInformation {

	private String project;
	private String developer;

	public CollaboratorInformation(String projectID, String developer) {
		this.project = projectID;
		this.developer = developer;
	}
	
	@Override
	public String getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCommentID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeveloper() {
		return this.developer;
	}

	@Override
	public String getAbstractionID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectID() {
		return this.project;
	}

	@Override
	public String getCommitID() {
		// TODO Auto-generated method stub
		return null;
	}

}
