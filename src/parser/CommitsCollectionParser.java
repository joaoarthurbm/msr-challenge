package parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.BSONObject;

import util.ProjectsUtil;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class CommitsCollectionParser implements MongoDBParser {

	private DB db;
	private ProjectsUtil util;
	private HashMap<String, Set<String>> allProjects;

	public CommitsCollectionParser(DB db) {
		this.db = db;
		util = new ProjectsUtil();
		allProjects = util.getAllProjects();
	}

	@Override
	public List<LineExtractedInformation> parse() {
		
		HashMap<String,Integer> freq = new HashMap<String, Integer>();
		
		List<LineExtractedInformation> linesExtracted = new LinkedList<LineExtractedInformation>();

		DBCollection collection = this.db.getCollection(COMMITS_COLLECTION);
		DBCursor cursor = collection.find();
		DBObject next;

		while (cursor.hasNext()) {
			next = cursor.next();
			
			String htmlUrl = (String) next.get("url");
			String[] split = htmlUrl.split("\\/");
			
			String projectID = split[4] + "/" + split[5];
			String parentProject = null;
			
			if (!allProjects.containsKey(projectID)) {
				
				parentProject = util.getParentProject(projectID);
				
				if (parentProject.equals("")) {
					parentProject = util.searchForParent(projectID);
				}
			
			} else {
				parentProject = projectID;
			}
			
			
			
			
			BSONObject committer = (BSONObject) next.get("author");
			
			if (committer != null) {
				Object b =  committer.get("login");
				if (b != null) {
					String key = parentProject + " " + b;
					if (freq.containsKey(key)) {
						Integer i = freq.get(key);
						freq.put(key, i+1);
					} else {
						freq.put(key, 1);
					}
				}
			}

		}
		
		for (Entry<String,Integer> entry : freq.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		
		return linesExtracted;
	}

}
