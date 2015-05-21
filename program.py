import twitter_stream
import cleaner

if __name__ == '__main__':

	credentials = '/home/alessio/Desktop/ProjectWork/credentials'

	data_path = '/home/alessio/Desktop/twitter_data'	

	print('getting tweets...')			
	twitter_stream.listen(credentials, data_path)

	print('cleaning and uploading tweets on DataBase')
	european = cleaner.clean(data_path)
	cleaner.insert(european)



