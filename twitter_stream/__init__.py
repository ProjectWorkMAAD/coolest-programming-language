#importing necessary methods from tweepy library

from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream

#Variables that contains the user credentials to access Twitter API

access_token = "3225932325-4j4an5ME8I28M2g38ZHa5v8725SViFtGsAYXPSD"
access_token_secret = "a7eMKLwoTfzB3tCP8lj9aCEIiq356vAyo9X2qnX9Ffv6c"
consumer_key = "dyssvu9i0sfikbYotvh6pCKNm"
consumer_secret = "XcccBIALmOQRjSxVTT5WThZqZeWtdRJKOOr1aLK6DPhUj9OIwN"


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
