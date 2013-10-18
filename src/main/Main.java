package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import model.Commit;
import model.CommitComment;
import model.Issue;
import model.IssueComment;
import model.Project;
import parser.CommitCommentParser;
import parser.IssueCommentParser;
import parser.LineExtractedInformation;

import com.mongodb.DB;
import com.mongodb.Mongo;

import facade.ProjectsAnalyzer;

public class Main {

	public static void main(String[] args) throws IOException {
		Mongo mongo = new Mongo("localhost");
		DB db = mongo.getDB("msr14");
		ProjectsAnalyzer analyzer = new ProjectsAnalyzer();

//		printProjectIssueCommentDeveloperRawData(db, analyzer);
		printProjectCommitCommentDeveloperRawData(db, analyzer);

//		String path = "/home/jarthur/workspace-msr/Classifier/data/";
//		generateIssueCommentDataFiles(path,analyzer);

	}

	private static void generateIssueCommentDataFiles(String path, ProjectsAnalyzer analyzer)
			throws IOException {
		BufferedWriter bf;

		Collection<Project> projects = analyzer.getProjects().values();

		for (Project project : projects) {

			for (Issue issue : project.getIssues()) {

				Collection<IssueComment> issueComments = issue.getIssueComments();
				StringBuffer sb = new StringBuffer();

				for (IssueComment comment : issueComments) {
					sb.append(comment.getBody());
				}

				bf = new BufferedWriter(new FileWriter(new File(path+issue.getId()+".txt")));
				bf.append(sb.toString());
				bf.close();
			}
		}
	}

	private static void printProjectCommitCommentDeveloperRawData(DB db,
			ProjectsAnalyzer analyzer) {
		CommitCommentParser parser = new CommitCommentParser(db);
		List<LineExtractedInformation> listInformation = parser.parse();

		for (LineExtractedInformation info : listInformation) {
			analyzer.addProject(info.getProjectID());
			analyzer.addCommit(info.getAbstractionID(), info.getProjectID());
			analyzer.addCommitComment(info.getCommentID(), info.getProjectID(), info.getAbstractionID(), info.getDeveloper(), info.getBody());
		}
				
		System.out.println("project commit comment developer");
		Collection<Project> values = analyzer.getProjects().values();
		for (Project p : values) {
			for (Commit commit : p.getCommits()) {
				for (CommitComment comment : commit.getComments()) {
					System.out.println(p.getId() + " " + commit.getId() + " " + comment.getCommentID() + " " + comment.getDeveloper());
				}
			}
		}
	}

	private static void printProjectIssueCommentDeveloperRawData(DB db,
			ProjectsAnalyzer analyzer) {
		IssueCommentParser parser = new IssueCommentParser(db);
		List<LineExtractedInformation> listInformation = parser.parse();

		for (LineExtractedInformation info : listInformation) {
			analyzer.addProject(info.getProjectID());
			analyzer.addIssue(info.getAbstractionID(), info.getProjectID());
			analyzer.addIssueComment(info.getCommentID(), info.getProjectID(), info.getAbstractionID(), info.getDeveloper(), info.getBody());
		}

		System.out.println("project issue comment developer");
		Collection<Project> values = analyzer.getProjects().values();
		for (Project p : values) {
			for (Issue i : p.getIssues()) {
				for (IssueComment c : i.getIssueComments()) {
					System.out.println(p.getId() + " " + i.getId() + " " + c.getId() + " " + c.getDeveloper());
				}
			}
		}
	}

}
