var mongoose  =   require("mongoose");
var Schema    =   mongoose.Schema;

mongoose.connect('mongodb://192.168.9.22:27017/rocketDB');

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

module.exports = mongoose.model('recipes', recipeSchema);
