package model;

public class CommitComment implements Comment {

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
	
	public String getID() {
		return commentID;
	}
	
	public String getDeveloper() {
		return developer;
	}

	@Override
	public String getCommitID() {
		return this.getID();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commentID == null) ? 0 : commentID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommitComment other = (CommitComment) obj;
		if (commentID == null) {
			if (other.commentID != null)
				return false;
		} else if (!commentID.equals(other.commentID))
			return false;
		return true;
	}
	
	

}
