Fixture = Backbone.Model.extend({

	urlRoot : BASE_REST_URL + "fixtures",
	validate : function(attributes) {
		
	},	
	initialize : function() {			
	}
});

var Fixtures = Backbone.Collection.extend({
  url : BASE_REST_URL + "fixtures",
  model: Fixture
});

var FixtureView = Backbone.View.extend({

	  el: '#main_container',

	//  events: {
	//    "click .icon":          "open",
	//    "click .button.edit":   "openEditDialog",
	//    "click .button.delete": "destroy"
	 // },

	  initialize: function() {
	 //   this.listenTo(this.model, "change", this.render);
	    this.render();
	  },

	  render: function() {
		  var source   = $("#entry-template").html();
		  var template = Handlebars.compile(source);
		  var html = template(this.model);
		  this.$el.html( html );
	  }

	});