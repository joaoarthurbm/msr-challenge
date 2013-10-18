package parser;

import java.util.LinkedList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class CommitCommentParser implements MongoDBParser {
	
	private DB db;

	public CommitCommentParser(DB db) {
		this.db = db;
	}


	@Override
	public List<LineExtractedInformation> parse() {
		
		List<LineExtractedInformation> linesExtracted = new LinkedList<LineExtractedInformation>();
		DBCollection collection = this.db.getCollection(COMMIT_COMMENTS_COLLECTION);
		
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {

			DBObject next = cursor.next();
			String body = (String) next.get("body");
			String developer = (String) next.get("user");
			String htmlUrl = (String) next.get("html_url");
			String[] split = htmlUrl.split("\\/");
			String projectID = split[3] + "-" + split[4];
			String commentID = Integer.toString((Integer) next.get("id"));
			String commitID =  (String) next.get("commit_id");
			linesExtracted.add(new IssueInformation(commentID, projectID, commitID, developer, body));
		
		}

		return linesExtracted;
	
	}
	
}