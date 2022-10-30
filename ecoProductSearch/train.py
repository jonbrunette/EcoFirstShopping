# -*- coding: utf-8 -*-

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib as mpl
import nltk.classify.util
import re
import string
import nltk
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.model_selection import train_test_split
from sklearn.metrics import confusion_matrix
from sklearn import linear_model
from sklearn import metrics
from sklearn.metrics import roc_curve, auc
from nltk.classify import NaiveBayesClassifier
from wordcloud import STOPWORDS

data = pd.read_csv("data.csv")
data.head()
data["value"].value_counts().plot.bar()

def preProcessing(sentence):
    sent = str(sentence)
    sent = sent.lower()
    sent = re.compile('[^a-z]+').sub(' ', sent).strip()
    return sent
data["clean_descrip"] = data["description"].apply(preProcessing)

data["clean_descrip"].head(20)

train_dataset = data[["clean_descrip", "value"]]
train = train_dataset.sample(frac=0.85)
test = train_dataset.drop(train.index)

stopwords = set(STOPWORDS)
vect = CountVectorizer(min_df=2 ,stop_words=stopwords , ngram_range=(1,2), max_features=1004)
tfidf_transformer = TfidfTransformer()

X_train_counts = vect.fit_transform(train["clean_descrip"])        
X_train_tfidf = tfidf_transformer.fit_transform(X_train_counts)

X_test_counts = vect.transform(test["clean_descrip"])
X_test_tfidf = tfidf_transformer.transform(X_test_counts)

import pickle

filename = 'tfid.pkl'
with open(filename, 'wb') as fout:
    pickle.dump((vect, tfidf_transformer), fout)

lg = linear_model.LogisticRegression(solver='lbfgs' , C=1000)
logistic = lg.fit(X_train_tfidf, train["value"])
pred_probability = lg.predict_proba(X_test_tfidf)[:,1]
print("LogRegression Acc: {}".format(lg.score(X_test_tfidf, test["value"])))


