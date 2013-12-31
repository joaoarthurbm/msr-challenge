freq = {}
for line in open('project-activity-category.data'):
	tokens = line.strip().split(" ")
	project, activity, category = tokens[0], tokens[1], tokens[2] 

	if activity not in freq:
		freq[activity] = (project,category)
	else:
		current = freq[activity][1]
		if current == "general" and category == "design":
			freq[activity] = (project,"design")


for k,v in freq.items():
	print v[0], k, v[1]
	

