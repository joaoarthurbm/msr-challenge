package parser;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.BasicBSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class CommentsCollectionParser implements MongoDBParser {

	private DB db;
	private String collectionName;
	private HashMap<String,Set<String>> allProjects;
	

	public CommentsCollectionParser(DB db, String collectionName) {
		this.db = db;
		this.collectionName = collectionName;
		allProjects = new HashMap<String, Set<String>>();
		loadAllProjects();
	}


	private void loadAllProjects() {
		allProjects.put("akka/akka",new HashSet<String>());
		allProjects.put("hadley/devtools",new HashSet<String>());
		allProjects.put("johnmyleswhite/ProjectTemplate",new HashSet<String>());
		allProjects.put("mavam/stat-cookbook",new HashSet<String>());
		allProjects.put("facebook/hiphop-php",new HashSet<String>());
		allProjects.put("yihui/knitr",new HashSet<String>());
		allProjects.put("rstudio/shiny",new HashSet<String>());
		allProjects.put("facebook/folly",new HashSet<String>());
		allProjects.put("mongodb/mongo",new HashSet<String>());
		allProjects.put("TTimo/doom3.gpl",new HashSet<String>());
		allProjects.put("ariya/phantomjs",new HashSet<String>());
		allProjects.put("TrinityCore/TrinityCore",new HashSet<String>());
		allProjects.put("mangos/MaNGOS",new HashSet<String>());
		allProjects.put("bitcoin/bitcoin",new HashSet<String>());
		allProjects.put("keithw/mosh",new HashSet<String>());
		allProjects.put("xbmc/xbmc",new HashSet<String>());
		allProjects.put("joyent/http-parser",new HashSet<String>());
		allProjects.put("kr/beanstalkd",new HashSet<String>());
		allProjects.put("antirez/redis",new HashSet<String>());
		allProjects.put("liuliu/ccv",new HashSet<String>());
		allProjects.put("memcached/memcached",new HashSet<String>());
		allProjects.put("openframeworks/openFrameworks",new HashSet<String>());
		allProjects.put("libgit2/libgit2",new HashSet<String>());
		allProjects.put("vmg/redcarpet",new HashSet<String>());
		allProjects.put("joyent/libuv",new HashSet<String>());
		allProjects.put("SignalR/SignalR",new HashSet<String>());
		allProjects.put("hbons/SparkleShare",new HashSet<String>());
		allProjects.put("moxiecode/plupload",new HashSet<String>());
		allProjects.put("mono/mono",new HashSet<String>());
		allProjects.put("NancyFx/Nancy",new HashSet<String>());
		allProjects.put("ServiceStack/ServiceStack",new HashSet<String>());
		allProjects.put("AutoMapper/AutoMapper",new HashSet<String>());
		allProjects.put("restsharp/RestSharp",new HashSet<String>());
		allProjects.put("ravendb/ravendb",new HashSet<String>());
		allProjects.put("SamSaffron/MiniProfiler",new HashSet<String>());
		allProjects.put("nathanmarz/storm",new HashSet<String>());
		allProjects.put("elasticsearch/elasticsearch",new HashSet<String>());
		allProjects.put("JakeWharton/ActionBarSherlock",new HashSet<String>());
		allProjects.put("facebook/facebook-android-sdk",new HashSet<String>());
		allProjects.put("clojure/clojure",new HashSet<String>());
		allProjects.put("Bukkit/CraftBukkit",new HashSet<String>());
		allProjects.put("netty/netty",new HashSet<String>());
		allProjects.put("github/android",new HashSet<String>());
		allProjects.put("joyent/node",new HashSet<String>());
		allProjects.put("jquery/jquery",new HashSet<String>());
		allProjects.put("h5bp/html5-boilerplate",new HashSet<String>());
		allProjects.put("bartaz/impress.js",new HashSet<String>());
		allProjects.put("mbostock/d3",new HashSet<String>());
		allProjects.put("harvesthq/chosen",new HashSet<String>());
		allProjects.put("FortAwesome/Font-Awesome",new HashSet<String>());
		allProjects.put("mrdoob/three.js",new HashSet<String>());
		allProjects.put("zurb/foundation",new HashSet<String>());
		allProjects.put("symfony/symfony",new HashSet<String>());
		allProjects.put("EllisLab/CodeIgniter",new HashSet<String>());
		allProjects.put("facebook/php-sdk",new HashSet<String>());
		allProjects.put("zendframework/zf2",new HashSet<String>());
		allProjects.put("cakephp/cakephp",new HashSet<String>());
		allProjects.put("ginatrapani/ThinkUp",new HashSet<String>());
		allProjects.put("sebastianbergmann/phpunit",new HashSet<String>());
		allProjects.put("codeguy/Slim",new HashSet<String>());
		allProjects.put("django/django",new HashSet<String>());
		allProjects.put("facebook/tornado",new HashSet<String>());
		allProjects.put("jkbr/httpie",new HashSet<String>());
		allProjects.put("mitsuhiko/flask",new HashSet<String>());
		allProjects.put("kennethreitz/requests",new HashSet<String>());
		allProjects.put("xphere-forks/symfony",new HashSet<String>());
		allProjects.put("reddit/reddit",new HashSet<String>());
		allProjects.put("boto/boto",new HashSet<String>());
		allProjects.put("django-debug-toolbar/django-debug-toolbar",new HashSet<String>());
		allProjects.put("midgetspy/Sick-Beard",new HashSet<String>());
		allProjects.put("divio/django-cms",new HashSet<String>());
		allProjects.put("rails/rails",new HashSet<String>());
		allProjects.put("mxcl/homebrew",new HashSet<String>());
		allProjects.put("mojombo/jekyll",new HashSet<String>());
		allProjects.put("gitlabhq/gitlabhq",new HashSet<String>());
		allProjects.put("diaspora/diaspora",new HashSet<String>());
		allProjects.put("plataformatec/devise",new HashSet<String>());
		allProjects.put("joshuaclayton/blueprint-css",new HashSet<String>());
		allProjects.put("imathis/octopress",new HashSet<String>());
		allProjects.put("vinc/vinc.cc",new HashSet<String>());
		allProjects.put("thoughtbot/paperclip",new HashSet<String>());
		allProjects.put("chriseppstein/compass",new HashSet<String>());
		allProjects.put("twitter/finagle",new HashSet<String>());
		allProjects.put("robey/kestrel",new HashSet<String>());
		allProjects.put("twitter/flockdb",new HashSet<String>());
		allProjects.put("twitter/gizzard",new HashSet<String>());
		allProjects.put("sbt/sbt",new HashSet<String>());
		allProjects.put("scala/scala",new HashSet<String>());
		allProjects.put("scalatra/scalatra",new HashSet<String>());
		allProjects.put("twitter/zipkin",new HashSet<String>());
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
				
				parentProject = getParentProject(projectID);
				
				if (parentProject.equals("")) {
					parentProject = searchForParent(projectID);
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

	private String getParentProject(String project) {
		
		 for (Entry<String,Set<String>> entry : this.allProjects.entrySet()) {
			 
			 for (String s : entry.getValue()) {
				 if (s.equals(project)) return entry.getKey();
			 }
			 
		 }
		return "";
	}


	private String searchForParent(String projectID) {
		
		DBCollection collection = this.db.getCollection("repos");
		BasicDBObject query = new BasicDBObject("full_name",projectID);
		DBObject next = collection.findOne(query);
			
		if ((Boolean) next.get("fork")) {
			Object object = ((BasicBSONObject) next.get("parent")).get("full_name");
			
			// updating parents and children
			if (this.allProjects.get(object.toString()) == null ) {
				return searchForParent(object.toString());
//				
//				HashSet<String> s = new HashSet<String>();
//				s.add(projectID);
//				this.allProjects.put(object.toString(), s);
			} else {
				this.allProjects.get(object.toString()).add(projectID);
			}
			
			return object.toString();
		}
		
		throw new IllegalArgumentException("Project not studied!");
	}

}