# manual test
a = ["Manna Stainless Steel Reusable Silicone Tip Straw Set provides an eco-friendly alternative to using plastic straws Soft touch silicone tip for comfort when drinking Straws are bent at an optimal angle for easy sipping Suitable for use with any type of beverage Rust-resistant, stainless steel material for long-lasting use",
     "Core Home reusable Produce Bags provides a colourful sustainable alternative to single-use plastic bags Made of food-safe durable polyester material Features a variety of versatile multi-use bags Semi-transparent design makes it easy to view the contents inside Colour coded by size for easy organization Drawstring and toggle closure keeps the bag securely closed Tare weight included on each tag so cashier can easily deduct BPA free Great solution for grocery shopping, organizing arts and crafts, laundry or traveling",
     "Set of 16 & High Quality - Pack of 12 Assorted colorful short regular silicone straws (about 6.7 inch length / outer diameter 0.27 inch / inner diameter 0.2 inch),together with 4 brushes,durable quality for reusable using.Best value for money.Eco-friendly Material - 100% reusable food-grade silicone material,BPA free,flexible,non-toxic,tasteless.It’s safe for little kids even babies chewing on and durable to tear,feel soft against the lips,keep liquid away from developing teeth,won't hurt teeth.Perfect for Drinks - Variety of colors can be used in many occasions, the size is suitable for many drinks,compatible with most 6oz-12oz tumblers and cups.Easy Clean - Come with cleaning brushes,very convenient to use dish soap or plenty water to wash straws after drinking,also dishwasher is safe but not use in oven or microwave.May you will use straws separately to go picnic,go travel,go parties,even in the home,4 pieces of brushes could satisfy you for cleaning requirement.Just remember to wash them carefully before the first use.Easy Carry - Silicone straws are soft or even can be folded,will fit your purse,bag,take it wherever you go.",
     "High Quality Silicone:Our reusable straws are made of high-class safe silicone, non-toxic, no oxidation, no metal aftertaste, durable, colorfast, eco-friendly and healthy.These drinking straws are smooth and clean, soft and flexible which won't hurt your lips.AND they are kid friendly.Easy to Clean: Extra long collapsible straws come with cleaning brushes,very convenient to use dish soap or plenty water to wash straws after drinking,also dishwasher is safe but not use in oven or microwave.May you will use straws separately to go picnic,go travel,go parties,even in the home.Unique Gift: An exceptional value and an excellent gift for any occasion. Reusable straws are safe for kids, good ideal for family or children's party celebrations and trendy for a family reunion.They are suitable for water, beverage, juice, milkshake, yogurt, beer, milk tea. Almost any liquid can be used.Dishwasher Safe: These big size straws for any kind of drinks and are suitable for any kinds of tumblers ramblers and easy to clean. Remember to wash the products carefully before the first use. May you will use straws separately to go picnic,go travel,go parties,even in the home,brushes could satisfy you for cleaning.Package Including: Pack of 6 assorted colorful and resistant big silicone straws (No Rubber Tast). Approx 10 Inch Length / 0.35 inch Inner Diameter / 0.45 inch Outside Diameter + 1 Brush. Color is random mixcolor, light up your drinking. Great gift choice for your families and friends.",
     "Healthy Home Cleaning Sets gather all of the essential JungleVine® scouring products into package deals priced below regular retail prices. Eco-friendly JungleVine® Fiber Products are made using handspun fiber from a vine that grows without any agricultural inputs. For those seeking to live a plastic-free, chemical-free, and zero-waste lifestyle, there is no better choice than JungleVine® for scrubbing and scouring. Environmentally, JungleVine® Fiber is Better than Hemp™. At the end of its usable life, JungleVine® Fiber can be composted, where soil organisms can break down the plant fibers into nutrients for use by new plants.",
     "Scary, but true: Microfiber cloths are non-recyclable microplastics, and throwing them in the washing machine to clean them may be harmful to our water supply.Luckily, in place of microfiber towels or the disposable cloths that you may use for cleaning surfaces and floors in your home, you can use these cheap and effective bamboo-fiber cloths. Not only are these eco-friendly products made from the fast-growing, self-generating bamboo plant, but the cloths are also long-lasting and able to safely wash after each use.",
     "compostable cases for phones and AirPods, and the brand’s site boasts that the production of its products produces 30% fewer carbon emissions and uses 34% less water than conventional cases.",
     "is a maker of home office tech accessories created using natural materials like wood, cork, and wool. For every handcrafted item the brand sells, one tree is planted. At the time of publication, Oakywood has planted more than 68,000 trees.",
     "brings the joy of camping everywhere, with portable campfires. These oversized candles are made with recycled wax and multiple paper wicks for clean burning—and the outer tin can be reused for storage.",
     "outdoor furniture is the perfect addition to a cozy backyard retreat. It’s also made with recycled ocean plastic, but looks just like natural wicker. The brand keeps a running tally of plastic waste rescued from oceans (430,000 pounds in 2022 alone, as of the time of publication) and offsets 100% of its carbon emissions.",
     "12 DRINKING STRAWS FIT ALL SIZE TUMBLERS - 3 straight straws and 3 bent straws of 10.5 for 30oz tumblers. 3 straight straws and 3 bent straws of 8.5 for 20oz tumblers. Perfect for you to enjoy your beverage, juice, lemonade, coffee, cocktail, ice tea, cappuccino, etc. 1:18/8 FOOD GRADE STAINLESS STEEL - BPA and lead free, Dishwasher Safe, No metal aftertaste, Colorfast, Plastic free, Reusable and Environmentally Friendly. As a safe and suitable alternative to plastic, they are safer for the environment. Not only the straw is environmental, the portable bag is the same. The material of the bag is natural cotton, It can be decomposed in the natural environment. Beside this, these straws are easy to carry by your handbag.2 LONG NYLON CLEANING BRUSHES - Very handy to rinse off stains. Featuring a stainless steel handle and nylon bristles, the brush is of the perfect size for these straws, also suitable for cleaning cups and bottles.BROAD USAGE - These metal straws look equally beautiful in a cocktail or a smoothie, which add fun to any party, trendy for family reunion, girls night out,cocktail party, picnics, boating and so on. Also suitable for family party, outdoor picnics, hiking, trips and office use. An exceptional value and an excellent gift for any occasion.SAVE MONEY - Each reusable straw has the potential to eliminate up to 500 plastic straws over its lifetime. When you purchase a 12 pack of reusable straws, you'll be saving over 6000 disposable straws. That's more money in your pocket and less waste in the landfill."]
a = pd.DataFrame(a)
a[0]
a_test_counts = vzer.transform(a[0])
a_test_tfidf = tfidf_tf.transform(a_test_counts)
lg.predict_proba(a_test_tfidf)

from joblib import dump, load
joblib_file = "logReg_model.pkl"

joblib_model = load(joblib_file)