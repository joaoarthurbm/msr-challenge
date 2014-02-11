proj_developer_amplitude = {str:[int,float]}

for line in open("../data-to-plot/to-plot-project-developer-intensity-amplitude.data"):
        project,developer,amplitude = line.strip().split(" ")[0],line.strip().split(" ")[1],line.strip().split(" ")[3]

	idp = project+" "+developer
	if idp not in proj_developer_amplitude:
		proj_developer_amplitude[idp] = [amplitude,None]

proj_commits = {}
for line in open("../data-to-plot/java-project-developer-commits.data"):
	project = line.strip().split(" ")[0]
	commits = line.strip().split(" ")[2]	

	if project not in proj_commits:
		proj_commits[project] = int(commits)
	else:
		proj_commits[project]+= int(commits)



for line in open("../data-to-plot/java-project-developer-commits.data"):
        project,developer,commits = line.strip().split(" ")[0],line.strip().split(" ")[1],line.strip().split(" ")[2]
        
	idp = project+" "+developer
	if idp in proj_developer_amplitude:
		proj_developer_amplitude[idp][1] = float(commits)/proj_commits[project]



print "Project Developer Commits Amplitude"
for k,v in proj_developer_amplitude.items():
	if v[1] != None and type(v[1]) == float:
		print k, v[0], v[1]*100
	
	
