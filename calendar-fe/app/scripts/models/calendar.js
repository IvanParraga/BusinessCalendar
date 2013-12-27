define([
  'underscore',
  'backbone'
], function(_, Backbone){
  var CalendarModel = Backbone.Model.extend({
    defaults: {
      name: "foo",
      year: 2014
    }
  });
  // Return the model for the module
  return CalendarModel;
});