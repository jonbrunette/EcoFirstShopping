# -*- coding: utf-8 -*-

import pandas as pd
import re
import pickle
from joblib import dump, load
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.feature_extraction.text import CountVectorizer
from wordcloud import STOPWORDS

joblib_file = "logReg_model.pkl"
logReg_model = load(joblib_file)

KEY = "16DA74BA7426487F81379E2DD698412A"
import requests
import json

keyword = 'strew'

params = {
  'api_key': KEY,
  'type': 'search',
  'amazon_domain': 'amazon.com',
  'search_term': keyword
}

api_result = requests.get('https://api.rainforestapi.com/request', params)
dic = api_result.json()


df = pd.DataFrame(dic['search_results'])
df = df[['title', 'asin', 'link', 'image', 'price', 'is_prime', 'rating', 'ratings_total']]

with open('tfid.pkl', 'rb') as f:
    vect, tfidf_tf = pickle.load(f)

def preProcessing(sentence):
    sent = str(sentence)
    sent = sent.lower()
    sent = re.compile('[^a-z]+').sub(' ', sent).strip()
    return sent

df["clean"] = df["title"].apply(preProcessing)
df["clean"]

counts = vect.transform(df["clean"])
pred_tfidf = tfidf_tf.transform(counts)
prob = logReg_model.predict_proba(pred_tfidf)

df["prob"] = prob[:,1]

df_sorted = df.sort_values(by="prob", ascending=False)

df_sorted = df_sorted[df_sorted["prob"] > 0.5]
df_sorted = df_sorted[:10]

res_json = json.dumps(df_sorted.drop(columns = ["clean", "prob"]).to_dict('records'))