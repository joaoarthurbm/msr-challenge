package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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


	public Project(String id) {
		this.id = id;
		this.developers = new HashSet<String>();
		this.issues = new HashMap<String,Issue>();
		this.commits = new HashMap<String,Commit>();

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

	public Issue getIssue(String issueID) {
		return this.issues.get(issueID);
	}
	
	private Commit getCommit(String commitID) {
		return this.commits.get(commitID);
	}


	public String getId() {
		return id;
	}

	public Collection<Issue> getIssues() {
		return issues.values();
	}
	
	public Collection<Commit> getCommits() {
		return this.commits.values();
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
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
