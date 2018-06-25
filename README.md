# IMDB-Search-Engine
A simple program that lets you search for movies and TV series in IMDB.
It consists of two parts - a client and a multithreaded server.

Once connected to the server, the client can send the following commands: 

     - "get-movie [movie-name] --fields [field1, field2, ...]" where the -fields is optional; if the --fields option is provided, only the specified fields are printed as a result, if not - all of the information; if the movie has been previously searched, then the information is taken from the server's cache;

     - "get-tv-series [tvSeriesName] --season [number]" - all episodes of the specified series' season are listed; if the season has been previously searched, the information about the episodes is taken from the server's cache, rather than downloaded;

    - "get-movie-poster [movieName]" - the movie poster of a previously searched movie is downloaded firstly on the server (if it's not already in the server's cache) and then sent to the client;
