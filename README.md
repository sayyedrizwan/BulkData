BulkData
======

<!--See the [project website][BulkData] for documentation and APIs. -->

Data is a modern way to run applications and create better user experience. It’s how we grow and interact with customers.

BulkData is an Data injection:

 * Insert ∞ numbers of data.
 * Return data in json format to test or deploy as per your need.
 * Work offline so no need to wait for network connection.

BulkData inject data in local storage, This help to get data, run or query as per your requirement.
BulkData support `16+ api` level any device api level more than `Jelly Bean 4.1` is supported.

<br/>
Using BulkData is easy. Its get/post data is designed with fluent builders and immutability.

Note:- Callbacks will be added and make avaliable in upcoming releases.


<br/>

Gradle Dependency
---------

```gradle
repositories {
	...
	maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.sayyedrizwan:BulkData:1.0.2'
}
```

Maven Dependency
---------

```gradle
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
	<groupId>com.github.sayyedrizwan</groupId>
	<artifactId>BulkData</artifactId>
	<version>Tag</version>
</dependency>
```

<br/>

Make / Add New Columns
---------

Create a structure, So data and be insert and viewed.

Kotlin

```kotlin
val arrayTableName = ArrayList<String>()
    arrayTableName.add("name")
    arrayTableName.add("emailid")
    arrayTableName.add("place")
    arrayTableName.add("interest")
    // add fields you want

BulkData.makeNewTableColumn(this, arrayTableName)
```


<br/>

Alter / Add New Columns
---------

Alter the structure, So data and be insert and viewed.

Kotlin

```kotlin
val arrayTableName = ArrayList<String>()
    arrayTableName.add("name")
    arrayTableName.add("emailid")
    arrayTableName.add("place")
    arrayTableName.add("interest")
    // add fields you want

BulkData.makeTableColumn(this, arrayTableName)
```

<br/>

Insert Data
---------

Insert data or any amount of data you want to add.

```kotlin
BulkData.insertData(this, 999,
        "'Rizwan Sayyed', 'sayyedrizwanahmed@gmail.com', 'mumbai India', 'skating'")

/* the first field is of context */
/* the second field is of number of value that you want to insert */
/* the third field is of data you want to insert. For eg:-
    If you have create a arraylist in which first column is name, second is emailid, third is place. So 
    
    you will create value like "'Rizwan Sayyed'(name), 'sayyedrizwanahmed@gmail.com' (EmailId), 'Mumbai' (Place)"
    
    It will go like ("'Rizwan Sayyed', 'sayyedrizwanahmed@gmail.com', 'Mumbai'") in third field. */
```


<br/>

Get whole data in JSON format
---------

Return whole add/inserted data in json format.

Kotlin

```kotlin
BulkData.getData(this)
```
<br/>

Get data with as per request
---------

Return whole add/inserted data using query.

Kotlin

```kotlin
BulkData.getData(this, "ORDER BY name LIMIT 10")
```
<br/>


Clear Data
---------

Clear all save data

Kotlin

```kotlin
BulkData.clearAllData(this)
```
<br/>

Drop all data
---------

Droping data with clear all Primary key start new data insert.

Kotlin

```kotlin
BulkData.dropData(this)
```
<br/>

Get Columns Name
---------

Return all Columns name under which data is saved.

Kotlin

```kotlin
BulkData.getColumnsName(this)
```
<br/>

Requirements
------------

BulkData works on Android 4.1+ (API level 16+).

BulkData depends on [Kotlin standard library][kotlin].

We highly recommend you keep BulkData up-to-date.


<br/>

License
-------

```
Copyright 2020 sayyedrizwan.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
