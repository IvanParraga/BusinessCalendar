define([
  'jquery',
  'underscore',
  'backbone',
  'views/calendars/list',
], function($, _, Backbone, CalendarListView){
  var AppRouter = Backbone.Router.extend({
    routes: {
      // Define some URL routes
      'calendar': 'showCalendars'
    }
  });

  var initialize = function(){
    var app_router = new AppRouter;
    app_router.on('route:showCalendars', function(){
      console.log(CalendarListView);
      var calendarListView = new CalendarListView();
      calendarListView.render();
    });

    Backbone.history.start();
    console.log("Router initialized");
  };
  return {
    initialize: initialize
  };
});