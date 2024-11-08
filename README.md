- Instructions on how to run your project
Unzip the project, then open the "Cashapp" app in android studio. To test the different API calls,
uncomment "getValidCashAppData()", "getMalformedCashAppData()", or "getEmptyCashAppData()" in the 
CashAppViewModel.kt init block. Make sure only one is uncommented at a time.

- The architectural approach you took and why
<br>
This project uses an MVVM (Model-View-ViewModel) architecture design. MVVM is typically preferred 
over MVP (Model-View-Presenter) since in MVVM data flow is unidirectional. Data flows from the View,
to the ViewModel, to the Model. The View is subscribed to changes in the ViewModel, and the 
ViewModel is subscribed to changes in the Model. MVVM is also great for configuration changes, as 
the ViewModel class provided by Android caches state and persists it through configuration changes.
It's also great for coroutine scopes, which is helpful since we use coroutine scopes to make our 
API calls.

- The trade offs you made and why
<br>
As mentioned above, I used MVVM because MVVM works well with coroutine scopes. With a couple lines
of code in the ViewModel, we are able to make an API call on a different thread, and update UI
on the main thread.

- 3rd party libraries or copied code you may have used
<br>
3rd party libraries include Compose ViewModel, Compose LiveData, Retrofit, Moshi, 
OkHttp Interceptor, test libraries like Junit, Truth, Mockwebserver, Coroutines Test,
and Core Testing.

- What area(s) did you focus on when working on the project? The architecture and data flow? 
- The UI? Something else?
<br>
I was moderately familiar with compose, viewmodel, and retrofit prior to this project. What I spent
the majority of my time learning was the various architectures and why we use them, as well as the 
different test libraries for testing API calls. I did not spend too much time on the UI, since I was
more focused on learning new skills and getting the actual API calls and tests working.

- Areas that best showcase your skills and expertise
<br>
I believe this project closely matches to meet MVVM architecture, and that the API call tests are
very good.

- Time spent on the project
<br>
~8 hours

- Any additional information you think is relevant for our evaluation
<br>
I was planning to investigate StateFlow as it seems to be flat out superior to LiveData
especially with repeatOnLifecycle api calls, but I'm more familiar with LiveData and we will be 
making extensions to this project so I'd rather stick to stuff I'm familiar with. I was also
looking into MVI (Model-View-Intent), but based on my basic knowledge of MVI it seemed like 
overcomplicating a simple project compared to MVVM.

- Any other information that you would like us to know
N/A
