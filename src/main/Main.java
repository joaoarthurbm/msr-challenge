package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import model.Comment;
import model.Commit;
import model.CommitComment;
import model.Issue;
import model.IssueComment;
import model.Project;
import model.ProjectActivity;
import model.PullRequest;
import model.PullRequestComment;
import parser.CommentsCollectionParser;
import parser.LineExtractedInformation;
import parser.MongoDBParser;

import com.mongodb.DB;
import com.mongodb.Mongo;

import facade.ProjectsAnalyzer;

public class Main {

	public static void main(String[] args) throws IOException {
		Mongo mongo = new Mongo("localhost");
		DB db = mongo.getDB("msr14");
		ProjectsAnalyzer analyzer = new ProjectsAnalyzer();
		populate(db,analyzer);
		saveDiscussions(analyzer);
	}
	
	private static void populate(DB db, ProjectsAnalyzer analyzer) {
		CommentsCollectionParser parser = new CommentsCollectionParser(db, MongoDBParser.PULL_REQUEST_COMMENTS_COLLECTION);
		List<LineExtractedInformation> listInformation = parser.parse();
		
		for (LineExtractedInformation info : listInformation) {
			analyzer.addProject(info.getProjectID());
			analyzer.addPullRequest(info.getAbstractionID(), info.getProjectID());
			analyzer.addPullRequestComment(info.getCommentID(), info.getProjectID(), info.getAbstractionID(), info.getDeveloper(), info.getBody(), info.getCommitID());
		}
		
		parser = new CommentsCollectionParser(db, MongoDBParser.COMMIT_COMMENTS_COLLECTION);
		listInformation = parser.parse();
		for (LineExtractedInformation info : listInformation) {
			analyzer.addProject(info.getProjectID());
			analyzer.addCommit(info.getAbstractionID(), info.getProjectID());
			analyzer.addCommitComment(info.getCommentID(), info.getProjectID(), info.getAbstractionID(), info.getDeveloper(), info.getBody());
		}
		
		
		parser = new CommentsCollectionParser(db, MongoDBParser.ISSUE_COMMENTS_COLLECTION);
		listInformation = parser.parse();
		for (LineExtractedInformation info : listInformation) {
			analyzer.addProject(info.getProjectID());
			analyzer.addIssue(info.getAbstractionID(), info.getProjectID());
			analyzer.addIssueComment(info.getCommentID(), info.getProjectID(),
					info.getAbstractionID(), info.getDeveloper(), info.getBody());
		}
	}

	private static void saveDiscussions(ProjectsAnalyzer analyzer) throws IOException {
		List<String> popularProjects = new LinkedList<String>();
		//popular projects
		BufferedReader fr = new BufferedReader(new FileReader("top-commented-projects.txt"));
		
		String p;
		while ( (p = fr.readLine()) != null) {
			if (!p.startsWith("#")) popularProjects.add(p);
		}
		fr.close();
		
		for (String projectID : popularProjects) {
			
			Project project = analyzer.getProject(projectID);
			
			Collection<ProjectActivity> activities = new LinkedList<ProjectActivity>();
			activities.addAll(project.getCommits());
			activities.addAll(project.getIssues());
			activities.addAll(project.getPullRequests());
			
			BufferedWriter bf;
			bf = new BufferedWriter(new FileWriter(new File("/home/jarthur/workspace-msr/msr-challenge/data/discussions/design/comments.data")));

			for (ProjectActivity projectActivity : activities) {
				
				Collection<? extends Comment> comments = projectActivity.getComments();
				if(comments.size() > 2) {
					
					for (Comment comment : comments) {
						String clean = cleanComment(comment.getBody());
						if (clean.split(" ").length > 2) {
							bf.append(clean);
							bf.newLine();
						}
					}
				}
			}
			bf.close();			

		}
	}
	
	private static String cleanComment(String comment) {
		String flat = comment.replace("\n", " ").replaceAll("[^\\x00-\\x7F]", "").replaceAll("//", "");
		return flat;
	}

	private static void printProjectColaborator(ProjectsAnalyzer analyzer) {
		System.out.println("project collaborator");
		Collection<Project> values = analyzer.getProjects().values();
		for (Project p : values) {
			for (String developer : p.getCollaborators())
				System.out.println(p.getID()+" "+developer);
		}
		
	}

	private static void printProjectPullCommentCommitDeveloper(ProjectsAnalyzer analyzer) {
		System.out.println("project pull comment commit developer");
		Collection<Project> values = analyzer.getProjects().values();
		for (Project p : values) {
			for (PullRequest pull : p.getPullRequests()) {
				for (PullRequestComment comment : pull.getComments()) {
					System.out.println(p.getID() + " " + pull.getID() + " " + comment.getID() + " " + comment.getCommitID() + " " + comment.getDeveloper());
				}
			}
		}
		
	}

	private static void printProjectCommitCommentDeveloperRawData(ProjectsAnalyzer analyzer) {
		System.out.println("project commit comment developer");
		Collection<Project> values = analyzer.getProjects().values();
		for (Project p : values) {
			for (Commit commit : p.getCommits()) {
				for (CommitComment comment : commit.getComments()) {
					System.out.println(p.getID() + " " + commit.getID() + " " + comment.getID() + " " + comment.getDeveloper());
				}
			}
		}
	}

	private static void printProjectIssueCommentDeveloperRawData(ProjectsAnalyzer analyzer) {
		System.out.println("project issue comment developer");
		Collection<Project> values = analyzer.getProjects().values();
		for (Project p : values) {
			for (Issue i : p.getIssues()) {
				for (IssueComment c : i.getComments()) {
					System.out.println(p.getID() + " " + i.getID() + " " + c.getID() + " " + c.getDeveloper());
				}
			}
		}
	}

}
