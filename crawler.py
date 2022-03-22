import requests as req
import json

class Product:
    def __init__(self):
        self.name = ""
        self.category = ""
        self.price = 0
        self.discount = 0
        self.description = ""
        self.thumbnailImage = ""
        self.otherImages = ""
        
    def __repr__(self):
        return str({
            "name": self.name,
            "category": self.category,
            "price": self.price,
            "discount": self.discount,
            "thumbnailImage": self.thumbnailImage,
            "otherImages": self.otherImages,
        })
        
    def toDict(self):
        return {
            "name": self.name,
            "category": self.category,
            "price": self.price,
            "discount": self.discount,
            "thumbnailImage": self.thumbnailImage,
            "otherImages": self.otherImages,
        }

APIs = [
    {
        "category": "phone",
        "url": "https://tiki.vn/api/personalish/v1/blocks/listings?limit=100&category=1789&page=1&urlKey=dien-thoai-may-tinh-bang"
    },
    {
        "category": "laptop",
        "url": "https://tiki.vn/api/personalish/v1/blocks/listings?limit=100&category=1846&page=1&urlKey=laptop-may-vi-tinh-linh-kien"
    },
    {
        "category": "book",
        "url": "https://tiki.vn/api/personalish/v1/blocks/listings?limit=100&category=8322&page=1&urlKey=nha-sach-tiki"
    },
    {
        "category": "fashion",
        "url": "https://tiki.vn/api/personalish/v1/blocks/listings?limit=100&category=917&page=1&urlKey=ao-thun-nam"
    }
]

HEADERS = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36'}

records = []
for api in APIs:
    res = req.get(api["url"], headers=HEADERS)
    data = res.json()
    data = data["data"]
    
    for item in data:
        product = Product()
        product.name = item["name"]
        product.category = api["category"]
        product.price = item["price"]
        product.discount = item["discount_rate"]
        product.description = item["short_description"]
        product.thumbnailImage = item["thumbnail_url"]
        product.otherImages = [option["thumbnail"] for option in item["option_color"]]

        records.append(product.toDict())
        
        print(f"{len(records)}: {product.name}")

jsonObj = json.dumps(records, indent=4)

with open("./src/main/resources/static/data/tiki.json", "w") as file:
    file.write(jsonObj)
