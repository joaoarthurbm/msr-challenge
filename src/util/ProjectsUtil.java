package util;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

import org.bson.BasicBSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class ProjectsUtil {

	private HashMap<String, Set<String>> allProjects;
	private DB db;

	public ProjectsUtil() {
		allProjects = new HashMap<String, Set<String>>();
		loadProjects();
		Mongo mongo;
		try {
			mongo = new Mongo("localhost");
			db = mongo.getDB("msr14");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void loadProjects() {
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


	public HashMap<String, Set<String>> getAllProjects() {
		return allProjects;
	}

	public String getParentProject(String project) {

		for (Entry<String,Set<String>> entry : this.allProjects.entrySet()) {

			for (String s : entry.getValue()) {
				if (s.equals(project)) return entry.getKey();
			}

		}
		return "";
	}

	public String searchForParent(String projectID) {

		DBCollection collection = this.db.getCollection("repos");
		BasicDBObject query = new BasicDBObject("full_name",projectID);
		DBObject next = collection.findOne(query);

		if ((Boolean) next.get("fork")) {
			Object object = ((BasicBSONObject) next.get("parent")).get("full_name");

			// updating parents and children
			if (this.allProjects.get(object.toString()) == null ) {
				return searchForParent(object.toString());
			} else {
				this.allProjects.get(object.toString()).add(projectID);
			}

			return object.toString();
		}

		throw new IllegalArgumentException("Project not studied!");
	}

}
