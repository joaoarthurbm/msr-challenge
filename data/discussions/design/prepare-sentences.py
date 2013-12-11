from nltk.corpus import stopwords
from textblob import *

def removeStopWords(sentence):
	stopset = set(stopwords.words('english'))
	
	stopset.add('might')
	stopset.add('may')
	stopset.add('must')
	stopset.add('lgtm')
	stopset.add('could')
	stopset.add('can')
	stopset.add('good')
	stopset.add('great')
	stopset.add('nice')
	stopset.add('well')
	stopset.add('better')
	stopset.add('worse')
	stopset.add('worst')
	stopset.add("should")	


	stopset.add("it's")
	stopset.add("i'll")
	stopset.add("they're")
	stopset.add("you're")
	stopset.add("that's")
	stopset.add("shouldn't")

	# negatives
	stopset.add("doesn't")	
	
	return [word for word in sentence.lower().split(" ") if word not in stopset]


for line in open('sentences.csv','r'):
	sentence = line.split(",")[0]
	category = line.split(",")[1]
	
	blob = TextBlob(sentence)
	
	try:	
		
		for sentence in blob.sentences:
			print ' '.join(removeStopWords(sentence))
	
	except ValueError:
		continue
