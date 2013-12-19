package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * ProjectID = userName + "-" + projectName
 * @author jarthur
 *
 */
public class Project {

	private String id;
	private Set<String> developers;
	private Map<String, Issue> issues;
	private Map<String, Commit> commits;
	private Map<String, PullRequest> pull;
	private Set<String> collaborators;
	private String parentID;


	public Project(String id, String parentID) {
		this.id = id;
		this.developers = new HashSet<String>();
		this.issues = new HashMap<String,Issue>();
		this.commits = new HashMap<String,Commit>();
		this.pull = new HashMap<String,PullRequest>();
		this.collaborators = new HashSet<String>();
		this.parentID = parentID;

	}
	
	public void addDeveloper(String login) {
		this.developers.add(login);
	}

	public void addIssue(String issueID) {
		if (this.issues.get(issueID) == null) this.issues.put(issueID, new Issue(issueID));
	}

	public void addCommit(String commitID) {
		if (this.commits.get(commitID) == null) this.commits.put(commitID, new Commit(commitID));
	}
	
	public void addPullRequest(String pullID) {
		if (this.pull.get(pullID) == null) this.pull.put(pullID, new PullRequest(pullID));
	}
	
	public String getParentID() {
		return parentID;
	}

	public Issue getIssue(String issueID) {
		return this.issues.get(issueID);
	}
	
	public PullRequest getPullRequest(String id) {
		return this.pull.get(id);
	}
	
	
	private Commit getCommit(String commitID) {
		return this.commits.get(commitID);
	}


	public String getID() {
		return id;
	}

	public Collection<Issue> getIssues() {
		return issues.values();
	}
	
	public Collection<Commit> getCommits() {
		return this.commits.values();
	}
	
	public Collection<PullRequest> getPullRequests() {
		return this.pull.values();
	}

	public int getNumberOfIssues() {
		return this.issues.size();
	}

	public int getNumberOfDevelopers() {
		return this.developers.size();
	}

	public void addCommentToIssue(String commentID, String issueID,
			String developer, String body) {
		Issue issue = this.getIssue(issueID);

		if (issue != null) {
			issue.addIssueComment(commentID, developer, body);
		} else {
			issue = new Issue(issueID);
			issue.addIssueComment(commentID, developer, body);
			this.issues.put(issueID, issue);
		}

	}

	public void addCommentToCommit(String commentID, String commitID,
			String developer, String body) {

		Commit commit = this.getCommit(commitID);

		if (commit != null) {
			commit.addComment(commentID, developer, body);
		} else {
			commit = new Commit(commitID);
			commit.addComment(commentID, developer, body);
			this.commits.put(commitID, commit);
		}

	}
	
	public void addPullRequestComment(String commentID, String pullID,
			String developer, String body, String commitID) {
		PullRequest pull = this.getPullRequest(pullID);

		if (pull != null) {
			pull.addPullRequestComment(commentID, developer, body,commitID);
		} else {
			pull = new PullRequest(id);
			pull.addPullRequestComment(commentID, developer, body, commitID);
			this.pull.put(pullID, pull);
		}
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parentID == null) ? 0 : parentID.hashCode());
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
		Project other = (Project) obj;
		if (parentID == null) {
			if (other.parentID != null)
				return false;
		} else if (!parentID.equals(other.parentID))
			return false;
		return true;
	}

	public void addCollaborator(String developer) {
		this.collaborators.add(developer);
	}
	
	public Set<String> getCollaborators() {
		return collaborators;
	}

	public Collection<Comment> getAllComments() {
	
		Collection<Comment> allComments = new LinkedList<Comment>();
		
		for (Commit commit : this.commits.values()) {
			Collection<CommitComment> commitComments = commit.getComments();
			allComments.addAll(commitComments);
		}
		
		for (Issue i : this.issues.values()) {
			Collection<IssueComment> issueComments = i.getComments();
			allComments.addAll(issueComments);
		}

		for (PullRequest p : this.pull.values()) {
			Collection<PullRequestComment> pullComments = p.getComments();
			allComments.addAll(pullComments);
		}
		
		return allComments;
		
	}
	
	@Override
	public String toString() {
		return this.getID();
	}
}
