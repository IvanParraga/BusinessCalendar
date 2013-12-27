define  ([
  'jquery',
  'underscore',
  'backbone',
  'text!templates/calendar/list.html'
], function($, _, Backbone, calendarListTemplate){
  var CalendarListView = Backbone.View.extend({
    el: $('#container'),
    render: function(){
      // Using Underscore we can compile our template with data
      var data = {};
      var compiledTemplate = _.template( calendarListTemplate, data);
      // Append our compiled template to this Views "el"
      this.$el.append( compiledTemplate );
    }
  });
  // Our module now returns our view
  return CalendarListView;
});