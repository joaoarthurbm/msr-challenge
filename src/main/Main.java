package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import parser.CommitsCollectionParser;
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
		loadCommits(db,analyzer);
//		loadComments(db,analyzer);
//		saveComments(analyzer);
	}
	
	
	private static void loadCommits(DB db, ProjectsAnalyzer analyzer) {
		CommitsCollectionParser parser = new CommitsCollectionParser(db);
		List<LineExtractedInformation> listInformation = parser.parse();
	}


	private static void loadComments(DB db, ProjectsAnalyzer analyzer) {
		CommentsCollectionParser parser = new CommentsCollectionParser(db, MongoDBParser.PULL_REQUEST_COMMENTS_COLLECTION);
		List<LineExtractedInformation> listInformation = parser.parse();

		for (LineExtractedInformation info : listInformation) {
			analyzer.addProject(info.getProjectID(), info.getParentProjectID());
			analyzer.addPullRequest(info.getAbstractionID(), info.getParentProjectID());
			analyzer.addPullRequestComment(info.getCommentID(), info.getParentProjectID(), info.getAbstractionID(), info.getDeveloper(), info.getBody(), info.getCommitID());
		}

		parser = new CommentsCollectionParser(db, MongoDBParser.COMMIT_COMMENTS_COLLECTION);
		listInformation = parser.parse();
		for (LineExtractedInformation info : listInformation) {
			analyzer.addProject(info.getProjectID(), info.getParentProjectID());
			analyzer.addCommit(info.getAbstractionID(), info.getParentProjectID());
			analyzer.addCommitComment(info.getCommentID(), info.getParentProjectID(), info.getAbstractionID(), info.getDeveloper(), info.getBody());
		}


		parser = new CommentsCollectionParser(db, MongoDBParser.ISSUE_COMMENTS_COLLECTION);
		listInformation = parser.parse();
		for (LineExtractedInformation info : listInformation) {
			analyzer.addProject(info.getProjectID(), info.getParentProjectID());
			analyzer.addIssue(info.getAbstractionID(), info.getParentProjectID());
			analyzer.addIssueComment(info.getCommentID(), info.getParentProjectID(),
					info.getAbstractionID(), info.getDeveloper(), info.getBody());
		}
	}

	private static void saveComments(ProjectsAnalyzer analyzer) throws IOException {

		for (String projectID : analyzer.getProjects().keySet()) {

			Project project = analyzer.getProject(projectID);

			Collection<ProjectActivity> activities = new LinkedList<ProjectActivity>();
			activities.addAll(project.getCommits());
			activities.addAll(project.getIssues());
			activities.addAll(project.getPullRequests());

			BufferedWriter bf;
			bf = new BufferedWriter(new FileWriter(new File("/media/old/jarthur/workspace-msr/msr-challenge/data/discussions/uncategorized/" + project.getParentID().toLowerCase().replaceAll("/", "-") + "-comments.data")));

			for (ProjectActivity projectActivity : activities) {

				Collection<? extends Comment> comments = projectActivity.getComments();
				
				if (comments.size() >= 2) {

					for (Comment comment : comments) {
	
						if (comment.getBody()!=null && !comment.getBody().isEmpty()) {
							String clean = cleanComment(comment.getBody());
							if (clean.split(" ").length > 2) {
								bf.append(comment.getDeveloper() + " " +  project.getParentID() + " " + projectActivity.getID() + " " + clean);
								bf.newLine();
							}
	
						}
					
					}
					
				}

			}
			
			bf.close();			
		}
	}

	//FIXME improve efficiency 
	private static String cleanComment(String comment) {

		String flat = comment.replaceAll("[^\\x00-\\x7F]", "");
		flat = flat.replaceAll("\\p{Punct}", " ");
		
		String[] split = flat.split(" ");

		StringBuilder current = new StringBuilder();
		for (String word : split) {
			if (!word.contains("http")) {
				current.append(word+" ");
			}
		}

		StringBuilder sb = new StringBuilder();

		char[] chars = current.toString().toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (Character.isLetterOrDigit(current.charAt(i)) || current.charAt(i) == ' ') {
				sb.append(current.charAt(i));
			}
			
		}

		return sb.toString().trim();
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
