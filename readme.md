#JBBP - Java BerkeleyDB Based Persister

This is a persister using [Java BerkeleyDB](http://www.oracle.com/technetwork/database/berkeleydb/overview/index-093405.html).  

BerkeleyDB is a key-value DB, supporting full ACID transactions, DPL Java access, multi-threading model but single process based, keyed and sequential data retrieval, and so on.   
    
However, using BerkeleyDB requires some annoying boilerplate codes in order to setup a production environment, so I wrote JBBP, a proxy/wrapper for BerkeleyDB under the hood.  

### Some examples:

* create: 
```
MyUser myUser = ...;
persister.create(myUser);
```
* update:
```
MyUser myUser = ...;
persister.update(myUser.getId(), myUser);
```
Agreeing with BerkeleyDB architecture, Value object, properly annotated, has to provide a unique id (for internal keys mapping), so MyUser could be something as:
```
@Entity
public class MyUser {
    @PrimaryKey
    private String id;

    ...
}
```
(Only native and String type are supported for PrimaryKey)

* sequential retrieve (using Java8 lambdas): 
```
persister.valuesIterator()
.forEachRemaining( a->myMap.put(a.getUid(), a) );
```

<br/><br/>

#### Setup How-To
#####1
Extends Helper (inheriting BerkeleyDBAutoCommittableHelper or BerkeleyDBTransactionableHelper):
```
// first generics parameter is String => Key type is String
public class YourHelper<Y extends YourValueObject> 
extends BerkeleyDBAutoCommittableHelper<String,Y> { // <- note "String" type
    public YourDBAutoCommittableHelper(String yourCorpusName, String yourSiloName) { ... }
...
}
```
Using AutoCommittableHelper each operations (create/put/delete) will be persisted immediately; using TransactionableHelper you must begin your persisting area codes with `persister.beginTransaction();` and end with `persister.commit();`



#####2
Extends Persister, specifying key and value type (key is first parameter, value the second one):
```
public class YourPersister<Y extends YourValueObject> extends Persister<String, Y> {
    public YourPersister(YourHelper<Y> yourHelper, Class<Y> valueClass) {
        // second parameter is String, because key type is string, coherently with Helper
    	super(yourHelper, String.class, valueClass);
    }
}
```

#####3
Use persister as showed in examples ;D

<br/><br/>
ps: obviously, you could use [Guice](https://github.com/google/guice) to inject dependencies easily.
  
  
Enjoy it ;D !
