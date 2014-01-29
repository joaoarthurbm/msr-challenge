design_discussions = {str():[]}
for line in open('developer-activity-category-project.data'):
	tokens = line.strip().split(" ")
	activity,category,project = tokens[1],tokens[2], tokens[3]
	
	id_activity = project+" "+activity

	if id_activity not in design_discussions:
		if category == "design":
			design_discussions[id_activity] = []

for line in open('developer-activity-category-project.data'):
        tokens = line.strip().split(" ")
        developer, activity, project = tokens[0], tokens[1], tokens[3]

	id_activity = project+" "+activity
	if id_activity in design_discussions:
		design_discussions[id_activity].append(developer)


for k,v in design_discussions.items():
	if len(set(v)) >= 2:
		print k, len(set(v)), len(v)
