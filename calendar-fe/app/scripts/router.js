define([
  'jquery',
  'underscore',
  'backbone',
  'collections/CalendarCollection',
  'views/calendars/CalendarListView',
  'views/calendars/CalendarEditView',
], function($, _, Backbone, CalendarCollection, CalendarListView, CalendarEditView){
  var AppRouter = Backbone.Router.extend({
    routes: {
      // Define some URL routes
      'calendars': 'showCalendars',
      'edit_calendar/:id': 'editCalendar'
    }
  });

  bootstrapCallendars = function() {
    calendarCollection = new CalendarCollection();

    $.ajax({
        url: calendarCollection.url,
        success: function(result) {
          calendarCollection.reset(result);
        },
        async:   false
    });  

    return calendarCollection;
  }

  var initialize = function(){
    var app_router = new AppRouter;
   
    calendarCollection = bootstrapCallendars();

    app_router.on('route:showCalendars', function(){
      var calendarListView = new CalendarListView({calendars: calendarCollection});
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