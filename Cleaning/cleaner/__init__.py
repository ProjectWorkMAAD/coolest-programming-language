import requests
import base64
import psycopg2
import json

def clean():

	data = []
	f = open('/home/ubuntu/data/twitter_data', "r")

	for l in f:
		try:
			tweets = json.loads(l)
			data.append(tweets)
		except:
			continue

	#inizio la pulizia dei dati. Passo uno nazione utente

	europei = []

	nazioni = open('/home/ubuntu/scripts/nazioni', 'r')
	citta = open('/home/ubuntu/scripts/citta', 'r')

	nation = []
	for naz in nazioni.readlines():
		nation.append(naz)
	cities = []
	for city in citta.readlines():
		cities.append(city)
	for tweet in data:
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

	print('saving tweets')
	try:
		insert()
	except:
		print('Error writing on database')
		break					
	f.close()
	nazioni.close()
	citta.close()


def clear():
		f = open('/home/ubuntu/data/twitter_data', "w")
		f.close()

def insert():

	conn = psycopg2.connect(host='localhost',
                           	port=5432,
                            dbname='db_twitter',
                            user='maad',
                            password='password')
	curs = conn.cursor()
	for tweet in europei:
		ut = tweet['user']
		curs.execute(
               	'INSERT INTO tweet (coordinate, data_creazione, testo, lingua, geolocalizzazione, luogo_tweet, nome_utente,paese_utente, time_zone, numero_amici, following) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)',
               		(tweet['coordinates'], tweet['created_at'], tweet['text'], tweet['lang'], tweet['geo'], tweet['place'], 				
               			ut['name'], ut['location'], ut['time_zone'], ut['friends_count'], ut['following'])
               		)
	conn.commit()
	conn.close()