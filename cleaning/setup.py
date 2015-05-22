from setuptools import setup, find_packages

setup(
    name='cleaner',
    version='1.0',
    description='this library cleans the raw .json twitter data and isert them into a POSTGRESql DB via psycopg',
    author='Alex0494',
    packages=find_packages(),

    ##adding files
    include_package_data=True,
    package_data={'':['data/*']},
)
