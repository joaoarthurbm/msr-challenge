package parser;

import java.util.List;

public interface MongoDBParser {
	
	public static final String ISSUE_COMMENTS_COLLECTION = "issue_comments";
	public  static final String COMMIT_COMMENTS_COLLECTION = "commit_comments";

	public List<LineExtractedInformation> parse();
	
}
