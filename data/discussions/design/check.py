f = open('terra.csv')
terra = f.readlines()

g = open('sentences-classified-by-me.csv')
joao = g.readlines()


i = 0
for t in terra:
	sterra,cterra = t.strip().split(";")[0],t.strip().split(";")[1]
	
	for j in joao:
		sjoao,cjoao = j.strip().split(",")[0],j.strip().split(",")[1]
		if sjoao == sterra and cterra == cjoao:
			print sjoao + "," + cjoao
