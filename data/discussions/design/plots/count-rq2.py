data = {str():(set(),set())}


cont = 0
for line in open("developer-project-category.data"):

	tokens = line.strip().split(" ")

	developer,project,category = tokens[0], tokens[1], tokens[2]

	if project not in data:
		if category == "design":
			new = set()
			new.add(developer)
			data[project] = (new,new)
		else:
			new = set()
                        new.add(developer)
                        data[project] = (set(),new)
	else:
		data[project][1].add(developer)
		if category == "design":
			data[project][0].add(developer)

for k,v in data.items():
	if len(v[0]) > 0:
		print float(len(v[0])) / len(v[1])
		#for i in range(len(v[0])):
		#	print k, "architect"
		#for i in range(len(v[1])):
		#	print k, "developer"
