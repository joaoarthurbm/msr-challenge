package parser;

import java.util.LinkedList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class CollaboratorsParser implements MongoDBParser {

	private DB db;

	public CollaboratorsParser(DB db) {
		this.db = db;
	}


	public List<LineExtractedInformation> parse() {

		List<LineExtractedInformation> linesExtracted = new LinkedList<LineExtractedInformation>();

		DBCollection collection = this.db.getCollection("repos");
		DBCursor cursor = collection.find();
		
		DBObject next;
//
		while (cursor.hasNext()) {
			next = cursor.next();
			System.out.println(next);
		}
		
		return linesExtracted;
		
	}

}
