package facade;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Comment;
import model.Commit;
import model.Issue;
import model.Project;
import model.PullRequest;


public class ProjectsAnalyzer {
	
	
	private Map<String, Project> projects;
	
	public ProjectsAnalyzer() {
		this.projects = new HashMap<String,Project>();
	}
	
	public Project getProject(String id) {
		return this.projects.get(id);
	}
	
	
	public void addProject(String id){
		if (this.projects.get(id) == null) this.projects.put(id, new Project(id)); 
	}
	
	public void addIssue(String issueID, String projectID){
		Project project = this.projects.get(projectID);
		project.addIssue(issueID);
	}
	
	public void addIssueComment(String commentID, String projectID, String issueID, String developer, String body){
		Project project = this.projects.get(projectID);
		project.addCommentToIssue(commentID,issueID,developer,body);
	}
	
	public void addPullRequestComment(String commentID, String projectID,
			String pullID, String developer, String body, String commitID) {
		Project project = this.projects.get(projectID);
		project.addPullRequestComment(commentID,pullID,developer,body, commitID);
	}
	
	public void addCommit(String commitID, String projectID) {
		Project project = this.projects.get(projectID);
		project.addCommit(commitID);
	}
	
	public void addPullRequest(String pullID, String projectID) {
		Project project = this.projects.get(projectID);
		project.addPullRequest(pullID);
	}


	public void addCommitComment(String commentID, String projectID,
			String commitID, String developer, String body) {
		Project project = this.projects.get(projectID);
		project.addCommentToCommit(commentID,commitID,developer,body);
	}
	
	public Map<String,Integer> getCommentsPerProjectFrequencyTable() {
		Map<String,Integer> commentsPerProject = new HashMap<String, Integer>();
		
		Integer numberOfComments = 0;
		for (Project p : projects.values()) {
			Collection<Issue> issues = p.getIssues();
			for (Issue i : issues) {
				numberOfComments+=i.getCommentsNumber();
			}
			commentsPerProject.put(p.getID(), numberOfComments);
			numberOfComments = 0;
		}
		
		return commentsPerProject;

	}


	public Collection<Issue> getIssues() {
		Collection<Issue> issues = new HashSet<Issue>();
		
		for (Project p : projects.values()) {
			issues.addAll(p.getIssues());
		}
		return issues;
	}
	
	public Collection<Commit> getCommits() {
		Collection<Commit> commits = new HashSet<Commit>();
		
		for (Project p : projects.values()) {
			commits.addAll(p.getCommits());
		}
		return commits;
	}
	
	public Collection<PullRequest> getPullRequests() {
		Collection<PullRequest> pullRequests = new HashSet<PullRequest>();
		
		for (Project p : projects.values()) {
			pullRequests.addAll(p.getPullRequests());
		}
		return pullRequests;
	}
	
	
	public Map<String, Project> getProjects() {
		return projects;
	}


	public void addCollaborator(String projectID, String developer) {
		Project project = this.projects.get(projectID);
		project.addCollaborator(developer);
	}
	
	public Collection<Comment> getAllComments() {
		Collection<Comment> comments = new LinkedList<Comment>();
		
		for (Project p : projects.values()) {
			comments.addAll(p.getAllComments());
		}
		return comments;
	}
	
	
	
}
