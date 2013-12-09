define([
  'underscore',
  'backbone',
  'models/calendar'
], function(_, Backbone, CalendarModel){
  var CalendarCollection = Backbone.Collection.extend({
    model: CalendarModel
  });
  // You don't usually return a collection instantiated
  return ProjectCollection;
});