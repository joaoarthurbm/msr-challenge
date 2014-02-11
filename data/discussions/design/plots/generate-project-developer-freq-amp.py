from __future__ import division
from collections import defaultdict

proj_discussions = {str():set()}
dev_project = defaultdict(int)
dev_discussions = {str():set()}

for line in open("../data-to-plot/raw-developer-project-design_activity.data"):

	tokens = line.strip().split(" ")
	developer, project, activity = tokens[0], tokens[1], tokens[2]	

	if project not in proj_discussions:
		new = set()
		new.add(activity)
		proj_discussions[project] = new
	else:
		ac = proj_discussions[project]
		ac.add(activity)
		proj_discussions[project] = ac

	idp = developer+" "+project

	if idp not in dev_discussions:
                new = set()
                new.add(activity)
                dev_discussions[idp] = new
        else:
                ac = dev_discussions[idp]
                ac.add(activity)
                dev_discussions[idp] = ac

	dev_project[idp] += 1

print "Project Developer Comments Amplitude"

for k,v in dev_project.items():

	developer = k.split(" ")[0]
	project = k.split(" ")[1]

	print project, developer, dev_project[k], "%.1f" % ( len(dev_discussions[k]) / len(proj_discussions[project]) * 100 )
