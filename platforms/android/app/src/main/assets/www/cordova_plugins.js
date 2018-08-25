cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
  {
    "id": "cordova-plugin-firestore.Firestore",
    "file": "plugins/cordova-plugin-firestore/www/firestore.js",
    "pluginId": "cordova-plugin-firestore",
    "clobbers": [
      "Firestore"
    ]
  }
];
module.exports.metadata = 
// TOP OF METADATA
{
  "cordova-plugin-firestore": "1.3.0"
};
// BOTTOM OF METADATA
});