import requests
import base64
import psycopg2
import json
import os
import warnings

##LOADING TWEETS

def clean(fname):

	data = []
	f = open(fname, "r")

	for line in f:
		try:
			tweets = json.loads(line)
			data.append(tweets)
		except:
			continue


	##CLEANING TWEETS

	europei = []

	#os.chdir(os.path.dirname(os.getcwd()))

	nazioni = open('data/nazioni.dat', 'r')
	citta = open('data/citta.dat', 'r')

	nation = []
	for naz in nazioni.readlines():
		nation.append(naz)

	cities = []
	for city in citta.readlines():
		cities.append(city)
	for tweet in data:	
		if 'user' in tweet:	
			ut = tweet['user']
		if(ut['location'] != None):
			print(ut['location'])
			appoggio = ut['location'].split()
			for p in appoggio:	
				for n in nation:	
					if(n.strip() == p):
						europei.append(tweet)
			for c in cities:					
				if(c.strip() == ut['location']):
					europei.append(tweet)
				else:
					continue					

	f.close()
	nazioni.close()
	citta.close()					
	return europei


def insert(tweets):
	conn = psycopg2.connect(host='localhost',
				port=5432,
				dbname='db_twitter',
				user='maad',
       	        password='password')
	curs = conn.cursor()	
	for tweet in tweets:
		
		if 'user' in tweet:				
			ut = tweet['user']
			if tweet['place'] != None:
				place_full_name = tweet['place']['full_name']
			else:
				place_full_name = None
			curs.execute(
      		'INSERT INTO tweet(created_at, text, lang, place, username,'
      			'user_location,time_zone, friends_count, followers_count) VALUES' 
      			'(%s, %s, %s, %s, %s, %s, %s, %s, %s)',
        		(tweet['created_at'], tweet['text'], tweet['lang'], place_full_name, 
				ut['name'], ut['location'], ut['time_zone'], int(ut['friends_count']), 
				int(ut['followers_count']))
				)		
				
	conn.commit()
	conn.close()


