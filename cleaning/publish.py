import botocore.session

session = botocore.session.Session(session_vars={'profile': (None,   None, 'tsid-test')})
s3 = session.create_client('s3', region_name='eu-west-1')
archive = open('/home/alessio/Desktop/ProjectWork/coolest-programming-language/Cleaning/dist/cleaner-0.1.tar.gz', 'rb')
s3.create_bucket(Bucket='tsid-maad-clean')
s3.put_object(Bucket='tsid-maad-clean', ACL='public-read', Key='app.tar.gz', Body=archive)


