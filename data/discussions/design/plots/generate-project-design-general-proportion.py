freq = {}
for line in open('../data-to-plot/raw-project-activity-category.data'):
	tokens = line.strip().split(" ")
	project, activity, category = tokens[0], tokens[1], tokens[2] 

	if activity not in freq:
		freq[activity] = (project,category)
	else:
		current = freq[activity][1]
		if current == "general" and category == "design":
			freq[activity] = (project,"design")


map_proj_design_general = {}

for k in freq.values():

	project,category = k[0],k[1]

	if project not in map_proj_design_general:
		if category == "design":
			map_proj_design_general[project] = (1,0)
		else:
			map_proj_design_general[project] = (0,1) 
	else:
		if category == "design":
			map_proj_design_general[project] = (map_proj_design_general[project][0]+1,map_proj_design_general[project][1])
		else:
			map_proj_design_general[project] = (map_proj_design_general[project][0],map_proj_design_general[project][1]+1)


print "Project Design General Proportion"
for k,v in map_proj_design_general.items():
	print k, v[0], v[1], float(v[0]) / (v[0] + v[1])

