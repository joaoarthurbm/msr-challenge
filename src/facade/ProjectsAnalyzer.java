package facade;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import model.Issue;
import model.Project;


public class ProjectsAnalyzer {
	
	
	private Map<String, Project> projects;
	
	public ProjectsAnalyzer() {
		this.projects = new HashMap<String,Project>();
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
	
	public void addCommit(String commitID, String projectID) {
		Project project = this.projects.get(projectID);
		project.addCommit(commitID);
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
			commentsPerProject.put(p.getId(), numberOfComments);
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
	
	public Map<String, Project> getProjects() {
		return projects;
	}
	
}
