f = open('sentences-from-terra-partI.txt')
terra = f.readlines()

g = open('sentences-classified-by-me.csv')
joao = g.readlines()


i = 0
for t in terra:
	sterra,cterra = t.strip().split(";")[0],t.strip().split(";")[1]
	
	for j in joao:
		if ";" in j:
			sjoao,cjoao = j.strip().split(";")[0],j.strip().split(";")[3]
			if sjoao == sterra and cterra == cjoao:
				i += 1
			elif sjoao == sterra:
				print sjoao


print i
