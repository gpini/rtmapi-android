Remember the Milk API Wrapper for Android and Java.

# Overview
This Remember the Milk API Kit has been developed for use in Android projects, using Android core libraries. It can be used also in Java code, importing the missing libraries.

# Support
If you have a question, a suggestion, an issue, if you are interested in contributing to the project or simply you want to tell that you are using this tool, please join the discussions in  [Google Groups](https://groups.google.com/forum/#!forum/rtmapi-for-androidjava)

# Getting Started
Jar file is available in [jar directory](https://github.com/gpini/rtmapi-android/tree/master/jar).
For usage you can refer to below quick start and to [Javadoc](https://github.com/gpini/rtmapi-android/tree/master/jar), but here follows a brief how-to.

## How to use in Android
If you are using Android, just import the JAR in your project. Then import the library with:
```
import it.bova.rtmapi;
```

## How to use in Java
If you want to use the library in Java you must also import the dependencies (JSON and HTTP-Apache libraries) that can be found in [lib folder](https://github.com/gpini/rtmapi-android/tree/master/jar)

If you want the most updated libraries see official JSON and Apache websites.

## Authentication
```
    RtmApiAuthenticator authenticator = new RtmApiAuthenticator("myAppApiKey1234","myAppSharedSecret5678");
    String frob = authenticator.authGetFrob();
    String validationUrl = authenticator.authGetDesktopUrl(Permission.DELETE,frob);
```
use the url to give the permission to the application, then come back here
```
    Token authToken = authenticator.authGetToken(frob);
```
## Use the API
Token object can be passed as argument
```
    RtmApi api = new RtmApi("myAppApiKey1234","myAppSharedSecret5678", tokenObject);
```
or token String
```
    RtmApi api = new RtmApi("myAppApiKey1234","myAppSharedSecret5678", tokenString);
    List<Task> tasks = api.tasksGetList();
    List<TaskList> lists = api.listsGetList();
```
A lot of methods are available in two types:
one taking RtmObject argument
```
    TaskList listZero = lists.get(0);
    listZero = api.listsSetName(listZero,”new name”);
```
and the other one with ID argument 
```
    String listId = listZero.getId();
    listZero = api.listsSetName(listId,”new name”);
```
## Transactions
Transactions can be managed through _RtmApiTransactable_ class. This class contains all the standard API methods, including transaction info where available and implementing the method _transactionsUndo(String timeline, Transaction<?> transaction)_
```
    RtmApiTransactable api = new RtmApiTransactable("myAppApiKey1234","myAppSharedSecret5678", TokenString);
    String timeline = api.timelinesCreate();
```
Not undoable transaction
```
    Transaction<TaskList> listTransaction = api.listsAdd(timeline, "New List To Be Cancelled");
    TaskList list = listTransaction.getObject();
    Boolean isUndone = api.transactionsUndo(timeline, listTransaction ); //returns false (adding operation is not undoable)
```
Undoable transaction
```
    Transaction<TaskList> listTransaction2 = transApi.listsDelete(timeline, list1);
    isUndone = api.transactionsUndo(timeline, listTransaction2 ); //return true
```

