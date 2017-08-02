var express             = require('express');
var app                 = express();
var bodyParser          = require("body-parser");
var router              = express.Router();
var cookbookController  = require("./controllers/CookbookController");
var port                = 3001;

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  "extended": false
}));

router.route("/")
  .get(cookbookController.getCookbook)
  .delete(cookbookController.deleteCookbook)
  .post(cookbookController.postRecipe);

router.route("/:id")
  .get(cookbookController.getRecipe)
  .delete(cookbookController.deleteRecipe);

app.use('/rest/cookbook', router);

app.listen(port)

console.log('RESTful API server started on: ' + port);
