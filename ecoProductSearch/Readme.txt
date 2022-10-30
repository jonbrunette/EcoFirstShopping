Before you start, you will need Python on your computer. Python downloads page: https://www.python.org/downloads/
This project need Python 3.x version.

After install Python, pip (package installer for Python) is recommended to install as well. 
Please take a look this documentation for how to install. https://pip.pypa.io/en/stable/installation/

Next step is to install Python packages. You can run below command from the command line
pip install fastapi
pip install uvicorn
pip install pandas
pip install joblib
pip install sklearn
pip install wordcloud
pip install requests

Run server (change the current working directory to the same directory with code):
uvicorn main:app --reload

If you get an error like this "NameError: name 'packageName' is not defined. It is because the package is missing. You can fix it by running "pip install packageName".