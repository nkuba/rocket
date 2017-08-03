var cookbookModel = require("../models/CookbookModel");

exports.getCookbook = function(req, res) {
  cookbookModel.find({}, function(err, data) {
    if (err) {
      response = responseMessage(err.message);
    } else {
      response = data;
    }
    res.json(response);
  })
}

exports.postRecipe = function(req, res) {
  var recipe = new cookbookModel(req.body);

  console.log("Add recipe: " + recipe);

  recipe.save(function(err) {
    if (err) {
      response = responseMessage(err.message);
    } else {
      response = recipe
      res.status(201)
    }
    res.json(response);
  })
}

exports.deleteCookbook = function(req, res) {
  cookbookModel.remove({}, function(err, data) {
    if (err) {
      response = responseMessage(err.message);
    } else {
      response = responseMessage("Removed all recipes");
    }
    res.json(response);
  })
}

exports.getRecipe = function(req, res) {
  var id = req.params.id;

  console.log("Get recipe with id: " + id);

  cookbookModel.findById(id, function(err, data) {
    if (err) {
      response = responseMessage(err.message);
    } else if (!data) {
      response = responseMessage("Recipe with id: " + id + " not found");
      res.status(404);
    } else {
      response = data;
    }
    res.json(response);
  })
}

exports.deleteRecipe = function(req, res) {
  var id = req.params.id;

  cookbookModel.findByIdAndRemove(id, function(err, data) {
    console.log("RESULT: " + data);
    if (err) {
      response = responseMessage(err.message);
    } else if (!data) {
      response = responseMessage("Recipe with id: " + id + " not found");
      res.status(404);
    } else {
      response = responseMessage("Removed recipe with id: " + id);
    }
    res.json(response);
  })
}

function responseMessage( msg ) {
  return { "message" : msg }
}
