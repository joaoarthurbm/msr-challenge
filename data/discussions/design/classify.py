from textblob import *
from textblob.classifiers import DecisionTreeClassifier

cl = DecisionTreeClassifier("sentences.csv", format="csv")

for line in open('comments.data','r'):
	blob = TextBlob(line)
	
	try:
		for sentence in blob.noun_phrases:
			blob.classify
	except ValueError:
		print "Line not parsed: ",line
