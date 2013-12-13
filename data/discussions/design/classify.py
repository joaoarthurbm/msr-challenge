import sys
from random import shuffle
from textblob import *
from textblob.classifiers import DecisionTreeClassifier
from nltk.corpus import stopwords
from textblob import *
from nltk import *
import itertools
from nltk.collocations import BigramCollocationFinder
from nltk.metrics import BigramAssocMeasures
import collections
import nltk.classify.util, nltk.metrics
from nltk.classify import NaiveBayesClassifier

def remove_stop_words(sentence):
	stopset = set(stopwords.words('english'))
	
	
	stopset.add('might')
	stopset.add('may')
	stopset.add('would')
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
	stopset.add("shouldnt")
	stopset.add("shouldn't")
	stopset.add("didn't")
	stopset.add("didnt")
	stopset.add("dont")
	stopset.add("don't")

	# negatives
	stopset.add("doesn't")	
	
	return [word for word in sentence.lower().split(" ") if word not in stopset]

def document_features(document):
	return dict([(w, True) for w in document])


def bigram_word_features(words, score_fn=BigramAssocMeasures.chi_sq, n=200):
    bigram_finder = BigramCollocationFinder.from_words(words)
    bigrams = bigram_finder.nbest(score_fn, n)
    return dict([(ngram, True) for ngram in itertools.chain(words, bigrams)])


def main():

	dataset = []
	
	lines = [ ( remove_stop_words(line.strip().split(';')[0]) , line.strip().split(';')[-1]) for line in open("sentences.csv", 'r')]
	shuffle(lines)
	


	for line in lines:
		pair = (bigram_word_features(line[0]), line[1])
		dataset.append(pair)

	train = dataset[len(dataset)/2:]
        test = dataset[:len(dataset)/2]

	classifier = NaiveBayesClassifier.train(train)
        
	print nltk.classify.accuracy(classifier, test)

	#for line in open(sys.argv[1]):
	#	print line, ';', classifier.classify(bigram_word_features(remove_stop_words(line)))

if  __name__ =='__main__':
    main()
