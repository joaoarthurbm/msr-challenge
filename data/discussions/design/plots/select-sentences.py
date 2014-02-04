import sys
from random import shuffle
f = open(sys.argv[1])
lines = f.readlines()
shuffle(lines)

for i in range(50):
	sentence = lines[i].strip().split(" ")[3:]

	if len(sentence) < 60:
		print ' '.join(sentence)

