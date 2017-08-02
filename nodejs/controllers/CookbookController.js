var cookbookModel = require("../models/CookbookModel");

exports.getCookbook = function(req, res) {
  var response = {};

  cookbookModel.find({}, function(err, data) {
    if (err) {
      response.message = err.message;
    } else {
      response = data;
    }
    res.json(response);
  })
}

exports.postRecipe = function(req, res) {
  var recipe = new cookbookModel(req.body);
  var response = {};

  console.log("Add recipe: " + recipe);

  recipe.save(function(err) {
    if (err) {
      response.message =  err.message;
    } else {
      response = recipe
      res.status(201)
    }
    res.json(response);
  })
}

exports.deleteCookbook = function(req, res) {
  var response = {};

  cookbookModel.remove({}, function(err, data) {
    if (err) {
      response.message = err.message;
    } else {
      response.message = "Removed all recipes";
    }
    res.json(response);
  })
}

exports.getRecipe = function(req, res) {
  var id = req.params.id;
  var response = {};

  console.log("Get recipe with id: " + id);

  cookbookModel.findById(id, function(err, data) {
    if (err) {
      response.message =  err.message;
    } else if (!data) {
      response.message =  "Recipe with id: " + id + " not found";
      res.status(404);
    } else {
      response = data;
    }
    res.json(response);
  })
}

exports.deleteRecipe = function(req, res) {
  var id = req.params.id;
  var response = {};

  cookbookModel.findByIdAndRemove(id, function(err, data) {
    console.log("RESULT: " + data);
    if (err) {
      response.message =  err.message;
    } else if (!data) {
      response.message =  "Recipe with id: " + id + " not found";
      res.status(404);
    } else {
      response.message = "Removed recipe with id: " + id;
    }
    res.json(response);
  })
}
