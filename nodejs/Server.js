var express     =   require('express');
var app         =   express();
var bodyParser  =   require("body-parser");
var router      =   express.Router();
var mongoOp     =   require("./models/mongo");

var port        =   3000;

//  https://codeforgeek.com/2015/08/restful-api-node-mongodb/

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({"extended" : false}));

router.get("/",function(req,res){
    res.json({"message" : "Hello World"});
});

router.route("/cookbook")
  .get(function(req, res) {
    var response = {};
    mongoOp.find({}, function(err, data) {
      if(err) {
        response = {"message" : "Error fetching data"};
      } else {
        response = data;
      }
      res.json(response);
    })
  })
  .post(function(req, res) {
    var db = new mongoOp();
        var response = {};
        db.name = req.body.name;
        db.save(function(err) {
          if (err) {
            response = {"message" : "Error adding data"};
          } else {
            response = {"message" : "Recipe added"}
          }
          res.json(response);
        })
  })

app.use('/',router);

app.listen(port)

console.log('RESTful API server started on: ' + port);
