package model;

public class PullRequestComment implements Comment {

	private String id;
	private String body;
	private String developer;
	private String commitID;
	
	public PullRequestComment(String id, String body, String developer, String commitID) {
		this.id = id;
		this.body = body;
		this.developer = developer;
		this.commitID = commitID;
	}
	
	public String getDeveloper() {
		return developer;
	}
	
	public String getID() {
		return id;
	}
	
	public String getBody() {
		return body;
	}
	
	public String getCommitID() {
		return commitID;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PullRequestComment other = (PullRequestComment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
