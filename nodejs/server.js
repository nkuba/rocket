var express = require('express');
var app = express();
var fs = require("fs");
var port = process.env.PORT || 3000;

var mongodb = require('mongodb');
var MongoClient = mongodb.MongoClient
var url = "mongodb://192.168.9.22:27017/rocketDB";
var tableName = "recipes"

// Create DB
MongoClient.connect(url, function(err, db) {
    if (err) throw err;

    db.createCollection(table, function(err, res) {
        if (err) throw err;
        console.log("Table created: " + tableName);
        db.close();
  });
});

// Connect to the db
MongoClient.connect("mongodb://localhost:27017/exampleDb", function(err, db) {
  if(!err) { console.log("We are connected"); }

});

app.get('/listUsers', function (req, res) {
   fs.readFile( __dirname + "/" + "users.json", 'utf8', function (err, data) {
       console.log( data );
       res.json(JSON.parse(data));
   });
})

var user = {
   "user4" : {
      "name" : "mohit",
      "password" : "password4",
      "profession" : "teacher",
      "id": 4
   }
}

app.post('/addUser', function (req, res) {
   // First read existing users.
   fs.readFile( __dirname + "/" + "users.json", 'utf8', function (err, data) {
       data = JSON.parse( data );
       data["user4"] = user["user4"];
       console.log( data );
       res.json(data);
   });
})

var server = app.listen(port)

console.log('todo list RESTful API server started on: ' + port);
