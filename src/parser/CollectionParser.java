package parser;
import java.util.LinkedList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class CollectionParser implements MongoDBParser {

	private DB db;
	private String collectionName;

	public CollectionParser(DB db, String collectionName) {
		this.db = db;
		this.collectionName = collectionName;
	}


	public List<LineExtractedInformation> parse() {

		List<LineExtractedInformation> linesExtracted = new LinkedList<LineExtractedInformation>();

		DBCollection collection = this.db.getCollection(collectionName);
		DBCursor cursor = collection.find();
		DBObject next;

		while (cursor.hasNext()) {
			next = cursor.next();

			// issue comment
			String body = (String) next.get("body");

			String developer;
			// workaround due to multiple object types present on data
			Object object = next.get("user");
			if (object instanceof String) 
				developer = object.toString();
			else
				developer = (String) ((DBObject)object).get("login");


			String htmlUrl = (String) next.get("html_url");
			String[] split = htmlUrl.split("\\/");
			String projectID = split[3] + "-" + split[4];

			// IssueID
			split = ((String) next.get("html_url")).split("\\/");
			String token = split[6];
			String issueNumber = token.split("#")[0];
			String issueID = projectID+"-"+issueNumber;

			// commentID
			String number = Integer.toString((Integer) next.get("id"));
			String commentID = issueID+"-"+number;
			 	
			linesExtracted.add(new CommentInformation(commentID,projectID,issueID,developer,body));
		}

		return linesExtracted;

	}

}