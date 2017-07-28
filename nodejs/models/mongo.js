var mongoose    =   require("mongoose");
mongoose.connect('mongodb://192.168.9.22:27017/rocketDB');

var mongoSchema =   mongoose.Schema;
var userSchema  = {
    "id" : String,
    "version" : String,
    "name": String
};

module.exports = mongoose.model('recipes',userSchema);
