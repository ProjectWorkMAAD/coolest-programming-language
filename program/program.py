import twitter_stream
import cleaner

if __name__ == '__main__':	

	while True:

		#raw_data file path
		raw_data = 'data/twitter_data.json'

		print('getting tweets...')			
		twitter_stream.listen(raw_data)

		print('cleaning tweets...')
		european = cleaner.clean(raw_data)
		print('uploading tweets on the Database...')
		cleaner.insert(european)

		print('ereasing file content...')
		f = open(raw_data, 'wr')
		f.close()
		print('repeating')


