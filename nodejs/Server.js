var Express             =   require('express');
var app                 =   Express();
var Router              =   Express.Router();
var BodyParser          =   require("body-parser");
var CookbookController  =   require("./controllers/CookbookController");
var Properties          =   require("./conf/Properties");
var port                =   Properties.get("main.port");

app.use(BodyParser.json());
app.use(BodyParser.urlencoded({"extended": false}));

Router.route("/")
  .get(CookbookController.getCookbook)
  .delete(CookbookController.deleteCookbook)
  .post(CookbookController.postRecipe);

Router.route("/:id")
  .get(CookbookController.getRecipe)
  .delete(CookbookController.deleteRecipe);

app.use('/rest/cookbook', Router);

app.listen(port)

console.log('ROCKET API server started on port: ' + port);
