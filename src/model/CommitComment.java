package model;

public class CommitComment {

	private String commentID;
	private String body;
	private String developer;

	public CommitComment(String commentID, String body, String developer) {
		this.commentID = commentID;
		this.body = body;
		this.developer = developer;
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

}
