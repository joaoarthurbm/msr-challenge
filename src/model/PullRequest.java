package model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class PullRequest implements ProjectActivity {
	
	private String id;
	private Map<String,PullRequestComment> pullRequestComments;
	
	public PullRequest(String id) {
		this.id = id;
		this.pullRequestComments = new TreeMap<String,PullRequestComment>();
	}
	
	public void addPullRequestComment(String commentID, String developer, String body, String commitID) {
		
		PullRequestComment pullRequestComment = this.pullRequestComments.get(commentID);
		
		if (pullRequestComment == null) {
			PullRequestComment newComment = new PullRequestComment(commentID,body,developer,commitID);
			this.pullRequestComments.put(commentID, newComment);
		}
		
	}
	
	public Collection<PullRequestComment> getComments() {
		Collection<PullRequestComment> result = new LinkedList<PullRequestComment>();
		
		for (String key : this.pullRequestComments.keySet()) {
			result.add(this.pullRequestComments.get(key));
		}
		
		return result;
	}
	
	
	public String getID() {
		return id;
	}

}
