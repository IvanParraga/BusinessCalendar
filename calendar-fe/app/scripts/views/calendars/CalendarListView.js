define  ([
  'jquery',
  'underscore',
  'backbone',
  'models/CalendarModel',
  'collections/CalendarCollection',
  'text!templates/calendar/CalendarList.html'
], function($, _, Backbone, CalendarModel, CalendarCollection, calendarListTemplate) {
  calendars = new CalendarCollection().fetch();
  var CalendarListView = Backbone.View.extend({
    el: $('#container'),    
    render: function(){
      var data = {
        calendars: calendars
      };
      var compiledTemplate = _.template(calendarListTemplate, data);
      this.$el.append(compiledTemplate);
    }
  });
  return CalendarListView;
});