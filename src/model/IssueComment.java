package model;

/**
 * IssueCommentID = projectID + "-" + issueIDNumber + "-" + commentIDNumber
 * @author jarthur
 *
 */
public class IssueComment {

	private String id;
	private String body;
	private String developer;
	
	public IssueComment(String id, String body, String developer) {
		this.id = id;
		this.body = body;
		this.developer = developer;
	}
	
	public String getDeveloper() {
		return developer;
	}
	
	public String getId() {
		return id;
	}
	
	public String getBody() {
		return body;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IssueComment other = (IssueComment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
