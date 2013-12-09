define([
  'jquery',
  'underscore',
  'backbone',
  'views/calendars/list',
], function($, _, Backbone, ProjectListView, UserListView){
  var AppRouter = Backbone.Router.extend({
    routes: {
      // Define some URL routes
      '/calendar': 'showCalendars',

      // Default
      '*actions': 'defaultAction'
    }
  });

  var initialize = function(){
    var app_router = new AppRouter;
    app_router.on('showCalendars', function(){
      var calendarListView = new CalendarListView();
      calendarListView.render();
    });
    app_router.on('defaultAction', function(actions){
      // We have no matching route, lets just log what the URL was
      console.log('No route:', actions);
    });
    Backbone.history.start();
  };
  return {
    initialize: initialize
  };
});