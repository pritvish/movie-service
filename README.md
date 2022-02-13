# movie-service
Application to perform CRUD operations on movies database

1. Creating movies
2. Updating movies
3. Fetching all movies
4. Fetch movies based on year
5. Fetch movies based on ratings 

The Movie object will have 3 main attributes - name, year & rating.

APIs : 

1. To insert a new movie into the database
`POST : {domain}/moviedb/v1/insert-movie
sample body : 
{
    "name":"Sample Movie",
    "year":"2022",
    "rating":"5"
}`

2. To Update an existing movie present in the database

`PUT : {domain}/moviedb/v1/update-movie?id={id}`

`sample body :
{
"name":"Sample Movie",
"year":"2022",
"rating":"5"
}`

3. To fetch all movies
`GET : {domain}/moviedb/v1/movies`

4. To fetch movies by year
`GET : {domain}/moviedb/v1/moviesByYear/{year}`

5. To fetch movies by rating
`GET : {domain}/moviedb/v1/moviesByRating/{rating}`
