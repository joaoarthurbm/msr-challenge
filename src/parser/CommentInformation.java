package parser;



public class CommentInformation implements LineExtractedInformation {

	
	private String commentID;
	private String projectID;
	private String id;
	private String developer;
	private String body;
	
	public CommentInformation(String commentID, String projectID, String issueID, String developer, String body) {
		this.commentID = commentID;
		this.projectID = projectID;
		this.id = issueID;
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
		return id;
	}
	
	public String getProjectID() {
		return projectID;
	}

}