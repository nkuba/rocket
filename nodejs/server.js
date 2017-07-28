var express     =   require('express');
var app         =   express();
var bodyParser  =   require("body-parser");
var router      =   express.Router();
var port        =   3001;

//  https://codeforgeek.com/2015/08/restful-api-node-mongodb/

var mongoose    =   require("mongoose");
mongoose.connect('mongodb://192.168.9.22:27017/rocketDB');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({"extended" : false}));

router.get("/",function(req,res){
    res.json({"error" : false,"message" : "Hello World"});
});


app.use('/',router);

var server = app.listen(port)

console.log('RESTful API server started on: ' + port);
