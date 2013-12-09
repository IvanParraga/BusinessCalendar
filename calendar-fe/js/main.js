require.config({
  paths: {
    jquery: 'http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery',
    underscore: 'http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.5.2/underscore',
    backbone: 'http://cdnjs.cloudflare.com/ajax/libs/backbone.js/1.1.0/backbone',
    templates: '../templates'
  }
});

require([
  // Load our app module and pass it to our definition function
  'app',
], function(App){
  // The "app" dependency is passed in as "App"
  App.initialize();
});