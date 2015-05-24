import twitter_stream
import cleaner

if __name__ == '__main__':

	credentials = '/home/alessio/Desktop/ProjectWork/credentials'

	data_path = '/home/alessio/Desktop/twitter_data.json'	

	print('getting tweets...')			
	twitter_stream.listen(credentials, data_path)

	print('cleaning tweets...')
	european = cleaner.clean(data_path)
	print('uploading tweets on the Database...')
	cleaner.insert(european)

	print('ereasing file content...')
	f = open(data_path, 'wr')
	f.close()
	print('DONE')


