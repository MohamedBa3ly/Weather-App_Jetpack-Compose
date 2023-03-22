# Weather-App_Jetpack-Compose
 provide a national and local weather forecast for cities.
 
## Project Description
1. Search screen, user can enter any city. 
2. Home Screen, display weather forecast for city.
3. Add Screen, user can add multiple cities.

## Screenshots
<img src="https://user-images.githubusercontent.com/103647107/226849071-07a63805-82ec-4b00-9147-09b2da947ee4.png" width="330" height="600" >
<img src="https://user-images.githubusercontent.com/103647107/226849302-7eb91ed7-57ea-4323-ac66-50512b122861.png" width="330" height="600" >
<img src="https://user-images.githubusercontent.com/103647107/226849323-71ae27a2-cdd7-40b5-90ac-3e5175e08662.png" width="330" height="600" >

## Architecture
1. Jetpack Compose is used for building UI.
2. MVVM (Model-View-ViewModel) Architecture is used in this project.
3. Dependency injection with Hilt.
4. Use the property delegate created by preferencesDataStore to create an instance of Datastore<Preferences>. Call it once at the top level of your kotlin file, and access it through this property throughout the rest of your application.
5. Save data in a local database using Room.

<img src="https://user-images.githubusercontent.com/103647107/226852362-e900c44f-6484-4b9e-9741-d095380210bc.png" >

## Installation
Clone the app using Git or import it to Android Studio.
