import tweepy

#Variables that contains the user credentials to access Twitter API	
access_token = "3225932325-4j4an5ME8I28M2g38ZHa5v8725SViFtGsAYXPSD"
access_token_secret = "a7eMKLwoTfzB3tCP8lj9aCEIiq356vAyo9X2qnX9Ffv6c"
consumer_key = "dyssvu9i0sfikbYotvh6pCKNm"
consumer_secret = "XcccBIALmOQRjSxVTT5WThZqZeWtdRJKOOr1aLK6DPhUj9OIwN"

#OAuth process
auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

#creation of the interface using the authentication

twitter = tweepy.API(auth)

data = twitter.search(q="#java OR #python", lang ="it", 
	since = "2013-05-05", count = "200") 

for tweet in data:
	try:
		print (tweet)		
	except UnicodeEncodeError:
		pass	
##PROBLEMI:
##-PRENDE SOLO 200 TWEET
##-PRENDE SEMPRE GLI STESSI 200
##-POSSO FARE SOLO 15 REQUEST PER ACCOUNT
##-NON SO DA CHE DATA A CHE DATA MI PRENDE I TWEET(CREDO DAL PIU RECENTE E POI VA INDIETRO, MA SOLO DI 200 TWEET)

		