from textblob import *
from textblob.classifiers import NaiveBayesClassifier

cl = NaiveBayesClassifier("sentences.csv", format="csv")

for line in open('comments.data','r'):
	blob = TextBlob(line)
	for sentence in blob.sentences:
		print sentence, cl.classify(sentence)

