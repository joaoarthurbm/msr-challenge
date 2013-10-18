package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Commit {

	private String id;
	private Map<String, CommitComment> commitComments;
	
	public Commit(String commitID) {
		this.id = commitID;
		this.commitComments = new HashMap<String,CommitComment>();
	}
	
	public String getId() {
		return id;
	}

	public void addComment(String commentID, String developer, String body) {

		CommitComment commitComment = this.commitComments.get(commentID);
		
		if (commitComment == null) {
			CommitComment newComment = new CommitComment(commentID,body,developer);
			this.commitComments.put(commentID, newComment);
		}
		
	}
	
	public Collection<CommitComment> getComments() {
		return this.commitComments.values();
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
		Commit other = (Commit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
