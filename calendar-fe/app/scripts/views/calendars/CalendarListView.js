define  ([
  'jquery',
  'underscore',
  'backbone',
  'models/CalendarModel',
  'collections/CalendarCollection',
  'text!templates/calendar/CalendarList.html'
], function($, _, Backbone, CalendarModel, CalendarCollection, calendarListTemplate){
  var CalendarListView = Backbone.View.extend({
    el: $('#container'),
    render: function(){
      var data = {
        calendars: [{name:'a',year:1999}]
      };
      var compiledTemplate = _.template(calendarListTemplate, data);
      this.$el.append(compiledTemplate);
    }
  });
  return CalendarListView;
});