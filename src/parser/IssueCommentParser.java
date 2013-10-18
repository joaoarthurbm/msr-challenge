package parser;
import java.util.LinkedList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class IssueCommentParser implements MongoDBParser {

	private DB db;

	public IssueCommentParser(DB db) {
		this.db = db;
	}

	
	public List<LineExtractedInformation> parse() {

		List<LineExtractedInformation> linesExtracted = new LinkedList<LineExtractedInformation>();
		
		// issue comments collection
		DBCollection collection = this.db.getCollection(ISSUE_COMMENTS_COLLECTION);
		DBCursor cursor = collection.find();
		DBObject next;
		while (cursor.hasNext()) {
			next = cursor.next();

			// issue comment
			String body = (String) next.get("body");
			String developer = (String) ((DBObject)next.get("user")).get("login");
			String htmlUrl = (String) next.get("html_url");
			String[] split = htmlUrl.split("\\/");
			String projectID = split[3] + "-" + split[4];
			
			// IssueID
			split = ((String) next.get("issue_url")).split("\\/");
			String issueID = projectID+"-"+split[split.length-1];
			
			// commentID
			String number = Integer.toString((Integer) next.get("id"));
			String commentID = issueID+"-"+number;
			
			linesExtracted.add(new IssueInformation(commentID, projectID, issueID, developer, body));

		}
		
		return linesExtracted;
		
	}

}