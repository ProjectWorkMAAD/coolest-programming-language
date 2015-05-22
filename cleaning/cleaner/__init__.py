import requests
import base64
import psycopg2
import json

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

	nazioni = open('nazioni', 'r')
	citta = open('citta', 'r')

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
		try:
			if 'user' in tweet:				
				ut = tweet['user']
				if tweet['place'] != None:
					place_full_name = tweet['place']['full_name']
				else:
					place_full_name = None
				curs.execute(
      			'INSERT INTO tweet(coordinate, data_creazione,' 
      				'testo, lingua, geolocalizzazione, luogo_tweet, nome_utente,'
      				'paese_utente,time_zone, numero_amici, followers) VALUES' 
      				'(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)',
        			(tweet['coordinates'], tweet['created_at'], 
        			tweet['text'], tweet['lang'], tweet['geo'], place_full_name, 
					ut['name'], ut['location'], ut['time_zone'], int(ut['friends_count']), 
					ut['followers_count'])
					)	
		except:
			continue			
	conn.commit()
	conn.close()

