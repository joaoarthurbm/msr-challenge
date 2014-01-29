# -*- coding: iso-8859-15 -*-
import logging
import sys
from random import shuffle
from textblob import *
from nltk.corpus import stopwords
from textblob import *
from nltk.classify import *
import itertools
from nltk.collocations import BigramCollocationFinder
from nltk.metrics import BigramAssocMeasures
import collections
import nltk.classify.util, nltk.metrics
from nltk.classify import NaiveBayesClassifier
from nltk.classify import DecisionTreeClassifier

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
	stopset.add("i'll")
	stopset.add("ill")
	stopset.add("it's")
	stopset.add("its")
	stopset.add("im")
	stopset.add("they're")
	stopset.add("theyre")
	stopset.add("you're")
	stopset.add("youre")
	stopset.add("that's")
	stopset.add("thats")
	stopset.add("theres")
	stopset.add("shouldnt")
	stopset.add("shouldn't")
	stopset.add("didn't")
	stopset.add("didnt")
	stopset.add("dont")
	stopset.add("don't")
	stopset.add("doesn't")	
	stopset.add("doesnt")
	stopset.add("wasnt")
	stopset.add("wasn't")
	#date
	stopset.add("mon")	
	stopset.add("monday")	
	stopset.add("tue")	
	stopset.add("tuesday")	
	stopset.add("wed")	
	stopset.add("wednesday")	
	stopset.add("thursday")	
	stopset.add("thu")	
	stopset.add("friday")	
	stopset.add("fri")	
	stopset.add("sat")	
	stopset.add("saturday")	
	stopset.add("sun")	
	stopset.add("sunday")	
	stopset.add("jan")	
	stopset.add("january")	
	stopset.add("feb")	
	stopset.add("february")	
	stopset.add("mar")	
	stopset.add("march")	
	stopset.add("apr")	
	stopset.add("april")	
	stopset.add("may")	
	stopset.add("jun")	
	stopset.add("june")	
	stopset.add("july")	
	stopset.add("jul")	
	stopset.add("aug")	
	stopset.add("august")	
	stopset.add("sep")	
	stopset.add("september")	
	stopset.add("nov")	
	stopset.add("november")	
	stopset.add("oct")	
	stopset.add("october")	
	stopset.add("dec")	
	stopset.add("december")	
	stopset.add("pm")
	stopset.add("am")
	stopset.add("sense")

	
	return [word for word in sentence.lower().split(" ") if word not in stopset]

def document_features(document):
	return dict([(w, True) for w in document])


def bigram_word_features(words, score_fn=BigramAssocMeasures.chi_sq, n=20):
    bigram_finder = BigramCollocationFinder.from_words(words)
    bigrams = bigram_finder.nbest(score_fn, n)
    #return dict([(ngram, True) for ngram in itertools.chain(words, bigrams)])
    return dict([(ngram, True) for ngram in bigrams])


def classifyAllData(classifier,logging):

	for file_name in open(sys.argv[1]):
        	short_file_name = file_name.strip().split("/")[-1]

                logging.info('Classifying ' + short_file_name)
                f = open('/media/old/jarthur/workspace-msr/msr-challenge/data/discussions/categorized/'+short_file_name,'w+')
                for line in open(file_name.strip()):

                        tokens = line.strip().split(" ")
                        developer = tokens[0]
                        root_project = tokens[1]
                        activity = tokens[2]
                        sentence = ' '.join(tokens[3:])

                        logging.info('classifying: '+ ' '.join(remove_stop_words(sentence)))
                        try:
                                toWrite = developer,root_project,activity,sentence,classifier.classify(bigram_word_features(remove_stop_words(sentence))),"\n"
                                toWrite = ' '.join(toWrite)
                                logging.info('line classified: '+toWrite)
                                f.write(toWrite)
                        except ZeroDivisionError:
                                logging.error('not classified ' + ' '.join(remove_stop_words(sentence)))
                logging.info('File classified ' + short_file_name)
                f.close()

	
def chunks(l, n):
    return [ l[i:i+n] for i in range(0, len(l), n)]


def main():
	logging.basicConfig(filename='log-classifier.log',level=logging.INFO)
	
	lines = [ ( remove_stop_words(line.strip().split(',')[0]) , line.strip().split(',')[-1]) for line in open("sentences-classified-by-me.csv.bkp", 'r')]
	shuffle(lines)
	
	
	dataset = []
	for line in lines:
                pair = (document_features(line[0]), line[1])
                dataset.append(pair)
	evaluate(dataset,"words")
	
	dataset = []
	for line in lines:
                pair = (bigram_word_features(line[0]), line[1])
                dataset.append(pair)
        evaluate(dataset,"bigrams")

	

def evaluate(dataset,feature):

	kfold = chunks(dataset,100)
	
	mean = 0.0	

	for i in range(10):
		train = []
		if i == 0:
			test = kfold[0]
			for l in kfold[1:]:
				for t in l:
					train.append(t)

		else:
			test = kfold[i]
			for l in kfold[i+1:] + kfold[:i]:
				for t in l:
					train.append(t)


		classifier = nltk.NaiveBayesClassifier.train(train)
		ac = nltk.classify.accuracy(classifier, test)	
	
		print i, feature, "NaiveBayes", ac

                #classifier = nltk.DecisionTreeClassifier.train(train)
                #logging.info("End of training tree " + str(i) + " " + str(nltk.classify.accuracy(classifier,test)))


if __name__ =='__main__':
    main()
