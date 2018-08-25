var options = {
    "datePrefix": '__DATE:',
    "fieldValueDelete": "__DELETE",
    "fieldValueServerTimestamp" : "__SERVERTIMESTAMP",
    "persist": true,
    "timestampsInSnapshots" : false,
    "config" : {

    }
  };

 

  Firestore.initialise(options).then(function(database) {
    myDatabaseReference = database;
    console.log('alert')
    console.log(database)
  });
