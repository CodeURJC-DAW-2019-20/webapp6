# Anime Sop - API REST Documentation

## About our API
All you can find in our API Rest is information about users, products, product amount, blogs and orders. All you need to do is simply; you have to follow the API rules. If you try to do following a different way, it's probably what you will recieve an error.

## How to use our API
1. Download [Postman](https://www.getpostman.com/).
2. You only can send GET, POST, PUT and DELETE requests.

## API requests

### Resources
The resource API has GET, POST, PUT and DELETE methods.
The base URL is: https: // localhost: 8443.

### Index
- [Authentication](#authentication)

**All API queries have been preceded by /api**

## Authentication

#### Login
Allows a user to log in.

* ##### URL:

	< /logIn >

* ##### Method:

	`GET`
	
* ##### Success Response:
  ```
  {
    "id": 1,
    "name": "user",
    "passwordHash": "$2a$10$/ceGzo0z3/gxarMV3ehTyehDHNDyQQT/4OyGOmWLhXQIO7x/.vn..",
    "delivery": {
        "shippingname": "David",
        "lastname": "Amor",
        "company": "URJC",
        "number": "654451912",
        "email": "David.a.almendra@hotmail.com",
        "street": "Calle de Uruguay, 6",
        "floor": "7D",
        "city": "Fuenlabrada",
        "country": "Españita",
        "zipcode": "28944",
        "name": "David"
    },
    "orderList": [
        {
            "id": 23,
            "status": null,
            "productList": [],
            "total": 0.0,
            "day": 8,
            "month": 2,
            "year": 2020
        }
    ],
    "roles": [
        "ROLE_USER"
    ]
  }
  ```
* ##### Error response:

	**Code**: 401 UNAUTHORIZED
	
#### Loguot  
Allows a user to log out.

* ##### URL:

	< /logOut >

* ##### Method:

	`GET`

* ##### Success Response:

	```
	true
	```

* ##### Error Response:

	**Code**: 401 UNAUTHORIZED
  
## Products

The following queries will be preceded by /products.

#### Show all products

Resource to show all pagined products with its information.

* ##### URL:

	< /products >

* ##### Method:

	`GET`
  
* ##### URL Params:
	* `page=[Integer] or Empty`
	
* ##### Example of query:

	* URL
		
		`/api/products?page=0`

* ##### Success Response:

	```
  {
    "content": [
        {
            "id": 3,
            "name": "Funko Pop! Levi Ackerman",
            "franchise": "Attack on Titans",
            "price": 14.49,
            "description": "Figura vinilo Funko POP de 9cm en caja regalo.",
            "width": 5.0,
            "height": 9.0,
            "weight": 200.0,
            "distributor": "Funko Pop!",
            "reference": "TM0ZZH441XA001",
            "stock": 100,
            "actual_Stock": 100,
            "image": "/img/product/levimin.png",
            "imagefull": "/img/product/levi2.jpg"
        },
        {
            "id": 4,
            "name": "Attack on Titans - Mikasa Ackerman",
            "franchise": "Attack on Titans",
            "price": 40.5,
            "description": "De la serie de anime Attack on Titan viene una figura de Mikasa con articulaciones regulables.",
            "width": 50.0,
            "height": 30.0,
            "weight": 1200.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XC002",
            "stock": 160,
            "actual_Stock": 160,
            "image": "/img/product/mikasa.png",
            "imagefull": "/img/product/mikasa2.jpg"
        },
        {
            "id": 5,
            "name": "Eren Jaeguer",
            "franchise": "Attack on Titans",
            "price": 50.25,
            "description": "Figura articulada del anime 'Attack on Titan', tamaño aprox. 25 cm. Viene en una caja con ventana.",
            "width": 31.0,
            "height": 25.0,
            "weight": 100.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XC003",
            "stock": 40,
            "actual_Stock": 40,
            "image": "/img/product/ErenJaeguer.png",
            "imagefull": "/img/product/ErenJaeguer2.jpg"
        },
        {
            "id": 6,
            "name": "Funko Pop! -Titan Armadura",
            "franchise": "Attack on Titans",
            "price": 20.0,
            "description": "A diferencia de los Pop! normales este mide 15cm; los denominados Super Sized.",
            "width": 40.0,
            "height": 19.0,
            "weight": 200.0,
            "distributor": "Funko Pop!",
            "reference": "TM0ZZH441XC004",
            "stock": 58,
            "actual_Stock": 58,
            "image": "/img/product/titanArmadura.png",
            "imagefull": "/img/product/titanArmadura2.jpg"
        },
        {
            "id": 7,
            "name": "Funko Pop! -Titan Colosal",
            "franchise": "Attack on Titans",
            "price": 17.99,
            "description": "Del divertido fabricante Funko, nos llegan las irresistibles Pop! figuras de colección de Videojuegos, Cine, Televisión...\\r\\n\" + \r\n\t\t\t\t\"\\r\\n\" + \r\n\t\t\t\t\"Son fantásticas para coleccionar, y es que cuando haces POP! ya no hay stop!. Quedáis avisados…",
            "width": 40.0,
            "height": 19.0,
            "weight": 200.0,
            "distributor": "Funko Pop!",
            "reference": "TM0ZZH441XC005",
            "stock": 80,
            "actual_Stock": 80,
            "image": "/img/product/titanColosal.png",
            "imagefull": "/img/product/titanColosal2.jpg"
        },
        {
            "id": 8,
            "name": "Charlotte Katakuri(Figuarts ZERO)",
            "franchise": "One Piece",
            "price": 69.99,
            "description": "Esta fantástica figura está fabricada en PVC y mide aproximadamente 22 centímetros de alto. Charlotte aparece sosteniendo su arma, un tridente que recibe el nombre de Mogura",
            "width": 15.0,
            "height": 20.0,
            "weight": 400.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XB006",
            "stock": 90,
            "actual_Stock": 90,
            "image": "/img/product/katakuri.png",
            "imagefull": "/img/product/katakurifull.jpg"
        },
        {
            "id": 9,
            "name": "Funko Pop! - Chopper",
            "franchise": "One Piece",
            "price": 56.75,
            "description": "Figura de la línea POP! de Chopper. Basada en el universo de One Piece creado por Eiichiro Oda, tiene una altura aproximada de 10cm.",
            "width": 17.0,
            "height": 30.0,
            "weight": 650.0,
            "distributor": "Funko Pop!",
            "reference": "TM0ZZH441XB007",
            "stock": 75,
            "actual_Stock": 75,
            "image": "/img/product/chopper.png",
            "imagefull": "/img/product/chopper2.jpg"
        },
        {
            "id": 10,
            "name": "Jinbei (Figuarts ZERO)",
            "franchise": "One Piece",
            "price": 80.2,
            "description": "Figura de Jinbei \"El Caballero del Mar\", el hombre-pez de One Piece, fabricada por Bandai-Tamashii Nations para su colección Figuarts ZERO",
            "width": 25.0,
            "height": 40.0,
            "weight": 500.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XB008",
            "stock": 60,
            "actual_Stock": 60,
            "image": "/img/product/Jinbei.png",
            "imagefull": "/img/product/Jinbei2.jpg"
        },
        {
            "id": 11,
            "name": "Luffy (Figuarts ZERO)",
            "franchise": "One Piece",
            "price": 35.5,
            "description": "Figura de Monkey D. Luffy, el protagonista de One Piece, perteneciente a la colección Figuarts ZERO de Bandai - Tamashii Nations.",
            "width": 30.0,
            "height": 25.0,
            "weight": 750.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XB009",
            "stock": 87,
            "actual_Stock": 87,
            "image": "/img/product/Luffy.png",
            "imagefull": "/img/product/Luffy2.jpg"
        },
        {
            "id": 12,
            "name": "Zoro (Figuarts ZERO)",
            "franchise": "One Piece",
            "price": 70.0,
            "description": "Figura de Zoro, personaje de One Piece, perteneciente a la colección Figuarts ZERO de Bandai - Tamashii Nations, ejecutando su espectacular técnica de ataque.",
            "width": 30.0,
            "height": 30.0,
            "weight": 835.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XB010",
            "stock": 120,
            "actual_Stock": 120,
            "image": "/img/product/Zoro.png",
            "imagefull": "/img/product/Zoro2.jpg"
        }
    ]
  }
  ```
  
#### Show all products ordered by price

Resource to show all pagined products ordered by price with its information.

* ##### URL:

	< /products >

* ##### Method:

	`GET`
  
* ##### URL Params:
	* `page=[Integer] or Empty`
    `sort=[String]`
	
* ##### Example of query:

	* URL
		
		`/api/products?page=1&sort=asc`

* ##### Success Response:

	```
  {
    "content": [
        {
            "id": 12,
            "name": "Zoro (Figuarts ZERO)",
            "franchise": "One Piece",
            "price": 70.0,
            "description": "Figura de Zoro, personaje de One Piece, perteneciente a la colección Figuarts ZERO de Bandai - Tamashii Nations, ejecutando su espectacular técnica de ataque.",
            "width": 30.0,
            "height": 30.0,
            "weight": 835.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XB010",
            "stock": 120,
            "actual_Stock": 120,
            "image": "/img/product/Zoro.png",
            "imagefull": "/img/product/Zoro2.jpg"
        },
        {
            "id": 16,
            "name": "Naruto sentado (Figuarts ZERO)",
            "franchise": "Naruto",
            "price": 75.5,
            "description": "WOW",
            "width": 25.0,
            "height": 20.0,
            "weight": 500.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XC014",
            "stock": 90,
            "actual_Stock": 90,
            "image": "/img/product/narutoSentado.png",
            "imagefull": "/img/product/narutoSentado2.jpg"
        },
        {
            "id": 10,
            "name": "Jinbei (Figuarts ZERO)",
            "franchise": "One Piece",
            "price": 80.2,
            "description": "Figura de Jinbei \"El Caballero del Mar\", el hombre-pez de One Piece, fabricada por Bandai-Tamashii Nations para su colección Figuarts ZERO",
            "width": 25.0,
            "height": 40.0,
            "weight": 500.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XB008",
            "stock": 60,
            "actual_Stock": 60,
            "image": "/img/product/Jinbei.png",
            "imagefull": "/img/product/Jinbei2.jpg"
        },
        {
            "id": 14,
            "name": "Namikaze (Figuarts ZERO Ex)",
            "franchise": "Naruto",
            "price": 141.25,
            "description": "WOW",
            "width": 16.0,
            "height": 45.0,
            "weight": 1200.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XC012",
            "stock": 80,
            "actual_Stock": 80,
            "image": "/img/product/namikaze.png",
            "imagefull": "/img/product/namikaze2.jpg"
        },
        {
            "id": 17,
            "name": "Pack Naruto y Sasuke Relation (Figuarts ZERO Ex)",
            "franchise": "Naruto",
            "price": 141.25,
            "description": "WOW",
            "width": 38.0,
            "height": 19.0,
            "weight": 1200.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XC015",
            "stock": 100,
            "actual_Stock": 100,
            "image": "/img/product/narutosasuke.png",
            "imagefull": "/img/product/narutosasukefull.jpg"
        },
        {
            "id": 19,
            "name": "Kyuubi - Uzumaki Naruto x Minato - Kizuna Relation",
            "franchise": "Naruto",
            "price": 242.07,
            "description": "WOW",
            "width": 24.0,
            "height": 27.0,
            "weight": 1101.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XC017",
            "stock": 35,
            "actual_Stock": 35,
            "image": "/img/product/naruto-kyuubi2.png",
            "imagefull": "/img/product/naruto-kyuubi.png"
        },
        {
            "id": 18,
            "name": "M.U.B Busto Predator",
            "franchise": "Predator",
            "price": 2899.95,
            "description": "WOW",
            "width": 45.0,
            "height": 105.0,
            "weight": 30000.0,
            "distributor": "Tsume",
            "reference": "TM0ZZH441XC016",
            "stock": 10,
            "actual_Stock": 10,
            "image": "/img/product/predator.png",
            "imagefull": "/img/product/predator2.png"
        }
    ]
  }
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND
  
#### Search all products based on a keyword

Resource to search for all pagined products based on a keyword with its information.

* ##### URL:

	< /products >

* ##### Method:

	`GET`
  
* ##### URL Params:
	* `page=[Integer] or Empty`
    `key=[String]`
	
* ##### Example of query:

	* URL
		
		`/api/products?key=nar`

* ##### Success Response:

	```
  {
    "content": [
        {
            "id": 15,
            "name": "Naruto Uzumaki",
            "franchise": "Naruto",
            "price": 59.99,
            "description": "WOW",
            "width": 12.0,
            "height": 30.0,
            "weight": 800.0,
            "distributor": "Banpresto",
            "reference": "TM0ZZH441XC013",
            "stock": 65,
            "actual_Stock": 65,
            "image": "/img/product/naruto.png",
            "imagefull": "/img/product/naruto2.jpg"
        },
        {
            "id": 16,
            "name": "Naruto sentado (Figuarts ZERO)",
            "franchise": "Naruto",
            "price": 75.5,
            "description": "WOW",
            "width": 25.0,
            "height": 20.0,
            "weight": 500.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XC014",
            "stock": 90,
            "actual_Stock": 90,
            "image": "/img/product/narutoSentado.png",
            "imagefull": "/img/product/narutoSentado2.jpg"
        },
        {
            "id": 17,
            "name": "Pack Naruto y Sasuke Relation (Figuarts ZERO Ex)",
            "franchise": "Naruto",
            "price": 141.25,
            "description": "WOW",
            "width": 38.0,
            "height": 19.0,
            "weight": 1200.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XC015",
            "stock": 100,
            "actual_Stock": 100,
            "image": "/img/product/narutosasuke.png",
            "imagefull": "/img/product/narutosasukefull.jpg"
        },
        {
            "id": 19,
            "name": "Kyuubi - Uzumaki Naruto x Minato - Kizuna Relation",
            "franchise": "Naruto",
            "price": 242.07,
            "description": "WOW",
            "width": 24.0,
            "height": 27.0,
            "weight": 1101.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XC017",
            "stock": 35,
            "actual_Stock": 35,
            "image": "/img/product/naruto-kyuubi2.png",
            "imagefull": "/img/product/naruto-kyuubi.png"
        }
    ]
  }
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND
  
#### Show determinate product

Resource to show determinate product based on its id

* ##### URL:

	< /products/{id} >

* ##### Method:

	`GET`
  
* ##### URL Params:
	* `id=[Long]`
	
* ##### Example of query:

	* URL
		
		`/api/products/4`

* ##### Success Response:

	```
  {
    "id": 4,
    "name": "Attack on Titans - Mikasa Ackerman",
    "franchise": "Attack on Titans",
    "price": 40.5,
    "description": "De la serie de anime Attack on Titan viene una figura de Mikasa con articulaciones regulables.",
    "width": 50.0,
    "height": 30.0,
    "weight": 1200.0,
    "distributor": "Figuarts ZERO",
    "reference": "TM0ZZH441XC002",
    "stock": 160,
    "actual_Stock": 160,
    "image": "/img/product/mikasa.png",
    "imagefull": "/img/product/mikasa2.jpg"
  }
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND
  
#### Show recommended products based on your orders

Resource to show determinate products based on your orders.
Advanced algorithm take user's orders automatically and show it based on the amount you bought.
If not logged user, it recomends randomly.

* ##### URL:

	< /products/recommendations >

* ##### Method:

	`GET`
	
* ##### Example of query:

	* URL
		
		`/api/products/recommendations`

* ##### Success Response:

	```
  [
    {
        "id": 10,
        "name": "Jinbei (Figuarts ZERO)",
        "franchise": "One Piece",
        "price": 80.2,
        "description": "Figura de Jinbei \"El Caballero del Mar\", el hombre-pez de One Piece, fabricada por Bandai-Tamashii Nations para su colección Figuarts ZERO",
        "width": 25.0,
        "height": 40.0,
        "weight": 500.0,
        "distributor": "Figuarts ZERO",
        "reference": "TM0ZZH441XB008",
        "stock": 60,
        "actual_Stock": 60,
        "image": "/img/product/Jinbei.png",
        "imagefull": "/img/product/Jinbei2.jpg"
    },
    {
        "id": 11,
        "name": "Luffy (Figuarts ZERO)",
        "franchise": "One Piece",
        "price": 35.5,
        "description": "Figura de Monkey D. Luffy, el protagonista de One Piece, perteneciente a la colección Figuarts ZERO de Bandai - Tamashii Nations.",
        "width": 30.0,
        "height": 25.0,
        "weight": 750.0,
        "distributor": "Figuarts ZERO",
        "reference": "TM0ZZH441XB009",
        "stock": 87,
        "actual_Stock": 87,
        "image": "/img/product/Luffy.png",
        "imagefull": "/img/product/Luffy2.jpg"
    },
    {
        "id": 12,
        "name": "Zoro (Figuarts ZERO)",
        "franchise": "One Piece",
        "price": 70.0,
        "description": "Figura de Zoro, personaje de One Piece, perteneciente a la colección Figuarts ZERO de Bandai - Tamashii Nations, ejecutando su espectacular técnica de ataque.",
        "width": 30.0,
        "height": 30.0,
        "weight": 835.0,
        "distributor": "Figuarts ZERO",
        "reference": "TM0ZZH441XB010",
        "stock": 120,
        "actual_Stock": 120,
        "image": "/img/product/Zoro.png",
        "imagefull": "/img/product/Zoro2.jpg"
    },
    {
        "id": 8,
        "name": "Charlotte Katakuri(Figuarts ZERO)",
        "franchise": "One Piece",
        "price": 69.99,
        "description": "Esta fantástica figura está fabricada en PVC y mide aproximadamente 22 centímetros de alto. Charlotte aparece sosteniendo su arma, un tridente que recibe el nombre de Mogura",
        "width": 15.0,
        "height": 20.0,
        "weight": 400.0,
        "distributor": "Figuarts ZERO",
        "reference": "TM0ZZH441XB006",
        "stock": 90,
        "actual_Stock": 90,
        "image": "/img/product/katakuri.png",
        "imagefull": "/img/product/katakurifull.jpg"
    },
    {
        "id": 9,
        "name": "Funko Pop! - Chopper",
        "franchise": "One Piece",
        "price": 56.75,
        "description": "Figura de la línea POP! de Chopper. Basada en el universo de One Piece creado por Eiichiro Oda, tiene una altura aproximada de 10cm.",
        "width": 17.0,
        "height": 30.0,
        "weight": 650.0,
        "distributor": "Funko Pop!",
        "reference": "TM0ZZH441XB007",
        "stock": 75,
        "actual_Stock": 75,
        "image": "/img/product/chopper.png",
        "imagefull": "/img/product/chopper2.jpg"
    }
  ]
  ```
  
#### Filter products

Resource to show determinate products based on franchise, distributor, height, width, max price and min price.

* ##### URL:

	< /products/filter >

* ##### Method:

	`GET`
  
* ##### URL Params:
	* `franchise=[String]`
    `distributor=[String]`
    `height=[Integer]`
    `width=[Integer]`
    `max_price=[Integer]`
    `min_price=[Integer]`
	
* ##### Example of query:

	* URL
		
		`/api/products/filter?distributor=Figuarts ZERO&height=26&max_price=70`

* ##### Success Response:

	```
  [
    {
        "id": 5,
        "name": "Eren Jaeguer",
        "franchise": "Attack on Titans",
        "price": 50.25,
        "description": "Figura articulada del anime 'Attack on Titan', tamaño aprox. 25 cm. Viene en una caja con ventana.",
        "width": 31.0,
        "height": 25.0,
        "weight": 100.0,
        "distributor": "Figuarts ZERO",
        "reference": "TM0ZZH441XC003",
        "stock": 40,
        "actual_Stock": 40,
        "image": "/img/product/ErenJaeguer.png",
        "imagefull": "/img/product/ErenJaeguer2.jpg"
    },
    {
        "id": 8,
        "name": "Charlotte Katakuri(Figuarts ZERO)",
        "franchise": "One Piece",
        "price": 69.99,
        "description": "Esta fantástica figura está fabricada en PVC y mide aproximadamente 22 centímetros de alto. Charlotte aparece sosteniendo su arma, un tridente que recibe el nombre de Mogura",
        "width": 15.0,
        "height": 20.0,
        "weight": 400.0,
        "distributor": "Figuarts ZERO",
        "reference": "TM0ZZH441XB006",
        "stock": 90,
        "actual_Stock": 90,
        "image": "/img/product/katakuri.png",
        "imagefull": "/img/product/katakurifull.jpg"
    },
    {
        "id": 11,
        "name": "Luffy (Figuarts ZERO)",
        "franchise": "One Piece",
        "price": 35.5,
        "description": "Figura de Monkey D. Luffy, el protagonista de One Piece, perteneciente a la colección Figuarts ZERO de Bandai - Tamashii Nations.",
        "width": 30.0,
        "height": 25.0,
        "weight": 750.0,
        "distributor": "Figuarts ZERO",
        "reference": "TM0ZZH441XB009",
        "stock": 87,
        "actual_Stock": 87,
        "image": "/img/product/Luffy.png",
        "imagefull": "/img/product/Luffy2.jpg"
    }
  ]
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND
  
#### Create new product (Administrator)

Resource to create new product with its information.

* ##### URL:

	< /products >

* ##### Method:

	`POST`
  
* ##### Data Params:
      {
        "name": "Saitama",
        "franchise": "One Punch Man",
        "price": 85.49,
        "description": "Esta fantástica figura está fabricada en PVC y mide aproximadamente 25 centímetros de alto. Saitama, si te mira te destruye",
        "width": 8,
        "height": 25,
        "weight": 680,
        "distributor": "Banpresto",
        "reference": "TM0ZZH441XB0027",
        "stock": 100,
        "actual_Stock": 100,
        "image": "/img/product/notavailable.png",
        "imagefull": "/img/product/notavailable2.jpg"
      }
	
* ##### Example of query:

	* URL
		
		`/api/products`

* ##### Success Response:

	```
  {
      "id": 26,
      "name": "Saitama",
      "franchise": "One Punch Man",
      "price": 85.49,
      "description": "Esta fantástica figura está fabricada en PVC y mide aproximadamente 25 centímetros de alto. Saitama, si te mira te destruye",
      "width": 8.0,
      "height": 25.0,
      "weight": 680.0,
      "distributor": "Banpresto",
      "reference": "TM0ZZH441XB0027",
      "stock": 100,
      "actual_Stock": 100,
      "image": "/img/product/notavailable.png",
      "imagefull": "/img/product/notavailable2.jpg"
  }
  ```
  
* ##### Error Response:

	**Code**: 401 UNAUTHORIZED or 400 BAD REQUEST 
  
#### Delete determinate product (Administrator)

Resource to delete determinate product based on its id.

* ##### URL:

	< /products/{id} >

* ##### Method:

	`DELETE`
  
* ##### URL Params:
   * `id=[long]`
	
* ##### Example of query:

	* URL
		
		`/api/products/8`

* ##### Success Response:

	```
  {
    "id": 8,
    "name": "Charlotte Katakuri(Figuarts ZERO)",
    "franchise": "One Piece",
    "price": 69.99,
    "description": "Esta fantástica figura está fabricada en PVC y mide aproximadamente 22 centímetros de alto. Charlotte aparece sosteniendo su arma, un tridente que recibe el nombre de Mogura",
    "width": 15.0,
    "height": 20.0,
    "weight": 400.0,
    "distributor": "Figuarts ZERO",
    "reference": "TM0ZZH441XB006",
    "stock": 90,
    "actual_Stock": 90,
    "image": "/img/product/katakuri.png",
    "imagefull": "/img/product/katakurifull.jpg"
  }
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND or 401 UNAUTHORIZED or 400 BAD REQUEST  
  
## Blogs

The following queries will be preceded by /blogs.

#### Show all blogs

Resource to show all blogs with their data.

* ##### URL:

	< /blogs >

* ##### Method:

	`GET`

* ##### Success Response:

	```
	[
    {
        "id": 20,
        "author": "Administrador",
        "name": "Nuevo Funko Pop! de Levi Ackerman - Attack on Titans",
        "text": "No pierdas la oportunidad de hacerte con esta nueva pieza por parte de Funko Pop! de uno de los personajes más icónicos de Attack on Titans.",
        "image": "/img/blog/levi-banner-icon.png",
        "textfull": "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura",
        "product": {
            "id": 3,
            "name": "Funko Pop! Levi Ackerman",
            "franchise": "Attack on Titans",
            "price": 14.49,
            "description": "Figura vinilo Funko POP de 9cm en caja regalo.",
            "width": 5.0,
            "height": 9.0,
            "weight": 200.0,
            "distributor": "Funko Pop!",
            "reference": "TM0ZZH441XA001",
            "stock": 100,
            "actual_Stock": 100,
            "image": "/img/product/levimin.png",
            "imagefull": "/img/product/levi2.jpg"
        }
    },
    {
        "id": 21,
        "author": "Administrador",
        "name": "Nueva figura Naruto Kyuubi mode por parte de Figuarts ZERO",
        "text": "Y es que otra vez lo han vuelto a hacer.. Figuarts ZERO nos sorprende con esta magnifica figura de Naruto en modo Kyuubi para los más amantes de la serie.",
        "image": "/img/blog/naruto-blog-main.png",
        "textfull": "Como ya avisamos hace unas semanas, Figuarts ZERO estaba esperando al décimoquinto aniversario de Naruto para darnos una sorpresa.. Y sí señores, ¡lo han conseguido! Échale un vistazo a esta impresionante figura de Naruto en modo Kyubi",
        "product": {
            "id": 14,
            "name": "Namikaze (Figuarts ZERO Ex)",
            "franchise": "Naruto",
            "price": 141.25,
            "description": "WOW",
            "width": 16.0,
            "height": 45.0,
            "weight": 1200.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XC012",
            "stock": 80,
            "actual_Stock": 80,
            "image": "/img/product/namikaze.png",
            "imagefull": "/img/product/namikaze2.jpg"
        }
    },
    {
        "id": 22,
        "author": "Administrador",
        "name": "INCREÍBLE busto M.U.B Predator by Tsume",
        "text": "Precio no apto para cardiacos. Eso sí, es una obra de arte de los piés a la cabeza.",
        "image": "/img/blog/predator-blog-main2.png",
        "textfull": "Menudo sorpresón nos acaba de dar Tsume Art. Sin esperarlo, de la noche a la mañana, se ha sacado de la manga éste impresionante busto de la saga Predator. ¡No te lo puedes perder!",
        "product": {
            "id": 18,
            "name": "M.U.B Busto Predator",
            "franchise": "Predator",
            "price": 2899.95,
            "description": "WOW",
            "width": 45.0,
            "height": 105.0,
            "weight": 30000.0,
            "distributor": "Tsume",
            "reference": "TM0ZZH441XC016",
            "stock": 10,
            "actual_Stock": 10,
            "image": "/img/product/predator.png",
            "imagefull": "/img/product/predator2.png"
        }
    }
  ]
	```

* ##### Error Response:

	**Code**: 404 NOT FOUND or 400 BAD REQUEST
  
#### Search determinate blog

Resource to search determinate blogs with its information.

* ##### URL:

	< /blogs >

* ##### Method:

	`GET`
  
* ##### URL Params:
	* `key=[String]`
	
* ##### Example of query:

	* URL
		
		`/api/blogs?key=lev`

* ##### Success Response:

	```
  [
    {
        "id": 20,
        "author": "Administrador",
        "name": "Nuevo Funko Pop! de Levi Ackerman - Attack on Titans",
        "text": "No pierdas la oportunidad de hacerte con esta nueva pieza por parte de Funko Pop! de uno de los personajes más icónicos de Attack on Titans.",
        "image": "/img/blog/levi-banner-icon.png",
        "textfull": "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura",
        "product": {
            "id": 3,
            "name": "Funko Pop! Levi Ackerman",
            "franchise": "Attack on Titans",
            "price": 14.49,
            "description": "Figura vinilo Funko POP de 9cm en caja regalo.",
            "width": 5.0,
            "height": 9.0,
            "weight": 200.0,
            "distributor": "Funko Pop!",
            "reference": "TM0ZZH441XA001",
            "stock": 100,
            "actual_Stock": 100,
            "image": "/img/product/levimin.png",
            "imagefull": "/img/product/levi2.jpg"
        }
    }
  ]
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND
  
#### Show determinate blog

Resource to show determinate blog with its information.

* ##### URL:

	< /blogs/{id} >

* ##### Method:

	`GET`
  
* ##### URL Params:
	* `id=[long]`
	
* ##### Example of query:

	* URL
		
		`/api/blogs/21`

* ##### Success Response:

	```
  {
    "id": 21,
    "author": "Administrador",
    "name": "Nueva figura Naruto Kyuubi mode por parte de Figuarts ZERO",
    "text": "Y es que otra vez lo han vuelto a hacer.. Figuarts ZERO nos sorprende con esta magnifica figura de Naruto en modo Kyuubi para los más amantes de la serie.",
    "image": "/img/blog/naruto-blog-main.png",
    "textfull": "Como ya avisamos hace unas semanas, Figuarts ZERO estaba esperando al décimoquinto aniversario de Naruto para darnos una sorpresa.. Y sí señores, ¡lo han conseguido! Échale un vistazo a esta impresionante figura de Naruto en modo Kyubi",
    "product": {
        "id": 14,
        "name": "Namikaze (Figuarts ZERO Ex)",
        "franchise": "Naruto",
        "price": 141.25,
        "description": "WOW",
        "width": 16.0,
        "height": 45.0,
        "weight": 1200.0,
        "distributor": "Figuarts ZERO",
        "reference": "TM0ZZH441XC012",
        "stock": 80,
        "actual_Stock": 80,
        "image": "/img/product/namikaze.png",
        "imagefull": "/img/product/namikaze2.jpg"
    }
  }
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND  
  
#### Create new blog (Administrator)

Resource to create new blog with its information and product refer.

* ##### URL:

	< /blogs >

* ##### Method:

	`POST`
  
* ##### Data Params:
      {
        "author": "Administrador",
        "name": "Nuevo Funko Pop! de Chopper - One Piece",
        "text": "No pierdas la oportunidad de hacerte con esta nueva pieza por parte de Funko Pop! de uno de los personajes más icónicos de One Piece.",
        "image": "/img/product/newblog.png",
        "textfull": "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura",
        "product": {
            "id": 9
        }
      }
	
* ##### Example of query:

	* URL
		
		`/api/blogs`

* ##### Success Response:

	```
  [
    {
        "id": 20,
        "author": "Administrador",
        "name": "Nuevo Funko Pop! de Levi Ackerman - Attack on Titans",
        "text": "No pierdas la oportunidad de hacerte con esta nueva pieza por parte de Funko Pop! de uno de los personajes más icónicos de Attack on Titans.",
        "image": "/img/blog/levi-banner-icon.png",
        "textfull": "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura",
        "product": {
            "id": 3,
            "name": "Funko Pop! Levi Ackerman",
            "franchise": "Attack on Titans",
            "price": 14.49,
            "description": "Figura vinilo Funko POP de 9cm en caja regalo.",
            "width": 5.0,
            "height": 9.0,
            "weight": 200.0,
            "distributor": "Funko Pop!",
            "reference": "TM0ZZH441XA001",
            "stock": 100,
            "actual_Stock": 100,
            "image": "/img/product/levimin.png",
            "imagefull": "/img/product/levi2.jpg"
        }
    },
    {
        "id": 21,
        "author": "Administrador",
        "name": "Nueva figura Naruto Kyuubi mode por parte de Figuarts ZERO",
        "text": "Y es que otra vez lo han vuelto a hacer.. Figuarts ZERO nos sorprende con esta magnifica figura de Naruto en modo Kyuubi para los más amantes de la serie.",
        "image": "/img/blog/naruto-blog-main.png",
        "textfull": "Como ya avisamos hace unas semanas, Figuarts ZERO estaba esperando al décimoquinto aniversario de Naruto para darnos una sorpresa.. Y sí señores, ¡lo han conseguido! Échale un vistazo a esta impresionante figura de Naruto en modo Kyubi",
        "product": {
            "id": 14,
            "name": "Namikaze (Figuarts ZERO Ex)",
            "franchise": "Naruto",
            "price": 141.25,
            "description": "WOW",
            "width": 16.0,
            "height": 45.0,
            "weight": 1200.0,
            "distributor": "Figuarts ZERO",
            "reference": "TM0ZZH441XC012",
            "stock": 80,
            "actual_Stock": 80,
            "image": "/img/product/namikaze.png",
            "imagefull": "/img/product/namikaze2.jpg"
        }
    },
    {
        "id": 22,
        "author": "Administrador",
        "name": "INCREÍBLE busto M.U.B Predator by Tsume",
        "text": "Precio no apto para cardiacos. Eso sí, es una obra de arte de los piés a la cabeza.",
        "image": "/img/blog/predator-blog-main2.png",
        "textfull": "Menudo sorpresón nos acaba de dar Tsume Art. Sin esperarlo, de la noche a la mañana, se ha sacado de la manga éste impresionante busto de la saga Predator. ¡No te lo puedes perder!",
        "product": {
            "id": 18,
            "name": "M.U.B Busto Predator",
            "franchise": "Predator",
            "price": 2899.95,
            "description": "WOW",
            "width": 45.0,
            "height": 105.0,
            "weight": 30000.0,
            "distributor": "Tsume",
            "reference": "TM0ZZH441XC016",
            "stock": 10,
            "actual_Stock": 10,
            "image": "/img/product/predator.png",
            "imagefull": "/img/product/predator2.png"
        }
    },
    {
        "id": 27,
        "author": "Administrador",
        "name": "Nuevo Funko Pop! de Chopper - One Piece",
        "text": "No pierdas la oportunidad de hacerte con esta nueva pieza por parte de Funko Pop! de uno de los personajes más icónicos de One Piece.",
        "image": "/img/product/newblog.png",
        "textfull": "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura",
        "product": {
            "id": 9,
            "name": "Funko Pop! - Chopper",
            "franchise": "One Piece",
            "price": 56.75,
            "description": "Figura de la línea POP! de Chopper. Basada en el universo de One Piece creado por Eiichiro Oda, tiene una altura aproximada de 10cm.",
            "width": 17.0,
            "height": 30.0,
            "weight": 650.0,
            "distributor": "Funko Pop!",
            "reference": "TM0ZZH441XB007",
            "stock": 75,
            "actual_Stock": 75,
            "image": "/img/product/chopper.png",
            "imagefull": "/img/product/chopper2.jpg"
        }
    }
  ]
  ```
  
* ##### Error Response:

	**Code**: 401 UNAUTHORIZED or 400 BAD REQUEST  
  
#### Delete determinate blog (Administrator)

Resource to delete determinate blog based on its id.

* ##### URL:

	< /blogs/{id} >

* ##### Method:

	`DELETE`
  
* ##### URL Params:
   * `id=[long]`
	
* ##### Example of query:

	* URL
		
		`/api/blogs/21`

* ##### Success Response:

	```
  [
    {
        "id": 20,
        "author": "Administrador",
        "name": "Nuevo Funko Pop! de Levi Ackerman - Attack on Titans",
        "text": "No pierdas la oportunidad de hacerte con esta nueva pieza por parte de Funko Pop! de uno de los personajes más icónicos de Attack on Titans.",
        "image": "/img/blog/levi-banner-icon.png",
        "textfull": "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura",
        "product": {
            "id": 3,
            "name": "Funko Pop! Levi Ackerman",
            "franchise": "Attack on Titans",
            "price": 14.49,
            "description": "Figura vinilo Funko POP de 9cm en caja regalo.",
            "width": 5.0,
            "height": 9.0,
            "weight": 200.0,
            "distributor": "Funko Pop!",
            "reference": "TM0ZZH441XA001",
            "stock": 100,
            "actual_Stock": 100,
            "image": "/img/product/levimin.png",
            "imagefull": "/img/product/levi2.jpg"
        }
    },
    {
        "id": 22,
        "author": "Administrador",
        "name": "INCREÍBLE busto M.U.B Predator by Tsume",
        "text": "Precio no apto para cardiacos. Eso sí, es una obra de arte de los piés a la cabeza.",
        "image": "/img/blog/predator-blog-main2.png",
        "textfull": "Menudo sorpresón nos acaba de dar Tsume Art. Sin esperarlo, de la noche a la mañana, se ha sacado de la manga éste impresionante busto de la saga Predator. ¡No te lo puedes perder!",
        "product": {
            "id": 18,
            "name": "M.U.B Busto Predator",
            "franchise": "Predator",
            "price": 2899.95,
            "description": "WOW",
            "width": 45.0,
            "height": 105.0,
            "weight": 30000.0,
            "distributor": "Tsume",
            "reference": "TM0ZZH441XC016",
            "stock": 10,
            "actual_Stock": 10,
            "image": "/img/product/predator.png",
            "imagefull": "/img/product/predator2.png"
        }
    },
    {
        "id": 27,
        "author": "Administrador",
        "name": "Nuevo Funko Pop! de Chopper - One Piece",
        "text": "No pierdas la oportunidad de hacerte con esta nueva pieza por parte de Funko Pop! de uno de los personajes más icónicos de One Piece.",
        "image": "/img/product/newblog.png",
        "textfull": "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura",
        "product": {
            "id": 9,
            "name": "Funko Pop! - Chopper",
            "franchise": "One Piece",
            "price": 56.75,
            "description": "Figura de la línea POP! de Chopper. Basada en el universo de One Piece creado por Eiichiro Oda, tiene una altura aproximada de 10cm.",
            "width": 17.0,
            "height": 30.0,
            "weight": 650.0,
            "distributor": "Funko Pop!",
            "reference": "TM0ZZH441XB007",
            "stock": 75,
            "actual_Stock": 75,
            "image": "/img/product/chopper.png",
            "imagefull": "/img/product/chopper2.jpg"
        }
    }
  ]
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND or 401 UNAUTHORIZED or 400 BAD REQUEST  
  
## Orders

The following queries will be preceded by /order.

#### Show all orders

Resource to show all orders with their data.

* ##### URL:

	< /order >

* ##### Method:

	`GET`

* ##### Success Response:

	```
  [
    {
        "id": 23,
        "status": null,
        "productList": [],
        "total": 0.0,
        "day": 8,
        "month": 2,
        "year": 2020
    },
    {
        "id": 24,
        "status": null,
        "productList": [],
        "total": 0.0,
        "day": 8,
        "month": 2,
        "year": 2020
    }
  ]
  ```
  
#### Show determinate order

Resource to show determinate order based on its id with its information.

* ##### URL:

	< /order/{orderId} >

* ##### Method:

	`GET`
  
* ##### URL Params:
   * `orderId=[long]`
	
* ##### Example of query:

	* URL
		
		`/api/order/24`

* ##### Success Response:

	```
  {
    "id": 24,
    "status": null,
    "productList": [],
    "total": 0.0,
    "day": 8,
    "month": 2,
    "year": 2020
  }
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND
  
#### Add product amount to determinate order

Resource to add product based on its id and its quantity to order based on its id.

* ##### URL:

	< /order/{orderId} >

* ##### Method:

	`POST`
  
* ##### URL Params:
   * `orderId=[long]`
   
* ##### Data Params:
      {
        "product":{
          "id": 9
        },
        "amount": 5
      }
	
* ##### Example of query:

	* URL
		
		`/api/order/24`

* ##### Success Response:

	```
  {
    "id": 24,
    "status": null,
    "productList": [
        {
            "id": 28,
            "product": {
                "id": 4,
                "name": "Attack on Titans - Mikasa Ackerman",
                "franchise": "Attack on Titans",
                "price": 40.5,
                "description": "De la serie de anime Attack on Titan viene una figura de Mikasa con articulaciones regulables.",
                "width": 50.0,
                "height": 30.0,
                "weight": 1200.0,
                "distributor": "Figuarts ZERO",
                "reference": "TM0ZZH441XC002",
                "stock": 160,
                "actual_Stock": 160,
                "image": "/img/product/mikasa.png",
                "imagefull": "/img/product/mikasa2.jpg"
            },
            "amount": 5,
            "total": 202.5
        },
        {
            "id": 29,
            "product": {
                "id": 9,
                "name": "Funko Pop! - Chopper",
                "franchise": "One Piece",
                "price": 56.75,
                "description": "Figura de la línea POP! de Chopper. Basada en el universo de One Piece creado por Eiichiro Oda, tiene una altura aproximada de 10cm.",
                "width": 17.0,
                "height": 30.0,
                "weight": 650.0,
                "distributor": "Funko Pop!",
                "reference": "TM0ZZH441XB007",
                "stock": 75,
                "actual_Stock": 75,
                "image": "/img/product/chopper.png",
                "imagefull": "/img/product/chopper2.jpg"
            },
            "amount": 5,
            "total": 283.75
        }
    ],
    "total": 486.25,
    "day": 8,
    "month": 2,
    "year": 2020
  }
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND or 403 FORBIDDEN or 400 BAD REQUEST 
  
#### Delete product amount to determinate order

Resource to delete product amount from an order based on its id.

* ##### URL:

	< /order/{orderId}/{pAmountId} >

* ##### Method:

	`DELETE`
  
* ##### URL Params:
   * `orderId=[long]`
     `pAmountId=[long]`
	
* ##### Example of query:

	* URL
		
		`/api/order/24/28`

* ##### Success Response:

	```
  {
    "id": 24,
    "status": null,
    "productList": [
        {
            "id": 29,
            "product": {
                "id": 9,
                "name": "Funko Pop! - Chopper",
                "franchise": "One Piece",
                "price": 56.75,
                "description": "Figura de la línea POP! de Chopper. Basada en el universo de One Piece creado por Eiichiro Oda, tiene una altura aproximada de 10cm.",
                "width": 17.0,
                "height": 30.0,
                "weight": 650.0,
                "distributor": "Funko Pop!",
                "reference": "TM0ZZH441XB007",
                "stock": 75,
                "actual_Stock": 75,
                "image": "/img/product/chopper.png",
                "imagefull": "/img/product/chopper2.jpg"
            },
            "amount": 5,
            "total": 283.75
        }
    ],
    "total": 283.75,
    "day": 8,
    "month": 2,
    "year": 2020
  }
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND or 403 FORBIDDEN or 400 BAD REQUEST 
  
#### Update product amount from determinate order

Resource to update product amount based on its id and its quantity from order based on its id.

* ##### URL:

	< /order/{orderId} >

* ##### Method:

	`PUT`
  
* ##### URL Params:
   * `orderId=[long]`
   
* ##### Data Params:
      {
        "id": 29,
        "amount": 2
      }
	
* ##### Example of query:

	* URL
		
		`/api/order/24`

* ##### Success Response:

	```
  {
    "id": 24,
    "status": null,
    "productList": [
        {
            "id": 29,
            "product": {
                "id": 9,
                "name": "Funko Pop! - Chopper",
                "franchise": "One Piece",
                "price": 56.75,
                "description": "Figura de la línea POP! de Chopper. Basada en el universo de One Piece creado por Eiichiro Oda, tiene una altura aproximada de 10cm.",
                "width": 17.0,
                "height": 30.0,
                "weight": 650.0,
                "distributor": "Funko Pop!",
                "reference": "TM0ZZH441XB007",
                "stock": 75,
                "actual_Stock": 75,
                "image": "/img/product/chopper.png",
                "imagefull": "/img/product/chopper2.jpg"
            },
            "amount": 2,
            "total": 113.5
        }
    ],
    "total": 113.5,
    "day": 8,
    "month": 2,
    "year": 2020
  }
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND or 403 FORBIDDEN or 400 BAD REQUEST 
  
#### Confirm determinate order

Resource to confirm and order and change its status to 'Complete'.

* ##### URL:

	< /order/{orderId}/confirmation >

* ##### Method:

	`PUT`
  
* ##### URL Params:
   * `orderId=[long]`
   
* ##### Data Params (Optional):
      {
        "shippingname": "David",
        "lastname": "Amor",
        "company": "URJC",
        "number": "654451912",
        "email": "David.a.almendra@hotmail.com",
        "street": "Calle de Uruguay, 6",
        "floor": "7D",
        "city": "Fuenlabrada",
        "country": "Españita",
        "zipcode": "28944",
        "name": "David"
      }
	
* ##### Example of query:

	* URL
		
		`/api/order/24/confirmation`

* ##### Success Response:

	```
  [
    {
        "id": 23,
        "status": "Complete",
        "productList": [
            {
                "id": 31,
                "product": {
                    "id": 3,
                    "name": "Funko Pop! Levi Ackerman",
                    "franchise": "Attack on Titans",
                    "price": 14.49,
                    "description": "Figura vinilo Funko POP de 9cm en caja regalo.",
                    "width": 5.0,
                    "height": 9.0,
                    "weight": 200.0,
                    "distributor": "Funko Pop!",
                    "reference": "TM0ZZH441XA001",
                    "stock": 100,
                    "actual_Stock": 100,
                    "image": "/img/product/levimin.png",
                    "imagefull": "/img/product/levi2.jpg"
                },
                "amount": 1,
                "total": 14.49
            },
            {
                "id": 29,
                "product": {
                    "id": 9,
                    "name": "Funko Pop! - Chopper",
                    "franchise": "One Piece",
                    "price": 56.75,
                    "description": "Figura de la línea POP! de Chopper. Basada en el universo de One Piece creado por Eiichiro Oda, tiene una altura aproximada de 10cm.",
                    "width": 17.0,
                    "height": 30.0,
                    "weight": 650.0,
                    "distributor": "Funko Pop!",
                    "reference": "TM0ZZH441XB007",
                    "stock": 75,
                    "actual_Stock": 75,
                    "image": "/img/product/chopper.png",
                    "imagefull": "/img/product/chopper2.jpg"
                },
                "amount": 2,
                "total": 113.5
            }
        ],
        "total": 127.99,
        "day": 8,
        "month": 2,
        "year": 2020
    },
    {
        "shippingname": "David",
        "lastname": "Amor",
        "company": "URJC",
        "number": "654451912",
        "email": "David.a.almendra@hotmail.com",
        "street": "Calle de Uruguay, 6",
        "floor": "7D",
        "city": "Fuenlabrada",
        "country": "Españita",
        "zipcode": "28944",
        "name": "David"
    },
    {
        "shippingname": "David",
        "lastname": "Amor",
        "company": "URJC",
        "number": "654451912",
        "email": "David.a.almendra@hotmail.com",
        "street": "Calle de Uruguay, 6",
        "floor": "7D",
        "city": "Fuenlabrada",
        "country": "Españita",
        "zipcode": "28944",
        "name": "David"
    }
  ]
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND or 403 FORBIDDEN or 400 BAD REQUEST 
  
## Users

The following queries will be preceded by /.

#### Show all users

Resource to show all users with their data.

* ##### URL:

	< /users >

* ##### Method:

	`GET`

* ##### Success Response:
  
  ```
  [
    {
        "id": 1,
        "name": "user",
        "passwordHash": "$2a$10$af11CBhkX8ymgbBDZ9ROKONWqO/YDxNymiqeS8oTDXwZBUG67t80K",
        "delivery": {
            "shippingname": "Carlos Francisco",
            "lastname": "Corraliza",
            "company": "URJC",
            "number": "666666666",
            "email": "David.a.almendra@hotmail.com",
            "street": "Calle Tulipan, 5",
            "floor": "Bajo",
            "city": "Mostoles",
            "country": "Españita",
            "zipcode": "28956",
            "name": "Carlos Francisco"
        },
        "orderList": [
            {
                "id": 23,
                "status": "Complete",
                "productList": [
                    {
                        "id": 31,
                        "product": {
                            "id": 3,
                            "name": "Funko Pop! Levi Ackerman",
                            "franchise": "Attack on Titans",
                            "price": 14.49,
                            "description": "Figura vinilo Funko POP de 9cm en caja regalo.",
                            "width": 5.0,
                            "height": 9.0,
                            "weight": 200.0,
                            "distributor": "Funko Pop!",
                            "reference": "TM0ZZH441XA001",
                            "stock": 100,
                            "actual_Stock": 100,
                            "image": "/img/product/levimin.png",
                            "imagefull": "/img/product/levi2.jpg"
                        },
                        "amount": 1,
                        "total": 14.49
                    },
                    {
                        "id": 29,
                        "product": {
                            "id": 9,
                            "name": "Funko Pop! - Chopper",
                            "franchise": "One Piece",
                            "price": 56.75,
                            "description": "Figura de la línea POP! de Chopper. Basada en el universo de One Piece creado por Eiichiro Oda, tiene una altura aproximada de 10cm.",
                            "width": 17.0,
                            "height": 30.0,
                            "weight": 650.0,
                            "distributor": "Funko Pop!",
                            "reference": "TM0ZZH441XB007",
                            "stock": 75,
                            "actual_Stock": 75,
                            "image": "/img/product/chopper.png",
                            "imagefull": "/img/product/chopper2.jpg"
                        },
                        "amount": 2,
                        "total": 113.5
                    }
                ],
                "total": 127.99,
                "day": 8,
                "month": 2,
                "year": 2020
            },
            {
                "id": 33,
                "status": null,
                "productList": [],
                "total": 0.0,
                "day": 8,
                "month": 2,
                "year": 2020
            }
        ],
        "roles": [
            "ROLE_USER"
        ]
    },
    {
        "id": 2,
        "name": "admin",
        "passwordHash": "$2a$10$cuS9RsHKTDOCMga/qCwzHee6QWPRICnl9KC6A9pR5HgcWOZyXN9CW",
        "delivery": {
            "shippingname": "",
            "lastname": "",
            "company": "",
            "number": "",
            "email": "",
            "street": "",
            "floor": "",
            "city": "",
            "country": "",
            "zipcode": "",
            "name": ""
        },
        "orderList": [],
        "roles": [
            "ROLE_USER",
            "ROLE_ADMIN"
        ]
    },
  ]
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND

#### Show determinate user information

Resource to show all information of determinate user.

* ##### URL:

	< /user/{id} >

* ##### Method:

	`GET`

* ##### Success Response:
  
  ```
  {
    "id": 1,
    "name": "user",
    "passwordHash": "$2a$10$TmBML/Clhc9oHbgijxbuWO.ZHcaG8E9yE6EnRUiwgJdGxa4AQr8sS",
    "delivery": {
        "shippingname": "David",
        "lastname": "Amor",
        "company": "URJC",
        "number": "654451912",
        "email": "David.a.almendra@hotmail.com",
        "street": "Calle de Uruguay, 6",
        "floor": "7D",
        "city": "Fuenlabrada",
        "country": "Españita",
        "zipcode": "28944",
        "name": "David"
    },
    "orderList": [
        {
            "id": 23,
            "status": "Complete",
            "productList": [
                {
                    "id": 31,
                    "product": {
                        "id": 3,
                        "name": "Funko Pop! Levi Ackerman",
                        "franchise": "Attack on Titans",
                        "price": 14.49,
                        "description": "Figura vinilo Funko POP de 9cm en caja regalo.",
                        "width": 5.0,
                        "height": 9.0,
                        "weight": 200.0,
                        "distributor": "Funko Pop!",
                        "reference": "TM0ZZH441XA001",
                        "stock": 100,
                        "actual_Stock": 100,
                        "image": "/img/product/levimin.png",
                        "imagefull": "/img/product/levi2.jpg"
                    },
                    "amount": 1,
                    "total": 14.49
                },
                {
                    "id": 29,
                    "product": {
                        "id": 9,
                        "name": "Funko Pop! - Chopper",
                        "franchise": "One Piece",
                        "price": 56.75,
                        "description": "Figura de la línea POP! de Chopper. Basada en el universo de One Piece creado por Eiichiro Oda, tiene una altura aproximada de 10cm.",
                        "width": 17.0,
                        "height": 30.0,
                        "weight": 650.0,
                        "distributor": "Funko Pop!",
                        "reference": "TM0ZZH441XB007",
                        "stock": 75,
                        "actual_Stock": 75,
                        "image": "/img/product/chopper.png",
                        "imagefull": "/img/product/chopper2.jpg"
                    },
                    "amount": 2,
                    "total": 113.5
                }
            ],
            "total": 127.99,
            "day": 8,
            "month": 2,
            "year": 2020
        },
        {
            "id": 33,
            "status": null,
            "productList": [],
            "total": 0.0,
            "day": 8,
            "month": 2,
            "year": 2020
        }
    ],
    "roles": [
        "ROLE_USER"
    ]
  }
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND or 403 FORBIDDEN or 401 UNAUTHORIZED
  
#### Edit information of determinate user

Resource to edit all information of determinate user.

* ##### URL:

	< /user/{userId} >

* ##### Method:

	`PUT`
  
* ##### URL Params:
   * `userId=[long]`
     `shippingName=[String]`
     `passwordHash=[String]`
   
* ##### Data Params:
      {
          "lastname": "Corraliza",
          "company": "URJC",
          "number": "666666666",
          "email": "David.a.almendra@hotmail.com",
          "street": "Calle Tulipan, 5",
          "floor": "Bajo",
          "city": "Mostoles",
          "country": "Españita",
          "zipcode": "28956",
          "name": "Carlos Francisco"
      }
	
* ##### Example of query:

	* URL
		
		`/api/user/1?shippingName=Carlos Francisco&passwordHash=urjc2020`  

* ##### Success Response:
  
  ```
  {
    "id": 1,
    "name": "user",
    "passwordHash": "$2a$10$af11CBhkX8ymgbBDZ9ROKONWqO/YDxNymiqeS8oTDXwZBUG67t80K",
    "delivery": {
        "shippingname": "Carlos Francisco",
        "lastname": "Corraliza",
        "company": "URJC",
        "number": "666666666",
        "email": "David.a.almendra@hotmail.com",
        "street": "Calle Tulipan, 5",
        "floor": "Bajo",
        "city": "Mostoles",
        "country": "Españita",
        "zipcode": "28956",
        "name": "Carlos Francisco"
    },
    "orderList": [
        {
            "id": 23,
            "status": "Complete",
            "productList": [
                {
                    "id": 31,
                    "product": {
                        "id": 3,
                        "name": "Funko Pop! Levi Ackerman",
                        "franchise": "Attack on Titans",
                        "price": 14.49,
                        "description": "Figura vinilo Funko POP de 9cm en caja regalo.",
                        "width": 5.0,
                        "height": 9.0,
                        "weight": 200.0,
                        "distributor": "Funko Pop!",
                        "reference": "TM0ZZH441XA001",
                        "stock": 100,
                        "actual_Stock": 100,
                        "image": "/img/product/levimin.png",
                        "imagefull": "/img/product/levi2.jpg"
                    },
                    "amount": 1,
                    "total": 14.49
                },
                {
                    "id": 29,
                    "product": {
                        "id": 9,
                        "name": "Funko Pop! - Chopper",
                        "franchise": "One Piece",
                        "price": 56.75,
                        "description": "Figura de la línea POP! de Chopper. Basada en el universo de One Piece creado por Eiichiro Oda, tiene una altura aproximada de 10cm.",
                        "width": 17.0,
                        "height": 30.0,
                        "weight": 650.0,
                        "distributor": "Funko Pop!",
                        "reference": "TM0ZZH441XB007",
                        "stock": 75,
                        "actual_Stock": 75,
                        "image": "/img/product/chopper.png",
                        "imagefull": "/img/product/chopper2.jpg"
                    },
                    "amount": 2,
                    "total": 113.5
                }
            ],
            "total": 127.99,
            "day": 8,
            "month": 2,
            "year": 2020
        },
        {
            "id": 33,
            "status": null,
            "productList": [],
            "total": 0.0,
            "day": 8,
            "month": 2,
            "year": 2020
        }
    ],
    "roles": [
        "ROLE_USER"
    ]
  }
  ```
  
* ##### Error Response:

	**Code**: 404 NOT FOUND or 403 FORBIDDEN or 401 UNAUTHORIZED
  
#### Create new user

Resource to create new user with all his information.

* ##### URL:

	< /register >

* ##### Method:

	`POST`
   
* ##### Data Params:
      {
        "name": "Ramon",
        "passwordHash": "2020urjc"
      }
	
* ##### Example of query:

	* URL
		
		`/api/register`  

* ##### Success Response:
  
  ```
  {
    "id": 35,
    "name": "Ramon",
    "passwordHash": "$2a$10$cgCBd5.yy2YCmkYGJraTzuS/Flc.bcqRlJPzEz4NDZppmqSBc0Spe",
    "delivery": {
        "shippingname": "",
        "lastname": "",
        "company": "",
        "number": "",
        "email": "",
        "street": "",
        "floor": "",
        "city": "",
        "country": "",
        "zipcode": "",
        "name": ""
    },
    "orderList": [
        {
            "id": 36,
            "status": null,
            "productList": [],
            "total": 0.0,
            "day": 8,
            "month": 2,
            "year": 2020
        }
    ],
    "roles": [
        "ROLE_USER"
    ]
  }
  ```
  
* ##### Error Response:

	**Code**: 409 CONFLICT or 401 UNAUTHORIZED or 400 BAD REQUEST
  
