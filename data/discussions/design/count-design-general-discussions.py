freq = {}
for line in open('activity-category.data'):
	activity = line.strip().split(" ")[0]
	category = line.strip().split(" ")[1]

	if activity not in freq:
		freq[activity] = category
	else:
		current = freq[activity]
		if current == "general" and category == "design":
			freq[activity] = "design"


for k,v in freq.items():
	print k, v
	

