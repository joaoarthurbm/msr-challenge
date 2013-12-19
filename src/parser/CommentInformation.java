package parser;



public class CommentInformation implements LineExtractedInformation {

	
	private String commentID;
	private String projectID;
	private String id;
	private String developer;
	private String body;
	private String commitID;
	private String parentProject;
	
	public CommentInformation(String commentID, String projectID, String parentProject, String issueID, String developer, String body, String commitID) {
		this.commentID = commentID;
		this.projectID = projectID;
		this.id = issueID;
		this.developer = developer;
		this.body = body;
		this.commitID = commitID;
		this.parentProject = parentProject;
	}
	
	public String getCommitID() {
		return commitID;
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

	@Override
	public String getParentProjectID() {
		return this.parentProject;
	}

}