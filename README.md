# The Noun Project for Android 

<p align="center">
  <img src= "art/logo.png" >
</p>

[The Noun Project](https://thenounproject.com) is a great resource for finding clip art for use in applications.
The application is built with MVP architectural approach and uses [The Noun Project API](http://api.thenounproject.com/) for fetching data.

## Project Structure
### data 
It contains all the data(Shared Preferences, Local DB and Remote API) accessing and manipulating components
### injection
Dependency providing classes using Dagger2
### events
EventBus events classes that simplifies communication between Activitie and Fragments
### ui
View classes along with their corresponding Presenters
### utils
Utility classes

Main Activity|Icons Activity 
-------------|-----------------
![alt text](art/main.png "Main Activity")  | ![alt text](art/search.png "Search Icons") 


Collections Activity|Detail Activity
-------------|-----------------
![alt text](art/collections.png "Collections") | ![alt text](art/icon_detail.png "Details")  


## Code Style
This sample project uses the IntelliJ IDEA [code style settings for Square's Java and Android projects](https://github.com/square/java-code-styles).

## Contributing

Contributions are welcome to the project. Make pull request and you are IN!

### Reporting Bugs & Bug Fixes

If you find a bug you can report it by using the [issues section](https://github.com/graviton57/TheNounProject-android/issues) for this project. Bug fixes should also be done in pull requests.

### Features & Feature Requests

If there is some feature you want to implement, I invite you to do a pull request.

Feature request should be reported in the [issues section](https://github.com/graviton57/TheNounProject-android/issues) for this project.

## Graphics & Icons

### The Noun Project

[The Noun Project](http://www.thenounproject.com) is the source for some the graphics used in this application. The following users' work was used:

- [Dice](http://thenounproject.com/term/dice/20125/) created by [Derek Palladino](http://thenounproject.com/derekjp/)

### The Noun Project API Keys

To obtain api keys for The Noun Project visit the [Getting Started](http://api.thenounproject.com/getting_started.html) page for additional information

Once you have the API key and secret, replace the placeholder values in: `/api.gradle`.
```groovy
ext {
          
     NOUN_DEV_API_KEY = "PASTE_YOUR_API_KEY";
     NOUN_DEV_SECRET_API_KEY = "PASTE_YOUR_SECRET_API_KEY";
}
 ```

## Library reference resources
- Support library
- [RxJava2](https://github.com/ReactiveX/RxJava), [RxAndroid](https://github.com/ReactiveX/RxAndroid) and [RxBindings](https://github.com/JakeWharton/RxBinding)
- [Dagger2](https://google.github.io/dagger/)
- [Retrofit2](http://square.github.io/retrofit/)
- [Okhttp3](https://github.com/square/okhttp/)
- [GreenDao](http://greenrobot.org/greendao/)
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Greenrobot EventBus](http://greenrobot.org/eventbus/)
- [Lottie-Android](https://github.com/airbnb/lottie-android)
- [Timber](https://github.com/JakeWharton/timber)
- [Fresco - A powerful image downloading and caching library for Android](https://github.com/facebook/fresco/)

## Testing reference resources
- [Mockito](http://site.mockito.org/)
- [Robolectric](http://robolectric.org/) 
- [Travis CI - Continuos integration platform](https://travis-ci.org/)


# License
```
The MIT License (MIT)

Copyright (c) 2017 Igor Havryluik 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
