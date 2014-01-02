define  ([
  'jquery',
  'underscore',
  'backbone',
  'models/CalendarModel',
  'collections/CalendarCollection',
  'text!templates/calendar/CalendarEdit.html'
], function($, _, Backbone, CalendarModel, CalendarCollection, calendarEditTemplate){
  var CalendarEditView = Backbone.View.extend({
    el: $('#container'),    
    render: function(){
      url = Backbone.history.fragment;
      id = url.split('/')[1];
      calendar = new CalendarCollection().get(id);
      var data = {
        calendar: calendar
      };
      var compiledTemplate = _.template(calendarEditTemplate, data);
      this.$el.append(compiledTemplate);
    }
  });
  return CalendarEditView;
});