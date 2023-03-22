# MusicWiki App
An Android app which let us browse information related to music like genres, albums, artists, tracks by using MVVM + Retrofit + Navigation Components

## Features
 #### - Shows a wide range of genres, almost all music genres to browse from
 #### - More detailed information about the genre choosen by user like genre description, its albums and artists
 #### - More detailed information about the choosen album or artist
 #### - Multiple Genre tags in each album since single album can have multiple genre music
 
## Key Decesions
 #### - Single Activity Applicaiton 
 #### - Use of Navigation Components as the application is based on single activity multiple fragments
 
## Assumptions Made 
 #### - The app should be completely online which means not using room DB and saving anything locally. This was not clearly mentioned in the assignment however its a bad practice to always make api calls
        
## Results

### Landing Page with Genres Collapsed


<img width="342" alt="genre collapsed" src="https://user-images.githubusercontent.com/71667923/227030056-09fcbf50-eb6e-4dd4-b737-206bc515d7d9.png">

##

### Landing Page with Genres Expanded

<img width="342" alt="genre expanded" src="https://user-images.githubusercontent.com/71667923/227030337-082ef6dc-3505-422a-b2a3-cfa3b940f5ad.png">

##

### Genre Detail Info Page with albums and artists with each item having their images titles, artist names

<img width="342" alt="genre detail" src="https://user-images.githubusercontent.com/71667923/227030988-571dc8fd-b0e0-47c6-b08e-4f7ea06dbaac.png">

##

### Album Detail page with the top genres related to album and other information. The Album description is nowhere to find in the API

<img width="343" alt="album info" src="https://user-images.githubusercontent.com/71667923/227031343-087a2599-1dc0-4dbc-94d6-d3397e86cb2c.png">



