define  ([
  'jquery',
  'underscore',
  'backbone',
  'models/CalendarModel',
  'text!templates/calendar/CalendarList.html'
], function($, _, Backbone, CalendarModel, calendarListTemplate) {
  var CalendarListView = Backbone.View.extend({
    el: $('#container'),
    render: function() {
      var data = {
        calendars: this.options.calendars.toJSON()
      };
      var compiledTemplate = _.template(calendarListTemplate, data);
      this.$el.append(compiledTemplate);
    },    
  });

  return CalendarListView;
});

