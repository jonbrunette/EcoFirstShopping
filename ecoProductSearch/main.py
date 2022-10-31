from fastapi import FastAPI
from predict import SearchEcoProducts


searchEcoProducts = SearchEcoProducts()
app = FastAPI()

@app.get("/")
def home():
    return {"Hello": "World"}

@app.get("/searchEcoProducts/")
def read_item(keyword: str = ""):
    return searchEcoProducts.getProductsList(keyword).to_dict('records')