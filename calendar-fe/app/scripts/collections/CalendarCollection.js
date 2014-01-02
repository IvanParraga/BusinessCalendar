define([
  'underscore',
  'backbone',
  'models/CalendarModel'
], function(_, Backbone, CalendarModel){
  var CalendarCollection = Backbone.Collection.extend({
    model: CalendarModel,
    url: 'server/calendar'
  });
  // You don't usually return a collection instantiated
  return CalendarCollection;
});