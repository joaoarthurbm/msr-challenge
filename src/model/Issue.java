package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * IssueID = projectName + "-" + issueIDNumber
 * 
 * @author jarthur
 *
 */
public class Issue implements ProjectActivity {
	
	private String id;
	private Map<String,IssueComment> issueComments;
	
	public Issue(String id) {
		this.id = id;
		this.issueComments = new TreeMap<String,IssueComment>();
	}
	
	public void addIssueComment(String commentID, String developer, String body) {
		
		IssueComment issueComment = this.issueComments.get(commentID);
		
		if (issueComment == null) {
			IssueComment newComment = new IssueComment(commentID,body,developer);
			this.issueComments.put(commentID, newComment);
		}
		
	}
	
	public Collection<IssueComment> getComments() {
		Collection<IssueComment> result = new LinkedList<IssueComment>();
		
		for (String key : this.issueComments.keySet()) {
			result.add(this.issueComments.get(key));
		}
		
		return result;
	}
	
	
	public int getCommentsNumber() {
		return this.issueComments.size();
	}
	
	public String getID() {
		return id;
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
		Issue other = (Issue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}













