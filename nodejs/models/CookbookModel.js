var properties  =   require("../conf/Properties");
var Mongoose    =   require("mongoose");
var Schema      =   Mongoose.Schema;

var dbHost  =   properties.get('db.host');
var dbPort  =   properties.get('db.port');
var dbName  =   properties.get('db.name');

Mongoose.connect("mongodb://" + dbHost + ":" + dbPort + "/" + dbName);

var recipeSchema  = Schema({
    _id : { type: Schema.Types.ObjectId, unique: true, required: true, auto: true},
    __v : { type: Number, alias: "version" },
    name: { type: String, unique: true, required: true },
    meal: [{ type: String, enum: ["BREAKFAST", "LUNCH", "DINNER", "SNACK"]}],
    ingredients: Schema.Types.Mixed,
    description: String,
    link: String,
},{
  retainKeyOrder: true,
  timestamps: {
    createdAt: "createDate",
    updatedAt: "updateDate"
  }
});

recipeSchema.set('toJSON', {
  transform: function(doc, ret, options) {
    ret.id = ret._id;
    ret.version = ret.__v
    delete ret._id;
    delete ret.__v;
  }
});

module.exports = Mongoose.model('recipes', recipeSchema);
