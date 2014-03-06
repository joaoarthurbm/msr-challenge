package parser;

import java.util.List;

public interface MongoDBParser {
	
	public static final String ISSUE_COMMENTS_COLLECTION = "issue_comments";
	public  static final String COMMIT_COMMENTS_COLLECTION = "commit_comments";
	public  static final String PULL_REQUEST_COMMENTS_COLLECTION = "pull_request_comments";
	public static final String COMMITS_COLLECTION = "commits";
	
	public List<LineExtractedInformation> parse();
	
}
