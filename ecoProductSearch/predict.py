# -*- coding: utf-8 -*-

import json
import pandas as pd
import re
import requests
import pickle
from joblib import dump, load
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.feature_extraction.text import CountVectorizer
from wordcloud import STOPWORDS


class SearchEcoProducts:
    def __init__(self):
        self.KEY = "16DA74BA7426487F81379E2DD698412A"
        self.joblib_file = "logReg_model.pkl"
        self.logReg_model = load(self.joblib_file)
        with open('tfid.pkl', 'rb') as f:
            self.vect, self.tfidf_tf = pickle.load(f)

    def preProcessing(self, sentence):
        sent = str(sentence)
        sent = sent.lower()
        sent = re.compile('[^a-z]+').sub(' ', sent).strip()
        return sent

    def searchByKeywords(self, keyword: str):
        params = {
            'api_key': self.KEY,
            'type': 'search',
            'amazon_domain': 'amazon.com',
            'search_term': keyword
        }
        api_result = requests.get('https://api.rainforestapi.com/request', params)
        items = pd.DataFrame(api_result.json()['search_results'])
        items = items.fillna({'is_prime': False})

        return items[['title', 'asin', 'link', 'image', 'price', 'is_prime', 'rating', 'ratings_total']]

    def getProductsList(self, keyword: str):
        products = self.searchByKeywords(keyword)
        products["clean_des"] = products["title"].apply(self.preProcessing)

        counts = self.vect.transform(products["clean_des"])
        pred_tfidf = self.tfidf_tf.transform(counts)
        prob = self.logReg_model.predict_proba(pred_tfidf)
        products["prob"] = prob[:,1]
        products_sorted = products.sort_values(by="prob", ascending=False)

        ecoProducts = products_sorted[products_sorted["prob"] > 0.5]
        ecoProducts = ecoProducts[:10]
        ecoProducts = ecoProducts.drop(columns = ["clean_des", "prob"])

        return ecoProducts