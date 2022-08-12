# A songs app that lists songs 


The data is stored in room, I am still learning room so my implementation can probably be improved!

//todo explain 
- add architecture choice (MVVM) and why
- add library choices and why
- choice of flow over RX / LiveData
- Explain 5 days work / nice to have
- Explain git flow 
- Explain convention branch name etc

to add -
- clean libs
- Config change
- tests
- tests room https://developer.android.com/training/data-storage/room/testing-db
- comment in code
- the domain layer may not be useful in this app, in a real app, we could do some operation there like aggregating data from various repo, transforming it etc


// nice to have
- elaborated Dark mode
- CI CD, alpha /beta channel, runs the test, 
- keystore handling with CICD
- code style for better readability 
- cache strategy
- A waaaaaay better UI
- Some animation (lottie)
- Some event bus and tracker like firebase 
- leak canary only in debug
- some better unit tests, now testing the flows, should also test correct data stream / mapping