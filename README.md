# Prueba desarrollada por Rubian Llanos Aguirre
## Capas utilizadas en el desarrollo de la prueba
# api:  
Esta capa contiene la clase ClientFactory y es utilizada para configurar Retrofit. Tambien contiene la interface RestMovieService , en esta interface se configura la respuesta del servicio. 
# app: 
### AppController 
Se encarga de las configuraciones globales de la app como por ejemplo la configuración del contexto, inicializar Realm, RestMovieServicie etc. 
# models: 
Esta capa contiene los modelos utilizados en la app.
### Movie
Esta clase Modelo se encarga de crear un ítem de películas.
### MovieResponse: 
Este modelo se encarga responder una lista del modelo Movie, es decir una lista de películas.
# ui: 
En esta capa se encuentran los Activity y dapters de la aplicación. 
### MainActivity
Se encarga de mostrar los métodos de acceso a la lista de películas desde las categorías. 
![main](https://user-images.githubusercontent.com/38786536/39595061-07029c70-4ed4-11e8-9a4f-7e129e45361b.JPG)
### ListMovieActivity 
Se encarga de mostrar la lista de películas de acuerdo a la categoría elegida desde el MainActivity. 
![lista](https://user-images.githubusercontent.com/38786536/39595252-8c1a5754-4ed4-11e8-989e-1bc5b7ee7ce2.JPG)
### DetailMovieActivity
Se encarga de mostrar el detalle de cada utem de la lista, con datos adicionales y efecto collapsing toolbar.
![detail](https://user-images.githubusercontent.com/38786536/39595287-a4473acc-4ed4-11e8-8ecb-13771764a0fa.JPG)
### MovieAdapter
Se encarga de crear el adaptador para mostrar la lista de películas en ListMovieActivity.
# utils: 
### Constant 
Interface responsable de configurar la BASE_URL del servicio.
### RxSerachObservable 
Es una clase que se encarga de configurar las acciones del buscador que se implementa en la actividad ListMovieActivity. 
### Util
La clase Util se encarga de realizar la validación de conexión a internet.
# viewModel: 
## La capa viewModel contiene varias clases y cada una de ellas tiene una función importante y es la vinculación directa de los datos que se manejan a través de los layouts, (DataBinding). 
### MainViewModel 
Se encarga de dirigir la navegación al ListMovieActivity.
### MovieViewModel 
Es una clase que se encarga de gestionar el modelo Movie y los layouts que se sincronizan a través de databinding , en ella se inicializan las vistas, por ejemplo el toolbar, el recyclerView, el progresbar etc.  Adicional esta clase se encarga de mostrar la lista de peliculas mediante el método fetchMovieList() y si no hay conexión a internet mediante el método getMoviesByCategory(); realizando las respectivas validaciones.
### ItemViewModel
Es una clase que se encarga de crear una instancia del modelo Movie y a su vez hereda de BaseObservable esto con el fin de sincronizar directamente el layout ítem_lis_movie.xml a través del databinding que conecta directamente con los métodos getters and setters de esta clase. 
### MovieDetailViewModel: 
Esta clase tiene la misma responsabilidad de la clase ItemViewModel pero para el layout activity_detail_movie.xml

