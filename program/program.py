import twitter_stream
import cleaner

if __name__ == '__main__':	

	#raw_data file path
	raw_data = 'data/twitter_data.json'

	while True:	

		##data retrival		
		twitter_stream.listen(raw_data)		
			
		##data cleaning
		european = cleaner.clean(raw_data)

		#insert
		cleaner.insert(european)

		##ereasing file content		
		f = open(raw_data, 'wr')
		f.close()