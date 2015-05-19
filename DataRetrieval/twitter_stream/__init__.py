from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream

##to run change the file path
credentials = open('/home/alessio/Desktop/ProjectWork/credentials', 'r')

def keyreader():
	for line in credentials.read().splitlines():
		yield line

keychain = keyreader()
access_token = keychain.next()
access_token_secret = keychain.next()
consumer_key = keychain.next()
consumer_secret = keychain.next()
credentials.close()

class Listener(StreamListener):
 
	def on_data(self, data):
		print (data)
		return True


	def on_error(self, status):	
		print (status)


auth = OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)
twitterStream = Stream(auth, Listener())
twitterStream.filter(track=["PYTHON" or "JAVA" or "PHP" or "JAVASCRIPT" or "RUBY" or "SHELL" or "BASH" or "COBOL" or "CLOJURE" or "C" or "C#" or "OBJECTIVE C" or "C++" or "ASSEMBLY" or "PERL" or "LUA" or "SCALA" or "GO" or "VISUALBASIC" or "SWIFT"])
