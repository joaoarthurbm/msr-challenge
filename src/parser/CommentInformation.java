package parser;



public class CommentInformation implements LineExtractedInformation {

	
	public final static String BODY = "body";
	public final static String COMMENT_ID = "comment_id";
	public final static String ISSUE_ID = "issue_id";
	public final static String DEVELOPER = "developer";
	public final static String PROJECT_ID = "project_id";

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