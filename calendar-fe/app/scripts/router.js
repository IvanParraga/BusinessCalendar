define([
  'jquery',
  'underscore',
  'backbone',
  'views/calendars/CalendarListView',
  'views/calendars/CalendarEditView',
], function($, _, Backbone, CalendarListView, CalendarEditView){
  var AppRouter = Backbone.Router.extend({
    routes: {
      // Define some URL routes
      'calendars': 'showCalendars',
      'edit_calendar/:id': 'editCalendar'
    }
  });

  var initialize = function(){
    var app_router = new AppRouter;

    app_router.on('route:showCalendars', function(){
      var calendarListView = new CalendarListView();
      calendarListView.render();
    });

    app_router.on('route:editCalendar', function(){
      var calendarEditView = new CalendarEditView();
      calendarEditView.render();
    });

    Backbone.history.start();
    console.log("Router initialized");
  };
  return {
    initialize: initialize
  };
});