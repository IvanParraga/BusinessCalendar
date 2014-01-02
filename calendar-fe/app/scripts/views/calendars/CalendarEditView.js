define  ([
  'jquery',
  'underscore',
  'backbone',
  'models/CalendarModel',
  'text!templates/calendar/CalendarEdit.html'
], function($, _, Backbone, CalendarModel, calendarEditTemplate){
  var CalendarEditView = Backbone.View.extend({
    el: $('#container'),    
    render: function(){
      url = Backbone.history.fragment;
      id = url.split('/')[1];
      calendar = this.options.calendars.get(id);
      var data = {
        calendar: calendar
      };
      var compiledTemplate = _.template(calendarEditTemplate, data);
      this.$el.append(compiledTemplate);
    }
  });
  return CalendarEditView;
});