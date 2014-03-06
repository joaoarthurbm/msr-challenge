package parser;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import util.ProjectsUtil;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class CommentsCollectionParser implements MongoDBParser {

	private DB db;
	private String collectionName;
	private HashMap<String,Set<String>> allProjects;
	private ProjectsUtil util;
	

	public CommentsCollectionParser(DB db, String collectionName) {
		this.db = db;
		this.collectionName = collectionName;
		allProjects = util.getAllProjects();
	}


	public List<LineExtractedInformation> parse() {

		List<LineExtractedInformation> linesExtracted = new LinkedList<LineExtractedInformation>();

		DBCollection collection = this.db.getCollection(collectionName);
		DBCursor cursor = collection.find();
		DBObject next;

		while (cursor.hasNext()) {
			next = cursor.next();
			
			// comment
			String body = (String) next.get("body");
			
			// workaround due to multiple object types present on data
			String developer = "unknown";
			Object object = next.get("user");
			if (object != null) {
				if (object instanceof String)
					developer = object.toString();
				else
					developer = (String) ((DBObject)object).get("login");
			}

			String htmlUrl = (String) next.get("html_url");
			String[] split = htmlUrl.split("\\/");
			
			String projectID = split[3] + "/" + split[4];
			String parentProject = null;
			
			if (!allProjects.containsKey(projectID)) {
				
				parentProject = util.getParentProject(projectID);
				
				if (parentProject.equals("")) {
					parentProject = util.searchForParent(projectID);
				}
			
			} else {
				parentProject = projectID;
			}
			
			// abstraction id
			split = ((String) next.get("html_url")).split("\\/");
			String token = split[6];
			String issueNumber = token.split("#")[0];
			String abstractionID = projectID+"-"+ collectionName + "-" + issueNumber;

			// commentID
			String number = Integer.toString((Integer) next.get("id"));
			String commentID = abstractionID+"-"+number;

			String commitID = (String) next.get("commit_id");


			linesExtracted.add(new CommentInformation(commentID,projectID,parentProject,abstractionID,developer,body, commitID));
		}

		return linesExtracted;

	}

}



