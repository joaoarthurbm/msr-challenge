package parser;


public class IssueInformation implements LineExtractedInformation {

	private String commentID, projectID, issueID, developer, body;
	
	public IssueInformation(String commentId, String projectID, String abstractionID, String developer, String body) {
		this.commentID = commentId;
		this.projectID = projectID;
	    this.issueID = abstractionID;
	    this.developer = developer;
	    this.body = body;
	}
	
	public String getBody() {
		return body;
	}
	
	public String getCommentID() {
		return commentID;
	}
	
	public String getDeveloper() {
		return developer;
	}
	
	public String getAbstractionID() {
		return issueID;
	}
	
	public String getProjectID() {
		return projectID;
	}
	
}
