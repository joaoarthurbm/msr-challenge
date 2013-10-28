package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PullRequest implements ProjectActivity {
	
	private String id;
	private Map<String,PullRequestComment> pullRequestComments;
	
	public PullRequest(String id) {
		this.id = id;
		this.pullRequestComments = new HashMap<String,PullRequestComment>();
	}
	
	public void addPullRequestComment(String commentID, String developer, String body, String commitID) {
		
		PullRequestComment pullRequestComment = this.pullRequestComments.get(commentID);
		
		if (pullRequestComment == null) {
			PullRequestComment newComment = new PullRequestComment(commentID,body,developer,commitID);
			this.pullRequestComments.put(commentID, newComment);
		}
		
	}
	
	public Collection<PullRequestComment> getComments() {
		return this.pullRequestComments.values();
	}
	
	
	public String getID() {
		return id;
	}

}
