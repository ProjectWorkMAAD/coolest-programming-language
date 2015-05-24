import time
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream

def listen(output_path):	
    
    credentials = open('data/credentials', 'r')

    def keyreader():
        for line in credentials.read().splitlines():
            yield line

    keychain = keyreader()
    access_token = next(keychain)
    access_token_secret = next(keychain)
    consumer_key = next(keychain)
    consumer_secret = next(keychain)
    credentials.close()

    #override of the methods 
    class Listener(StreamListener):

        def __init__(self, start_time, time_limit, path):
            self.time = start_time
            self.limit = time_limit
            self.file_path = path

        def on_data(self, data):
            saveFile = open(self.file_path, 'a')            
            while (time.time() - self.time) < self.limit:                  
                saveFile.write(data)                                
                return True 
            saveFile.close()                
            return False

        def on_error(self, status_code):	
            error_log = open('data/error_log', 'a')
            error_log.write(status_code)
            if status_code == 420:                
                return False
            else   
                continue 

    auth = OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    twitterStream = Stream(auth, Listener(time.time(), 120, output_path))
    twitterStream.filter(track=["PYTHON" or "JAVA" or "PHP" or "JAVASCRIPT" or "RUBY" or 
    "SHELL" or "BASH" or "COBOL" or "CLOJURE" or "C" or "C#" or "OBJECTIVE C" or "C++" or 
    "ASSEMBLY" or "PERL" or "LUA" or "SCALA" or "GO" or "VISUALBASIC" or "SWIFT"])