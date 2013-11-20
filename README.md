AndroidDataBaseExample
======================
This application will give you idea regarding all types of data storage in android. This will help's you to find out where and how to use what kind of data storage.Android mainly provide 4 types data storage.

1. Shared Preferences.
2. Internal data storage.
3. External data storage.
4. SQLite  

We will go through one by one. And also will see pros and corns of each.

* Shared Preferences: It is most simple and efficient way to store local data for your android application. This allows you to store and associate various Key-Value pair with your application.Each application associated with its own shared preferences class and stored data persists across your application.Shared preferences allow you to save only primitive data types like int, Boolean, float etc...

example of usage of shared preferences: Remembering user already logged in to your application. In this case we can store simple variable inside your application shared preferences. So that your application come to know that user is already logged in. 

* Internal data storage: Android simply allows you to read and write to files that are associated with each application's internal memory.These files access by owner application only not by any other application.Data remain private for single application only and also when application uninstalled;these files automatically removed as well.

example usage of internal storage: consider user store few file like xml files but he don't want those files when he remove that application then such files stored into applications internal memory. Such data remain valid for only that application only not open for other applications.

* External data storage: When you want to store large amount data and data security is not that much important for that data then you can store data on external data storage like SD card. File store on SD card by any application can access by any other application. Also those files remain on SD card even you uninstall application.

Example usage of external storage: Consider u build android application which will download wallpapers or images and user want those images even user uninstall that application. In such cases you can save those files on SD card so that those will persist even user uninstall application.

Pros and Corns :
In shared preferences writing and reading from shared preferences is very simple and efficient but it store only simple data types/ it will store large data files like xml files or much complicated files like media files. 

Internal storage provide small about of storage space to store data. also it uses applications internal memory.
data safety is more cause data files expose to owner application only not to other application. 

External storage provides large amount of space to store data. use can store data in GB also for particular application and its much greater than internal storage.
data safety is less cause data expose to every one and any one can read or write. 

* SQLite databases: Most powerful local data storage method in android is SQLite data storage.Each application can create own sqlite database for there own purpose and it is accisible to that application only not any other outside application. But owner application can allow to expose there data to other application as well.This is relational data base where you can store your data in structured table format.
You will loose all data if your uninstall application.

Example of SQLite database : consider you are building application which shows list of restaurants so in this case you can create restaurants table and can stores list restaurants locally. 


