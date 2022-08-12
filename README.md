# The song app list !

This sample app loads, stores and display a list of songs. The goal is to showcase the architecture, libraries and patterns I am able to use.
It took me approximately 5 full working days to build it. With more time, I could add some improvement (cf Nice to have)

## Architecture
### MVVM
The application uses an MVVM architecture which is the most common one (in Android apps), the basic components of this architecture are :
- The model: Containing the data of the application
- The view: What will be shown on the screen, what the user will interact with
- The viewmodel: acting like a bridge between the model and the view. It will be able to get the data and transform it so it can be used by the view. The view will be subscribed to it to be updated when needed.

MVVM is the recommended pattern for Android app.

### Modules
In order to respect the "separation of concerns" concept, this app is separated in 3 modules : 
- The data module, exposing repositories and handling how the data is retrieved.
- The domain module, supposed to encapsulate business logic (not so important in this current app as it is very simple but still!).
- The ui module, displaying the data on the screen, containing all the Android specific related code.

### Flow
I chose to use Kotlin Flow (over live data / Rx) for async tasks.
I prefer Flow over LiveData mostly because LiveData is bound to Android while we can use Flow everywhere as it comes from Kotlin (like in our domain and data modules). 
Rx is a great reactive solution too, I simply had to make a choice ;)

## Libraries
### Hilt
Hilt (dagger) for the dependency injection, reducing boilerplate code and making testing easier. (Alternative I used : Koin)

### MockK
MockK in order to mock classes while testing. (Alternative: Mockito)

### Espresso
Espresso to UI test the app, unfortunately I didn't have enough time to implement proper UI tests, I only set up en environment... 
You can find a basic UI test in songs\app\src\androidTest\java\com\pierre\songs\AppUiTests.kt

### Retrofit (and Gson)
Probably the best REST client to easily implement network calls.
Works super well with Gson, to serialize and deserialize classes in Json

### Room
A local storing library allowing us to use SQL database for our local data. My implementation may not be perfect as I never had the opportunity to use it before!

### Glide
Helping to load images from url (And transforms it if we need)


## Working flow
### Git Flow
I am used to work in a team following git flow concepts, it means defining some specific branches with roles :
- master : the production branch, containing the code currently in production.
- dev : the branch on which we implement new features, contains the latest development changes.
- features : we create feature branches from dev to implement new features that will then be merged on dev (see name convention below).
- release : When ready we build a release candidate from dev and we start some rollout (alpha, beta... depending on the strategy). Once the rollout is completed, we merge it on master and dev.
- hotfix : If we need to fix a bug quickly, we can branch from master, do our changes and merge back on master and dev.

We usually tag branches with some convention name in order to trigger actions on the CI.

### Convention
In this project, the feature branches have been named : 'ANDX-name-of-feature'
The goal is to have a unique 'X' id for every feature so we keep track of who is working on which issue and what is the status (When working in agile team with a kanban board)


## Nice to have
Here is a list of things I would like to add if I could spend more time on this project : 

### CICD - channels
Implement a CICD tool (Jenkins, Bitrise...) in order to build and test and deploy the app efficiently.
We could also configure some channels (Alpha, Beta) to have some internal or external testing on each features / releases

### Keystore
The app doesn't have any keystore strategy while it is very important for an Android app. Imo the best solution is to let the CICD handle the Keystore so only it can build a release of the app.

### A cache strategy
Our SongsRepository could have a cache strategy that would reload the data if something changes or every X days...

### Some logging and event tools
An event tool is always useful to understand how our users interact with the app.
We can use pre built tools like Firebase to centralize and monitor the user actions.

### Codestyle
Define some common codestyle rules for readability, consistency

### Lint
Add some lint checks to avoid mistakes

### Better UI and Unit test
There aren't any UI tests and unit tests are mainly testing the flows without testing the correct data stream are passed

### Test Room
Test room using https://developer.android.com/training/data-storage/room/testing-db

### UI
A more elaborated UI (I know.... x) ), a more adaptive dark mode, some animation (maybe using Lottie?) and a real launcher icon :D 


Thank you for your time, reading this and the code!!!
